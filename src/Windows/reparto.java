package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class reparto extends JFrame {
    public reparto(JFrame reparto, JPanel panelNorte, JPanel contentPane){


        JPanel pb= new JPanel();
        reparto.setTitle("Reparto");

        JButton volver = new JButton("<html><u>Volver</u></html>");
        volver.setForeground(new Color(255, 255, 255));
        volver.setBackground(new Color(4, 140, 128, 255));
        volver.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panelNorte.add(volver, BorderLayout.WEST);
        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reparto.getContentPane().removeAll();
                reparto.getContentPane().revalidate();
                reparto.getContentPane().repaint();
                panelNorte.remove(volver);
                reparto.add(panelNorte, BorderLayout.NORTH);
                reparto.add(contentPane, BorderLayout.CENTER);
            }
        });

        reparto.add(panelNorte, BorderLayout.NORTH);
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
            panel1.setBorder(BorderFactory.createTitledBorder("Servicio 1"));
            panel1.setLayout(new FlowLayout());
            panel1.add(new JLabel("Internacional"));
            panel1.add(new JLabel(new ImageIcon("src\\Windows\\images\\internacional.jpeg")));
            mainPanel.add(panel1);

            // Segundo ejemplo de reparto
            JPanel panel2 = new JPanel();
            panel2.setPreferredSize(new Dimension(100 , 100));
            panel2.setBorder(BorderFactory.createTitledBorder("Servicio 2"));
            panel2.setPreferredSize(new Dimension(100 , 100));
            panel2.setLayout(new FlowLayout());
            panel2.add(new JLabel("Nacional"));
            panel2.add(new JLabel(new ImageIcon("src\\Windows\\images\\nacional.jpeg")));
            mainPanel.add(panel2);



        reparto.add(mainPanel);
        reparto.setVisible(true);




    }
}
