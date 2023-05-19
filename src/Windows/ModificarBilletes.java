package Windows;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static Windows.Login.ea;
import static Windows.Ventana.*;

public class ModificarBilletes extends JFrame {
    static String  optionDest;
    static String  optionOrg;

    public JPanel panelCentro, panelBilletes;

    JScrollPane scrollPane;

    String [] num_billete;
    String [] origen = db.selectFromTableNoDistinct("Billetes", new String[]{"Origen"}, new String[]{"DNI IN (SELECT DNI FROM USERSACCS WHERE email = '" + ea + "') ORDER BY Num_Billete ASC"});
    String [] destino = db.selectFromTableNoDistinct("Billetes", new String[]{"Destino"}, new String[]{"DNI IN (SELECT DNI FROM USERSACCS WHERE email = '" + ea + "') ORDER BY Num_Billete ASC"});
    String [] precio = db.selectFromTableNoDistinct("Billetes", new String[]{"Precio"}, new String[]{"DNI IN (SELECT DNI FROM USERSACCS WHERE email = '" + ea + "') ORDER BY Num_Billete ASC"});
    String [] fecha = db.selectFromTableNoDistinct("Billetes", new String[]{"Fecha"}, new String[]{"DNI IN (SELECT DNI FROM USERSACCS WHERE email = '" + ea + "') ORDER BY Num_Billete ASC"});

    public ModificarBilletes(JPanel panelNorte) {

        num_billete = db.selectFromTableNoDistinct("Billetes", new String[]{"Num_Billete"}, new String[]{"DNI IN (SELECT DNI FROM USERSACCS WHERE email = '" + ea + "') ORDER BY Num_Billete ASC"});

        panelNorte.add(volverButton, BorderLayout.WEST);

        JLabel title = new JLabel("<html><span style='font-family: Calibri, sans-serif; font-size: 32px; color: #333333; font-weight: bold;'>Billetes comprados</span></html>");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);

        panelCentro = new JPanel();
        panelCentro.setLayout(new BorderLayout()); // Set BorderLayout for panelCentro

        panelBilletes = new JPanel();
        panelBilletes.setLayout(new BoxLayout(panelBilletes, BoxLayout.Y_AXIS)); // Use BoxLayout with vertical orientation for panelBilletes
        scrollPane = new JScrollPane(panelBilletes);
        scrollPane.setPreferredSize(new Dimension(600, 500));

        panelCentro.add(title, BorderLayout.NORTH);

        for (int i = 0; i < num_billete.length ; i++) {
            nuevoViaje(i);
        }

        panelCentro.add(scrollPane, BorderLayout.CENTER);

        frame.add(panelCentro, BorderLayout.CENTER);
        frame.add(panelNorte, BorderLayout.NORTH);

