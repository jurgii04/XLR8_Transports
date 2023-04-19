package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class login extends JFrame {

    public login(JButton botonLogin, JPanel panelNorte) {
        // Configurar la ventana principal
        JFrame frame = new JFrame("Introduce usuario y contraseña");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(350, 150);

        // Configurar el panel de contenido
        JPanel contentPane = new JPanel(new GridLayout(2, 2));
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel userLabel = new JLabel("Usuario:");
        JTextField userField = new JTextField();
        JLabel passLabel = new JLabel("Contraseña:");
        JPasswordField passField = new JPasswordField();
        contentPane.add(userLabel);
        contentPane.add(userField);
        contentPane.add(passLabel);
        contentPane.add(passField);

        // Configurar el panel de botones
        JPanel buttonPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton loginButton = new JButton("Iniciar sesión");
        JButton cancelButton = new JButton("Cancelar");
        buttonPane.add(loginButton);
        buttonPane.add(cancelButton);

        // Agregar los paneles a la ventana
        frame.add(contentPane, BorderLayout.CENTER);
        frame.add(buttonPane, BorderLayout.SOUTH);

        // Configurar el botón de inicio de sesión
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());

                if (authenticate(username, password)) {
                    frame.dispose();

                    //Crear una nueva etiqueta para mostrar el nombre de usuario después de iniciar sesión
                    JLabel nombreLabel = new JLabel();
                    panelNorte.add(nombreLabel, BorderLayout.EAST);

                    nombreLabel.setText("Bienvenido " + username);

                    //Ocultar el botón "Iniciar sesión"
                    //botonLogin.setVisible(false);
                    panelNorte.remove(botonLogin);

                } else {
                    JOptionPane.showMessageDialog(login.this, "Usuario o contraseña incorrecta","ERROR",JOptionPane.ERROR_MESSAGE);
                    passField.setText("");
                }
            }
        });

        // Configurar el botón de cancelar
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        passField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Verificar si la tecla presionada es la tecla Enter
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Activar el botón de inicio de sesión
                    loginButton.doClick();
                }
            }
        });

        // Mostrar la ventana
        frame.setVisible(true);
    }

    private boolean authenticate(String username, String password) {
        return username.equals("bsadmn") && password.equals("admin");
    }

}
