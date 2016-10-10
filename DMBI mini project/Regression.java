import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.*;
class Regression implements ActionListener
{
	Frame f1;
	TextField tf1;
    TextArea ta;
    Button b1;
    Label l;
    Regression()
    {
        f1=new Frame("Linear Regression");
        f1.setVisible(true);
        f1.setSize(1024,768);
        f1.setLayout(new FlowLayout(FlowLayout.LEADING,50,50));
        tf1=new TextField();
        b1=new Button("Predict!");
        ta=new TextArea();
        l=new Label("Enter The Literacy Rate:");
        tf1.setColumns(6);
        ta.setEditable(false);
        f1.add(l);
        f1.add(tf1);
        f1.add(b1);
        f1.add(ta);
        b1.addActionListener(this);
        //JOptionPane.showMessageDialog(f,"erfkj", "error",ERROR_MESSAGE);
        
    }

	@Override
    public void actionPerformed(ActionEvent e) 
	{
		int i=0,j;
		double literacy[]=new double [25];
		double gdp[]=new double [25];
		double w0=0,w1=0,meana=0,meant=0,sum1=0,sum2=0,ap,t=0;
		String str;
        str=tf1.getText();
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbc");
			Connection con=DriverManager.getConnection("jdbc:odbc:PostgreSQL30","postgres","postgres");
			java.sql.Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("Select * from regression");
			while(rs.next() && i<20)
			{
				
				literacy[i]=rs.getInt(1);
                                gdp[i]=rs.getInt(2);
                                i++;
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		for(j=0;j<20;j++)
		{
			meana=meana+literacy[j];
			meant=meant+gdp[j];
		}
		meana=meana/20;
		meant=meant/20;
		for(j=0;j<20;j++)
		{
			sum1=sum1+((Math.abs(literacy[j]-meana))*(Math.abs(gdp[j]-meant)));
			sum2=sum2+(Math.pow(literacy[j]-meana,2));
		}
		w1=sum1/sum2;
		w0=meant-(w1*meana);
		
		ap=Integer.parseInt(str);
		t=w0+(w1*ap);
		ta.append("\n The number of gdp for given literacy will be "+t);

	}
}

