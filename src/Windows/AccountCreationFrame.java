package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.formdev.flatlaf.FlatLightLaf;

public class AccountCreationFrame extends JFrame {


        private JLabel nameLabel, lastNameLabel,password, emailLabel, dobLabel, dniLabel;
        private JTextField nameField, lastNameField, emailField, dobField, dniField;
        private JPasswordField passwordf;
        private JRadioButton maleRadioButton, femaleRadioButton;
        private JButton createButton;

    public AccountCreationFrame() {
            FlatLightLaf.install();
            // Set the title and size of the frame
            setTitle("Crear una cuenta");
            setSize(400, 350);

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
            maleRadioButton = new JRadioButton("Male");
            femaleRadioButton = new JRadioButton("Female");
            maleRadioButton.setSelected(true); // Select the male radio button by default
            ButtonGroup genderGroup = new ButtonGroup();
            genderGroup.add(maleRadioButton);
            genderGroup.add(femaleRadioButton);

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
            panel.add(new JLabel("Gender:"), c);
            c.gridx = 1;
            JPanel genderPanel = new JPanel();
            genderPanel.add(maleRadioButton);
            genderPanel.add(femaleRadioButton);
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
                    // TODO: Implement account creation logic here
                }
            });
        }




}

