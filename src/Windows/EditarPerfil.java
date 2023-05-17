package Windows;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import static Windows.login.ea;
import static Windows.ventana.tipouser;

import Dbconnection.GestorDB;
import com.formdev.flatlaf.FlatLightLaf;

public class editarPerfil extends JFrame {

    //Empresa
    private JLabel companyNameLabel,emailLabel, dniLabel, phoneLabel, sectorLabel,passactulabel,newpasslabel;
    private JTextField companyNameField ,emailField, dniField, phoneField, sectorField;
    private JPasswordField passactu,newpass;

    //Persona
    private JLabel nameLabel, emailLabel2, fechaNacimientoLabel, dniLabel2;
    private JTextField nameField, emailField2, fechaNacimientoField, dniField2;
    private JRadioButton maleRadioButton, femaleRadioButton, otroRadioButton;

    private JButton updateButton;
    public static String path="src\\Windows\\images\\PerfilFotos\\user.png";

    public editarPerfil() {
        FlatLightLaf.install();
        GestorDB db = new GestorDB();

        // Set the title and size of the frame
        setTitle("Editar perfil");
        setSize(550, 750);

        //Create a panel and add the components to it

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        if (tipouser.equals("Empresa")) {
            //Create the labels
            companyNameLabel = new JLabel("<html><h3>Nombre completo de la empresa:</h3></html>");
            dniLabel = new JLabel("<html><h3>DNI:</h3></html>");
            phoneLabel = new JLabel("<html><h3>Número de teléfono:</h3></html>");
            sectorLabel = new JLabel("<html><h3>Sector de Trabajo:</h3></html>");
            emailLabel= new JLabel("<html><h3>Email:</h3></html>");
            passactulabel=new JLabel("<html><h3>Contraseña Actual:</h3></html>");
            newpasslabel=new JLabel("<html><h3>Nueva Contraseña:</h3></html>");

            //Create the text fields
            companyNameField = new JTextField(20);
            dniField = new JTextField(20);
            phoneField = new JTextField(20);
            sectorField = new JTextField(20);
            emailField=new JTextField(20);
            passactu = new JPasswordField(20);
            newpass=new JPasswordField(20);

            //Create the button
            updateButton = new JButton("<html><h2>Actualizar</h2></html>");

            updateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    encription en=new encription();
                    Map<String, Object> datos = new LinkedHashMap<>();
                    datos.put("nombre_completo", companyNameField.getText());
                    datos.put("DNI", dniField.getText());
                    datos.put("telefono", phoneField.getText());
                    datos.put("sector", sectorField.getText());
                    String whereStAt = "EMAIL= '" + ea + "'";

                    if (en.encriptar(passactu.getText()).equals(db.selectFromTable("USERSACCS",new String[]{"CONTRASENA"},new String[]{"EMAIL='"+ea+"'"})[0])){
                        datos.put("CONTRASENA", en.encriptar(newpass.getText()));
                        try {
                            db.update("USERSACCS", whereStAt, datos);
                            JOptionPane.showMessageDialog(editarPerfil.this, "Perfil Actualizado","Perfil Actualizado",JOptionPane.INFORMATION_MESSAGE);
                        }catch (Exception x){
                            JOptionPane.showMessageDialog(editarPerfil.this, x.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                        }

                    } else if (en.encriptar(passactu.getText()).equals("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855")) {
                        try {
                            db.update("USERSACCS", whereStAt, datos);
                            JOptionPane.showMessageDialog(editarPerfil.this, "Perfil Actualizado","Perfil Actualizado",JOptionPane.INFORMATION_MESSAGE);
                        }catch (Exception x){
                            JOptionPane.showMessageDialog(editarPerfil.this, x.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                        }

                    } else {
                        JOptionPane.showMessageDialog(editarPerfil.this, "La contraseña no es correcta","ERROR",JOptionPane.ERROR_MESSAGE);
                    }




                }
            });

            String name=db.selectFromTable("USERSACCS",new String[]{"nombre_completo"},new String[]{"EMAIL='"+ea+"'"})[0];
            String ema=db.selectFromTable("USERSACCS",new String[]{"email"},new String[]{"EMAIL='"+ea+"'"})[0];
            String DNI=db.selectFromTable("USERSACCS",new String[]{"DNI"},new String[]{"EMAIL='"+ea+"'"})[0];
            String tele=db.selectFromTable("USERSACCS",new String[]{"telefono"},new String[]{"EMAIL='"+ea+"'"})[0];
            String sector=db.selectFromTable("USERSACCS",new String[]{"sector"},new String[]{"EMAIL='"+ea+"'"})[0];
            companyNameField.setText(name);
            emailField.setText(ema);
            emailField.setEditable(false);
            dniField.setText(DNI);
            phoneField.setText(tele);
            sectorField.setText(sector);

            c.gridx = 0;
            c.gridy = 0;
            c.insets = new Insets(10, 10, 10, 10);
            panel.add(companyNameLabel, c);

            c.gridx = 1;
            c.gridy = 0;
            panel.add(companyNameField, c);

            c.gridx = 0;
            c.gridy = 1;
            panel.add(emailLabel, c);

            c.gridx = 1;
            c.gridy = 1;
            panel.add(emailField, c);

            c.gridx = 0;
            c.gridy = 2;
            panel.add(dniLabel, c);

            c.gridx = 1;
            c.gridy = 2;
            panel.add(dniField, c);

            c.gridx = 0;
            c.gridy = 3;
            panel.add(phoneLabel, c);

            c.gridx = 1;
            c.gridy = 3;
            panel.add(phoneField, c);

            c.gridx = 0;
            c.gridy = 4;
            panel.add(sectorLabel, c);

            c.gridx = 1;
            c.gridy = 4;
            panel.add(sectorField, c);

            c.gridx = 0;
            c.gridy = 5;
            panel.add(passactulabel, c);

            c.gridx = 1;
            c.gridy = 5;
            panel.add(passactu, c);

            c.gridx = 0;
            c.gridy = 6;
            panel.add(newpasslabel, c);

            c.gridx = 1;
            c.gridy = 6;
            panel.add(newpass, c);

            c.gridx = 0;
            c.gridy =8;
            c.gridwidth = 3;
            c.fill = GridBagConstraints.CENTER;
            panel.add(updateButton, c);
            setVisible(true);

            // Add the panel to the frame
            add(panel);
        } else if (tipouser.equals("Persona")) {
            nameLabel = new JLabel("<html><h3>Nombre completo:</h3></html>");
            emailLabel2= new JLabel("<html><h3>Email:</h3></html>");
            fechaNacimientoLabel = new JLabel("<html><h3>Sector de Trabajo:</h3></html>");
            dniLabel2 = new JLabel("<html><h3>DNI:</h3></html>");
            passactulabel=new JLabel("<html><h3>Contraseña Actual:</h3></html>");
            newpasslabel=new JLabel("<html><h3>Nueva Contraseña:</h3></html>");

            //Create the text fields
            nameField = new JTextField(20);
            dniField2 = new JTextField(20);
            fechaNacimientoField = new JTextField(20);
            emailField2=new JTextField(20);
            passactu = new JPasswordField(20);
            newpass=new JPasswordField(20);

            updateButton = new JButton("<html><h2>Actualizar</h2></html>");
            updateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Map<String, Object> datos = new LinkedHashMap<>();
                    datos.put("nombre_completo", nameField.getText());
                    datos.put("DNI", dniField2.getText());
                    datos.put("fecha_nacimiento", fechaNacimientoField.getText());
                    //datos.put("sector", sectorField.getText());
                    encription en =new encription();

                    String whereStAt = "EMAIL= '" + ea + "'";
                    if (en.encriptar(passactu.getText()).equals(db.selectFromTable("USERSACCS",new String[]{"CONTRASENA"},new String[]{"EMAIL='"+ea+"'"})[0])){
                        datos.put("CONTRASENA", en.encriptar(newpass.getText()));
                        try {
                            db.update("USERSACCS", whereStAt, datos);
                            JOptionPane.showMessageDialog(editarPerfil.this, "Perfil Actualizado","Perfil Actualizado",JOptionPane.INFORMATION_MESSAGE);
                        }catch (Exception x){
                            JOptionPane.showMessageDialog(editarPerfil.this, x.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                        }

                    }
                    else if (en.encriptar(passactu.getText()).equals("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855")) {
                        try {
                            db.update("USERSACCS", whereStAt, datos);
                            JOptionPane.showMessageDialog(editarPerfil.this, "Perfil Actualizado","Perfil Actualizado",JOptionPane.INFORMATION_MESSAGE);
                        }catch (Exception x){
                            JOptionPane.showMessageDialog(editarPerfil.this, x.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                        }

                    } else {
                        JOptionPane.showMessageDialog(editarPerfil.this, "La contraseña no es correcta","ERROR",JOptionPane.ERROR_MESSAGE);
                    }



                }
            });

