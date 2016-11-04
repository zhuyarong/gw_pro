package com.jl.simple.deal;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;


public class DealData implements Runnable {
	 private static Logger logger = Logger.getLogger(DealData.class);  
	private String data = "";
	private Socket socket;
	public DealData(String data,Socket s) {
		this.data = data;
		this.socket=s;
		
	}
	public void run() {
		logger.info("deal data:"+data);

		BufferedOutputStream bos;
		try {
			bos = new BufferedOutputStream(socket.getOutputStream());
			bos.write("server:OK".getBytes("UTF-8"));
			bos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void run1() {
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost
		HttpPost httppost = new HttpPost(
				"http://127.0.0.1:3000/api/test");
		// 创建参数队列
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("x", "1"));
		formparams.add(new BasicNameValuePair("y", "1"));
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			System.out.println("executing request " + httppost.getURI());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					System.out
							.println("--------------------------------------");
					System.out.println("Response content: "
							+ EntityUtils.toString(entity, "UTF-8"));
					System.out
							.println("--------------------------------------");
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
