package Windows;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

import static Windows.Buses.bill;
import static Windows.Login.*;
import static Windows.ModificarBilletes.optionDest;
import static Windows.ModificarBilletes.optionOrg;

import Dbconnection.GestorDB;
import com.formdev.flatlaf.FlatLightLaf;

public class PayWindow extends JFrame {
    public PayWindow() {// Set the title of the window
        FlatLightLaf.install();
        setTitle("Pagar ticket");

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
        JLabel cardNumberLabel = new JLabel("Número de la tarjeta:");
        cardNumberLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        MaskFormatter dateFormatter = null;

            try {
                dateFormatter = new MaskFormatter("     ####     -     ####     -     ####     -     #### ");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

        JFormattedTextField cardNumberField = new JFormattedTextField(dateFormatter);
        cardNumberField.setColumns(15);


        JLabel nameOnCardLabel = new JLabel("Titular de la tarjeta:");
        nameOnCardLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField nameOnCardField = new JTextField(20);
        nameOnCardField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        nameOnCardField.setText(name);
        JLabel expirationLabel = new JLabel("Fecha de caducidad (MM/YY):");
        expirationLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        MaskFormatter dateFormatter2 = null;

        try {
            dateFormatter2 = new MaskFormatter("    ## / ## ");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        JFormattedTextField expirationField = new JFormattedTextField(dateFormatter2);
        expirationField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        expirationField.setColumns(6);
        JLabel securityLabel = new JLabel("CVV:");
        securityLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        MaskFormatter dateFormatter3 = null;

        try {
            dateFormatter3 = new MaskFormatter("   ###      ");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        JFormattedTextField securityField = new JFormattedTextField(dateFormatter3);
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
        JButton payButton = new JButton("Pagar");
        payButton.setFont(new Font("Arial", Font.PLAIN, 16));
        payButton.setForeground(Color.black);
        payButton.setBackground(new Color(238, 238, 238));
        payButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        payButton.setFocusPainted(false);
        payButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        FlatLightLaf.install();

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
                if ((cardNumberField.getText().isEmpty() || cardNumberField.getText()==null) || (nameOnCardField.getText().isEmpty() || nameOnCardField.getText()==null)|| (securityField.getText().isEmpty() || securityField.getText()==null)|| (expirationField.getText().isEmpty() || expirationField.getText()==null)){
                    JOptionPane.showMessageDialog(null, "No puede haber campos vacíos","ERROR",JOptionPane.ERROR_MESSAGE);
                }else {

                    try {
                        GestorDB db =new GestorDB();

                        Map<String,Object> data=new LinkedHashMap<>();
                        Random rand = new Random();
                        int randomNum = rand.nextInt(01) + 60;
                        //calculate the age
                        LocalDate dateOfBirth = LocalDate.parse(user.getFECHA_NACIMIENTO(),
                                java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                        // Calculate the age based on the current date and add the cliente to db
                        LocalDate currentDate = LocalDate.now();
                        Period period = Period.between(dateOfBirth, currentDate);
                        int age = period.getYears();
                        data.put("DNI",user.getDNI());
                        data.put("NOMBRE",user.getNombre_completo());
                        data.put("EDAD",age);
                        data.put("NUM_DE_ASIENTO",randomNum);
                        int exist=Integer.parseInt(db.selectFromTable("CLIENTES",new String[]{"count(DNI)"},new String[]{"DNI='"+user.getDNI()+"'"})[0]);
                        if (exist==0){
                            db.insert("CLIENTES",data);

                        }

                        //insert the billete in db
                        SimpleDateFormat formatchnger = null;
                        Date myDate = null;
                        String formattedDate=null;
                        try {
                            formatchnger = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                            myDate = new SimpleDateFormat("yyyy-MM-dd").parse("2023-09-09");
                            formattedDate = formatchnger.format(myDate);
                        } catch (ParseException f) {
                            throw new RuntimeException(f);
                        }
                        String num_viage=db.selectFromTable("VIAJES" , new String[]{"NUM_VIAJE"},new String[]{"DESTINO='"+optionDest+"'","ORIGEN='"+optionOrg+"'"})[0];
                        Map<String,Object> databill=new LinkedHashMap<>();
                        databill.put("NUM_BILLETE",bill.getNum_billete());
                        databill.put("FECHA",formattedDate);
                        databill.put("PRECIO",bill.getPrecio());
                        databill.put("TIPO_PAGO",bill.getTipo_pago());
                        databill.put("DESTINO",bill.getDestino());
                        databill.put("ORIGEN",bill.getOrigen());
                        databill.put("DNI",bill.getDNI());
                        databill.put("NUM_VIAJE",Integer.parseInt(num_viage));

                        db.insert("BILLETES",databill);
                        Gmail g=new Gmail(ea,"src\\Windows\\images\\tickets.jpg");
                        JOptionPane.showMessageDialog(null, "Transacción completada exitosamente! El ticket ha sido enviado a tu email.","Transacción completada",JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } catch (Exception ex) {
                        //JOptionPane.showMessageDialog(null, ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();

                    }
                }

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
