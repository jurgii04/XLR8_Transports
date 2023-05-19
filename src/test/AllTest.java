package test;
import Dbconnection.GestorDB;
import Objects.*;

import Windows.*;

import Windows.Buses;
import org.junit.Assume;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(Suite.class)
@SuiteClasses({
        GestorDB.class,
        Login.class,
        Encription.class,
        Buses.class,
        Billetes.class,
        Van.class,
        Login.class,
        Hacer.class,
        Ventana.class

})
public class AllTest {
    @BeforeAll
    static void setup() {
        System.out.println("Before all tests");
        GestorDB db=new GestorDB();
    }

    @AfterAll
    static void cleanup() {
        System.out.println("After all tests");
    }

    @DisplayName("Test for Encription")
    @RepeatedTest(3)
    public void testEncription() {

        Encription enc = new Encription();
        String password = "password123";
        String expected = "ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f";
        String actual = enc.encriptar(password);
        assertEquals(expected, actual);
    }
    @Test
    @DisplayName("authenticate Login")
    public void testAuthenticate() {
        /*login loginObj = new login(new JButton(),new JPanel(),true,new JFrame());
        assertTrue(loginObj.authenticate("admin", "admin"));
        assertTrue(loginObj.authenticate("pepe", "pepe"));
        assertFalse(loginObj.authenticate("admin", "password"));
        assertFalse(loginObj.authenticate("pepe", "wrongpassword"));*/
    }
    @EnabledOnOs(OS.WINDOWS)
    @Test
    @DisplayName("WINDOWS OD TEST")
    public void myTest() {
        Assume.assumeTrue(System.getProperty("os.name").startsWith("Windows"));
        assertTrue(true);
    }
    @Test
    //@Disabled
    @DisplayName("iNSERT TEST")
    public void testInsert() {
        GestorDB db=new GestorDB();
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("Matricula", 123456);

        data.put("NUEMP", 16);

        String tableName="CONDUCE_BUS";
        try {
            //db.insert(tableName, data);
        } catch (RuntimeException e) {
            fail("Insert operation failed: " + e.getMessage());
        }

        // verify that the data was inserted correctly
        String[] columnNames = {"Matricula", "NUEMP" };
        String[] whereConditions = {"NUEMP=16"};
        String[] expectedData = {"10001", "16"};

        String[] actualData = db.selectFromTable(tableName, columnNames, whereConditions);
        assertEquals(Arrays.toString(expectedData), Arrays.toString(actualData));
        db.closeDB();
    }
    @Test
    //@Disabled
    @DisplayName("Update TEST")
    public void testUpdate() {
        GestorDB db=new GestorDB();
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("Matricula", 10001);
        String tableName="CONDUCE_BUS";
        try {
            db.update(tableName,"NUEMP=16",data);
        } catch (RuntimeException e) {
            fail("Update operation failed: " + e.getMessage());
        }

        // verify that the data was inserted correctly
        String[] columnNames = {"Matricula" };
        String[] whereConditions = {"NUEMP=16"};
        String[] expectedData = {"10001"};

        String[] actualData = db.selectFromTable(tableName, columnNames, whereConditions);
        assertEquals(Arrays.toString(expectedData), Arrays.toString(actualData));
        db.closeDB();
    }
    @Test
    //@Disabled
    @DisplayName("delete TEST")
    public void testDelete() {
        GestorDB db=new GestorDB();
        int resu=0;

        try {
            resu=db.delete("EMPLEADOS" , "NUEMP in (15 , 17)");
        } catch (RuntimeException e) {
            fail("Update operation failed: " + e.getMessage());
        }
        assertEquals(resu,0);
        db.closeDB();
    }

