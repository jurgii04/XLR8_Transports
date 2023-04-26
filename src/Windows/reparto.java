package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.formdev.flatlaf.FlatLightLaf;

public class reparto extends JFrame  {

    public reparto(JFrame reparto, JPanel panelNorte, JPanel contentPane) {
        FlatLightLaf.install();

        // Set up the "Volver" button
        reparto.setTitle("Reparto");

        JButton volver = new JButton("<html><u>Volver</u></html>");
        volver.setPreferredSize(new Dimension(100, 70));
        volver.setForeground(Color.WHITE);
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
                reparto.setTitle("XLR8 Transports");
            }
        });
        reparto.add(panelNorte, BorderLayout.NORTH);

        // Set up the panel that holds the "Volver" button
        JPanel pb = new JPanel();
        pb.setBackground(Color.WHITE);


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
//------
        JPanel panela22 = new JPanel(new BorderLayout(10,10));
        panela22.setPreferredSize(new Dimension(90, 30));
        panela22.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panela22.setBackground(Color.WHITE);
        ImageIcon checkIcon22 = new ImageIcon("src\\Windows\\images\\chimicicon.png");
        Image img22 = checkIcon22.getImage();
        Image resizedImg22 = img22.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
        ImageIcon resizedIcon22 = new ImageIcon(resizedImg22);
        JLabel iconLabel22 = new JLabel(resizedIcon22);
        JLabel materialLabel22 = new JLabel("<html><center><h1><strong>Material químico</strong></h1>Confíe en nosotros para transportar de manera segura sus valiosos materiales químicos en nuestros camiones de última generación.</center></html>");
        materialLabel22.setFont(new Font("Arial", Font.PLAIN, 12));
        panela22.add(iconLabel22, BorderLayout.WEST);
        panela22.add(materialLabel22, BorderLayout.CENTER);
        panel1.add(panela22, BorderLayout.SOUTH);



