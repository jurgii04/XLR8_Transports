package Windows;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;
import com.formdev.flatlaf.FlatLightLaf;

public class adminBuses extends JFrame {
    private JTable tablaViajes;
    private ViajesModeloTabla modeloTabla;

    public adminBuses() {




        super("Ventana de Viajes");

        FlatLightLaf.install();




        // Crear el modelo de tabla personalizado y agregar los datos iniciales
        modeloTabla = new ViajesModeloTabla();
        modeloTabla.agregarViaje(new Viaje("Madrid", "Barcelona", "25/04/2023", 50.0));
        modeloTabla.agregarViaje(new Viaje("Barcelona", "Valencia", "26/04/2023", 30.0));

        // Crear la tabla y establecer el modelo de tabla personalizado
        tablaViajes = new JTable(modeloTabla);

        // Crear los botones para insertar, actualizar y eliminar filas
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modeloTabla.agregarViaje(new Viaje("", "", "", 0.0));
            }
        });

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaViajes.getSelectedRow();
                if (filaSeleccionada != -1) {
                    modeloTabla.actualizarViaje(filaSeleccionada);
                }
            }
        });

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaViajes.getSelectedRow();
                if (filaSeleccionada != -1) {
                    modeloTabla.eliminarViaje(filaSeleccionada);
                }
            }
        });

        // Crear un panel para los botones y agregarlos
        JPanel panelBotones = new JPanel(new GridLayout(1, 3));
        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        JPanel tablaslist=new JPanel();
        JComboBox<String> lista=new JComboBox<>(new String[]{"tabla1","tabla2","tabla3","tabla4","tabla5","tabla6"});
        tablaslist.add(lista,BorderLayout.CENTER);

        // Agregar la tabla y el panel de botones a la ventana principal
        add(tablaslist, BorderLayout.NORTH);
        add(new JScrollPane(tablaViajes), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Establecer el tamaño de la ventana y hacerla visible
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Clase interna para el modelo de tabla personalizado
    private class ViajesModeloTabla extends AbstractTableModel {
        private String[] columnas = {"Origen", "Destino", "Fecha", "Precio"};
        private Object[][] datos = {};

        public int getColumnCount() {
            return columnas.length;
        }

        public int getRowCount() {
            return datos.length;
        }

        public String getColumnName(int col) {
            return columnas[col];
        }

        public Object getValueAt(int fila, int col) {
            return datos[fila][col];
        }

        public boolean isCellEditable(int fila, int col) {
            return true;
        }

        public void setValueAt(Object valor, int fila, int col) {
            datos[fila][col] = valor;
            fireTableCellUpdated(fila, col);
        }

        public void agregarViaje(Viaje viaje) {
            Object[] fila = {viaje.getOrigen(), viaje.getDestino(), viaje.getFecha(), viaje.getPrecio()};
            datos = agregarFila(datos, fila);
            fireTableDataChanged();
        }

        public void actualizarViaje(int fila) {
            Viaje viaje = new Viaje(
                    (String)getValueAt(fila, 0),
                    (String)getValueAt(fila, 1),
                    (String)getValueAt(fila, 2),
                    (Double)getValueAt(fila, 3)
            );
            // Lógica para actualizar los datos en la base de datos
            fireTableDataChanged();
        }

        public void eliminarViaje(int fila) {
            datos = eliminarFila(datos, fila);
            fireTableDataChanged();
        }

        private Object[][] agregarFila(Object[][] datos, Object[] fila) {
            Object[][] nuevosDatos = new Object[datos.length + 1][4];
            for (int i = 0; i < datos.length; i++) {
                for (int j = 0; j < 4; j++) {
                    nuevosDatos[i][j] = datos[i][j];
                }
            }
            nuevosDatos[datos.length] = fila;
            return nuevosDatos;
        }

        private Object[][] eliminarFila(Object[][] datos, int fila) {
            Object[][] nuevosDatos = new Object[datos.length - 1][4];
            int indice = 0;
            for (int i = 0; i < datos.length; i++) {
                if (i != fila) {
                    for (int j = 0; j < 4; j++) {
                        nuevosDatos[indice][j] = datos[i][j];
                    }
                    indice++;
                }
            }
            return nuevosDatos;
        }
    }

    // Clase interna para representar un viaje
    private class Viaje {
        private String origen;
        private String destino;
        private String fecha;
        private double precio;

        public Viaje(String origen, String destino, String fecha, double precio) {
            this.origen = origen;
            this.destino = destino;
            this.fecha = fecha;
            this.precio = precio;
        }

        public String getOrigen() {
            return origen;
        }

        public String getDestino() {
            return destino;
        }

        public String getFecha() {
            return fecha;
        }

        public double getPrecio() {
            return precio;
        }
    }

    public static void main(String[] args) {
        adminBuses ventana = new adminBuses();
    }
}
