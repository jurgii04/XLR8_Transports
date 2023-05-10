package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static Windows.login.ea;
import static Windows.ventana.*;

import Dbconnection.GestorDB;
import com.formdev.flatlaf.FlatLightLaf;

public class ContactarConNos extends JFrame {

    private JLabel companyNameLabel,emailLabel, dniLabel, phoneLabel, sectorLabel;
    private JTextField companyNameField ,emailField, dniField, phoneField, sectorField;

    private JButton createButton;

    public ContactarConNos() {
        FlatLightLaf.install();
        GestorDB db = new GestorDB();

        // Set the title and size of the frame
        setTitle("Crear cuenta de empresa");
        setSize(550, 500);

        // Create the labels
        companyNameLabel = new JLabel("<html><h3>Nombre completo de la empresa:</h3></html>");

        dniLabel = new JLabel("<html><h3>DNI:</h3></html>");
        phoneLabel = new JLabel("<html><h3>Número de teléfono:</h3></html>");
        sectorLabel = new JLabel("<html><h3>Sector de Trabajo:</h3></html>");
        emailLabel= new JLabel("<html><h3>Email:</h3></html>");

        // Create the text fields
        companyNameField = new JTextField(20);

        dniField = new JTextField(20);
        phoneField = new JTextField(20);
        sectorField = new JTextField(20);
        emailField=new JTextField(20);

        // Create the button
        createButton = new JButton("<html><h2>Enviar.</h2></html>");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Gracias! Os contactaremos lo antes posible");
                try {
                    LocalDate currentDate = LocalDate.now();


                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


                    String formattedDate = currentDate.format(formatter);

                    BufferedWriter writer = new BufferedWriter(new FileWriter("src\\Windows\\TextsFiles\\DatosEmpresas.txt",true));
                    writer.write("Empresa : " + companyNameField.getText());
                    writer.newLine();
                    writer.write("Email: "+ emailField.getText());
                    writer.newLine();
                    writer.write("Dni: "+ dniField.getText());
                    writer.newLine();
                    writer.write("Telefono: "+ phoneField.getText());
                    writer.newLine();
                    writer.write("Sector: "+ sectorField.getText());
                    writer.newLine();
                    writer.write("FICHA: "+ formattedDate);
                    writer.newLine();
                    writer.write("==========================================================================================================");
                    writer.newLine();
                    writer.close();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        if (tipouser.equals("Empresa")){

            String name=db.selectFromTable("USERSACCS",new String[]{"nombre_completo"},new String[]{"EMAIL='"+ea+"'"})[0];
            String ema=db.selectFromTable("USERSACCS",new String[]{"email"},new String[]{"EMAIL='"+ea+"'"})[0];
            String DNI=db.selectFromTable("USERSACCS",new String[]{"DNI"},new String[]{"EMAIL='"+ea+"'"})[0];
            String tele=db.selectFromTable("USERSACCS",new String[]{"telefono"},new String[]{"EMAIL='"+ea+"'"})[0];
            String sector=db.selectFromTable("USERSACCS",new String[]{"sector"},new String[]{"EMAIL='"+ea+"'"})[0];
            companyNameField.setText(name);
            emailField.setText(ema);
            dniField.setText(DNI);
            phoneField.setText(tele);
            sectorField.setText(sector);

        }

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
        panel.add(createButton, c);

        // Add the panel to the frame
        add(panel);

        // Set the frame visible
        setVisible(true);

        // Set the default close operation




        // Set the frame to be centered on the screen
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        ContactarConNos c =new ContactarConNos();
    }




}