// add the new panel to panel1 using a BorderLayout
        panel1.add(panela22, BorderLayout.SOUTH);
        //-----
        JPanel panela = new JPanel(new BorderLayout(10,10));
        panela.setPreferredSize(new Dimension(90, 30));
        panela.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panela.setBackground(Color.WHITE);
        ImageIcon checkIcon = new ImageIcon("src\\Windows\\images\\conge.png");
        Image img = checkIcon.getImage();
        Image resizedImg = img.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImg);
        JLabel iconLabel = new JLabel(resizedIcon);
        JLabel materialLabel1 = new JLabel("<html><center><h1><strong>Material de congelación</strong></h1>Confíe en nosotros para transportar de forma segura sus productos congelados con nuestros camiones especializados y nuestro equipo de expertos..</center></html>");
        materialLabel1.setFont(new Font("Arial", Font.PLAIN, 12));
        panela.add(iconLabel, BorderLayout.WEST);
        panela.add(materialLabel1, BorderLayout.CENTER);
        panel1.add(panela, BorderLayout.SOUTH);
        //---
        panel1.add(panela22, BorderLayout.SOUTH);
        //-----
        JPanel panelb = new JPanel(new BorderLayout(10,10));
        panelb.setPreferredSize(new Dimension(90, 30));
        panelb.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelb.setBackground(Color.WHITE);
        ImageIcon checkIconb = new ImageIcon("src\\Windows\\images\\nar.png");
        Image imgb = checkIconb.getImage();
        Image resizedImgb = imgb.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
        ImageIcon resizedIconb = new ImageIcon(resizedImgb);
        JLabel iconLabelb = new JLabel(resizedIconb);
        JLabel materialLabel1b = new JLabel("<html><center><h1><strong>material inflamable</strong></h1>Confíe en nosotros para transportar de manera segura sus materiales inflamables con nuestro equipo capacitado por expertos y camiones de última generación..</center></html>");
        materialLabel1b.setFont(new Font("Arial", Font.PLAIN, 12));
        panelb.add(iconLabelb, BorderLayout.WEST);
        panelb.add(materialLabel1b, BorderLayout.CENTER);
        panel1.add(panelb, BorderLayout.SOUTH);
        //--
        JPanel panelc = new JPanel(new BorderLayout(10,10));
        panelc.setPreferredSize(new Dimension(90, 30));
        panelc.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelc.setBackground(Color.WHITE);
        ImageIcon checkIconc = new ImageIcon("src\\Windows\\images\\cons.png");
        Image imgc = checkIconc.getImage();
        Image resizedImgc = imgc.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        ImageIcon resizedIconc = new ImageIcon(resizedImgc);
        JLabel iconLabelc = new JLabel(resizedIconc);
        JLabel materialLabel1c = new JLabel("<html><center><h1><strong>Material de construcción</strong></h1>¿Necesita transporte confiable para sus materiales de construcción? ¡No busque más! Nuestros camiones están equipados para transportar sus materiales de manera segura y eficiente a su lugar de trabajo.</center></html>");
        materialLabel1c.setFont(new Font("Arial", Font.PLAIN, 12));
        panelc.add(iconLabelc, BorderLayout.WEST);
        panelc.add(materialLabel1c, BorderLayout.CENTER);
        panel1.add(panelc, BorderLayout.SOUTH);
        //--
        JPanel paneld = new JPanel(new BorderLayout(10,10));
        paneld.setPreferredSize(new Dimension(90, 30));
        paneld.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        paneld.setBackground(Color.WHITE);
        ImageIcon checkIcond = new ImageIcon("src\\Windows\\images\\alim.png");
        Image imgd = checkIcond.getImage();
        Image resizedImgd = imgd.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
        ImageIcon resizedIcond = new ImageIcon(resizedImgd);
        JLabel iconLabeld = new JLabel(resizedIcond);
        JLabel materialLabel1d = new JLabel("<html><center><h1><strong>Productos alimenticios</strong></h1>¡Transportamos tus productos alimenticios de forma segura y confiable en nuestros camiones especializados!</center></html>");
        materialLabel1d.setFont(new Font("Arial", Font.PLAIN, 12));
        paneld.add(iconLabeld, BorderLayout.WEST);
        paneld.add(materialLabel1d, BorderLayout.CENTER);
        panel1.add(paneld, BorderLayout.SOUTH);





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
        /**panel 2 servers*/
        JPanel panela22q = new JPanel(new BorderLayout(10,10));
        panela22q.setPreferredSize(new Dimension(90, 30));
        panela22q.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panela22q.setBackground(Color.WHITE);
        ImageIcon checkIcon22q = new ImageIcon("src\\Windows\\images\\chimicicon.png");
        Image img22q = checkIcon22q.getImage();
        Image resizedImg22q = img22q.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
        ImageIcon resizedIcon22q = new ImageIcon(resizedImg22);
        JLabel iconLabel22q = new JLabel(resizedIcon22q);
        JLabel materialLabel22q = new JLabel("<html><center><h1><strong>Material químico</strong></h1>Confíe en nosotros para transportar de manera segura sus valiosos materiales químicos en nuestros camiones de última generación.</center></html>");
        materialLabel22q.setFont(new Font("Arial", Font.PLAIN, 12));
        panela22q.add(iconLabel22q, BorderLayout.WEST);
        panela22q.add(materialLabel22q, BorderLayout.CENTER);




