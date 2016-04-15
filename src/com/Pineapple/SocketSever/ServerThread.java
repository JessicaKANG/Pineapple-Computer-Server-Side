package com.Pineapple.SocketSever;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerThread implements Runnable {    
    
    private Socket socket;  
    private String accept;  
    String username,passwd;  
    DataInputStream in = null;  
    DataOutputStream out = null;  
    //LinkMySql linkMySql;  
   // String dept;  
   // private SocketServer socketServer;  
    // 创建静态全局变量  
  
      
    public ServerThread(Socket socket)  
    {    
        //this.socketServer = socketServer;  
        this.socket = socket;    
        //linkMySql = new LinkMySql(this);  
    }    
    
    // 任务是为一个用户提供服务    
    @Override    
    public void run()   
    {    
        try  
        {    
            // 读取客户端传过来信息的DataInputStream    
            in = new DataInputStream(socket.getInputStream());    
            // 向客户端发送信息的DataOutputStream    
            out = new DataOutputStream(socket.getOutputStream());     
            System.out.println("等待接收用户信息");  
            // 读取来自客户端的信息    
            accept = in.readUTF();    
            System.out.println("接收到信息"+accept);  
            

           
        }                 
        catch (IOException e)  
        {    
            e.printStackTrace();    
        }  
        if(accept.equals("LOGIN"))  
        {  
            try  
            {  
                  username = in.readUTF(); 
                  passwd = in.readUTF();
                  System.out.println("用户名："+username+"\n密码："+passwd);                                                     
                 
                                  
            }  
            catch(IOException e)  
            {  
            }  
        }  
        else if(accept.equals("SIGNUP"))  
        {  
              
        } 
        try{
        in.close();
        socket.close();
        }
        catch(IOException e){
        	
        }
    }  
} 
