package Windows;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Random;

import Objects.User;
import com.formdev.flatlaf.FlatLightLaf;

import static Windows.Login.user;
import static Windows.Ventana.db;

public class VerPedidos extends JFrame {

    public VerPedidos(JFrame ped, JPanel panelNorte ) {
        FlatLightLaf.install();


        JPanel pb = new JPanel();
        ped.setTitle("pedidos");
        panelNorte.add(Ventana.volverButton, BorderLayout.WEST);
        ped.add(panelNorte, BorderLayout.NORTH);
        ped.add(pb, BorderLayout.CENTER);
        String [] dataemp=db.selectFromTable("PEDIDOS" , new String[]{},new String[]{"NUM_EMPRESA in(select NUM_EMPRESA from EMPRESA  where NOMBRE_EMPRESA='"+Login.name+"')"});
        String [] codigos = new String[dataemp.length/10];
        for (int i=0,j=0;i<dataemp.length;i=i+10,j++){
            codigos[j]=dataemp[i];
        }


        JButton Seguimiento = new JButton("Seguimiento del pedido");
        JButton consultas = new JButton("Consulta sobre los pedidos");

        // Apply custom styles to the buttons
        Seguimiento.setBackground(new Color(4, 140, 128, 255));
        Seguimiento.setForeground(Color.WHITE);
        Seguimiento.setFont(new Font("Arial", Font.PLAIN, 14));

        Seguimiento.setMargin(new Insets(10, 20, 10, 20));

        consultas.setBackground(new Color(4, 140, 128, 255));
        consultas.setForeground(Color.WHITE);
        consultas.setFont(new Font("Arial", Font.PLAIN, 14));

        consultas.setMargin(new Insets(10, 20, 10, 20));

        /*for (int i=0;i<data.length;i++){
            data[i]
        }*/
        // int rows=dataemp.length/10;
        MyTableModel m=new MyTableModel("PEDIDOS",dataemp);
        JTable t=new JTable(m);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(pb.getBackground());

        // Set the cell renderer for all columns
        for (int i = 0; i < t.getColumnCount(); i++) {
            t.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
            t.getTableHeader().setDefaultRenderer(cellRenderer);
        }
        t.setPreferredScrollableViewportSize(new Dimension(760, (20*m.getRowCount())));
        //t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        t.setBorder(new EmptyBorder(0, 0, 0, 0));
        JScrollPane scrollPane = new JScrollPane(t);
        pb.add(scrollPane,BorderLayout.WEST);
        JPanel butts=new JPanel();
        butts.add(Seguimiento,BorderLayout.NORTH);
        butts.add(consultas,BorderLayout.NORTH);
        pb.add(butts,BorderLayout.EAST);
        consultas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Consultas");
                frame.setSize(500, 300);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);

                // Create the components
                JLabel pedidoLabel = new JLabel("ID Pedido: ");
                JComboBox<String> comboBox = new JComboBox<>(codigos);
                JLabel messageLabel = new JLabel("Mensaje");
                JTextArea textField = new JTextArea();
                JScrollPane scrollPane = new JScrollPane(textField);
                textField.setLineWrap(true);
                textField.setWrapStyleWord(true);

                // Create panels to hold the components
                JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                northPanel.add(pedidoLabel);
                northPanel.add(comboBox);

                JPanel centerPanel = new JPanel(new BorderLayout());
                centerPanel.add(messageLabel, BorderLayout.NORTH);
                centerPanel.add(scrollPane, BorderLayout.CENTER);

                JButton enviar = new JButton("Enviar");
                JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                enviar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(null, "¡Gracias! Os contactaremos lo antes posible");
                        try {
                            LocalDate currentDate = LocalDate.now();


                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


                            String formattedDate = currentDate.format(formatter);

                            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\Windows\\TextsFiles\\Consultas.txt",true));
                            writer.write("El numero del pedido: " + comboBox.getSelectedItem().toString());
                            writer.newLine();
                            writer.write("Empresa: " + user.getNombre_completo());
                            writer.newLine();
                            writer.write("Email: "+ user.getEMAIL());
                            writer.newLine();
                            writer.write("Dni: "+ user.getDNI());
                            writer.newLine();
                            writer.write("Teléfono: "+ user.getTELEFONO());
                            writer.newLine();
                            writer.write("Sector: "+ user.getSECTOR());
                            writer.newLine();
                            writer.write("Fecha: "+ formattedDate);
                            writer.newLine();
                            writer.write("Mensaje: "+ textField.getText());
                            writer.newLine();
                            writer.write("==========================================================================================================");
                            writer.newLine();
                            writer.close();

                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        frame.dispose();
                    }
                });
                Random random = new Random();
                int randomNumber = random.nextInt(101);
                int minProgress = 0;
                int maxProgress = 100;
                int currentProgress = randomNumber;

                // Create a JLabel for the start text
                JLabel startLabel = new JLabel();

                // Create a JProgressBar
                JProgressBar progressBar = new JProgressBar(minProgress, maxProgress);
                progressBar.setValue(currentProgress);
                progressBar.setStringPainted(true); // Display progress as text

                // Create a JLabel for the end text
                JLabel endLabel = new JLabel();
                progressBar.setForeground(new Color(0, 150, 136));

                // Create a JPanel to hold the components
                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());
                panel.add(startLabel, BorderLayout.WEST);
                panel.add(progressBar, BorderLayout.CENTER);
                panel.add(endLabel, BorderLayout.EAST);
                Seguimiento.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String org="";
                        String des="";
                        try {
                            int row=t.getSelectedRow();
                             org=m.getValueAt(row,1).toString();
                             des=m.getValueAt(row,2).toString();
                            startLabel.setText(org+"   ");
                            endLabel.setText("   "+des);
                            Random random = new Random();
                            int randomNumber = random.nextInt(101);

                            progressBar.setValue(randomNumber);
                            // Create a JOptionPane and set the panel as the message
                            JOptionPane.showMessageDialog(null, panel, "Progreso del pedido", JOptionPane.PLAIN_MESSAGE);
                        }catch (Exception x){
                            JOptionPane.showMessageDialog(null, "Selecciona un pedido","Error" , JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                southPanel.add(enviar);

                // Set font styles
                Font labelFont = new Font("Arial", Font.PLAIN, 16);
                pedidoLabel.setFont(labelFont);
                messageLabel.setFont(labelFont);
                textField.setFont(new Font("Arial", Font.PLAIN, 14));
                enviar.setFont(new Font("Arial", Font.PLAIN, 14));

                // Set background colors
                frame.getContentPane().setBackground(Color.WHITE);
                northPanel.setBackground(Color.WHITE);
                centerPanel.setBackground(Color.WHITE);
                southPanel.setBackground(Color.WHITE);

                // Set component sizes
                pedidoLabel.setPreferredSize(new Dimension(100, 30));
                comboBox.setPreferredSize(new Dimension(150, 30));
                scrollPane.setPreferredSize(new Dimension(300, 150));
                enviar.setPreferredSize(new Dimension(100, 30));

                // Add the panels to the frame
                frame.add(northPanel, BorderLayout.NORTH);
                frame.add(centerPanel, BorderLayout.CENTER);
                frame.add(southPanel, BorderLayout.SOUTH);
                frame.setVisible(true);
            }
        });




        //System.out.println(Arrays.toString(dataemp));


    }




}
