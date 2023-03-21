import javax.swing.*;
import java.awt.*;

public class login {

    public login() {
        JFrame frame = new JFrame("Introduce usuario y contraseña");
        frame.setLayout(new BorderLayout());
        frame.setSize(500,500);

        // Icono de la ventana
        Image icono = new ImageIcon("C:\\Users\\ik012982i9\\Pictures\\X8.png").getImage();
        frame.setIconImage(icono);

        // Labels
        JLabel user = new JLabel("Usuario:");
        user.setSize(100,100);
        JLabel pass = new JLabel("Contraseña:");
        pass.setSize(100,100);

        JTextField userT = new JTextField();
        userT.setSize(100,100);
        JTextField passT = new JTextField();
        passT.setSize(100,100);

        frame.add(user);
        frame.add(userT);
        frame.add(pass);
        frame.add(passT);

        frame.setVisible(true);
    }
}
