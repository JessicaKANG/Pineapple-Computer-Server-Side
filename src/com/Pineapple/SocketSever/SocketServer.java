package com.Pineapple.SocketSever;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class SocketServer {
	public ServerSocket server;
	public LinkedList<Socket> mysocket = new LinkedList<Socket>(); 
	public SocketServer(){
		
	}
	public void startServer() {    
        int i = 0;    
        try {   
            //设置监听端口号和最大接入数  
            server = new ServerSocket(8889);    
            System.out.println("服务器端准备就绪");   
            new Thread(new ListenThread(this)).start();  //启动监听客户端关闭线程
            
            while (true) {    
                Socket socket = server.accept();  
                
                mysocket.add(socket);  
                i++;    
                System.out.println("第" + i + "个用户连接成功！");   
                System.out.println("该用户端的地址信息为:"+socket.getInetAddress());  
                new Thread(new ServerThread(socket)).start(); //启动服务线程
                //new Thread(new NotifyThread(socket)).start(); //启动提示消息线程
            }    
        } catch (IOException e) {    
            e.printStackTrace();    
        }    
    }    

}


