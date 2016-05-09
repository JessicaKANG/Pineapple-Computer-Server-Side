package com.Pineapple.SocketSever;

import java.io.DataInputStream;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import com.Pineapple.Dao.DBAddorder;
import com.Pineapple.Dao.DBCheckcomputer;
import com.Pineapple.Dao.DBCheckstock;
import com.Pineapple.Dao.DBClientLogin;
import com.Pineapple.Dao.model.Client;
import com.Pineapple.Dao.model.Computer;
import com.Pineapple.Dao.model.Order;

public class ServerThread implements Runnable {    
    
    private Socket socket;  
    private String accept = "NULL";  
    private String username,passwd;  
    private DataInputStream in = null;  
    private DataOutputStream out = null;  
    private ObjectInputStream inBean = null;
    private ObjectOutputStream outBean = null;
    private Client client;
    private Boolean check = false;
    
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
        	   System.out.println("接收到LOGIN");
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
        	   System.out.println("接收到SIGNUP");
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
           else if(accept.equals("SHOWALL")){
        	   System.out.println("接收到SHOWALL");
        	   List<Computer> list = DBCheckcomputer.select();
	        	   try {
					outBean = new ObjectOutputStream(socket.getOutputStream());
					outBean.writeObject(list);
					outBean.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	   
           }
           /////////////////////////////////////////////////////////////////////////////
           else if(accept.equals("ORDER")){
        	   System.out.println("接收到ORDER");
        	   
	        	   try {
	        		   inBean = new ObjectInputStream(socket.getInputStream());
	        		   List<String> computerlist = (List<String>) inBean.readObject();
	        		   List<String> componentlist = (List<String>) inBean.readObject();	        		  
	        		   Iterator iterator = computerlist.iterator();
	        		   while (iterator.hasNext()){
	        			   String computerID = null;
	        			   computerID = (String)iterator.next();	        			   
	        			   check = DBCheckstock.checkcpr(computerID);
	        			   if(!check){
	        				   return;
	        			   }
	        		   }	        		   
	        		   if (check){	
	        			   Map<String,Integer> map = new HashMap<String,Integer>();        			   
	        			   for (int m=0;m<componentlist.size();m++){
	        				   int p = map.getOrDefault(componentlist.get(m), 0);
	        				   if(p==0){
	        					   map.put(componentlist.get(m), 1);
	        					   for(int n=m+1;n<componentlist.size();n++){
	        						   if(componentlist.get(n).equals(componentlist.get(m))){	        							
	        							   int k = map.get(componentlist.get(m))+1;
	        							   map.put(componentlist.get(m), k);
	        						   }
	        					   }
	        					   
	        				   }	        				  	        					   	        				           				   
	        			   }
	        			   Iterator<Map.Entry<String, Integer>> entries = map.entrySet().iterator();
	        			   while (entries.hasNext()){
	        				   check = false;
	        				   Entry<String, Integer> entry = entries.next();
	        				   check = DBCheckstock.checkcpt(entry.getKey(), entry.getValue());
	        				   if(!check){
	        					   return;
	        				   }
	        			   }
	        			   if(check){
	        				   out = new DataOutputStream(socket.getOutputStream());
	        				   out.writeUTF("True");
	        				   out.flush();	
		        			   Iterator iterator2 = computerlist.iterator();
		        			   while(iterator2.hasNext()){
		        				   String computerID = null;
			        			   computerID = (String)iterator2.next();
		        				   DBCheckstock.modifycpr(computerID);
		        			   	}//电脑库存修改
		        			   Iterator<Map.Entry<String, Integer>> entries2 = map.entrySet().iterator();
		        			   while (entries2.hasNext()){
		        				   Entry<String, Integer> entry2 = entries2.next();
		        				   DBCheckstock.modifycpt(entry2.getKey(),entry2.getValue());
		        			   }//配件库存修改
		        			   inBean = new ObjectInputStream(socket.getInputStream());
		        			   Order order = (Order)inBean.readObject();
		        			   if(DBAddorder.save(order)){
		        				   out.writeUTF("True");
		        				   out.flush();	
		        				   JOptionPane.showMessageDialog(null,
		   								"您有一条新订单待处理.", "订单提醒",
		   								JOptionPane.INFORMATION_MESSAGE);
		        			   }
		        			   
	        			   }
	        			   else {
		        			   out = new DataOutputStream(socket.getOutputStream());
		        			   out.writeUTF("False");
		        			   out.flush();	        			   
		        		   }
	        		   }
	        		   else {
	        			   out = new DataOutputStream(socket.getOutputStream());
	        			   out.writeUTF("False");
	        			   out.flush();	        			   
	        		   }
	        		   
	        		   
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
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
