import javax.swing.*;
import java.awt.*;

public class ventana {

    public ventana() {
        // Ventana principal
        JFrame frame = new JFrame("XLR8 Transports");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);

        // Icono de la ventana
        Image icono = new ImageIcon("C:\\Users\\ik012982i9\\Pictures\\X8.png").getImage();
        frame.setIconImage(icono);

        // Paneles
        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new GridLayout(2,1));

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.RED);

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.BLUE);

        // Botón de inicio de sesión
        JButton botonLogin = new JButton("Iniciar sesión");

        // Panel para el botón
        JPanel panelBoton = new JPanel(new BorderLayout());
        panelBoton.add(botonLogin, BorderLayout.EAST);

        // Agregar paneles
        frame.add(panelBoton, BorderLayout.NORTH);
        frame.add(panelCentro);
        panelCentro.add(panel1);
        panelCentro.add(panel2);

        // Mostrar la ventana principal
        frame.setVisible(true);
    }
}
