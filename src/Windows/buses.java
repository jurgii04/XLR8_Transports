package Windows;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;

import Dbconnection.GestorDB;

public class buses extends JFrame {
    ;
    public buses(JFrame buses, JPanel panelNorte, JPanel contentPane , JButton botonLogin,GestorDB db  ){



        JPanel pb= new JPanel();
        buses.setTitle("Buses");
        JPanel formulario = new JPanel();
        JPanel boton = new JPanel();

        JButton volver = new JButton("<html><u>Volver</u></html>");
        volver.setPreferredSize(new Dimension(100, 70));
        volver.setForeground(Color.WHITE);
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
                JLabel labelAux = new JLabel();
                labelAux.setPreferredSize(new Dimension(100, 70));
                panelNorte.add(labelAux, BorderLayout.WEST);
                buses.add(panelNorte, BorderLayout.NORTH);
                buses.add(contentPane, BorderLayout.CENTER);
                buses.setTitle("XLR8 Transports");
            }
        });

        buses.add(panelNorte, BorderLayout.NORTH);
        buses.add(pb, BorderLayout.CENTER);
        //--------
        JPanel centre = new JPanel();
        centre.setLayout(new BoxLayout(centre, BoxLayout.Y_AXIS));
        centre.setPreferredSize(new Dimension(600,500));
        centre.setBackground(Color.WHITE);
        JLabel title = new JLabel("<html><span style='font-family: Calibri, sans-serif; font-size: 32px; color: #333333; font-weight: bold;'>BUSCA TU VIAJE</span></html>");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        String[] columnNames = {"destino"};
        String [] destinos = db.selectFromTable("Viajes" , columnNames, new String[]{});
        String []origin = db.selectFromTable("Viajes" , new String[]{"origen"}, new String[]{});

        //String [] destinos = {"Bilbao", "Madrid", "Barcelona"};
        //String []origin = {"Bilbao", "Madrid", "Barcelona"};

        JLabel  destination = new JLabel("    Destino →  ");
        JComboBox dest = new JComboBox<>(destinos);
        dest.setPreferredSize(new Dimension(150, dest.getPreferredSize().height));
        JLabel  origen = new JLabel("Origen →  ");
        JComboBox org= new JComboBox<>(origin);
        org.setPreferredSize(new Dimension(150 , org.getPreferredSize().height));
        JButton buscar =new JButton("Buscar");
        buscar.setForeground(Color.WHITE);
        buscar.setBackground(new Color(4, 140, 128, 255));
        buscar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        boton.add(buscar);
        JPanel viajes = new JPanel();
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viajes.removeAll();

                String optionOrg = org.getSelectedItem().toString();
                String optionDest = dest.getSelectedItem().toString();
                if (optionOrg.equals(optionDest)){
                    JOptionPane.showMessageDialog(null , "El destino y el origen no pueden ser iguales", "ERROR",JOptionPane.ERROR_MESSAGE);
                }else {

                    // Create main panel

                    viajes.setLayout(new BoxLayout(viajes, BoxLayout.Y_AXIS));

                    // Create first trip panel
                    JPanel v1 = new JPanel(new BorderLayout());
                    v1.setBackground(Color.WHITE);
                    JLabel labelI = new JLabel("<html><span style='font-size:16px; color:#333333;'>" + optionOrg + " <span style='color:#999999;'>==></span> " + optionDest + " <span style='color:#999999;'>Precio</span> <span style='color:#FF9900;'>50€</span></span><br><span style='font-size:14px; color:#666666;'>6:00 (12h)</span></html>");
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

                    comprar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            /*PayWindow P = new PayWindow();
                            boolean use = test.get();
                            System.out.println(use);
                            if (use==false) {
                                login l = new login(botonLogin, panelNorte);
                                test.set(l.isLoginstate());
                            } else {
                                P.Pay();
                            }*/
                            login l = new login(botonLogin, panelNorte,true, buses);
                        }
                    });

                    v1.add(comprar, BorderLayout.SOUTH);
                    viajes.add(v1);

                    // Create second trip panel
                    JPanel vII = new JPanel(new BorderLayout());
                    vII.setBackground(Color.WHITE);
                    JLabel labelII = new JLabel("<html><span style='font-size:16px; color:#333333;'>" + optionOrg + " <span style='color:#999999;'>==></span> " + optionDest + " <span style='color:#999999;'>Precio</span> <span style='color:#FF9900;'>50€</span></span><br><span style='font-size:14px; color:#666666;'>6:00 (12h)</span></html>");
                    vII.add(labelII, BorderLayout.CENTER);
                    ImageIcon imgII = new ImageIcon("src\\Windows\\images\\barcelona.jpg");
                    ImageIcon img2II = new ImageIcon("src\\Windows\\images\\madrid.jpg");
                    JLabel labelimg1II = new JLabel(imgII);
                    labelimg1II.setPreferredSize(new Dimension(100, 100));
                    JLabel labelimg2II = new JLabel(img2II);
                    labelimg2II.setPreferredSize(new Dimension(100, 100));
                    vII.add(labelimg1II, BorderLayout.EAST);
                    vII.add(labelimg2II, BorderLayout.WEST);
                    JButton comprarII = new JButton("Comprar");
                    comprarII.setBackground(new Color(46, 204, 113));
                    comprarII.setForeground(Color.WHITE);
                    comprarII.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            login l = new login(botonLogin, panelNorte,true, buses);
                        }
                    });
                    vII.add(comprarII, BorderLayout.SOUTH);
                    viajes.add(vII);
                    JScrollPane scrollPane = new JScrollPane(viajes);
                    add(scrollPane);




                    //-------------
                    centre.add(viajes);
                    //centre.repaint();
                    centre.add(boton);
                    centre.revalidate();

                }
            }
        });
        JPanel TITLE=new JPanel();
        TITLE.add(title);

        centre.add(TITLE);

        formulario.setLayout(new GridBagLayout());
        formulario.add(origen);
        formulario.add(org);
        formulario.add(destination);
        formulario.add(dest);

        centre.add(formulario);


        centre.add(boton);
        pb.add(centre);
        pack();
    }



}

