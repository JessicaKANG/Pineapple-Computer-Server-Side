package com.Pineapple.SocketSever;
//开启一个线程运行服务器
public class ServerMainThread implements Runnable{
	private SocketServer server;
	public ServerMainThread(){
		
	}
	public void run(){
		SocketServer server = new SocketServer();
		server.startServer(); 
	}

}
