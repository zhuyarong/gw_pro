package com.jl.simple;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.jl.simple.deal.ReceiveData;
import com.jl.simple.deal.SocketMap;

/**
 * C/S架构的服务端对象。
 * http://coach.iteye.com/blog/2024444
 * http://blog.csdn.net/lin910429/article/details/44835319
 * http://www.cnblogs.com/linzheng/archive/2011/01/23/1942328.html
 */
public class SimpleServer {
	
	 private static Logger logger = Logger.getLogger(SimpleServer.class);  
	
	/**
	 * 要处理客户端发来的对象，并返回一个对象，可实现该接口。
	 */
	public interface ObjectAction{
		Object doAction(Object rev);
		//String doStrAction(String rev);
		void doStrAction(String instr, Socket s,int len);
	}
	
	public static final class DefaultObjectAction implements ObjectAction{
		public Object doAction(Object rev) {
			System.out.println("deal and return > "+rev);
			return rev;
		}

		public void doStrAction(String rev,Socket s,int len) {
			logger.info("deal > "+rev);  
			
			if(len<10){
				return;
			}else{
				BufferedOutputStream bos;
				try {
					bos = new BufferedOutputStream(s.getOutputStream());
					bos.write((s.getRemoteSocketAddress().toString()).getBytes("UTF-8"));
					bos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			
		}
	}
	
	public static void main(String[] args) {
		int port = 5000;
		SimpleServer server = new SimpleServer(port);
		server.start();
	}
	
	private int port;
	private volatile boolean running=false;
	private long receiveTimeDelay=3*60*1000;//3000;
	@SuppressWarnings("rawtypes")
	private ConcurrentHashMap<Class, ObjectAction> actionMapping = new ConcurrentHashMap<Class,ObjectAction>();
	private Thread connWatchDog;
	
	public SimpleServer(int port) {
		this.port = port;
	}

	public void start(){
		if(running)return;
		running=true;
		connWatchDog = new Thread(new ConnWatchDog());
		connWatchDog.start();
	}
	
	@SuppressWarnings("deprecation")
	public void stop(){
		if(running)running=false;
		if(connWatchDog!=null)connWatchDog.stop();
	}
	
	public void addActionMap(Class<Object> cls,ObjectAction action){
		actionMapping.put(cls, action);
	}
	
	class ConnWatchDog implements Runnable{
		public void run(){
			try {
				ServerSocket ss = new ServerSocket(port,5);
				while(running){
					Socket s = ss.accept();
					s.setKeepAlive(true);
					new Thread(new SocketAction(s)).start();
					
				}
			} catch (IOException e) {
				e.printStackTrace();
				SimpleServer.this.stop();
			}
			
		}
	}
	
	class SocketAction implements Runnable{
		Socket s;
		boolean run=true;
		long lastReceiveTime = System.currentTimeMillis();
		public SocketAction(Socket s) {
			this.s = s;
			SocketMap.add(s.getRemoteSocketAddress().toString(),  s);
		}
		public void run() {
			while(running && run){
				if(System.currentTimeMillis()-lastReceiveTime>receiveTimeDelay){
					overThis();
				}else{
					try {
						InputStream in = s.getInputStream();
						//System.out.println("KeepAlive:"+s.getKeepAlive());
						byte[] b = new byte[1024];
						if(in.available()>0){
							
							BufferedInputStream bis =new BufferedInputStream(in);
							int ss = bis.read(b);
							
							System.out.println("byte lenght is: "+ss);
							logger.info("byte lenght is: "+ss);  
							String instr ="";
							System.out.println("data detail:");
							logger.info("data detail:");

							for(int i=0;i<ss;i++){
								instr+=(char)b[i];
							}
							String key=s.getRemoteSocketAddress().toString();
							new ReceiveData().deal(b, ss,key);
//							String instr ="";
//							DataInputStream dis = new DataInputStream(in);
//							instr = dis.readUTF();
							lastReceiveTime = System.currentTimeMillis();
							System.out.println("server receive > "+instr);
							logger.info("server receive > "+instr);  
							ObjectAction oa = actionMapping.get(instr.getClass());
							oa = oa==null?new DefaultObjectAction():oa;
							oa.doStrAction(instr,s,ss);

						}else{
							Thread.sleep(10);
						}
					} catch (Exception e) {
						e.printStackTrace();
						overThis();
					} 
				}
			}
		}
		
		private void overThis() {
			if(run)run=false;
			if(s!=null){
				try {
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			SocketMap.remove(s.getRemoteSocketAddress().toString());
			System.out.println("close > "+s.getRemoteSocketAddress());
			logger.info("close > "+s.getRemoteSocketAddress());
		}
		
	}
	
}