import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ventana extends JFrame{

    public ventana() {
        // Ventana principal
        JFrame frame = new JFrame("XLR8 Transports");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // Icono de la ventana
        Image icono = new ImageIcon("src\\X8.png").getImage();
        frame.setIconImage(icono);

        //Panel contenido
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Paneles
        JPanel panelNorte = new JPanel();
        panelNorte.setLayout(new BorderLayout());

        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new GridLayout(2,1));

        JPanel panel1 = new JPanel();

        JPanel panel2 = new JPanel();

        // Botón de inicio de sesión
        JButton botonLogin = new JButton("Iniciar sesión");
        botonLogin.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        botonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login login = new login();
            }
        });
        panelNorte.add(botonLogin, BorderLayout.EAST);

        // Botones opciones
        ImageIcon fondo1 = new ImageIcon("src\\xlr8bus.jpg");
        JButton opcion1 = new JButton();
        opcion1.setIcon(fondo1);
        opcion1.setBorder(null);
        opcion1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(ventana.this, "Has iniciado sesión correctamente","INICIO DE SESIÓN",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panel1.add(opcion1);

        ImageIcon fondo2 = new ImageIcon("src\\xlr8camion.jpg");
        JButton opcion2 = new JButton();
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
        frame.setSize(900,1080);
        frame.setVisible(true);
    }
}
