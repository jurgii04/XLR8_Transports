package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.formdev.flatlaf.FlatLightLaf;

public class reparto extends JFrame {

    public reparto(JFrame reparto, JPanel panelNorte, JPanel contentPane) {
        FlatLightLaf.install();

        // Set up the "Volver" button

        JButton volver = new JButton("<html><u>Volver</u></html>");
        volver.setPreferredSize(new Dimension(100, 70));
        volver.setForeground(Color.WHITE);
        volver.setBackground(new Color(4, 140, 128, 255));
        volver.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
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

        // Set up the panel that holds the "Volver" button
        JPanel pb = new JPanel();
        pb.setBackground(Color.WHITE);
        pb.add(volver);

        // Set up the main panel that holds the service examples
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new GridLayout(1, 2));

        // Set up the first service example
        JPanel panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(200, 200));
        panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        JLabel title1 = new JLabel("INTERNACIONAL");
        title1.setFont(new Font("Arial", Font.BOLD, 16));
        title1.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel image1 = new JLabel(new ImageIcon("src\\Windows\\images\\internacional.jpeg"));
        image1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel1.add(title1);
        panel1.add(Box.createRigidArea(new Dimension(0, 10)));
        panel1.add(image1);

        // Set up the second service example
        JPanel panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(200, 200));
        panel2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        JLabel title2 = new JLabel("NACIONAL");
        title2.setFont(new Font("Arial", Font.BOLD, 16));
        title2.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel image2 = new JLabel(new ImageIcon("src\\Windows\\images\\nacional.jpeg"));
        image2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel2.add(title2);
        panel2.add(Box.createRigidArea(new Dimension(0, 10)));
        panel2.add(image2);

        // Add the service examples to the main panel
        mainPanel.add(panel1);
        mainPanel.add(panel2);

        // Set up the top panel that holds the "Volver" button and the title
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        topPanel.setLayout(new BorderLayout());
        JLabel title = new JLabel("Reparto");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setHorizontalAlignment(JLabel.CENTER);
        topPanel.add(title, BorderLayout.CENTER);
        topPanel.add(pb, BorderLayout.WEST);

        // Add the top panel and the main panel to the frame
        reparto.add(topPanel, BorderLayout.NORTH);
        reparto.add(mainPanel);
    }


}