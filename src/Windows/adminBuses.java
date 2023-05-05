package Windows;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;

import Dbconnection.GestorDB;
import com.formdev.flatlaf.FlatLightLaf;

public class adminBuses extends JFrame {
    private JTable tablaViajes;
    private MyTableModel modeloTabla;

    public adminBuses() {




        super("Ventana de Viajes");

        FlatLightLaf.install();

        // Crear los botones para insertar, actualizar y eliminar filas
        JButton btnAgregar = new JButton("Agregar");
        /*btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modeloTabla.agregarViaje(new Viaje("", "", "", 0.0));
            }
        });*/

        JButton btnActualizar = new JButton("Actualizar");
       /* btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaViajes.getSelectedRow();
                if (filaSeleccionada != -1) {
                    modeloTabla.actualizarViaje(filaSeleccionada);
                }
            }
        });*/

        JButton btnEliminar = new JButton("Eliminar");
        /*btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaViajes.getSelectedRow();
                if (filaSeleccionada != -1) {
                    modeloTabla.eliminarViaje(filaSeleccionada);
                }
            }
        });*/

        // Crear un panel para los botones y agregarlos
        JPanel panelBotones = new JPanel(new GridLayout(1, 3));
        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        JPanel tablaslist=new JPanel();
        JComboBox<String> lista=new JComboBox<>(new String[]{"VIAJES","BILLETES","BUSES","CONDUCE_BUS" , "CLIENTES" , "HACER" , "TELEFONOS_CLI"});

        JPanel c=new JPanel();
        lista.addActionListener(new ActionListener() {
            JScrollPane s;
            @Override
            public void actionPerformed(ActionEvent e) {
                c.removeAll();
                String table= lista.getSelectedItem().toString();
                System.out.println(table);
                //modeloTabla.updateData(table);
                MyTableModel m=new MyTableModel(table);
                JTable t=new JTable(m);
                m.updateData(table);

                 s=new JScrollPane(t);

                 c.add(s);





            }
        });
        tablaslist.add(lista,BorderLayout.NORTH);
        add(c, BorderLayout.CENTER);

        // Agregar la tabla y el panel de botones a la ventana principal
        add(tablaslist, BorderLayout.NORTH);
        //add(new JScrollPane(tablaViajes), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Establecer el tamaño de la ventana y hacerla visible
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Clase interna para el modelo de tabla personalizado
    public class MyTableModel extends AbstractTableModel {
        private String[] columnNames;
        private Object[][] data;
        private GestorDB db;

        public MyTableModel(String tableName) {
            db = new GestorDB();


            updateData(tableName);
        }
        public void updateData(String tableName) {

            this.columnNames = db.selectFromTable("all_tab_columns" , new String[]{"column_name"},new String[]{"table_name = '"+tableName+"'"});

            String[] dbData = db.selectFromTable(tableName, columnNames, new String[]{});
            int rowCount = dbData.length / columnNames.length;
            data = new Object[rowCount][columnNames.length];
            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < columnNames.length; j++) {
                    data[i][j] = dbData[i * columnNames.length + j];
                }
            }

            // notify any listeners that the model has changed
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

       /* @Override
       public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
            // save changes to database
            String tableName = ...; // set the name of the table you want to update
            String columnName = columnNames[col];
            String newValue = value.toString();
            String[] whereConditions = ...; // set any conditions for the update, e.g. the primary key of the row
            String updateSql = "UPDATE " + tableName + " SET " + columnName + " = '" + newValue + "'";
            if (whereConditions.length > 0) {
                updateSql += " WHERE ";
                for (int i = 0; i < whereConditions.length; i++) {
                    updateSql += whereConditions[i];
                    if (i < whereConditions.length - 1) {
                        updateSql += " AND ";
                    }
                }
            }
            db.executeUpdate(updateSql);
        }*/

        @Override
        public boolean isCellEditable(int row, int col) {
            // allow editing of all cells
            return true;
        }
    }


    public static void main(String[] args) {
        adminBuses ventana = new adminBuses();
    }
}
