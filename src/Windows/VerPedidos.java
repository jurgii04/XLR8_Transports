package Windows;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import static Windows.Ventana.db;
import static Windows.Ventana.volverButton;

public class VerPedidos extends JFrame {

    public VerPedidos(JFrame ped, JPanel panelNorte ) {
        FlatLightLaf.install();


        JPanel pb = new JPanel();
        ped.setTitle("pedidos");
        panelNorte.add(volverButton, BorderLayout.WEST);
        ped.add(panelNorte, BorderLayout.NORTH);
        ped.add(pb, BorderLayout.CENTER);
        String [] dataemp=db.selectFromTable("PEDIDOS" , new String[]{},new String[]{"NUM_EMPRESA in(select NUM_EMPRESA from EMPRESA  where NOMBRE_EMPRESA='"+Login.name+"')"});


        System.out.println(Arrays.toString(dataemp));


    }


}
