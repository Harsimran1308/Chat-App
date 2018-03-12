 import java .net.*;
import java.util.*;
import java.io.*;


     class server
     {

          static Vector vl;
          static Vector<String> names;
          static int i=0;


          public static void main(String args[])
          {
               try
               {
                    vl=new Vector();
                    names=new Vector();

                    ServerSocket ss =new ServerSocket(800);

                    while(true)
                    {
                         Socket s= ss.accept();
                         ServerThread st=new ServerThread(s);
                         
                         st.start();
                         vl.addElement(st);
                    }

               }

               catch(Exception e)
               {
                    System.out.println(e);
               }
          }


          public synchronized static void recieve(String str)
          {
               String r=str.substring(1);
               names.addElement(r);
               i++;
               Enumeration e=vl.elements();
               Enumeration<String> n=names.elements();


               try
               {
                    while(true)
                    {
                         ServerThread st=(ServerThread)e.nextElement();

                         st.echo(str);

                          System.out.println("inside receive : " + str);
                         if(!e.hasMoreElements())
                         {
                              for(int j=0;j<i-1;j++)
                              {
                                   String m=n.nextElement();
                                   System.out.println(m);
                                   st.echo("~"+m);
                              }
                              break;
                         }
                    }
               }
               catch(Exception q)
               {
                    System.out.println(q);
               }
          }

          public synchronized static void echoAll(String str)
          {

               int l=str.indexOf('>');
               Enumeration <String> n =names.elements();

               String rec=str.substring(1,l);
               String sender=str.substring(l+1);

               Enumeration e=vl.elements();

               while(e.hasMoreElements())
               {
                    try
                    {
                         ServerThread st=(ServerThread)e.nextElement();
                         String p;
                         if(n.hasMoreElements())
                         {


                          p = n.nextElement();
                    }

                    else
                    {

                         p="";
                    }

                         if(rec.equals(p) || rec.equals("null"))
                         {
                              
                              st.echo(sender);

                         }


                    }

                    catch(Exception t)
                    {
                         System.out.println(t);
                    }
               }

          }

     }