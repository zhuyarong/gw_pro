package com.jl.servlet;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jl.simple.deal.SocketMap;

public class SendCommand extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		Enumeration names = req.getParameterNames();
//		while(names.hasMoreElements()){
//			System.out.println(names.nextElement());
//		}
		String command =(String) req.getParameter("command");
		
		sendCommand(command);
		resp.setStatus(200);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String command =(String) req.getAttribute("command");
		System.out.println(command);
		sendCommand(command);
		resp.setStatus(200);
	}
	private void sendCommand(String command){
		command =command==null?"":command;
		Map<String, Socket> map = SocketMap.getAll();
		for(Socket s:map.values()){
			BufferedOutputStream bos;
			try {
				bos = new BufferedOutputStream(s.getOutputStream());
				bos.write(command.getBytes("UTF-8"));
				bos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
