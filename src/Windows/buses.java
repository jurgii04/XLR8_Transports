package Windows;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class buses extends JFrame {
    public buses(JFrame buses, JPanel panelNorte, JPanel contentPane){


        JPanel pb= new JPanel();
        buses.setTitle("Buses");

        JButton volver = new JButton("<html><u>Volver</u></html>");
        volver.setForeground(new Color(255, 255, 255));
        volver.setBackground(new Color(4, 140, 128, 255));
        volver.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panelNorte.add(volver, BorderLayout.WEST);
        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buses.getContentPane().removeAll();
                buses.getContentPane().revalidate();
                buses.getContentPane().repaint();
                panelNorte.remove(volver);
                buses.add(panelNorte, BorderLayout.NORTH);
                buses.add(contentPane, BorderLayout.CENTER);
            }
        });

        buses.add(panelNorte, BorderLayout.NORTH);
        buses.add(pb, BorderLayout.CENTER);
        //--------
        JPanel centre = new JPanel();
        //buses.setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));

        centre.setPreferredSize(new Dimension(500 , 600));
        centre.setBackground(Color.GRAY);
        JLabel  title = new JLabel("BUSCA TU VIAJE");
        JLabel  destination = new JLabel("Destino");
        String [] destinos = {"BARCELONA" , "BILBAO" , "MADRID"};
        String []origin = {"MADRID" , "BILBAO" , "BARCELONA"};

        JComboBox dest = new JComboBox<>(destinos);
        JLabel  origen = new JLabel("Origen");
        JComboBox org= new JComboBox<>(origin);
        JButton buscar =new JButton("Buscar");
        int i=0;
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String optionOrg = org.getSelectedItem().toString();
                String optionDest = dest.getSelectedItem().toString();
                if (optionOrg.equals(optionDest)){
                    JOptionPane.showMessageDialog(null , "El destino y el origen no pueden ser iguales", "ERROR",JOptionPane.ERROR_MESSAGE);
                }else {
                    JPanel viajes = new JPanel();
                    viajes.setBackground(Color.DARK_GRAY);
                    viajes.setLayout(new BoxLayout(viajes, BoxLayout.Y_AXIS));
                    JPanel v1= new JPanel();
                    v1.setLayout(new BorderLayout());
                    JLabel label = new JLabel(optionOrg + "==>" + optionDest + " Precio 100€" + "\n" + "22:00 (7h)");
                    v1.add(label , BorderLayout.CENTER);
                    ImageIcon img = new ImageIcon("src\\Windows\\images\\barcelona.jpg");

                    ImageIcon img2 = new ImageIcon("src\\Windows\\images\\madrid.jpg");
                    JLabel labelimg1= new JLabel(img);
                    labelimg1.setPreferredSize(new Dimension(100 , 100));
                    JLabel labelimg2= new JLabel(img2);
                    labelimg2.setPreferredSize(new Dimension(100 , 100));
                    v1.add(labelimg1 ,BorderLayout.EAST);
                    v1.add(labelimg2 , BorderLayout.WEST);
                    JButton comprar = new JButton("Comprar");
                    v1.add(comprar , BorderLayout.SOUTH);
                    JScrollPane P= new JScrollPane(viajes);

                    viajes.add(v1);
                    //---------------------------------------------------
                    JPanel vII= new JPanel();
                    vII.setLayout(new BorderLayout());
                    JLabel labelII = new JLabel(optionOrg + "==>" + optionDest + " Precio 50€" + "\n" + "6:00 (12h)");
                    vII.add(labelII , BorderLayout.CENTER);
                    ImageIcon imgII = new ImageIcon("src\\Windows\\images\\barcelona.jpg");

                    ImageIcon img2II = new ImageIcon("src\\Windows\\images\\madrid.jpg");
                    JLabel labelimg1II= new JLabel(imgII);
                    labelimg1II.setPreferredSize(new Dimension(100 , 100));
                    JLabel labelimg2II= new JLabel(img2II);
                    labelimg2II.setPreferredSize(new Dimension(100 , 100));
                    vII.add(labelimg1II ,BorderLayout.EAST);
                    vII.add(labelimg2II , BorderLayout.WEST);
                    JButton comprarII = new JButton("Comprar");
                    vII.add(comprarII , BorderLayout.SOUTH);
                    viajes.add(vII);


                    //-------------
                    centre.add(viajes,BorderLayout.CENTER);
                    //centre.repaint();
                    centre.revalidate();

                }

            }
        });
        JPanel TITLE=new JPanel();
        TITLE.add(title);
        JPanel buscarp = new JPanel();
        buscarp.add(origen);
        buscarp.add(org);
        buscarp.add(destination);
        buscarp.add(dest);
        buscarp.add(buscar);

        centre.add(TITLE , BorderLayout.NORTH);
        centre.add(buscarp);
        //centre.add(destination);
        //centre.add(dest);
        //centre.add(origen);
        //centre.add(org);
        //centre.add(buscar);
        pb.add(centre);
        buses.add(pb);





    }

}
