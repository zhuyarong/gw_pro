package com.jl.simple;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentHashMap;



/**
 *	C/S架构的客户端对象，持有该对象，可以随时向服务端发送消息。
 */
public class SimpleClient {

	/**
	 * 处理服务端发回的对象，可实现该接口。
	 */
	public static interface ObjectAction{
		void doAction(Object obj,SimpleClient client);
		void doStrAction(String str,SimpleClient client);
	}
	public static final class DefaultObjectAction implements ObjectAction{
		public void doAction(Object obj,SimpleClient client) {
			System.out.println("deal：\t"+obj.toString());
		}

		public void doStrAction(String str, SimpleClient client) {
			System.out.println("deal：\t"+str);

		}
	}
	public static void main(String[] args) throws UnknownHostException, IOException {
		String serverIp ="127.0.0.1";//"123.57.33.22";//"127.0.0.1";"123.57.33.22";
		int port =5000;
		SimpleClient client = new SimpleClient(serverIp,port);
		client.start();
	}
	
	private String serverIp;
	private int port;
	private Socket socket;
	private boolean running=false;
	private long lastSendTime;
	@SuppressWarnings("rawtypes")
	private ConcurrentHashMap<Class, ObjectAction> actionMapping = new ConcurrentHashMap<Class,ObjectAction>();
	
	public SimpleClient(String serverIp, int port) {
		this.serverIp=serverIp;this.port=port;
	}
	
	public void start() throws UnknownHostException, IOException {
		if(running)return;
		socket = new Socket(serverIp,port);
		socket.setKeepAlive(true);
		System.out.println("location port："+socket.getLocalPort());
		lastSendTime=System.currentTimeMillis();
		running=true;
		new Thread(new KeepAliveWatchDog()).start();
		new Thread(new ReceiveWatchDog()).start();
	}
	
	public void stop(){
		if(running)running=false;
	}
	
	/**
	 * 添加接收对象的处理对象。
	 * @param cls 待处理的对象，其所属的类。
	 * @param action 处理过程对象。
	 */
	public void addActionMap(Class<Object> cls,ObjectAction action){
		actionMapping.put(cls, action);
	}

	public void sendObject(Object obj) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(obj);
		System.out.println("send：\t"+obj);
		oos.flush();
	}
	public void sendString(String s) throws IOException {

		BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
		byte[] b = s.getBytes();
		bos.write(b);
		System.out.println("send：\t"+s);
		bos.flush();
		
//		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
//		oos.writeObject(s);
//		System.out.println("send：\t"+s);
//		oos.flush();
	}
	public void sendByte() throws IOException {
		byte[] b= {2,1,2,0,0,1,2,3,4,5,6,7,50,48,49,54,45,48,56,45,48,50,84,
				49,53,58,50,48,58,49,55,43,48,56,48,48,32,32,32,123,10,9,
				34,114,120,112,107,116,34,58,9,91,123,10,9,9,9,34,77,111,
				116,101,32,73,68,34,58,9,49,44,10,9,9,9,34,76,97,116,105,
				34,58,9,50,50,46,53,54,54,51,49,52,44,10,9,9,9,34,76,111,
				110,103,34,58,9,49,49,52,46,48,55,56,50,56,54,44,10,9,9,
				9,34,68,97,116,114,34,58,9,57,57,44,10,9,9,9,34,115,116,
				101,112,115,34,58,9,48,10,9,9,125,44,32,123,10,9,9,9,34,
				77,111,116,101,32,73,68,34,58,9,50,44,10,9,9,9,34,76,97,
				116,105,34,58,9,45,50,50,46,53,54,54,51,49,52,44,10,9,9,
				9,34,76,111,110,103,34,58,9,45,49,49,52,46,48,55,56,50,
				56,54,44,10,9,9,9,34,68,97,116,114,34,58,9,57,57,44,10,
				9,9,9,34,115,116,101,112,115,34,58,9,48,10,9,9,125,93,10};
		BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
		bos.write(b);
		System.out.println("send：\t"+b.toString());
		bos.flush();
		
	}
	
	class KeepAliveWatchDog implements Runnable{
		long checkDelay = 10;
		long keepAliveDelay = 60*1000;
		public void run() {
			try {
				SimpleClient.this.sendByte();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			while(running){
				if(System.currentTimeMillis()-lastSendTime>keepAliveDelay){
					try {
					//	SimpleClient.this.sendByte();
						/**
						 * 	"rxpkt":	[{
					  				"Mote ID":	1,
					  				"Lati":	22.566314,
					  				"Long":	114.078286,
					  				"Datr":	99,
					  				"steps":	0
					  			}, {
					  				"Mote ID":	2,
					  				"Lati":	-22.566314,
					  				"Long":	-114.078286,
					  				"Datr":	99,
					  				"steps":	0
					  			}]

						
						SimpleClient.this.sendString("0120456789AB2016-03-12T09:08:17+0090  \n{\n\"rxpk\":    [{"
						 +"\n\"Mote ID\":	1,"
						 +"\n\"Lati\":	22.566314,"
						 +"\n\"Long\":	114.078286,"
						 +"\n\"Datr\":	99,"
						 +"\n\"steps\":	0"
						 +"\n}, {"
						 +"\n\"Mote ID\":	2,"
						 +"\n\"Lati\":	-22.566314,"
						 +"\n\"Long\":	-114.078286,"
						 +"\n\"Datr\":	99,"
						 +"\n\"steps\":	0"
						 +"\n}]");
						  */
						SimpleClient.this.sendByte();
//						if(new Random().nextInt(3)==1){
//							
//						}else{
//							SimpleClient.this.sendString("]\n}");
//						}
//						
						//SimpleClient.this.sendObject(new String("12345678"));
						
					} catch (IOException e) {
						e.printStackTrace();
						SimpleClient.this.stop();
					}
					lastSendTime = System.currentTimeMillis();
				}else{
					try {
						Thread.sleep(checkDelay);
					} catch (InterruptedException e) {
						e.printStackTrace();
						SimpleClient.this.stop();
					}
				}
			}
		}
	}
	
	class ReceiveWatchDog implements Runnable{
		public void run() {
			while(running){
				try {
					InputStream in = socket.getInputStream();
					byte[] b = new byte[1024];
					if(in.available()>0){
						BufferedInputStream bis =new BufferedInputStream(in);
						int ss = bis.read(b );
						String instr ="";
						for(int i=0;i<ss;i++){
							System.out.println("b["+i+"]:"+b[i]+"<=>"+(char)b[i]);
						}
						for(int i=17;i<ss;i++){
							instr+=(char)b[i];
						}
						instr =instr.replace(" ", "@");
						System.out.println("len:"+instr.length());
						System.out.println("receive["+ss+"]：\t"+instr);
						ObjectAction oa = actionMapping.get(instr.getClass());
						oa = oa==null?new DefaultObjectAction():oa;
						oa.doStrAction(instr, SimpleClient.this);
						
//						ObjectInputStream ois = new ObjectInputStream(in);
//						Object obj = ois.readObject();
//						
//						System.out.println("receive：\t"+obj);
//						ObjectAction oa = actionMapping.get(obj.getClass());
//						oa = oa==null?new DefaultObjectAction():oa;
//						oa.doAction(obj, SimpleClient.this);
					}else{
						Thread.sleep(10);
					}
				} catch (Exception e) {
					e.printStackTrace();
					SimpleClient.this.stop();
				} 
			}
		}
	}
	
}