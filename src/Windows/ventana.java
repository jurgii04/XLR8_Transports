package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ventana extends JFrame{
     boolean loginstate=false;

    public ventana() {
        // Ventana principal
        JFrame frame = new JFrame("XLR8 Transports");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
                login login = new login(botonLogin, panelNorte,false);


            }
        });
        panelNorte.add(botonLogin, BorderLayout.EAST);
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
                buses b = new buses(frame , panelNorte,contentPane,botonLogin );
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
                JButton VOLVER = new JButton("Volver");
                panelNorte.add(VOLVER, BorderLayout.WEST);
                VOLVER.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.getContentPane().removeAll();
                        frame.getContentPane().revalidate();
                        frame.getContentPane().repaint();
                        frame.add(panelNorte, BorderLayout.NORTH);
                        frame.add(contentPane);
                        frame.setTitle("XLR8 Transports");

                    }
                });
                frame.add(panelNorte, BorderLayout.NORTH);
                reparto r= new reparto(frame , panelNorte,contentPane);
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
        frame.setPreferredSize(new Dimension(900,900));

        frame.pack();
        frame.setVisible(true);
    }
}

/*import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ventana extends JFrame {

    public ventana() {
        // Configurar la ventana
        JFrame frame = new JFrame("XLR8 Transports");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());

        // Establecer margen y fondo del panel de los botones
        JPanel panelCentral = new JPanel(new GridLayout(2, 1));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelCentral.setBackground(new Color(255, 255, 255, 200));

        // Crear los botones con imágenes de fondo y aplicar estilos
        ImageIcon icono1 = new ImageIcon("src\\Windows\\images\\xlr8bus.jpg");
        ImageIcon icono2 = new ImageIcon("src\\Windows\\images\\xlr8camion.jpg");
        Image imagen1 = icono1.getImage().getScaledInstance(847, 435, Image.SCALE_SMOOTH);
        Image imagen2 = icono2.getImage().getScaledInstance(847, 435, Image.SCALE_SMOOTH);
        JButton btnBuses = new JButton(new ImageIcon(imagen1));
        JButton btnRep = new JButton(new ImageIcon(imagen2));
        btnBuses.setBorderPainted(false);
        btnRep.setBorderPainted(false);
        btnBuses.setFocusPainted(false);
        btnRep.setFocusPainted(false);
        btnBuses.setContentAreaFilled(false);
        btnRep.setContentAreaFilled(false);
        btnBuses.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRep.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Dimension dimension1 = new Dimension(imagen1.getWidth(null), imagen1.getHeight(null));
        Dimension dimension2 = new Dimension(imagen2.getWidth(null), imagen2.getHeight(null));
        btnBuses.setPreferredSize(dimension1);
        btnRep.setPreferredSize(dimension2);
        panelCentral.add(btnBuses);
        panelCentral.add(btnRep);

        // Crear el botón de inicio de sesión y aplicar estilos
        JButton btnLogin = new JButton("Iniciar sesión");
        btnLogin.setForeground(new Color(255, 255, 255));
        btnLogin.setBackground(new Color(0, 150, 136));
        btnLogin.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelSuperior.setBackground(new Color(0, 150, 136));
        panelSuperior.add(btnLogin);
        frame.add(panelSuperior, BorderLayout.NORTH);

        btnBuses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println(frame.getTitle());
                panelCentral.removeAll();
                panelCentral.revalidate();
                panelCentral.repaint();
                JButton VOLVER = new JButton("Volver");
                panelSuperior.add(VOLVER, BorderLayout.WEST);
                VOLVER.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.getContentPane().removeAll();
                        frame.getContentPane().revalidate();
                        frame.getContentPane().repaint();
                        frame.add(panelSuperior, BorderLayout.NORTH);


                    }
                });
                buses b = new buses(frame);
            }
        });

        // Agregar el panel de los botones a la ventana
        frame.add(panelCentral, BorderLayout.CENTER);

        // Aplicar fuente personalizada
        Font customFont = new Font("Arial", Font.PLAIN, 16);
        UIManager.put("Button.font", customFont);

        // Mostrar la ventana
        frame.setVisible(true);
    }
}*/




