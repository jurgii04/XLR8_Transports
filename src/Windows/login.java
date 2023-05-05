package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.formdev.flatlaf.FlatLightLaf;

public class login extends JFrame {
    private boolean loginstate=false;
    private boolean done=false;

    public login(JButton botonLogin, JPanel panelNorte , boolean paymode, JFrame ventana) {
        FlatLightLaf.install();
        // Configurar la ventana principal
        JFrame frame = new JFrame("Introduce usuario y contraseña");

        Image icono = new ImageIcon("src\\Windows\\images\\X8.png").getImage();
        frame.setIconImage(icono);

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
        JButton Crear = new JButton("Crear Cuenta");
        buttonPane.add(loginButton);
        buttonPane.add(Crear);
        buttonPane.add(cancelButton);

        Crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChooseTypeFrame c=new ChooseTypeFrame();
            }
        });

        // Agregar los paneles a la ventana
        frame.add(contentPane, BorderLayout.CENTER);
        frame.add(buttonPane, BorderLayout.SOUTH);

        // Configurar el botón de inicio de sesión
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());


                if (authenticate(username, password)) {

                    if (username.equals("admin") && password.equals("admin")) {
                        frame.dispose();
                        adminBuses adminBuses = new adminBuses();

                    } else {
                        frame.dispose();


                        //Mostrar el nombre de usuario
                        JLabel nombreLabel = new JLabel();
                        panelNorte.add(nombreLabel, BorderLayout.EAST);

                        nombreLabel.setText("<html><b>Bienvenido " + username + "</b></html>→");
                        nombreLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 30));

                        //Ocultar el botón "Iniciar sesión"
                        panelNorte.remove(botonLogin);
                        setLoginstate(true);
                        setDone(true);
                        if (paymode){
                            PayWindow P=new PayWindow();
                        }
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

        frame.setLocationRelativeTo(null);

        // Mostrar la ventana
        setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public boolean authenticate(String username, String password) {
        return username.equals("admin") && password.equals("admin") || username.equals("pepe") && password.equals("pepe");
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
