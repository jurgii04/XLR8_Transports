package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.formdev.flatlaf.FlatLightLaf;

public class ChooseTypeFrame extends JFrame {

    public JButton personaButton;
    public JButton empresaButton;

    public ChooseTypeFrame() {
        // Set the Look and Feel
        FlatLightLaf.install();

        // Set the title and size of the frame
        setTitle("Elija Tipo");
        setSize(300, 150);

        // Create the labels
        JLabel questionLabel = new JLabel("<html><h2 style='text-align:center'>¿Eres <b>cliente</b> o <b>empresa</b>?</h2></html>");
        questionLabel.setHorizontalAlignment(JLabel.CENTER);

        // Create the buttons
        personaButton = new JButton("<html><h3>Persona</h3></html>");
        empresaButton = new JButton("<html><h3>Empresa</h3></html>");
        personaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountCreationFrame a =new AccountCreationFrame(ChooseTypeFrame.this);
            }
        });
        empresaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CompanyCreationFrame c =new CompanyCreationFrame(ChooseTypeFrame.this);
            }
        });

        // Create a panel and add the components to it
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        northPanel.add(questionLabel, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPanel.add(personaButton);
        centerPanel.add(empresaButton);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(northPanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);

        // Add the panel to the frame
        add(panel);

        // Set the frame in the center of the screen
        setLocationRelativeTo(null);

        // Set the frame visible
        setVisible(true);

        // Set the default close operation

    }


}