// add the new panel to panel1 using a BorderLayout

        //-----
        JPanel panelaq = new JPanel(new BorderLayout(10,10));
        panelaq.setPreferredSize(new Dimension(90, 30));
        panelaq.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panela.setBackground(Color.WHITE);
        ImageIcon checkIconq = new ImageIcon("src\\Windows\\images\\conge.png");
        Image imgq = checkIconq.getImage();
        Image resizedImgq = imgq.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
        ImageIcon resizedIconq = new ImageIcon(resizedImgq);
        JLabel iconLabelq = new JLabel(resizedIconq);
        JLabel materialLabel1q = new JLabel("<html><center><h1><strong>Material de congelación</strong></h1>Confíe en nosotros para transportar de forma segura sus productos congelados con nuestros camiones especializados y nuestro equipo de expertos..</center></html>");
        materialLabel1q.setFont(new Font("Arial", Font.PLAIN, 12));
        panelaq.add(iconLabelq, BorderLayout.WEST);
        panelaq.add(materialLabel1q, BorderLayout.CENTER);
        panel1.add(panelaq, BorderLayout.SOUTH);
        //---

        //-----
        JPanel panelbq = new JPanel(new BorderLayout(10,10));
        panelbq.setPreferredSize(new Dimension(90, 30));
        panelbq.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelbq.setBackground(Color.WHITE);
        ImageIcon checkIconbq = new ImageIcon("src\\Windows\\images\\nar.png");
        Image imgbq = checkIconbq.getImage();
        Image resizedImgbq = imgbq.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
        ImageIcon resizedIconbq = new ImageIcon(resizedImgbq);
        JLabel iconLabelbq = new JLabel(resizedIconbq);
        JLabel materialLabel1bq = new JLabel("<html><center><h1><strong>material inflamable</strong></h1>Confíe en nosotros para transportar de manera segura sus materiales inflamables con nuestro equipo capacitado por expertos y camiones de última generación..</center></html>");
        materialLabel1b.setFont(new Font("Arial", Font.PLAIN, 12));
        panelbq.add(iconLabelbq, BorderLayout.WEST);
        panelbq.add(materialLabel1bq, BorderLayout.CENTER);
        panel1.add(panelbq, BorderLayout.SOUTH);
        //--
        JPanel panelcq = new JPanel(new BorderLayout(10,10));
        panelcq.setPreferredSize(new Dimension(90, 30));
        panelcq.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelcq.setBackground(Color.WHITE);
        ImageIcon checkIconcq = new ImageIcon("src\\Windows\\images\\cons.png");
        Image imgcq = checkIconcq.getImage();
        Image resizedImgcq = imgcq.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        ImageIcon resizedIconcq = new ImageIcon(resizedImgcq);
        JLabel iconLabelcq= new JLabel(resizedIconcq);
        JLabel materialLabel1cq = new JLabel("<html><center><h1><strong>Material de construcción</strong></h1>¿Necesita transporte confiable para sus materiales de construcción? ¡No busque más! Nuestros camiones están equipados para transportar sus materiales de manera segura y eficiente a su lugar de trabajo.</center></html>");
        materialLabel1cq.setFont(new Font("Arial", Font.PLAIN, 12));
        panelcq.add(iconLabelcq, BorderLayout.WEST);
        panelcq.add(materialLabel1cq, BorderLayout.CENTER);
        panel1.add(panelcq, BorderLayout.SOUTH);
        //--
        JPanel paneldq = new JPanel(new BorderLayout(10,10));
        paneldq.setPreferredSize(new Dimension(90, 30));
        paneldq.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        paneldq.setBackground(Color.WHITE);
        ImageIcon checkIcondq = new ImageIcon("src\\Windows\\images\\alim.png");
        Image imgdq = checkIcondq.getImage();
        Image resizedImgdq = imgdq.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
        ImageIcon resizedIcondq = new ImageIcon(resizedImgdq);
        JLabel iconLabeldq = new JLabel(resizedIcondq);
        JLabel materialLabel1dq = new JLabel("<html><center><h1><strong>Productos alimenticios</strong></h1>¡Transportamos tus productos alimenticios de forma segura y confiable en nuestros camiones especializados!</center></html>");
        materialLabel1dq.setFont(new Font("Arial", Font.PLAIN, 12));
        paneldq.add(iconLabeldq, BorderLayout.WEST);
        paneldq.add(materialLabel1dq, BorderLayout.CENTER);
        panela22q.addMouseListener(new HoverListener());
        panelaq.addMouseListener(new HoverListener());
        panelbq.addMouseListener(new HoverListener());
        panelcq.addMouseListener(new HoverListener());
        paneldq.addMouseListener(new HoverListener());

        panela22.addMouseListener(new HoverListener());
        panela.addMouseListener(new HoverListener());
        panelb.addMouseListener(new HoverListener());
        panelc.addMouseListener(new HoverListener());
        paneld.addMouseListener(new HoverListener());

// Define the HoverListener class




        panel2.add(panela22q, BorderLayout.SOUTH);
        panel2.add(panelaq, BorderLayout.SOUTH);
        panel2.add(panelbq, BorderLayout.SOUTH);
        panel2.add(panelcq, BorderLayout.SOUTH);
        panel2.add(paneldq, BorderLayout.SOUTH);
        /***/

        // Add the service examples to the main panel
        JPanel contactar =new JPanel(new BorderLayout());
        contactar.setPreferredSize(new Dimension(200,50));
        JButton con=new JButton("Contactar Con Nosotros");
        Font font = new Font("Arial", Font.PLAIN, 16);
        con.setFont(font);
        con.setBackground(new Color(0, 150, 136));
        con.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        contactar.add(con,BorderLayout.CENTER);
        con.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ContactarConNos c =new ContactarConNos();
            }
        });
        mainPanel.add(panel1);
        mainPanel.add(panel2);



        reparto.add(mainPanel);
        reparto.add(contactar,BorderLayout.SOUTH);
    }
    class HoverListener extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            JPanel panel = (JPanel) e.getSource();
            panel.setPreferredSize(new Dimension(120, 80));
            panel.revalidate();
        }
        @Override
        public void mouseExited(MouseEvent e) {
            JPanel panel = (JPanel) e.getSource();
            panel.setPreferredSize(new Dimension(90, 30));
            panel.revalidate();
        }
    }


}