        pack();
    }

    public void nuevoViaje(int i) {

        num_billete = db.selectFromTableNoDistinct("Billetes", new String[]{"Num_Billete"}, new String[]{"DNI IN (SELECT DNI FROM USERSACCS WHERE email = '" + ea + "') ORDER BY Num_Billete ASC"});
        origen = db.selectFromTableNoDistinct("Billetes", new String[]{"Origen"}, new String[]{"DNI IN (SELECT DNI FROM USERSACCS WHERE email = '" + ea + "') ORDER BY Num_Billete ASC"});
        destino = db.selectFromTableNoDistinct("Billetes", new String[]{"Destino"}, new String[]{"DNI IN (SELECT DNI FROM USERSACCS WHERE email = '" + ea + "') ORDER BY Num_Billete ASC"});
        precio = db.selectFromTableNoDistinct("Billetes", new String[]{"Precio"}, new String[]{"DNI IN (SELECT DNI FROM USERSACCS WHERE email = '" + ea + "') ORDER BY Num_Billete ASC"});
        fecha = db.selectFromTableNoDistinct("Billetes", new String[]{"Fecha"}, new String[]{"DNI IN (SELECT DNI FROM USERSACCS WHERE email = '" + ea + "') ORDER BY Num_Billete ASC"});

        JPanel billete = new JPanel(new BorderLayout());
        billete.setMaximumSize(new Dimension(600, 150));
        billete.setMinimumSize(new Dimension(600, 150));
        billete.setBackground(Color.WHITE);
        JLabel labelI = new JLabel("<html><span style='font-size:16px; color:#333333;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + origen[i] + " <span style='color:#999999;'>==></span> " + destino[i] + " <span style='color:#999999;'> - Precio</span> <span style='color:#FF9900;'> "+ precio[i] + "€ </span></span><br><span style='font-size:14px; color:#666666;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;6:00 (12h)&nbsp;&nbsp;&nbsp;20:30&nbsp;&nbsp;&nbsp; " +fecha[i].substring(0,10) + " </span></html>");
        billete.add(labelI, BorderLayout.CENTER);
        ImageIcon img = new ImageIcon("src\\Windows\\images\\" + destino[i] + ".jpg");
        ImageIcon img2 = new ImageIcon("src\\Windows\\images\\" + origen[i] +".jpg");
        JLabel labelimg1 = new JLabel(img);
        labelimg1.setPreferredSize(new Dimension(100, 100));
        JLabel labelimg2 = new JLabel(img2);
        labelimg2.setPreferredSize(new Dimension(100, 100));
        billete.add(labelimg1, BorderLayout.EAST);
        billete.add(labelimg2, BorderLayout.WEST);

        JPanel botonesPanel = new JPanel(new FlowLayout());
        JButton modificarButton = new JButton("Modificar fecha");
        modificarButton.setBackground(new Color(46, 204, 113));
        modificarButton.setForeground(Color.WHITE);
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MaskFormatter dateFormatter = new MaskFormatter("####-##-##");
                    JFormattedTextField fechaNueva = new JFormattedTextField(dateFormatter);
                    fechaNueva.setText(fecha[i]);
                    fechaNueva.setHorizontalAlignment(SwingConstants.CENTER);

                    int cambiarFecha = JOptionPane.showConfirmDialog(null,fechaNueva,"Introduce la fecha deseada",JOptionPane.OK_CANCEL_OPTION);
                    if (cambiarFecha == JOptionPane.OK_OPTION) {

                        SimpleDateFormat formatchnger = null;
                        Date myDate = null;
                        String formattedDate=null;

                        formatchnger = new SimpleDateFormat("dd-MM-yyyy");
                        myDate = new SimpleDateFormat("yyyy-MM-dd").parse(fechaNueva.getText());
                        formattedDate = formatchnger.format(myDate);

                        Map<String, Object> data = new LinkedHashMap<>();
                        data.put("Fecha", formattedDate);
                        db.update("Billetes","num_billete = " + num_billete[i],data);

                        num_billete = db.selectFromTableNoDistinct("Billetes", new String[]{"Num_Billete"}, new String[]{"DNI IN (SELECT DNI FROM USERSACCS WHERE email = '" + ea + "') ORDER BY Num_Billete ASC"});

                        panelBilletes.removeAll();
                        panelBilletes.revalidate();
                        panelBilletes.repaint();
                        for (int j = 0; j < num_billete.length; j++) {
                            nuevoViaje(j);
                        }



                    }
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
        botonesPanel.add(modificarButton);

        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.setBackground(new Color(46, 204, 113));
        eliminarButton.setForeground(Color.WHITE);
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.delete("Billetes","num_billete = " + num_billete[i]);

                num_billete = db.selectFromTableNoDistinct("Billetes", new String[]{"Num_Billete"}, new String[]{"DNI IN (SELECT DNI FROM USERSACCS WHERE email = '" + ea + "') ORDER BY Num_Billete ASC"});

                panelBilletes.removeAll();
                panelBilletes.revalidate();
                panelBilletes.repaint();
                for (int j = 0; j < num_billete.length; j++) {
                    nuevoViaje(j);
                }
            }
        });
        botonesPanel.add(eliminarButton);

        billete.add(botonesPanel, BorderLayout.SOUTH);

        panelBilletes.add(billete);
    }

}
