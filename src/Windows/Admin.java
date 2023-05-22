package Windows;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.MaskFormatter;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import Dbconnection.GestorDB;
import com.formdev.flatlaf.FlatLightLaf;

public class Admin extends JFrame {
    private JTable t;
    private MyTableModel m;
    private JScrollPane s;
    private JComboBox<String> lista;
    private JPanel c;

    public Admin(String  AdminType) {


        super("Ventana de Viajes");

        FlatLightLaf.install();

        // Crear los botones para insertar, actualizar y eliminar filas
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    m.insertdatos(lista.getSelectedItem().toString());

                } catch (Exception x) {
                    JOptionPane.showMessageDialog(null, x.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

                }
            }
        });

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    m.update(lista.getSelectedItem().toString());
                } catch (Exception x) {
                    JOptionPane.showMessageDialog(null, x.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

                }


            }
        });

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    m.delete(lista.getSelectedItem().toString());

                } catch (Exception x) {
                    JOptionPane.showMessageDialog(null, x.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

                }
            }
        });


        JPanel panelBotones = new JPanel(new GridLayout(1, 3));
        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        JPanel tablaslist = new JPanel();
        String[] TablesToAcces;
        String admin=AdminType.toLowerCase();


        switch (admin){
            case "adminbuses":
                TablesToAcces=new String[]{"VIAJES", "BILLETES", "BUSES", "CONDUCE_BUS", "CLIENTES", "HACER", "TELEFONOS_CLI"};
                break;
            case "adminreparto":
                TablesToAcces=new String[]{"CAMIONES", "CONDUCE_CAMIÓN", "EMPRESA", "MERCANCIAS", "PEDIDOS", "VAN"};
                break;
            case "jefe":
                TablesToAcces=new String[]{"VIAJES", "BILLETES", "BUSES", "CONDUCE_BUS", "CLIENTES", "HACER", "TELEFONOS_CLI","CAMIONES", "CONDUCE_CAMIÓN", "EMPRESA", "MERCANCIAS", "PEDIDOS", "VAN","VEHÍCULOS","TELÉFONOS_EMP","EMPLEADOS","USERSACCS"};

                break;
            default:
                TablesToAcces=new String[]{"null"};
        }
        lista = new JComboBox<>(TablesToAcces);

        c = new JPanel();
        lista.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                create_thetable();
            }
        });
        ImageIcon checkIcon = new ImageIcon("src\\Windows\\images\\refresh.png");
        Image img = checkIcon.getImage();
        Image resizedImg = img.getScaledInstance(23, 23, java.awt.Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImg);
        JButton ac = new JButton(resizedIcon);
        ac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                create_thetable();
            }
        });
        tablaslist.add(lista, BorderLayout.NORTH);
        tablaslist.add(ac, BorderLayout.WEST);
        add(c, BorderLayout.CENTER);


        add(tablaslist, BorderLayout.NORTH);

        add(panelBotones, BorderLayout.SOUTH);
        Image icono = new ImageIcon("src\\Windows\\images\\X8.png").getImage();
        setIconImage(icono);


        setSize(870, 600);

        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void create_thetable() {

        try {
            c.removeAll();
            String table = lista.getSelectedItem().toString();
            m = new MyTableModel(table);
            t = new JTable(m);
            m.updateData(table);
            s = new JScrollPane(t);
            t.setPreferredScrollableViewportSize(new Dimension(800, 500));
            c.add(s);
            repaint();
            revalidate();
        }
        catch (Exception x){
            JOptionPane.showMessageDialog(null, x.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }


    public class MyTableModel extends AbstractTableModel {
        private String[] columnNames;
        private Object[][] data;
        private GestorDB db;

        public MyTableModel(String tableName) {
            db = new GestorDB();
            this.columnNames = db.selectFromTable("all_tab_columns", new String[]{"column_name"}, new String[]{"table_name = '" + tableName + "'"});

            String[] dbData = db.selectFromTable(tableName, columnNames, new String[]{});
            int rowCount = dbData.length / columnNames.length;
            data = new Object[rowCount][columnNames.length];
            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < columnNames.length; j++) {
                    data[i][j] = dbData[i * columnNames.length + j];
                }
            }


        }

        public void updateData(String tableName) {

            this.columnNames = db.selectFromTable("all_tab_columns", new String[]{"column_name"}, new String[]{"table_name = '" + tableName + "'"});

            String[] dbData = db.selectFromTable(tableName, columnNames, new String[]{});
            int rowCount = dbData.length / columnNames.length;
            data = new Object[rowCount][columnNames.length];
            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < columnNames.length; j++) {
                    data[i][j] = dbData[i * columnNames.length + j];
                }
            }


            fireTableDataChanged();
        }

        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public boolean isCellEditable(int row, int col) {

            return true;
        }

        public void update(String table) {
            JFrame upframe = new JFrame("Update " + table);
            upframe.setSize(new Dimension(600, 200));
            upframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            String[] pkar = new String[data.length];
            for (int i = 0; i < data.length; i++) {
                pkar[i] = data[i][0].toString();
            }

            JPanel nopanel = new JPanel(new BorderLayout());
            nopanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 5));
            JLabel pklable = new JLabel("PK:     ");
            pklable.setPreferredSize(new Dimension(80, 20));
            pklable.setHorizontalAlignment(SwingConstants.RIGHT);
            nopanel.add(pklable, BorderLayout.WEST);
            JComboBox<String> pkcom = new JComboBox<>(pkar);
            pkcom.setPreferredSize(new Dimension(150, 20));
            nopanel.add(pkcom, BorderLayout.CENTER);


            JPanel leftPanel = new JPanel(new BorderLayout());
            leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 5));


            JLabel columnLabel = new JLabel("Columna:     ");
            columnLabel.setPreferredSize(new Dimension(80, 20));
            columnLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            leftPanel.add(columnLabel, BorderLayout.WEST);
            JComboBox<String> comboBox = new JComboBox<>(columnNames);
            comboBox.setPreferredSize(new Dimension(150, 20));
            leftPanel.add(comboBox, BorderLayout.CENTER);


            JPanel rightPanel = new JPanel(new BorderLayout());
            rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 10));


            JLabel valueLabel = new JLabel("Valor:      ");
            valueLabel.setPreferredSize(new Dimension(80, 20));
            valueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            rightPanel.add(valueLabel, BorderLayout.WEST);
            MaskFormatter dateFormatter = null;
            try {
                dateFormatter = new MaskFormatter("####-##-##");
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            JFormattedTextField textFieldx = new JFormattedTextField(dateFormatter);
            textFieldx.setColumns(10);
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate0 = currentDate.format(formatter);
            textFieldx.setValue(formattedDate0);
            JTextField textField;

            textField = new JTextField();
            textField.setPreferredSize(new Dimension(150, 20));
            rightPanel.add(textField, BorderLayout.CENTER);
            comboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (comboBox.getSelectedItem().toString().contains("FECHA")){
                        rightPanel.remove(textField);

                        rightPanel.add(textFieldx, BorderLayout.CENTER);


                        upframe.revalidate();

                        upframe.repaint();
                    }else{
                        rightPanel.removeAll();
                        //rightPanel.remove(textField);
                        rightPanel.add(valueLabel, BorderLayout.WEST);
                        rightPanel.add(textField, BorderLayout.CENTER);
                        upframe.revalidate();

                        upframe.repaint();
                    }




                }
            });



            JButton button = new JButton("Actualizar");
            button.setPreferredSize(new Dimension(110, 20));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    try {

                        String where = columnNames[0] + "=" + "'" + pkcom.getSelectedItem().toString() + "'";
                        Map<String, Object> datos = new LinkedHashMap<>();
                        if (comboBox.getSelectedItem().toString().contains("FECHA")){

                            SimpleDateFormat formatchnger = null;
                            Date myDate = null;
                            String formattedDate=null;

                                formatchnger = new SimpleDateFormat("dd-MM-yyyy");
                                myDate = new SimpleDateFormat("yyyy-MM-dd").parse(textFieldx.getText());
                                formattedDate = formatchnger.format(myDate);
                            datos.put(comboBox.getSelectedItem().toString(), formattedDate);
                        }else {
                            datos.put(comboBox.getSelectedItem().toString(), textField.getText());
                        }

                        db.update(table, where, datos);
                        JOptionPane.showMessageDialog(null, "DATA updated 100%");



                    } catch (Exception x) {
                        JOptionPane.showMessageDialog(null, x.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

                    }

                }
            });

            upframe.getRootPane().setDefaultButton(button);
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            buttonPanel.add(button);
            upframe.add(buttonPanel, BorderLayout.SOUTH);


            upframe.add(leftPanel, BorderLayout.WEST);
            upframe.add(rightPanel, BorderLayout.EAST);
            upframe.add(nopanel, BorderLayout.NORTH);
            JFrame frame = new JFrame();



            upframe.setLocationRelativeTo(null);
            upframe.setVisible(true);


        }

        public void insertdatos(String table) {
            JFrame f = new JFrame("Añadir a " + table);
            JPanel mainPanel;
            JTextField[] textFields;
            mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            f.add(mainPanel);

            textFields = new JTextField[columnNames.length];
            for (int i = 0; i < columnNames.length; i++) {
                JLabel l = new JLabel(columnNames[i]);
                l.setPreferredSize(new Dimension(90,50));
                if (columnNames[i].contains("FECHA")){
                    l.setPreferredSize(new Dimension(200,50));
                    //l.setText(columnNames[i]+"                   ");
                    MaskFormatter dateFormatter = null;
                    try {
                        dateFormatter = new MaskFormatter("####-##-##");
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                    JFormattedTextField tf = new JFormattedTextField(dateFormatter);
                    tf.setColumns(10);
                    LocalDate currentDate = LocalDate.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String formattedDate0 = currentDate.format(formatter);
                    tf.setValue(formattedDate0);
                    textFields[i] = tf;
                    JPanel row = new JPanel();
                    row.add(l);
                    row.add(tf);
                    mainPanel.add(row);
                }else{
                    JTextField tf = new JTextField(20);
                    textFields[i] = tf;
                    JPanel row = new JPanel();
                    row.add(l);
                    row.add(tf);
                    mainPanel.add(row);
                }

            }
            JButton anadir=new JButton("Añadir");
            anadir.setPreferredSize(new Dimension(110, 20));
            anadir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String[] textValues = new String[textFields.length];
                    for (int i = 0; i < textFields.length; i++) {
                        if (columnNames[i].contains("FECHA")){
                            SimpleDateFormat formatchnger = null;
                            Date myDate = null;
                            String formattedDate=null;
                            try {
                                formatchnger = new SimpleDateFormat("dd-MM-yyyy");
                                myDate = new SimpleDateFormat("yyyy-MM-dd").parse(textFields[i].getText());
                                formattedDate = formatchnger.format(myDate);

                                textValues[i] = formattedDate;
                            } catch (ParseException P) {
                                throw new RuntimeException(P);
                            }

                        }else {
                            textValues[i] = textFields[i].getText();
                        }

                    }

                    Map<String, Object> datos = new LinkedHashMap<>();
                    for (int i = 0; i < columnNames.length; i++) {
                        datos.put(columnNames[i], textValues[i]);
                    }
                    db.insert(table,datos);
                    JOptionPane.showMessageDialog(null, "DATA INSERTED 100%");

                }
            });




            f.add(anadir,BorderLayout.SOUTH);
            f.pack();
            f.setLocationRelativeTo(null);

            f.setVisible(true);


        }
        public void delete(String table){
            JFrame detframe = new JFrame("Borrar de  " + table);
            detframe.setSize(new Dimension(400, 150));
            detframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


            JPanel leftPanel = new JPanel(new BorderLayout());
            leftPanel.setPreferredSize(new Dimension(70,15));


            String[] pkar = new String[data.length];
            for (int i = 0; i < data.length; i++) {
                pkar[i] = data[i][0].toString();
            }
            JComboBox<String> comboBox = new JComboBox<>(pkar);
            comboBox.setPreferredSize(new Dimension(70, 10));
            leftPanel.add(comboBox, BorderLayout.CENTER);



            JButton button = new JButton("Borrar");
            button.setPreferredSize(new Dimension(80, 25));
            button.addActionListener(e -> {
                db.delete(table,columnNames[0]+"=" + "'"+comboBox.getSelectedItem().toString()+"'");
                JOptionPane.showMessageDialog(null, "DATA deleted 100%");

            });
            detframe.getRootPane().setDefaultButton(button);
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            buttonPanel.add(button);
            detframe.add(buttonPanel, BorderLayout.SOUTH);


            detframe.add(leftPanel, BorderLayout.CENTER);



            detframe.setLocationRelativeTo(null);
            detframe.setVisible(true);

        }



    }
    /*public static void main(String[] args) {
       // jefe/adminreparto/adminbuses

        Admin ventana = new Admin("jefe");
    }*/
}