            String name=db.selectFromTable("USERSACCS",new String[]{"nombre_completo"},new String[]{"EMAIL='"+ea+"'"})[0];
            String email=db.selectFromTable("USERSACCS",new String[]{"email"},new String[]{"EMAIL='"+ea+"'"})[0];
            String DNI=db.selectFromTable("USERSACCS",new String[]{"DNI"},new String[]{"EMAIL='"+ea+"'"})[0];
            String fechaNacimiento=db.selectFromTable("USERSACCS",new String[]{"fecha_nacimiento"},new String[]{"EMAIL='"+ea+"'"})[0];
            nameField.setText(name);
            emailField2.setText(email);
            emailField2.setEditable(false);
            dniField2.setText(DNI);
            fechaNacimientoField.setText(fechaNacimiento);

            c.gridx = 0;
            c.gridy = 0;
            c.insets = new Insets(10, 10, 10, 10);
            panel.add(nameLabel, c);

            c.gridx = 1;
            c.gridy = 0;
            panel.add(nameField, c);

            c.gridx = 0;
            c.gridy = 2;
            panel.add(dniLabel2, c);

            c.gridx = 1;
            c.gridy = 2;
            panel.add(dniField2, c);

            c.gridx = 0;
            c.gridy = 3;
            panel.add(fechaNacimientoLabel, c);

