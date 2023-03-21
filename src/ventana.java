import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ventana {

    public ventana() {
        // Ventana principal
        JFrame frame = new JFrame("XLR8 Transports");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // Icono de la ventana
        Image icono = new ImageIcon("C:\\Users\\ik012982i9\\Pictures\\X8.png").getImage();
        frame.setIconImage(icono);

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
        JButton botonLogin = new JButton("Iniciar sesión");
        botonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login login = new login();
            }
        });
        panelNorte.add(botonLogin, BorderLayout.EAST);

        // Botones opciones
        ImageIcon fondo1 = new ImageIcon("C:\\Users\\ik012982i9\\Pictures\\xlr8bus.jpg");
        JButton opcion1 = new JButton();
        opcion1.setIcon(fondo1);
        opcion1.setBorder(null);
        //opcion1.setPreferredSize(new Dimension(1000, 500));
        panel1.add(opcion1);


        // Agregar paneles
        frame.add(panelNorte, BorderLayout.NORTH);
        panelCentro.add(panel1);
        panelCentro.add(panel2);
        frame.add(panelCentro, BorderLayout.CENTER);


        // Mostrar la ventana principal
        frame.pack();
        frame.setVisible(true);
    }
}
