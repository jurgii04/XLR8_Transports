package Windows;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Arrays;

import com.formdev.flatlaf.FlatLightLaf;

import Dbconnection.GestorDB;

import static Windows.buses.volver;
import static Windows.ventana.db;
import static Windows.ventana.loginstat;

public class VerPedidos extends JFrame {

    public VerPedidos(JFrame ped, JPanel panelNorte ) {
        FlatLightLaf.install();


        JPanel pb = new JPanel();
        ped.setTitle("pedidos");
        panelNorte.add(ventana.vol, BorderLayout.WEST);
        ped.add(panelNorte, BorderLayout.NORTH);
        ped.add(pb, BorderLayout.CENTER);
        String [] dataemp=db.selectFromTable("PEDIDOS" , new String[]{},new String[]{"NUM_EMPRESA in(select NUM_EMPRESA from EMPRESA  where NOMBRE_EMPRESA='"+login.name+"')"});


        System.out.println(Arrays.toString(dataemp));


    }


}