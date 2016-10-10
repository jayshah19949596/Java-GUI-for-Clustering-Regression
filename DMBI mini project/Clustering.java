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
class Clustering implements ActionListener
{
	Frame f1;
    TextArea ta;
    TextArea ta1;
    Button b1;
    Clustering()
    {
        f1=new Frame("K-Means Clustering");
        f1.setVisible(true);
        f1.setSize(1024,768);
        f1.setLayout(new FlowLayout(FlowLayout.LEADING,50,50));
        b1=new Button("Click Here For Demo");
        ta=new TextArea();
        ta1=new TextArea();
        ta.setEditable(false);
        ta1.setEditable(false);
        f1.add(ta1);
        f1.add(b1);
        f1.add(ta);
        b1.addActionListener(this);
        //JOptionPane.showMessageDialog(f,"erfkj", "error",ERROR_MESSAGE);
    }

	@Override
    public void actionPerformed(ActionEvent e) 
	{
		int i=0,j=0,k=0,h=0;
		double gs[]=new double [25];
		double c1[]=new double [25];
		double c2[]=new double [25];
		double tmp1,tmp2,m1,m2,t1=0,t2=0,d1,d2,sum1,sum2;
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbc");
			Connection con=DriverManager.getConnection("jdbc:odbc:PostgreSQL30","postgres","postgres");
			java.sql.Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("Select GoalsScored from foot");
			while(rs.next() && i<10)
			{
				
				gs[i]=rs.getDouble(1);
				
				i++;
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		ta1.append("Input Dataset:");
		for(i=0;i<10;i++)
		ta1.append(" "+gs[i]+"\n");
		ta1.append("\nNumber of Clusters = 2\n");
		System.out.println(" ");
		tmp1=gs[0];
		tmp2=gs[4];
		do{
			j=0;k=0;m1=tmp1;m2=tmp2;
			sum1=0;sum2=0;
		for(i=0;i<15;i++)
		{
			d1=Math.abs(gs[i]-m1);
			d2=Math.abs(gs[i]-m2);
			if(d1<d2){
			c1[j]=gs[i];
			j++;}
			else{
			c2[k]=gs[i];
			k++;}
		}
		ta.append("\nCluster 1:");
		for(i=0;i<j;i++)
		{
			ta.append("\n"+c1[i]);
		}
		ta.append("\nCluster 2:");
		for(i=0;i<k;i++)
		{
			ta.append("\n"+c2[i]);
		}
		for(i=0;i<j;i++)
		{
			sum1=sum1+c1[i];
			
		}
		t1=sum1/j;
		for(i=0;i<k;i++)
		{
			sum2=sum2+c2[i];
			
		}
		t2=sum2/k;
		if((m1!=t1) || (m2!=t2))
		{
			tmp1=t1;
			tmp2=t2;
		}
		ta.append("\nMean 1: "+tmp1+"\n");
		ta.append("Mean 2: "+tmp2+"\n");
		
		}while((m1!=t1) && (m2!=t2));
	}
}
