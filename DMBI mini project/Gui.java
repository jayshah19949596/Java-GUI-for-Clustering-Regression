import java.awt.Button;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class Gui implements ActionListener
{
    Frame f;
    public void actionPerformed(ActionEvent a)
    {
        if(a.getActionCommand().matches("Clustering"))
        {
            Clustering clust=new Clustering();
            f.setVisible(false);
        }
        else
        {
            Regression reg=new Regression();
            f.setVisible(false);
        }
    }

    public Gui()
    {
        Button b=new Button("Clustering");
        Button b1=new Button("Regression");
        f=new Frame();
        f.setLayout(new FlowLayout(FlowLayout.CENTER,150,300));
        f.setVisible(true);
        f.setSize(1024,768);
        b.setPreferredSize(new Dimension(200,100));
        b1.setPreferredSize(new Dimension(200,100));
        f.add(b);
        f.add(b1);
        b.addActionListener(this);
        b1.addActionListener(this);
    }
    
    public static void main(String[] args) 
    {
        Gui g=new Gui();
    }
    
}
