import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
public class buses {
    public buses(JFrame buses){
        JPanel pb= new JPanel();
        buses.setTitle("Buses");
        buses.add(pb);
        //--------
        JPanel nouth = new JPanel();
        JLabel  destination = new JLabel("detino");
        JTextField dis= new JTextField(15);
        JLabel  origen = new JLabel("origen");
        JTextField org= new JTextField(15);
        nouth.add(destination);
        nouth.add(dis);
        nouth.add(origen);
        nouth.add(org);
        pb.add(nouth);
        buses.add(pb);



    }

}
