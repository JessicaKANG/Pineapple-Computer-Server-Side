package com.Pineapple.SocketSever;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import com.Pineapple.Dao.model.Client;

public class NotifyThread implements Runnable  
{  
	private Socket socket;  
    private String accept = "NULL";  
    private String username,passwd;  
    private DataInputStream in = null;  
    private DataOutputStream out = null;  
    private ObjectInputStream inBean = null;
    private ObjectOutputStream outBean = null;
    private Client client;
    private Boolean check = false;
    public NotifyThread(Socket socket)  
    {  
        this.socket = socket;  
    }  
    public void run()  
    {  
    	boolean exitLable= true;
    	while(exitLable){
    	   try  
           {    
               // 读取客户端传过来信息的DataInputStream    
               in = new DataInputStream(socket.getInputStream());       
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
    	   if (accept.equals("SAVEORDER")){
    		   JOptionPane.showMessageDialog(null,
						"您有一条新订单待处理.", "新订单",
						JOptionPane.INFORMATION_MESSAGE);
    	   }
    	}
    }            
}  