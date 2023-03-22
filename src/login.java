import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class login extends JFrame {


    public login() {
        JFrame frame = new JFrame("Introduce usuario y contraseña");

        Image icono = new ImageIcon("src\\X8.png").getImage();
        frame.setIconImage(icono);

        JLabel usuer = new JLabel("Usuario:");
        JTextField userT = new JTextField(15);

        JLabel pass = new JLabel("Contraseña:");
        JPasswordField passT = new JPasswordField(15);
        JButton botonLogin = new JButton("Iniciar sesión");


        JPanel contentPane = new JPanel(new GridLayout(4, 2));
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.add(usuer);
        contentPane.add(userT);
        contentPane.add(pass);
        contentPane.add(passT);
        contentPane.add(new JLabel());
        contentPane.add(new JLabel());
        contentPane.add(new JLabel());
        contentPane.add(botonLogin);
        frame.add(contentPane);
        botonLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userT.getText();
                String password = new String(passT.getPassword());

                if (authenticate(username, password)) {
                    JOptionPane.showMessageDialog(login.this, "Has iniciado sesión correctamente","INICIO DE SESIÓN",JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(login.this, "Usuario o contraseña incorrecta","ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.pack();
        frame.setVisible(true);
    }

    private boolean authenticate(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }
}
