import java.net.*;
import java.io.*;


   class ServerThread extends Thread
   {
     BufferedReader br;
     PrintWriter pw;
    
    
     ServerThread(Socket s)
     {
     	try
     	{
     		 br= new BufferedReader(new InputStreamReader(s.getInputStream()));
             pw=new PrintWriter(s.getOutputStream(),true);


     	}

     	catch(Exception e)
     	{
     		System.out.println(e);
     	}
     }

     public void run()
     {
     	try
     	{


     	while(true)
     	{
     		String str=br.readLine();

     		if(str.charAt(0)=='~')
     		{
     			server.recieve(str);

     		}

        

     		else
     		{
     			server.echoAll(str);
     		}
     	}
     }
     catch(Exception d)
     {
     	System.out.println(d);
     }
     }


   public void termination()
   {

   }

   public void echo(String str)
   {
   	try
   	{
   		pw.println(str);

   	}
   	catch(Exception a)
   	{
   		System.out.println(a);
   	}
   }

}