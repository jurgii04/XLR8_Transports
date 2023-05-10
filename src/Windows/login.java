package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Dbconnection.GestorDB;
import com.formdev.flatlaf.FlatLightLaf;

import static Windows.ventana.*;

public class login extends JFrame {

    private boolean done=false;
    public static String ea;

    public login(JButton botonLogin, JPanel panelNorte , boolean paymode, JFrame ventana) {
        FlatLightLaf.install();
        GestorDB db=new GestorDB();
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
        JLabel userLabel = new JLabel("Email:");
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
                try{
                    encription enc=new encription();
                    String username = userField.getText();
                    ea=username;
                    String password = enc.encriptar(passField.getText());

                    String [] data=db.logindata(username);
                    if (data==null){
                        JOptionPane.showMessageDialog(login.this, "El usuario no existe","ERROR",JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        String pass=data[1];
                        String tipo=data[0];
                        //System.out.println(pass);
                        Admin  a;
                        if (password.equals(pass)){
                            switch (tipo){
                                case "adminbuses":
                                    frame.dispose();
                                    a= new Admin(tipo);

                                    break;
                                case "adminreparto":
                                    a= new Admin(tipo);
                                    frame.dispose();
                                    break;
                                case "jefe":
                                    a= new Admin(tipo);
                                    frame.dispose();
                                    break;
                                default:
                                    frame.dispose();
                                    String name=db.selectFromTable("USERSACCS",new String[]{"NOMBRE_COMPLETO"},new String[]{"EMAIL='"+username+"'"})[0];
                                    loginstat=true;


                                    //Mostrar el nombre de usuario
                                    tipouser=tipo;

                                    JMenuBar menuBar = new JMenuBar();
                                    JMenu menu = new JMenu("<html><b>Bienvenido<br><u>" + name + "</u></b></html>");
                                    JMenuItem editarPerfil = new JMenuItem("Editar perfil");
                                    editarPerfil.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            editarPerfilEmpresa editarPerfilEmpresa = new editarPerfilEmpresa();
                                        }
                                    });
                                    JMenuItem cerrarSesion = new JMenuItem("Cerrar sesión");
                                    cerrarSesion.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            panelNorte.remove(menuBar);
                                            panelNorte.revalidate();
                                            panelNorte.repaint();
                                            panelNorte.add(botonLogin, BorderLayout.EAST);
                                            loginstat=false;
                                            tipouser="";
                                        }
                                    });
                                    menuBar.setBackground(new Color(0, 150, 136));
                                    menuBar.setPreferredSize(new Dimension(100, 70));
                                    menuBar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, -70));


                                    int textWidth = name.length();
                                    if (textWidth > 10) {
                                        String usernameSubstring = name.substring(0,8) + "...";
                                        menu.setText("<html><b>Bienvenido<br><u>" + usernameSubstring + "</u></b></html>");
                                    }



                                    menu.add(editarPerfil);
                                    menu.add(cerrarSesion);
                                    menuBar.add(menu);

                                    panelNorte.add(menuBar, BorderLayout.EAST);
                                    panelNorte.revalidate();
                                    panelNorte.repaint();

                                    //Ocultar el botón "Iniciar sesión"
                                    panelNorte.remove(botonLogin);
                                    loginstat=true;




                            }
                        }else {
                            JOptionPane.showMessageDialog(login.this, "La contraseña no es correcta","ERROR",JOptionPane.ERROR_MESSAGE);
                            passField.setText("");
                        }

                    }
                }catch (Exception x){
                    JOptionPane.showMessageDialog(login.this, x.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);

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
        frame.setVisible(true);
    }


    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}