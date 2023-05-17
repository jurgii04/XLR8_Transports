package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Windows.ventana.vol;

public class modificarBilletes extends JFrame {
    static String  optionDest;
    static String  optionOrg;

    public modificarBilletes(JPanel panelNorte, JFrame frame, JPanel contentPane) {

        JLabel title = new JLabel("<html><span style='font-family: Calibri, sans-serif; font-size: 32px; color: #333333; font-weight: bold;'>Billetes comprados</span></html>");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);

/*
        JPanel v1 = new JPanel(new BorderLayout());
        v1.setBackground(Color.WHITE);
        JLabel labelI = new JLabel("<html><span style='font-size:16px; color:#333333;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + optionOrg + " <span style='color:#999999;'>==></span> " + optionDest + " <span style='color:#999999;'>Precio</span> <span style='color:#FF9900;'>50€</span></span><br><span style='font-size:14px; color:#666666;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;6:00 (12h)&nbsp;&nbsp;&nbsp;20:30&nbsp;&nbsp;&nbsp;01-09-2023</span></html>");
        v1.add(labelI, BorderLayout.CENTER);
        ImageIcon img = new ImageIcon("src\\Windows\\images\\barcelona.jpg");
        ImageIcon img2 = new ImageIcon("src\\Windows\\images\\madrid.jpg");
        JLabel labelimg1 = new JLabel(img);
        labelimg1.setPreferredSize(new Dimension(100, 100));
        JLabel labelimg2 = new JLabel(img2);
        labelimg2.setPreferredSize(new Dimension(100, 100));
        v1.add(labelimg1, BorderLayout.EAST);
        v1.add(labelimg2, BorderLayout.WEST);
        JButton comprar = new JButton("Comprar");
        comprar.setBackground(new Color(46, 204, 113));
        comprar.setForeground(Color.WHITE);

        v1.add(comprar, BorderLayout.SOUTH);
*/

        panelNorte.add(vol, BorderLayout.WEST);

        frame.add(title);
        frame.add(panelNorte, BorderLayout.NORTH);

        pack();
    }
}
