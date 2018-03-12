import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



     public class login extends JFrame implements ActionListener
     {
              JTextField t1;
              JPasswordField t2;
              JLabel l1,l2;
              JButton b1;


         login(String s)
         {
         	super(s);

              Container con=getContentPane();
         	t1=new JTextField(10);
            t2=new JPasswordField(10);
             l1=new JLabel("ENTER USERNAME :");
             l2=new JLabel("ENTER PASSWORD :");
             b1=new JButton("LOGIN");
                 setLayout(new FlowLayout());

                 con.add(l1); con.add(t1);
                 con.add(l2); con.add(t2);

                 con.add(b1);

                 b1.addActionListener(this);




         }

           public  void actionPerformed(ActionEvent ae)
           {


                 if(ae.getSource()==b1)
                 {
                 	Connection con=null;
                 	Statement stmt=null;
                 	ResultSet rs =null;

                 	try
                 	{
                 		Class.forName("com.mysql.jdbc.Driver");
                 		String url="jdbc:mysql://localhost/chatserver";
                 		con=DriverManager.getConnection(url,"username","password");

                 		stmt=con.createStatement();
                 		String uname=t1.getText();
                 		String pass=t2.getText();

                 		String ss="select * from user where name='"+uname+"' and password='"+pass+"'";

                 		rs=stmt.executeQuery(ss);

                 		rs.next();

                 		int n=rs.getRow();

                 		if(n>0)
                 		{
                 			client obj=new client(uname);
                 			obj.setVisible(true);
                 			obj.setSize(1000,1000);
                 			this.dispose();

                 		}
                 		else
                 		{
                 			JOptionPane.showMessageDialog(null,"NOT YET REGISTERED");
                 		}
                 	}
                 	catch(Exception t)
                 	{
                 		System.out.println(t);
                 	}
                 }

           }


           public static void main(String args[])
           {

           	login obj = new login("LOGIN");

           	obj.setVisible(true);
           	obj.pack();
           }



     }