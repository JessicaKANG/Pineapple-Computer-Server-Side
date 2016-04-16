package com.Pineapple.SocketSever;

class ListenThread implements Runnable  
{  
    private SocketServer socketServer;  
    public ListenThread(SocketServer socketServer)  
    {  
        this.socketServer = socketServer;  
    }  
    public void run()  
    {  
        while(true)  
        {  
        for(int i=0;i<socketServer.mysocket.size();i++)  
        {  
            if(socketServer.mysocket.get(i).isClosed())  
            {   
                        socketServer.mysocket.remove(socketServer.mysocket.get(i));  
                        
                        System.out.println("1个客户端已经断开");  
                    }  
            }  
          
        try  
        {  
            Thread.sleep(500);  
        }  
        catch(Exception e)  
        {}  
    }}  
}  