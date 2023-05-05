package Windows;

import Dbconnection.GestorDB;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ventana extends JFrame{
     boolean loginstate=false;
        GestorDB db;

    public ventana() {
        FlatLightLaf.install();
        db=new GestorDB();
        // Ventana principal
        JFrame frame = new JFrame("XLR8 Transports");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(900,1000));
        frame.setResizable(false);

        // Icono de la ventana
        Image icono = new ImageIcon("src\\Windows\\images\\X8.png").getImage();
        frame.setIconImage(icono);

        //Panel contenido
        JPanel contentPane = new JPanel(new BorderLayout());


        // Paneles
        JPanel panelNorte = new JPanel();
        panelNorte.setLayout(new BorderLayout());

        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new GridLayout(2,1));

        JPanel panel1 = new JPanel();
        //panel1.setBackground(Color.RED);
        panel1.setPreferredSize(new Dimension(1000,330));

        JPanel panel2 = new JPanel();
        //panel2.setBackground(Color.BLUE);
        panel2.setPreferredSize(new Dimension(1000,330));

        // Botón de inicio de sesión
        JButton botonLogin = new JButton(("<html><u>Iniciar sesión</u></html>"));
        botonLogin.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        botonLogin.setForeground(new Color(255, 255, 255));
        botonLogin.setBackground(new Color(0, 150, 136));
        botonLogin.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        botonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login login = new login(botonLogin, panelNorte,false,frame);


            }
        });
        ImageIcon checkIcon = new ImageIcon("src\\Windows\\images\\logo.png");
        Image img = checkIcon.getImage();
        Image resizedImg = img.getScaledInstance(100, 70, java.awt.Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImg);
        JLabel iconLabel = new JLabel(resizedIcon);
        JLabel labelAux = new JLabel();
        labelAux.setPreferredSize(new Dimension(100, 70));

        panelNorte.add(iconLabel, BorderLayout.CENTER);
        panelNorte.add(botonLogin, BorderLayout.EAST);
        panelNorte.add(labelAux, BorderLayout.WEST);
        panelNorte.setBackground(new Color(0, 150, 136));

        // Botones opciones
        ImageIcon fondo1 = new ImageIcon("src\\Windows\\images\\xlr8bus.jpg");
        JButton opcion1 = new JButton();
        opcion1.setIcon(fondo1);
        opcion1.setBorder(null);
        panel1.add(opcion1);
        opcion1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println(frame.getTitle());
                frame.getContentPane().removeAll();
                frame.getContentPane().revalidate();
                frame.getContentPane().repaint();
                buses b = new buses(frame , panelNorte, contentPane, botonLogin,db );
            }
        });


        ImageIcon fondo2 = new ImageIcon("src\\Windows\\images\\xlr8camion.jpg");
        JButton opcion2 = new JButton();
        opcion2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.getContentPane().revalidate();
                frame.getContentPane().repaint();
                reparto r= new reparto(frame, panelNorte, contentPane);
            }
        });
        opcion2.setIcon(fondo2);
        opcion2.setBorder(null);
        panel2.add(opcion2);


        // Agregar paneles
        contentPane.add(panelNorte, BorderLayout.NORTH);
        panelCentro.add(panel1);
        panelCentro.add(panel2);
        contentPane.add(panelCentro, BorderLayout.CENTER);
        frame.add(contentPane);


        // Mostrar la ventana principal
        frame.pack();
        frame.setVisible(true);
    }
}





