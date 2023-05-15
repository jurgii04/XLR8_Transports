package Windows;

import javax.mail.MessagingException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import Dbconnection.GestorDB;
import com.formdev.flatlaf.FlatLightLaf;

import static Windows.ventana.*;
import static Windows.editarPerfil.*;

public class login extends JFrame {

    private boolean done=false;
    public  static String name="";
    public static String ea;

    public login(JButton botonLogin, JPanel panelNorte , boolean paymode, JFrame ventana) {
        FlatLightLaf.install();
        GestorDB db=new GestorDB();
        // Configurar la ventana principal
        JFrame frame = new JFrame("Introduce usuario y contraseña");

        Image icono = new ImageIcon("src\\Windows\\images\\X8.png").getImage();
        frame.setIconImage(icono);

        frame.setLayout(new BorderLayout());
        frame.setSize(450, 150);
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
        JButton cancelButton = new JButton("¿Has olvidado la contraseña?");
        JButton Crear = new JButton("Crear Cuenta");
        buttonPane.add(loginButton);
        buttonPane.add(Crear);
        buttonPane.add(cancelButton);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            try {
                String email = JOptionPane.showInputDialog(null, "Introduce tu dirección de correo:");
                if (!db.AccExist(email)){
                    if (email == null || email.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Ninguna dirección de correo introducida","ERROR",JOptionPane.ERROR_MESSAGE);
                        System.out.println("No email address entered.");
                    } else {
                        Random rand = new Random();
                        int randomNum = rand.nextInt(90000) + 10000;
                        String message ="Recientemente hemos recibido una solicitud para restablecer tu contraseña en nuestro sistema. Para completar el proceso de recuperación de contraseña, por favor utiliza el siguiente código de verificación:\n" +
                                "\n" +
                                "Código de verificación: "+randomNum+"\n" +
                                "\n" +
                                "Por favor, ingresa este código en la página de recuperación de contraseña para continuar con el proceso. Si no has solicitado un restablecimiento de contraseña, te recomendamos que tomes las medidas necesarias para proteger tu cuenta.\n" +
                                "\n" +
                                "Si tienes alguna pregunta o necesitas asistencia adicional, no dudes en contactar a nuestro equipo de soporte. Estaremos encantados de ayudarte.\n" +
                                "\n" +
                                "Atentamente,\n" +
                                "El equipo de XLR8 Transports.";
                        String input = "";
                        try {
                            gmail g=new gmail(email,message,"Código de recuperación");

                             input = JOptionPane.showInputDialog(null, "Revisa el correo electrónico e introduce el código:");
                            int number=0;
                            try {
                                number = Integer.parseInt(input);
                                // Do something with the integer

                            } catch (NumberFormatException c) {
                                // The user entered an invalid integer or clicked the Cancel button
                                System.out.println("Entrada inválida o cancelada");
                            }
                            int trys=2;
                            for (int i=0;i<3;i++){
                                //System.out.println(number);

                                if ((randomNum!=number) && i==2){
                                    JOptionPane.showMessageDialog(null, "El código no es correcto, no tienes mas intentos.","ERROR",JOptionPane.ERROR_MESSAGE);
                                } else if (randomNum!=number) {
                                    String intentos = JOptionPane.showInputDialog(null, "El código no es correcto, intentalo otra vez (tienes "+trys+" intentos):");
                                    number = Integer.parseInt(input);
                                    trys--;
                                }

                            }
                            if (randomNum==number){
                                JPasswordField passwordField = new JPasswordField();
                                JLabel label = new JLabel("Ingresa tu contraseña: ");

                                Object[] components = {label, passwordField};

                                int option = JOptionPane.showConfirmDialog(
                                        null,
                                        components,
                                        "Ingresa tu contraseña",
                                        JOptionPane.OK_CANCEL_OPTION,
                                        JOptionPane.PLAIN_MESSAGE
                                );
                                if (option == JOptionPane.OK_OPTION) {
                                    String newcontrasena= passwordField.getText();
                                    Map<String, Object> datos = new LinkedHashMap<>();
                                    encription en =new encription();
                                    datos.put("CONTRASENA", en.encriptar(newcontrasena));
                                    db.update("USERSACCS", "EMAIL= '" + email + "'", datos);
                                    JOptionPane.showMessageDialog(null,"La contraseña ha sido cambiada.","Cambiar la contraseña",JOptionPane.INFORMATION_MESSAGE);
                                }

                            }
                        } catch (MessagingException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                        } catch (HeadlessException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                        }


                    }






                    System.out.println("Entered email address: " + email);
                }else {
                    JOptionPane.showMessageDialog(null, "Email no existe.","ERROR",JOptionPane.ERROR_MESSAGE);
                }

            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
            }
            }
        });

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
                                    path=db.selectFromTable("USERSACCS",new String[]{"IMG"},new String[]{"EMAIL='"+username+"'"})[0];
                                    frame.dispose();
                                     name=db.selectFromTable("USERSACCS",new String[]{"NOMBRE_COMPLETO"},new String[]{"EMAIL='"+username+"'"})[0];
                                    loginstat=true;


                                    //Mostrar el nombre de usuario
                                    tipouser=tipo;

                                    JMenuBar menuBar = new JMenuBar();
                                    JMenu menu = new JMenu("<html><b>Bienvenido<br><u>" + name + "</u></b></html>");
                                    JMenuItem editarPerfil = new JMenuItem("Editar perfil");
                                    editarPerfil.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            Windows.editarPerfil editarPerfilEmpresa = new editarPerfil();
                                        }
                                    });
                                    JMenuItem modificarBilletes = new JMenuItem("Mis billetes");
                                    modificarBilletes.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {

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
                                    menu.add(modificarBilletes);
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