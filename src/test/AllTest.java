package test;
import Dbconnection.GestorDB;
import Objects.*;

import Windows.*;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(Suite.class)
@SuiteClasses({
        GestorDB.class,
        login.class,
        encription.class,
        buses.class,
        Billetes.class,
        Van.class,
        login.class,
        Hacer.class,
        ventana.class

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

        encription enc = new encription();
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
    @Disabled
    @DisplayName("iNSERT TEST")
    public void testInsert() {
        GestorDB db=new GestorDB();
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("Matricula", 123456);

        data.put("NUEMP", 16);

        String tableName="CONDUCE_BUS";
        try {
            db.insert(tableName, data);
        } catch (RuntimeException e) {
            fail("Insert operation failed: " + e.getMessage());
        }

        // verify that the data was inserted correctly
        String[] columnNames = {"Matricula", "NUEMP" };
        String[] whereConditions = {"NUEMP=16"};
        String[] expectedData = {"123456", "16"};

        String[] actualData = db.selectFromTable(tableName, columnNames, whereConditions);
        assertEquals(Arrays.toString(expectedData), Arrays.toString(actualData));
        db.closeDB();
    }
    @Test
    @Disabled
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
    @Disabled
    @DisplayName("delete TEST")
    public void testDelete() {
        GestorDB db=new GestorDB();
        int resu=0;

        try {
            resu=db.delete("EMPLEADOS" , "NUEMP in (15 , 17)");
        } catch (RuntimeException e) {
            fail("Update operation failed: " + e.getMessage());
        }
        assertEquals(resu,1);
        db.closeDB();
    }

    @ParameterizedTest
    @CsvSource({"monitor, 5", "keyboard, 20", "mouse, 10" })
    void hasStock(String product, int number) {
        assertNotNull(product);
        assertTrue(number > 0);

    }






}