    @ParameterizedTest
    @CsvSource({"monitor, 5", "keyboard, 20", "mouse, 10" })
    void hasStock(String product, int number) {
        assertNotNull(product);
        assertTrue(number > 0);

    }
    @Test
    @DisplayName("Test  Billetes")
    public void testGettersAndSetters() {
        // Arrange
        int numBillete = 1;
        String fecha = "2023-05-18";
        float precio = 10.5F;
        String tipoPago = "Credit Card";
        String destino = "Destination";
        String origen = "Origin";
        String dni = "1234567890";
        int numViaje = 123;

        // Act
        Billetes billete = new Billetes(numBillete, fecha, precio, tipoPago, destino, origen, dni);
        billete.setNum_viaje(numViaje);

        // Assert
        assertEquals(numBillete, billete.getNum_billete());
        assertEquals(fecha, billete.getFecha());
        assertEquals(precio, billete.getPrecio(), 10.5F);
        assertEquals(tipoPago, billete.getTipo_pago());
        assertEquals(destino, billete.getDestino());
        assertEquals(origen, billete.getOrigen());
        assertEquals(dni, billete.getDNI());
        assertEquals(numViaje, billete.getNum_viaje());
    }
    @Test
    @DisplayName("Test  Buses")
    public void testGetSetMatricula() {
        // Arrange
        BusesO b = new BusesO();
        String expectedMatricula = "ABC123";

        // Act
        b.setMatricula(expectedMatricula);
        String actualMatricula = b.getMatricula();
        int expectedNumAsientos = 50;

        // Act
        b.setNum_asientos(expectedNumAsientos);
        int actualNumAsientos = b.getNum_asientos();

        // Assert
        Assertions.assertEquals(expectedNumAsientos, actualNumAsientos);

        // Assert
        Assertions.assertEquals(expectedMatricula, actualMatricula);
    }
    @Test
    @DisplayName("Test  Camiones")
    void testCamiones() {
        // Create an instance of the Camiones class
        Camiones camion = new Camiones();

        // Set the values
        camion.setMatricula("ABC123");
        camion.setPeso_neto(2000.0f);
        camion.setPeso_total(2500.0f);

        // Verify the values using assertions
        assertEquals("ABC123", camion.getMatricula());
        assertEquals(2000.0f, camion.getPeso_neto());
        assertEquals(2500.0f, camion.getPeso_total());

        // Test modifying the values
        camion.setMatricula("XYZ789");
        camion.setPeso_neto(1800.0f);
        camion.setPeso_total(2200.0f);

        // Verify the modified values
        assertEquals("XYZ789", camion.getMatricula());
        assertEquals(1800.0f, camion.getPeso_neto());
        assertEquals(2200.0f, camion.getPeso_total());
    }
    @Test
    @DisplayName("Test  Clientes")
    public void testClientes() {
        // Create a new instance of Clientes
        Clientes cliente = new Clientes();

        // Set the values for the Clientes object
        cliente.setDNI("123456789");
        cliente.setNombre("John Doe");
        cliente.setEdad(30);
        cliente.setNum_de_asiento(10);

        // Verify the values using assertions
        assertEquals("123456789", cliente.getDNI());
        assertEquals("John Doe", cliente.getNombre());
        assertEquals(30, cliente.getEdad());
        assertEquals(10, cliente.getNum_de_asiento());
    }

