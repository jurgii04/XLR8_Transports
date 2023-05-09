package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedHashMap;
import java.util.Map;

import Dbconnection.GestorDB;
import com.formdev.flatlaf.FlatLightLaf;

public class AccountCreationFrame extends JFrame {


        private JLabel nameLabel, lastNameLabel,password, emailLabel, dobLabel, dniLabel;
        private JTextField nameField, lastNameField, emailField, dobField, dniField;
        private JPasswordField passwordf;
        private JRadioButton maleRadioButton, femaleRadioButton, otroRadioButton;
        private JButton createButton;
        private encription encry;
        GestorDB db;

    public AccountCreationFrame() {
            FlatLightLaf.install();
            // Set the title and size of the frame
            setTitle("Crear una cuenta");
            setSize(400, 350);
            db=new GestorDB();

            // Create the labels
            nameLabel = new JLabel("Nombre:");
            lastNameLabel = new JLabel("Apellidos:");
            password= new JLabel("Contraseña:");
            emailLabel = new JLabel("Email:");
            dobLabel = new JLabel("Fecha De Nacimiento:");
            dniLabel = new JLabel("DNI:");

            // Create the text fields
            nameField = new JTextField(20);
            lastNameField = new JTextField(20);
            passwordf= new JPasswordField(20);
            emailField = new JTextField(20);
            dobField = new JTextField(20);
            dniField = new JTextField(20);

            // Create the radio buttons
            maleRadioButton = new JRadioButton("Masculino");
            femaleRadioButton = new JRadioButton("Femenino");
            otroRadioButton = new JRadioButton("Otro");
            maleRadioButton.setSelected(true); // Select the male radio button by default
            ButtonGroup genderGroup = new ButtonGroup();
            genderGroup.add(maleRadioButton);
            genderGroup.add(femaleRadioButton);
            genderGroup.add(otroRadioButton);

            // Create the button
            createButton = new JButton("Create");

            // Create a panel and add the components to it
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.anchor = GridBagConstraints.LINE_START;

            c.insets = new Insets(5, 5, 5, 5);
            panel.add(nameLabel, c);
            c.gridx = 1;
            panel.add(nameField, c);
            c.gridx = 0;
            c.gridy = 1;
            panel.add(lastNameLabel, c);
            c.gridx = 1;
            panel.add(lastNameField, c);
            c.gridx = 0;
            c.gridy = 2;
            panel.add(password, c);
            c.gridx = 1;
            panel.add(passwordf, c);
            c.gridx = 0;
            c.gridy = 3;
            panel.add(emailLabel, c);
            c.gridx = 1;
            panel.add(emailField, c);
            c.gridx = 0;
            c.gridy = 4;
            panel.add(dobLabel, c);
            c.gridx = 1;
            panel.add(dobField, c);
            c.gridx = 0;
            c.gridy = 5;
            panel.add(dniLabel, c);
            c.gridx = 1;
            panel.add(dniField, c);
            c.gridx = 0;
            c.gridy = 6;
            panel.add(new JLabel("Genero:"), c);
            c.gridx = 1;
            JPanel genderPanel = new JPanel();
            genderPanel.add(maleRadioButton);
            genderPanel.add(femaleRadioButton);
            genderPanel.add(otroRadioButton);
            panel.add(genderPanel, c);
            c.gridx = 1;
            c.gridy = 7;
            panel.add(createButton, c);

            // Add the panel to the frame
            add(panel);
        setLocationRelativeTo(null);

        // Set the frame visible
            setVisible(true);

            // Set the default close operation


            // Add action listener to the create button
            createButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        //nameField, lastNameField, emailField, dobField, dniField;
                        encry=new encription();
                        String nombre=nameField.getText();
                        String apellido=lastNameField.getText();
                        String email=emailField.getText();
                        String fecha_nac=dobField.getText();
                        String dni=dniField.getText();
                        String password=encry.encriptar(passwordf.getText());
                        String gender="";
                        //maleRadioButton, femaleRadioButton, otroRadioButton;
                        if(maleRadioButton.isSelected()){
                                gender="Male";
                        } else if (femaleRadioButton.isSelected()) {
                                gender="Female";
                        }
                        else {gender="otro";}

                        Map<String,Object> datos=new LinkedHashMap<>();
                        datos.put("nombre_completo",nombre+" "+apellido);
                        datos.put("tipo_user","Persona");
                        datos.put("email",email);
                        datos.put("fecha_nacimiento",fecha_nac);
                        datos.put("DNI",dni);
                        datos.put("genero",gender);
                        datos.put("direccion","");
                        datos.put("telefono","");
                        datos.put("sector","");
                        datos.put("contrasena",password);
                        try {
                                db.insert("USERSACCS",datos);
                                JOptionPane.showMessageDialog(AccountCreationFrame.this,"Cuenta creada!");
                        }
                        catch (Exception x){
                                JOptionPane.showMessageDialog(AccountCreationFrame.this, x.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);

                        }

                        /*nombre_completo VARCHAR(255) PRIMARY KEY,
                          email VARCHAR(255),
                          fecha_nacimiento VARCHAR(255),
                          DNI VARCHAR(255),
                          genero VARCHAR(255),
                          contrasena VARCHAR(500),
                          direccion VARCHAR(255),
                          telefono VARCHAR(255),
                          sector VARCHAR(255),
                          tipo_user VARCHAR(255)*/



                        /*System.out.println(nombre);
                        System.out.println(apellido);
                        System.out.println(email);
                        System.out.println(fecha_nac);
                        System.out.println(dni);
                        System.out.println(password);
                        System.out.println(gender);*/



                }
            });
        }




}

