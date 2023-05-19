package Windows;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

import Dbconnection.GestorDB;
import com.formdev.flatlaf.FlatLightLaf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Windows.EditarPerfil.path;

public class AccountCreationFrame extends JFrame {


        public JLabel nameLabel, lastNameLabel,password, emailLabel, dobLabel, dniLabel;
        public JTextField nameField, lastNameField, emailField, dniField;
        public JFormattedTextField dobField;

        public JPasswordField passwordf;
        public JRadioButton maleRadioButton, femaleRadioButton, otroRadioButton;
        public JButton createButton;
        public Encription encry;
        GestorDB db;

    public AccountCreationFrame(JFrame type) {
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
            MaskFormatter dateFormatter = null;
            try {
                    dateFormatter = new MaskFormatter("##-##-####");
            } catch (ParseException e) {
                    throw new RuntimeException(e);
            }
             dobField = new JFormattedTextField(dateFormatter);
            dobField.setColumns(10);
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formattedDate = currentDate.format(formatter);
            dobField.setValue(formattedDate);
            dniField = new JTextField(20);

            // Create the radio buttons
            maleRadioButton = new JRadioButton("Masculino");
            femaleRadioButton = new JRadioButton("Femenino");
            otroRadioButton = new JRadioButton("Otro");
            ButtonGroup genderGroup = new ButtonGroup();
            genderGroup.add(maleRadioButton);
            genderGroup.add(femaleRadioButton);
            genderGroup.add(otroRadioButton);

            // Create the button
            createButton = new JButton("Crear cuenta");

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
            createButton.addActionListener(new ActionListener() {;
                @Override
                public void actionPerformed(ActionEvent e) {
                        //nameField, lastNameField, emailField, dobField, dniField;
                        encry=new Encription();
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


                        String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
                        Pattern pattern = Pattern.compile(emailPattern);

                        Matcher matcher = pattern.matcher(email);

                        if (matcher.matches()) {
                                // La dirección de correo es válida
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
                                datos.put("IMG",path);
                                try {
                                        db.insert("USERSACCS",datos);
                                        JOptionPane.showMessageDialog(AccountCreationFrame.this,"Cuenta creada exitosamente");
                                        type.dispose();
                                        dispose();
                                }
                                catch (Exception x){
                                        JOptionPane.showMessageDialog(AccountCreationFrame.this, x.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);

                                }
                        } else {
                                JOptionPane.showMessageDialog(AccountCreationFrame.this,"Dirección de correo invalida","ERROR",JOptionPane.ERROR_MESSAGE);
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