    @Test
    @DisplayName("Test  ConduceBus")
    public void testConduceBus() {
        // Create an instance of the ConduceBus class
        ConduceBus conduceBus = new ConduceBus();

        // Set the values using the setter methods
        conduceBus.setMatricula("ABC123");
        conduceBus.setNumemp(123);

        // Get the values using the getter methods
        String matricula = conduceBus.getMatricula();
        int numemp = conduceBus.getNumemp();

        // Assert that the values are set correctly
        assertEquals("ABC123", matricula);
        assertEquals(123, numemp);
    }
    @Test
    @DisplayName("Test  ConduceCamion")
    void testConduceCamion() {
        // Arrange
        ConduceCamion conduceCamion = new ConduceCamion();
        String expectedMatricula = "ABC123";

        // Act
        conduceCamion.setMatricula(expectedMatricula);
        String actualMatricula = conduceCamion.getMatricula();
        int expectedNumemp = 10;

        // Act
        conduceCamion.setNumemp(expectedNumemp);
        int actualNumemp = conduceCamion.getNumemp();

        // Assert
        assertEquals(expectedNumemp, actualNumemp);

        // Assert
        assertEquals(expectedMatricula, actualMatricula);
    }
    @Test
    @DisplayName("Test  Empleados")
    public void testEmpleados() {
        // Create an instance of Empleados
        Empleados empleado = new Empleados();

        // Test the getter and setter methods
        empleado.setNumemp(1001);
        assertEquals(1001, empleado.getNumemp());

        empleado.setDNI("123456789");
        assertEquals("123456789", empleado.getDNI());

        Date fechaContrato = new Date();
        empleado.setFecha_contrato(fechaContrato);
        assertEquals(fechaContrato, empleado.getFecha_contrato());

        empleado.setNombre_apellido("John Doe");
        assertEquals("John Doe", empleado.getNombre_apellido());

        empleado.setJefe(101);
        assertEquals(101, empleado.getJefe());
    }
    @Test
    @DisplayName("Test  Empresa")
    public void testEmpresa() {
        // Create an instance of the Empresa class
        Empresa empresa = new Empresa();

        // Test the default values of the instance variables
        assertEquals(0, empresa.getNum_empresa());
        assertNull(empresa.getLocalizacion());
        assertNull(empresa.getNombre_empresa());

        // Set values for the instance variables
        int numEmpresa = 1;
        String localizacion = "Location";
        String nombreEmpresa = "Company";

        empresa.setNum_empresa(numEmpresa);
        empresa.setLocalizacion(localizacion);
        empresa.setNombre_empresa(nombreEmpresa);

        // Test the updated values
        assertEquals(numEmpresa, empresa.getNum_empresa());
        assertEquals(localizacion, empresa.getLocalizacion());
        assertEquals(nombreEmpresa, empresa.getNombre_empresa());

        // Test changing the values of the instance variables
        int newNumEmpresa = 2;
        String newLocalizacion = "New Location";
        String newNombreEmpresa = "New Company";

        empresa.setNum_empresa(newNumEmpresa);
        empresa.setLocalizacion(newLocalizacion);
        empresa.setNombre_empresa(newNombreEmpresa);

        // Test the updated values again
        assertEquals(newNumEmpresa, empresa.getNum_empresa());
        assertEquals(newLocalizacion, empresa.getLocalizacion());
        assertEquals(newNombreEmpresa, empresa.getNombre_empresa());
    }
    @Test
    @DisplayName("Test  Hacer")
    public void testHacerClass() {
        // Create an instance of the Hacer class
        Hacer hacer = new Hacer();

        // Test initial values
        assertNull(hacer.getDNI());
        assertEquals(0, hacer.getNum_viaje());

        // Set DNI and Num_viaje
        hacer.setDNI("123456789");
        hacer.setNum_viaje(5);

        // Test the updated values
        assertEquals("123456789", hacer.getDNI());
        assertEquals(5, hacer.getNum_viaje());

        // Set DNI to null and Num_viaje to a negative value
        hacer.setDNI(null);
        hacer.setNum_viaje(-1);

        // Test the updated values
        assertNull(hacer.getDNI());
        assertEquals(-1, hacer.getNum_viaje());
    }
    @Test
    @DisplayName("Test  Mercancias")
    public void testMercancias() {
        // Create an instance of Mercancias
        Mercancias mercancias = new Mercancias();

        // Set values for the Mercancias object
        mercancias.setNum_producto(1);
        mercancias.setNum_empresa(100);
        mercancias.setDescripcion("Product A");
        mercancias.setPeso(10.5f);
        mercancias.setPrecio(25.0f);

        // Verify the values using assertions
        assertEquals(1, mercancias.getNum_producto());
        assertEquals(100, mercancias.getNum_empresa());
        assertEquals("Product A", mercancias.getDescripcion());
        assertEquals(10.5f, mercancias.getPeso(), 0.01);
        assertEquals(25.0f, mercancias.getPrecio(), 0.01);

        // Modify some values and verify the changes
        mercancias.setDescripcion("Updated Description");
        mercancias.setPeso(15.0f);

        assertEquals("Updated Description", mercancias.getDescripcion());
        assertEquals(15.0f, mercancias.getPeso(), 0.01);
    }
    @Test
    @DisplayName("Test  Pedidos")
    public void testPedidos() {
        // Create an instance of Pedidos
        Pedidos pedido = new Pedidos();

        // Set values for the pedido object
        pedido.setCodigo(1);
        pedido.setNum_empresa(123);
        pedido.setOrigen("City A");
        pedido.setDestino("City B");
        pedido.setTipo_envio("Express");
        pedido.setMatricula("XYZ123");
        pedido.setPeso_total(10.5f);
        pedido.setDistancia(100.0f);
        pedido.setPrecio(50.0f);
        pedido.setGastos(5.0f);

        // Verify the values using assertions
        assertEquals(1, pedido.getCodigo());
        assertEquals(123, pedido.getNum_empresa());
        assertEquals("City A", pedido.getOrigen());
        assertEquals("City B", pedido.getDestino());
        assertEquals("Express", pedido.getTipo_envio());
        assertEquals("XYZ123", pedido.getMatricula());
        assertEquals(10.5f, pedido.getPeso_total(), 0.001f);
        assertEquals(100.0f, pedido.getDistancia(), 0.001f);
        assertEquals(50.0f, pedido.getPrecio(), 0.001f);
        assertEquals(5.0f, pedido.getGastos(), 0.001f);

        // Modify some values and verify again
        pedido.setCodigo(2);
        pedido.setPeso_total(8.0f);

        assertEquals(2, pedido.getCodigo());
        assertEquals(8.0f, pedido.getPeso_total(), 0.001f);
    }
    @Test
    @DisplayName("Test  TelefonosCli")
    public void testTelefonosCli() {
        // Create an instance of TelefonosCli
        TelefonosCli telefonosCli = new TelefonosCli();

        // Set values for DNI and numeros
        telefonosCli.setDNI("123456789");
        telefonosCli.setNumeros("1234567890");

        // Verify that the values are set correctly
        assertEquals("123456789", telefonosCli.getDNI());
        assertEquals("1234567890", telefonosCli.getNumeros());
    }
    @Test
    @DisplayName("Test  Telefonosemp")
    public void testTelefonosemp() {
        // Create an instance of Telefonosemp
        Telefonosemp telefonosemp = new Telefonosemp();

        // Set the values for DNI and numeros
        telefonosemp.setDNI("123456789");
        telefonosemp.setNumeros("1234567890");

        // Verify that the values are set correctly
        assertEquals("123456789", telefonosemp.getDNI());
        assertEquals("1234567890", telefonosemp.getNumeros());

        // Change the values
        telefonosemp.setDNI("987654321");
        telefonosemp.setNumeros("0987654321");

        // Verify that the values are updated correctly
        assertEquals("987654321", telefonosemp.getDNI());
        assertEquals("0987654321", telefonosemp.getNumeros());
    }
    @Test
    @DisplayName("Test  User")
    public void testUserClass() {
        // Create a User object using the constructor
        String nombre_completo = "John Doe";
        String EMAIL = "johndoe@example.com";
        String FECHA_NACIMIENTO = "1990-01-01";
        String DNI = "123456789";
        String GENERO = "Male";
        String IMG = "profile.jpg";

        User user = new User(nombre_completo, EMAIL, FECHA_NACIMIENTO, DNI, GENERO, IMG);
        // Set values using the setters
        User user1 = new User();


        String DIRECCION = "123 Main St";
        String TELEFONO = "555-1234";
        String SECTOR = "Finance";

        user1.setNombre_completo(nombre_completo);
        user1.setEMAIL(EMAIL);
        user1.setDIRECCION(DIRECCION);
        user1.setDNI(DNI);
        user1.setTELEFONO(TELEFONO);
        user1.setIMG(IMG);
        user1.setSECTOR(SECTOR);

        // Verify the values using the getters
        assertEquals(nombre_completo, user1.getNombre_completo());
        assertEquals(EMAIL, user1.getEMAIL());
        assertEquals(DIRECCION, user1.getDIRECCION());
        assertEquals(DNI, user1.getDNI());
        assertEquals(TELEFONO, user1.getTELEFONO());
        assertEquals(IMG, user1.getIMG());
        assertEquals(SECTOR, user1.getSECTOR());

        // Verify the values using the getters
        assertEquals(nombre_completo, user.getNombre_completo());
        assertEquals(EMAIL, user.getEMAIL());
        assertEquals(FECHA_NACIMIENTO, user.getFECHA_NACIMIENTO());
        assertEquals(DNI, user.getDNI());
        assertEquals(GENERO, user.getGENERO());
        assertEquals(IMG, user.getIMG());
    }
    @Test
    @DisplayName("Test  Van")
    void testVan() {
        // Create a Van object
        Van van = new Van();
        assertEquals(0, van.getNum_empresa());
        assertEquals(0, van.getNum_producto());
        assertEquals(0, van.getCodigo());
        assertEquals(0, van.getCantidad());

        // Set values using setters
        van.setNum_empresa(1);
        van.setNum_producto(2);
        van.setCodigo(3);
        van.setCantidad(4);

        // Verify values using getters
        assertEquals(1, van.getNum_empresa());
        assertEquals(2, van.getNum_producto());
        assertEquals(3, van.getCodigo());
        assertEquals(4, van.getCantidad());
    }
    @Test
    @DisplayName("Test  Vehiculos")
    public void testVehiculos() {
        // Create a Vehiculos object
        Vehiculos vehiculo = new Vehiculos();

        // Test the initial values
        assertNull(vehiculo.getMatricula());
        assertNull(vehiculo.getTipo_seguro());
        assertNull(vehiculo.getFecha_matricula());

        // Set values and test the getters
        vehiculo.setMatricula("ABC123");
        assertEquals("ABC123", vehiculo.getMatricula());

        vehiculo.setTipo_seguro("Full");
        assertEquals("Full", vehiculo.getTipo_seguro());

        Date fechaMatricula = new Date();
        vehiculo.setFecha_matricula(fechaMatricula);
        assertEquals(fechaMatricula, vehiculo.getFecha_matricula());

        // Update values and re-test the getters
        vehiculo.setMatricula("XYZ789");
        assertEquals("XYZ789", vehiculo.getMatricula());

        vehiculo.setTipo_seguro("Third-party");
        assertEquals("Third-party", vehiculo.getTipo_seguro());

        Date newFechaMatricula = new Date();
        vehiculo.setFecha_matricula(newFechaMatricula);
        assertEquals(newFechaMatricula, vehiculo.getFecha_matricula());
    }
    @Test
    @DisplayName("Test  Viajes")
    public void testViajes() {
        // Create an instance of Viajes
        Viajes viaje = new Viajes();

        // Test default values
        assertNull(viaje.getTipo_viaje());
        assertNull(viaje.getDestino());
        assertNull(viaje.getMatricula());
        assertNull(viaje.getOrigen());
        assertEquals(0, viaje.getNum_viaje());
        assertEquals(0, viaje.getDistancia());
        assertEquals(0.0f, viaje.getGastos(), 0.01);
        assertEquals(0.0f, viaje.getPrecio(), 0.01);

        // Test setting values
        viaje.setTipo_viaje("Tipo A");
        viaje.setDestino("Destino X");
        viaje.setMatricula("ABC123");
        viaje.setOrigen("Origen Y");
        viaje.setNum_viaje(123);
        viaje.setDistancia(100);
        viaje.setGastos(50.0f);
        viaje.setPrecio(150.0f);

        // Test getting values
        assertEquals("Tipo A", viaje.getTipo_viaje());
        assertEquals("Destino X", viaje.getDestino());
        assertEquals("ABC123", viaje.getMatricula());
        assertEquals("Origen Y", viaje.getOrigen());
        assertEquals(123, viaje.getNum_viaje());
        assertEquals(100, viaje.getDistancia());
        assertEquals(50.0f, viaje.getGastos(), 0.01);
        assertEquals(150.0f, viaje.getPrecio(), 0.01);
    }
    @Test
    @DisplayName("Test AccountCreationFrame")
    void testCreateButtonAction() {
        AccountCreationFrame frame = new AccountCreationFrame(new JFrame());
        JTextField nameField = getFieldByName(frame, "nameField");
        JTextField lastNameField = getFieldByName(frame, "lastNameField");
        JTextField emailField = getFieldByName(frame, "emailField");
        JFormattedTextField dobField = getFieldByName(frame, "dobField");
        JTextField dniField = getFieldByName(frame, "dniField");
        JRadioButton maleRadioButton = getFieldByName(frame, "maleRadioButton");
        JRadioButton femaleRadioButton = getFieldByName(frame, "femaleRadioButton");
        JRadioButton otroRadioButton = getFieldByName(frame, "otroRadioButton");
        JButton createButton = getFieldByName(frame, "createButton");

        nameField.setText("John");
        lastNameField.setText("Doe");
        emailField.setText("johndoe@example.com");
        dobField.setValue("01-01-1990");
        dniField.setText("123456789");
        maleRadioButton.setSelected(true);

        createButton.getActionListeners()[0].actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));

        // Assertions or verifications based on the expected behavior
        // For example:
        assertEquals("Crear una cuenta", frame.getTitle());
        assertTrue(frame.isVisible());
        // Add more assertions as per your test requirements

    }

    private <T> T getFieldByName(Object obj, String fieldName) {
        try {
            return (T) obj.getClass().getDeclaredField(fieldName).get(obj);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed to access field: " + fieldName, e);
        }
    }
    @Test
    @DisplayName("Test Buses")
    public void testBuses() {
         Buses buses;

        JFrame frame = new JFrame();
        buses = new Buses(frame, new JPanel(), new JPanel(), new JButton(), new GestorDB());


        JComboBox<String> org = new JComboBox<>(new String[]{"Bilbao", "Madrid", "Barcelona"});
        JComboBox<String> dest = new JComboBox<>(new String[]{"Bilbao", "Madrid", "Barcelona"});

        buses.buscar.getActionListeners()[0].actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));

        Ventana.loginstat = true;

        JButton comprarButton = new JButton();
        comprarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verify PayWindow creation
                // You can assert the expected behavior or state of the PayWindow instance
            }
        });

        // Trigger the action performed event on the comprar button
        comprarButton.getActionListeners()[0].actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
        Ventana.loginstat = false;


        comprarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verify LoginWindow creation
                // You can assert the expected behavior or state of the LoginWindow instance
            }
        });

        // Trigger the action performed event on the comprar button
        comprarButton.getActionListeners()[0].actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
    }
    @Test
    @DisplayName("Test ChooseTypeFrame")
    public void testChooseTypeFrame() {
        ChooseTypeFrame chooseTypeFrame = new ChooseTypeFrame();

        // Simulate button clicks
        simulateButtonClick(chooseTypeFrame.personaButton);
        simulateButtonClick(chooseTypeFrame.empresaButton);

        // Get all the windows currently open
        Frame[] frames = Frame.getFrames();

        // Check if AccountCreationFrame and CompanyCreationFrame were created
        boolean accountCreationFrameExists = false;
        boolean companyCreationFrameExists = false;
        for (Frame frame : frames) {
            if (frame instanceof AccountCreationFrame) {
                accountCreationFrameExists = true;
            } else if (frame instanceof CompanyCreationFrame) {
                companyCreationFrameExists = true;
            }
        }

        // Assert that both frames were created
        Assertions.assertTrue(accountCreationFrameExists);
        Assertions.assertTrue(companyCreationFrameExists);
    }
    // Helper method to simulate button click
    private void simulateButtonClick(JButton button) {
        ActionListener[] actionListeners = button.getActionListeners();
        for (ActionListener listener : actionListeners) {
            listener.actionPerformed(new ActionEvent(button, ActionEvent.ACTION_PERFORMED, ""));
        }
    }
    @Test
    @DisplayName("Test CircleImagePanel")
    public void testCircleImagePanel() {
        try {
            // Create a file object for the image file
            File imageFile = new File("src\\Windows\\images\\PerfilFotos\\jurgi.png");

            // Create a new CircleImagePanel
            CircleImagePanel imagePanel = new CircleImagePanel(imageFile);

            // Create a new JFrame
            JFrame frame = new JFrame();

            // Add the panel to the frame
            frame.add(imagePanel);

            // Pack the frame to resize it
            frame.pack();
            frame.setVisible(true);
            // Check if the preferred size of the panel is set correctly
            int expectedWidth = 150;
            int expectedHeight = 150;
            int actualWidth = (int) imagePanel.getPreferredSize().getWidth();
            int actualHeight = (int) imagePanel.getPreferredSize().getHeight();

            assertEquals(expectedWidth, actualWidth,
                    "The preferred width of the panel is incorrect.");
            assertEquals(expectedHeight, actualHeight,
                    "The preferred height of the panel is incorrect.");

            // Check if the frame is visible
            assertTrue("The frame is not visible.", frame.isVisible());

            // Dispose the frame
            frame.dispose();
        } catch (IOException ex) {
            fail("Exception occurred: " + ex.getMessage());
        }
    }
    @org.junit.Test
    public void testCreateButtonClicked_ValidEmail() {
        // Create a CompanyCreationFrame instance
        JFrame parentFrame = new JFrame();
        CompanyCreationFrame frame = new CompanyCreationFrame(parentFrame);

        // Set the text fields with valid data
        frame.companyNameField.setText("Test Company");
        frame.addressField.setText("Test Address");
        frame.emailField.setText("test@example.com");
        frame.dniField.setText("12345678");
        frame.phoneField.setText("123456789");
        frame.sectorField.setText("Test Sector");
        frame.passwordF.setText("testPassword");

        // Simulate the create button click
        frame.createButton.getActionListeners()[0].actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));

        // Check if the parent frame and the current frame are disposed
        assertTrue(parentFrame.isDisplayable());
        assertTrue(frame.isDisplayable());
    }












}



















