package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PayWindow extends JFrame {
    public PayWindow() {// Set the title of the window
        setTitle("Pay for Ticket");

        // Set a beautiful theme for the window
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create a panel to hold the payment form
        JPanel paymentPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        paymentPanel.setBackground(Color.WHITE);
        paymentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add the payment form components to the panel
        JLabel cardNumberLabel = new JLabel("Card Number:");
        cardNumberLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField cardNumberField = new JTextField(20);
        cardNumberField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JLabel nameOnCardLabel = new JLabel("Name on Card:");
        nameOnCardLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField nameOnCardField = new JTextField(20);
        nameOnCardField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JLabel expirationLabel = new JLabel("Expiration Date (MM/YY):");
        expirationLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField expirationField = new JTextField(6);
        expirationField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JLabel securityLabel = new JLabel("Security Code:");
        securityLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField securityField = new JTextField(3);
        securityField.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JPanel expirationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        expirationPanel.add(expirationLabel);
        expirationPanel.add(Box.createHorizontalStrut(5));
        expirationPanel.add(expirationField);
        JPanel securityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        securityPanel.add(securityLabel);
        securityPanel.add(Box.createHorizontalStrut(5));
        securityPanel.add(securityField);
        //-----
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        JButton payButton = new JButton("pay");
        payButton.setFont(new Font("Arial", Font.PLAIN, 16));
        payButton.setForeground(Color.black);
        payButton.setBackground(new Color(238, 238, 238));
        payButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        payButton.setFocusPainted(false);
        payButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Add hover effect
        payButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(Color.BLACK);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(new Color(5, 253, 9));
            }
        });
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel successLabel = new JLabel("Transaction completed successfully!");
                ImageIcon checkIcon = new ImageIcon("src\\Windows\\images\\deal.png");
                Image img = checkIcon.getImage();
                Image resizedImg = img.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
                ImageIcon resizedIcon = new ImageIcon(resizedImg);
                JLabel iconLabel = new JLabel(resizedIcon);
                JPanel successPanel = new JPanel();
                successPanel.add(iconLabel);
                successPanel.add(successLabel);
                JFrame successFrame = new JFrame("Transaction Successful");
                successFrame.add(successPanel);
                successFrame.setSize(300, 100);
                successFrame.setLocationRelativeTo(null);
                successFrame.setVisible(true);
            }
        });
        buttonPanel.add(payButton);

        paymentPanel.add(cardNumberLabel);
        paymentPanel.add(cardNumberField);
        paymentPanel.add(nameOnCardLabel);
        paymentPanel.add(nameOnCardField);
        paymentPanel.add(expirationPanel);
        paymentPanel.add(securityPanel);


        // Add the panel to the window
        add(paymentPanel);
        add(buttonPanel, BorderLayout.SOUTH);
        // Set the size of the window
        setSize(400, 300);

        // Center the window on the screen
        setLocationRelativeTo(null);

        // Make the window visible
        setVisible(true);

        pack();
    }




}