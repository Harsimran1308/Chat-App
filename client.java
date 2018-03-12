import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
//import java.awt.event.WindowAdapter.*;


    public class client extends JFrame implements ActionListener,Runnable,WindowListener
    {

        
    	  JTextField t1;
    	  JTextArea a1;
    	  List l1;
         BufferedReader br;
         PrintWriter pw;
         String h;
         Socket s;


    	client(String a)
    	{
    		super(a);
    		h=a;

             t1=new JTextField(10);
             l1=new List();
             a1=new JTextArea();
             a1.setEditable(false);
             setLayout(new BorderLayout());
             add(t1,BorderLayout.SOUTH);
              add(l1,BorderLayout.EAST);
             add(a1,BorderLayout.CENTER);

             t1.addActionListener(this);
 addWindowListener(this);
                 try
                 {
                 	 s=new Socket("127.0.0.1",800);
                 	br=new BufferedReader(new InputStreamReader(s.getInputStream()));
                 	pw=new PrintWriter(s.getOutputStream(),true);

                 	    String m ="~"+h;
                 	    pw.println(m);

            


                 }

                 catch(Exception w)
                 {
                 	System.out.println(w);
                 }
     	    Thread t=new Thread(this);
                 	    t.start();


    	}
        public void windowClosing(WindowEvent we)
        {
            





           


            
        }
        public void windowClosed(WindowEvent we)
        {
           
        }

        public void windowIconified(WindowEvent we)
        {

        }

         public void windowDeiconified(WindowEvent we)
        {

        }

         public void windowActivated(WindowEvent we)
        {

        }

          public void windowDeactivated(WindowEvent we)
        {

        }
        public void windowOpened(WindowEvent we)
        {

        }

    	public void actionPerformed(ActionEvent ae)
    	{
                String rec;
                try
                {


                rec=l1.getSelectedItem();

                String str="<"+rec+">"+h+":"+t1.getText();

                pw.println(str);

                t1.setText("");
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
    		    		String msg=br.readLine();
                         
    		    		if(msg.charAt(0)=='~')
    		    		{
    		    			l1.add(msg.substring(1));
    		    		}

                        else if(msg.charAt(0)=='$')
                        {
                            
                        }

    		    		else
    		    		{
    		    			a1.append(msg+"\n");
    		    		}
    		    	}

    		    }
    		    catch(Exception e)
    		    {
    		    	System.out.println(e);
    		    }

    	}
    }