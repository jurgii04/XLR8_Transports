package Windows;

import javax.swing.*;
import java.awt.*;

public class reparto extends JFrame {
    public reparto(JFrame reparto){
        JPanel pb= new JPanel();
        reparto.setTitle("Reparto");
        reparto.add(pb);
        JPanel centre = new JPanel();
        centre.setPreferredSize(new Dimension(500 , 600));
        centre.setBackground(Color.GRAY);
        centre.setLayout(new BoxLayout(centre, BoxLayout.Y_AXIS));

        pb.add(centre);
        reparto.add(pb);
        //=====================

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new GridLayout(1, 3));

            // Primer ejemplo de reparto
            JPanel panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(100 , 100));
            panel1.setBorder(BorderFactory.createTitledBorder("Servecio 1"));
            panel1.setLayout(new FlowLayout());
            panel1.add(new JLabel("internacional"));
            panel1.add(new JLabel(new ImageIcon("src\\Windows\\images\\inter.png")));
            mainPanel.add(panel1);

            // Segundo ejemplo de reparto
            JPanel panel2 = new JPanel();
            panel2.setPreferredSize(new Dimension(100 , 100));
            panel2.setBorder(BorderFactory.createTitledBorder("Servecio 1"));
            panel2.setPreferredSize(new Dimension(100 , 100));
            panel2.setLayout(new FlowLayout());
            panel2.add(new JLabel("Nacional"));
            panel2.add(new JLabel(new ImageIcon("src\\Windows\\images\\nacional.png")));
            mainPanel.add(panel2);



        reparto.add(mainPanel);
        reparto.setVisible(true);




    }
}
