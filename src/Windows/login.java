package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class login extends JFrame {
    private boolean loginstate=false;
    private boolean done=false;

    public login(JButton botonLogin, JPanel panelNorte , boolean paymode) {
        // Configurar la ventana principal
        JFrame frame = new JFrame("Introduce usuario y contraseña");

        frame.setLayout(new BorderLayout());
        frame.setSize(350, 150);
        frame.setResizable(false);


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


                    //Mostrar el nombre de usuario
                    JLabel nombreLabel = new JLabel();
                    panelNorte.add(nombreLabel, BorderLayout.EAST);

                    nombreLabel.setText("Bienvenido " + username);
                    nombreLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 30));

                    //Ocultar el botón "Iniciar sesión"
                    panelNorte.remove(botonLogin);
                    setLoginstate(true);
                    setDone(true);
                    if (paymode){
                        PayWindow P=new PayWindow();
                    }



                } else {
                    JOptionPane.showMessageDialog(login.this, "Usuario o contraseña incorrecta","ERROR",JOptionPane.ERROR_MESSAGE);
                    passField.setText("");
                    setLoginstate(false);
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
        return username.equals("admin") && password.equals("admin");
    }

    public boolean isLoginstate() {
        return loginstate;
    }

    public void setLoginstate(boolean loginstate) {
        this.loginstate = loginstate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}