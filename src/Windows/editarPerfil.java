package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

import static Windows.login.ea;
import static Windows.ventana.tipouser;

import Dbconnection.GestorDB;
import com.formdev.flatlaf.FlatLightLaf;

public class editarPerfil extends JFrame {

    //Empresa
    private JLabel companyNameLabel,emailLabel, dniLabel, phoneLabel, sectorLabel;
    private JTextField companyNameField ,emailField, dniField, phoneField, sectorField;

    //Persona
    private JLabel nameLabel, emailLabel2, fechaNacimientoLabel, dniLabel2;
    private JTextField nameField, emailField2, fechaNacimientoField, dniField2;
    private JRadioButton maleRadioButton, femaleRadioButton, otroRadioButton;

    private JButton updateButton;

    public editarPerfil() {
        FlatLightLaf.install();
        GestorDB db = new GestorDB();

        // Set the title and size of the frame
        setTitle("Editar perfil");
        setSize(550, 500);

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

            //Create the text fields
            companyNameField = new JTextField(20);
            dniField = new JTextField(20);
            phoneField = new JTextField(20);
            sectorField = new JTextField(20);
            emailField=new JTextField(20);

            //Create the button
            updateButton = new JButton("<html><h2>Actualizar</h2></html>");

            updateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Map<String, Object> datos = new LinkedHashMap<>();
                    datos.put("nombre_completo", companyNameField.getText());
                    datos.put("DNI", dniField.getText());
                    datos.put("telefono", phoneField.getText());
                    datos.put("sector", sectorField.getText());

                    String whereStAt = "EMAIL= '" + ea + "'";

                    db.update("USERSACCS", whereStAt, datos);
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

            c.gridx = 1;
            c.gridy = 1;

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

            c.gridx = 1;
            c.gridy = 6;

            c.gridx = 0;
            c.gridy =7;
            c.gridwidth = 2;
            c.fill = GridBagConstraints.CENTER;
            panel.add(updateButton, c);

            // Add the panel to the frame
            add(panel);
        } else if (tipouser.equals("Persona")) {
            nameLabel = new JLabel("<html><h3>Nombre completo:</h3></html>");
            emailLabel2= new JLabel("<html><h3>Email:</h3></html>");
            fechaNacimientoLabel = new JLabel("<html><h3>Sector de Trabajo:</h3></html>");
            dniLabel2 = new JLabel("<html><h3>DNI:</h3></html>");

            //Create the text fields
            nameField = new JTextField(20);
            dniField2 = new JTextField(20);
            fechaNacimientoField = new JTextField(20);
            emailField2=new JTextField(20);

            updateButton = new JButton("<html><h2>Actualizar</h2></html>");
            updateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Map<String, Object> datos = new LinkedHashMap<>();
                    datos.put("nombre_completo", nameField.getText());
                    datos.put("DNI", dniField2.getText());
                    datos.put("fecha_nacimiento", fechaNacimientoField.getText());
                    //datos.put("sector", sectorField.getText());

                    String whereStAt = "EMAIL= '" + ea + "'";

                    db.update("USERSACCS", whereStAt, datos);
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

            c.gridx = 1;
            c.gridy = 6;

            c.gridx = 0;
            c.gridy =7;
            c.gridwidth = 2;
            c.fill = GridBagConstraints.CENTER;
            panel.add(updateButton, c);

            // Set the frame visible
            setVisible(true);

            // Add the panel to the frame
            add(panel);
        }





        // Set the frame to be centered on the screen
        setLocationRelativeTo(null);
    }




}


