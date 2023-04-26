package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.formdev.flatlaf.FlatLightLaf;

public class ContactarConNos extends JFrame {

    private JLabel companyNameLabel,emailLabel, dniLabel, phoneLabel, sectorLabel;
    private JTextField companyNameField ,emailField, dniField, phoneField, sectorField;

    private JButton createButton;

    public ContactarConNos() {
        FlatLightLaf.install();

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
            }
        });

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


        // Add action listener to the create button
        createButton.addActionListener(e -> {
            // TODO: Implement account creation logic here
        });

        // Set the frame to be centered on the screen
        setLocationRelativeTo(null);
    }


}