            c.gridx = 1;
            c.gridy = 3;
            panel.add(fechaNacimientoField, c);


            c.gridx = 0;
            c.gridy = 5;
            panel.add(emailLabel2, c);

            c.gridx = 1;
            c.gridy = 5;
            panel.add(emailField2, c);

            c.gridx = 0;
            c.gridy = 6;
            panel.add(passactulabel, c);

            c.gridx = 1;
            c.gridy = 6;
            panel.add(passactu, c);

            c.gridx = 0;
            c.gridy = 7;
            panel.add(newpasslabel, c);

            c.gridx = 1;
            c.gridy = 7;
            panel.add(newpass, c);

            c.gridx = 0;
            c.gridy =8;
            c.gridwidth = 2;
            c.fill = GridBagConstraints.CENTER;
            panel.add(updateButton, c);

            // Set the frame visible
            setVisible(true);

            // Add the panel to the frame
            add(panel);
        }

        try {
            //System.out.println(path);
            CircleImagePanel imagePanel = new CircleImagePanel(new File(path));
            imagePanel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    test();

                    CircleImagePanel imagePanel = null;
                    try {

                        imagePanel = new CircleImagePanel(new File(path));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    add(imagePanel,BorderLayout.NORTH);

                    imagePanel.repaint();
                    imagePanel.revalidate();
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });


            // add the panel to the window
            add(imagePanel,BorderLayout.NORTH);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(editarPerfil.this,ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }


        // Set the frame to be centered on the screen
        setLocationRelativeTo(null);
    }
    public void test(){
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(editarPerfil.this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                Image image = ImageIO.read(file);
                // save the image to a file
                File outputFile = new File("src\\Windows\\images\\PerfilFotos\\"+ea+".png");
                ImageIO.write((BufferedImage) image, "png", outputFile);
                // return the file path of the output file
                String outputFilePath = outputFile.getAbsolutePath();
                File pathcutting = new File(outputFilePath);
                String basePath = new File("").getAbsolutePath();
                String relativePath = pathcutting.getPath().replace(basePath, "");


                path= relativePath.substring(1,relativePath.length());
                GestorDB db =new GestorDB();
                Map<String,Object> datos=new LinkedHashMap<>();
                datos.put("IMG", path);
                db.update("USERSACCS", "EMAIL='" + ea + "'", datos);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(editarPerfil.this,ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);

            }
        }
    }




}


