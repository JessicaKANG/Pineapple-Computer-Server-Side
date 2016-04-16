package com.Pineapple.SocketSever;

import java.io.DataInputStream;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import com.Pineapple.Dao.DBClientLogin;
import com.Pineapple.Dao.model.Client;

public class ServerThread implements Runnable {    
    
    private Socket socket;  
    private String accept = "NULL";  
    private String username,passwd;  
    private DataInputStream in = null;  
    private DataOutputStream out = null;  
    private ObjectInputStream inBean = null;
    private ObjectOutputStream outBean = null;
    private Client client;
    
    
    public ServerThread(Socket socket)  
    {            
        this.socket = socket;             
    }    
    
    // 任务是为一个用户提供服务    
    @Override    
    public void run()  
    {    
    	boolean exitLable= true;
    	while(exitLable){
    	   try  
           {    
               // 读取客户端传过来信息的DataInputStream    
               in = new DataInputStream(socket.getInputStream());    
               
               System.out.println("等待接收用户信息");  
               // 读取来自客户端的信息    
               accept = in.readUTF();               
           }                 
           catch (IOException e)  
           {  
        	   try {
        		
   				in.close();
   				socket.close();
   				exitLable = false;
           	   } catch (IOException e1) {
           	   }                  
           } 
    	   ///////////////////////////////登陆部分逻辑检测////////////////////////
           if(accept.equals("LOGIN"))  
           {  
               try  
               {  
            	// 向客户端发送信息的DataOutputStream    
                   out = new DataOutputStream(socket.getOutputStream());     
                     username = in.readUTF(); 
                     passwd = in.readUTF();
                     if (DBClientLogin.exists(username)) {
   						out.writeUTF("true"); 
   						out.flush();
   						if (DBClientLogin.check(username, passwd)) {
   							out.writeUTF("true");   
   							out.flush();
   							}
   						else{
   							out.writeUTF("false");
   							out.flush();
   							}  					
   						}
                     else{
                   	  out.writeUTF("false");
                   	  out.flush();
                     }                                      
               }
               catch(IOException e)  
               { }  
           } 
           //////////////////////////////////////注册逻辑检测/////////////////////////////
           else if(accept.equals("SIGNUP"))  
           {  
                 try {
                	out = new DataOutputStream(socket.getOutputStream());
                	username = in.readUTF();					
					if(DBClientLogin.exists(username)){
						out.writeUTF("true");
						out.flush();
					}
					else{
						out.writeUTF("false");
						out.flush();
						inBean = new ObjectInputStream(socket.getInputStream());
						Client client = (Client) inBean.readObject();
						if(DBClientLogin.save(client)){
						out.writeUTF("true");
						out.flush();
						}
						else{
							out.writeUTF("false");
							out.flush();
						}					
					}					
				} catch (IOException | ClassNotFoundException e) {	
					e.printStackTrace();
				}
           } 
           /////////////////////////////////////////////////////////////////////////////
           try  
           {  
               Thread.sleep(500);  
           }  
           catch(Exception e)  
           {
        	             	
           }  
       }      
    }  
} 
