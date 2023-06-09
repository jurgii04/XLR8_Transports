package Windows;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Dbconnection.GestorDB;

import static Windows.Login.user;
import static Windows.Ventana.loginstat;
import static Windows.Ventana.volverButton;
import static Windows.ModificarBilletes.optionDest;
import static Windows.ModificarBilletes.optionOrg;
import Objects.Billetes;


public class Buses extends JFrame {
    static Billetes bill;
    static String billdate;
    public JButton buscar;


    public Buses(JFrame buses, JPanel panelNorte, JPanel contentPane , JButton botonLogin, GestorDB db){



        JPanel pb= new JPanel();
        buses.setTitle("Buses");
        JPanel formulario = new JPanel();
        JPanel boton = new JPanel();

        panelNorte.add(volverButton, BorderLayout.WEST);

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
         buscar =new JButton("Buscar");
        buscar.setForeground(Color.WHITE);
        buscar.setBackground(new Color(4, 140, 128, 255));
        buscar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        boton.add(buscar);
        JPanel viajes = new JPanel();
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viajes.removeAll();

                optionOrg = org.getSelectedItem().toString();
                optionDest = dest.getSelectedItem().toString();
                if (optionOrg.equals(optionDest)){
                    JOptionPane.showMessageDialog(null , "Origen y destino no pueden ser iguales", "ERROR",JOptionPane.ERROR_MESSAGE);
                }else {

                    // Create main panel

                    viajes.setLayout(new BoxLayout(viajes, BoxLayout.Y_AXIS));

                    // Create first trip panel
                    JPanel v1 = new JPanel(new BorderLayout());
                    v1.setBackground(Color.WHITE);
                    JLabel labelI = new JLabel("<html><span style='font-size:16px; color:#333333;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + optionOrg + " <span style='color:#999999;'>==></span> " + optionDest + " <span style='color:#999999;'>Precio</span> <span style='color:#FF9900;'>50€</span></span><br><span style='font-size:14px; color:#666666;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;6:00 (12h)&nbsp;&nbsp;&nbsp;20:30&nbsp;&nbsp;&nbsp;01-09-2023</span></html>");
                    v1.add(labelI, BorderLayout.CENTER);
                    ImageIcon img = new ImageIcon("src\\Windows\\images\\"+optionDest+".jpg");
                    ImageIcon img2 = new ImageIcon("src\\Windows\\images\\"+optionOrg+".jpg");
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

                            if (loginstat){
                                PayWindow P=new PayWindow();
                                int biilnum= Integer.parseInt(db.selectFromTable("BILLETES",new String[]{"max(NUM_BILLETE)"},new String[]{})[0]);
                                SimpleDateFormat formatchnger = null;
                                Date myDate = null;
                                String formattedDate=null;
                                try {
                                    formatchnger = new SimpleDateFormat("dd-MMM-yyyy");
                                    myDate = new SimpleDateFormat("yyyy-MM-dd").parse("2023-09-01");
                                    formattedDate = formatchnger.format(myDate);
                                } catch (ParseException p) {
                                    throw new RuntimeException(p);
                                }
                                billdate="2023-09-01";
                                bill=new Billetes(biilnum+1,formattedDate,50,"tarjeta",optionDest,optionOrg,user.getDNI());
                            }
                            else {
                                Login l = new Login(botonLogin, panelNorte);
                            }

                        }
                    });

                    v1.add(comprar, BorderLayout.SOUTH);
                    viajes.add(v1);

                    // Create second trip panel
                    JPanel vII = new JPanel(new BorderLayout());
                    vII.setBackground(Color.WHITE);
                    JLabel labelII = new JLabel("<html><span style='font-size:16px; color:#333333;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + optionOrg + " <span style='color:#999999;'>==></span> " + optionDest + " <span style='color:#999999;'>Precio</span> <span style='color:#FF9900;'>50€</span></span><br><span style='font-size:14px; color:#666666;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;6:00 (12h)&nbsp;&nbsp;&nbsp;06:30&nbsp;&nbsp;&nbsp;12-07-2023</span></html>");
                    vII.add(labelII, BorderLayout.CENTER);
                    ImageIcon imgII = new ImageIcon("src\\Windows\\images\\"+optionDest+".jpg");
                    ImageIcon img2II = new ImageIcon("src\\Windows\\images\\"+optionOrg+".jpg");
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

                            if (loginstat){
                                PayWindow P=new PayWindow();
                                int biilnum= Integer.parseInt(db.selectFromTable("BILLETES",new String[]{"max(NUM_BILLETE)"},new String[]{})[0]);
                                SimpleDateFormat formatchnger = null;
                                Date myDate = null;
                                String formattedDate=null;
                                try {
                                    formatchnger = new SimpleDateFormat("dd-MMM-yyyy");
                                    myDate = new SimpleDateFormat("yyyy-MM-dd").parse("2023-09-01");
                                    formattedDate = formatchnger.format(myDate);
                                } catch (ParseException p) {
                                    throw new RuntimeException(p);
                                }
                                billdate="2023-09-01";
                                bill=new Billetes(biilnum+1,formattedDate,50,"tarjeta",optionDest,optionOrg,user.getDNI());
                            }
                            else {
                                Login l = new Login(botonLogin, panelNorte);
                            }
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

