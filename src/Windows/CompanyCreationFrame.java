package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Dbconnection.GestorDB;
import com.formdev.flatlaf.FlatLightLaf;
import org.junit.Test;

import static Windows.EditarPerfil.path;
import static org.junit.Assert.assertTrue;

public class CompanyCreationFrame extends JFrame {

    private JLabel companyNameLabel, addressLabel,password,emailLabel, dniLabel, phoneLabel, sectorLabel;
    public JTextField companyNameField;
    public JTextField addressField;
    public JTextField emailField;
    public JTextField dniField;
    public JTextField phoneField;
    public JTextField sectorField;
    public JPasswordField passwordF;
    public JButton createButton;

    public CompanyCreationFrame(JFrame type) {
        FlatLightLaf.install();
        GestorDB db=new GestorDB();

        // Set the title and size of the frame
        setTitle("Crear cuenta de empresa");
        setSize(550, 650);

        // Create the labels
        companyNameLabel = new JLabel("<html><h3>Nombre completo de la empresa:</h3></html>");
        addressLabel = new JLabel("<html><h3>DIRECCIÓN:</h3></html>");
        password= new JLabel("<html><h3>Contraseña:</h3></html>");
        dniLabel = new JLabel("<html><h3>DNI:</h3></html>");
        phoneLabel = new JLabel("<html><h3>Número de teléfono:</h3></html>");
        sectorLabel = new JLabel("<html><h3>Sector de Trabajo:</h3></html>");
        emailLabel= new JLabel("<html><h3>Email:</h3></html>");

        // Create the text fields
        companyNameField = new JTextField(20);
        addressField = new JTextField(20);
        passwordF=new JPasswordField(20);
        dniField = new JTextField(20);
        phoneField = new JTextField(20);
        sectorField = new JTextField(20);
        emailField=new JTextField(20);

        // Create the button
        createButton = new JButton("<html><h2>Create</h2></html>");

        // Create a panel and add the components to it
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 10, 10);
        panel.add(companyNameLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        panel.add(companyNameField, c);

        c.gridx = 0;
        c.gridy = 1;
        panel.add(addressLabel, c);

        c.gridx = 1;
        c.gridy = 1;
        panel.add(addressField, c);

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
        panel.add(emailLabel, c);

        c.gridx = 1;
        c.gridy = 5;
        panel.add(emailField, c);

        c.gridx = 0;
        c.gridy = 6;
        panel.add(password, c);

        c.gridx = 1;
        c.gridy = 6;
        panel.add(passwordF, c);

        c.gridx = 0;
        c.gridy =7;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.CENTER;
        panel.add(createButton, c);

        // Add the panel to the frame
        add(panel);

        // Set the frame visible
        setVisible(true);

        // Set the default close operation


        // Add action listener to the create button
        createButton.addActionListener(e -> {
            //passwordF,companyNameField, addressField,emailField, dniField, phoneField, sectorField;
            Encription enc=new Encription();
            String name=companyNameField.getText();
            String address=addressField.getText();
            String email=emailField.getText();
            String dni=dniField.getText();
            String phone=phoneField.getText();
            String sector=sectorField.getText();
            String password=enc.encriptar(passwordF.getText());
            String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            Pattern pattern = Pattern.compile(emailPattern);

            Matcher matcher = pattern.matcher(email);

            if (matcher.matches()) {
                // La dirección de correo es válida
                Map<String,Object> datos=new LinkedHashMap<>();
                datos.put("nombre_completo",name);
                datos.put("tipo_user","Empresa");
                datos.put("email",email);
                datos.put("fecha_nacimiento","");
                datos.put("DNI",dni);
                datos.put("genero","");
                datos.put("direccion",address);
                datos.put("telefono",phone);
                datos.put("sector",sector);
                datos.put("contrasena",password);
                datos.put("IMG",path);
                try {
                    db.insert("USERSACCS",datos);
                    JOptionPane.showMessageDialog(CompanyCreationFrame.this,"Cuenta creada exitosamente");
                    type.dispose();
                    dispose();
                }
                catch (Exception x){
                    JOptionPane.showMessageDialog(CompanyCreationFrame.this, x.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);

                }
            } else {
                JOptionPane.showMessageDialog(CompanyCreationFrame.this,"Dirección de correo invalida","ERROR",JOptionPane.ERROR_MESSAGE);
            }


        });

        // Set the frame to be centered on the screen
        setLocationRelativeTo(null);
    }



}
