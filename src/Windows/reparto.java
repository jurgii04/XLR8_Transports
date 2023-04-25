package Windows;

import javax.swing.*;
import javax.swing.border.Border;
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
            mainPanel.setLayout(new GridLayout(1, 2));

            // Primer ejemplo de reparto
            JPanel prin=new JPanel();
            prin.setLayout(new BoxLayout(prin,BoxLayout.Y_AXIS));
            prin.setPreferredSize(new Dimension(100 , 100));
            prin.setBorder(BorderFactory.createTitledBorder("Servicio 1"));

            JPanel panel1 = new JPanel();

            //panel1.setLayout(new BoxLayout(panel1,BoxLayout.Y_AXIS));
            panel1.add(new JLabel("Internacional"));
            panel1.add(new JLabel(new ImageIcon("src\\Windows\\images\\internacional.jpeg")));
            prin.add(panel1);
            panel1.setPreferredSize(new Dimension(100, 50));


        // Create the JPanel and set its layout manager
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(100, 50));

// Create the icon and label
        ImageIcon icon = new ImageIcon("src\\Windows\\images\\chimicicon.png");
        Image scaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JLabel iconLabel = new JLabel(new ImageIcon(scaledImage));
        iconLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

// Create the center label
        JLabel centerLabel = new JLabel("Chemical Materials");
        centerLabel.setFont(new Font("Arial", Font.BOLD, 16));

// Create the price label
        JLabel priceLabel = new JLabel("Starting from $1000");
        priceLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

// Add the components to the panel
        panel.add(iconLabel, BorderLayout.WEST);
        panel.add(centerLabel, BorderLayout.CENTER);
        panel.add(priceLabel, BorderLayout.EAST);

// Add some padding and spacing between the components
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(Box.createHorizontalStrut(10), BorderLayout.WEST);
        panel.add(Box.createHorizontalStrut(10), BorderLayout.CENTER);
        panel.add(Box.createHorizontalStrut(10), BorderLayout.EAST);
        Border border = BorderFactory.createTitledBorder("Chemical Materials Service");

// Set the border for the panel
        panel.setBorder(border);
        prin.add(panel);


        mainPanel.add(prin);

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
