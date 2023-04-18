package Dbconnection;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GestorDB {
    static final String SERVER_IP = "localhost";
    static final String DB_NAME = "orcl";
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@//" + SERVER_IP + ":1521/" + DB_NAME;
    static final String USER = "XLR8O";
    static final String PASS = "XLR8";
    private Connection conn;
    private Statement st;
    private ResultSet resu;

    public GestorDB() {
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("driver done1 100%");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("connected 100%");

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeDB() {
        try {
            conn.close();
            System.out.println("connection closed");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(String tableName, Map<String, Object> data) {
        String insertStat = "insert into " + tableName + "(";
        String values = " values (";
        PreparedStatement ps;
        int index = 1;
        try {
            for (String column : data.keySet()) {
                insertStat = insertStat + column + " , ";
                values = values + "?,";
            }
            insertStat = insertStat.substring(0, (insertStat.length() - 2)) + ")";
            values = values.substring(0, (values.length() - 1)) + ")";
            insertStat = insertStat + values;
            ps = conn.prepareStatement(insertStat);
            for (Object valor : data.values()) {
                ps.setObject(index, valor);
                index++;
            }
            ps.executeUpdate();
            System.out.println("DATA INSERTED 100%");
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] arg) {
        GestorDB DB = new GestorDB();
        /**
         *This code uses the SimpleDateFormat class in Java to convert a date string from one format to another.
         *
         * The code first initializes three variables: format, myDate, and formattedDate, all of which are initially set to null.
         *
         * The try block begins by creating a new instance of the SimpleDateFormat class and assigning it to the format variable.
         * The format string "dd-MMM-yyyy" specifies that the date should be formatted with the day of the month followed by
         * abbreviation for the month (e.g., "18-04-2023").
         *
         * The next line of code uses another instance of SimpleDateFormat to parse a date string in the format "yyyy-MM-dd". The date string "2023-04-18"
         * is passed to the parse method, which returns a Date object representing that date. This Date object is assigned to the myDate variable.
         *
         * Finally, the code formats the date by calling the format method on the format object, passing in the Date object as an argument. The resulting
         * string is assigned to the formattedDate variable.
         * */
        SimpleDateFormat formatchnger = null;
        Date myDate = null;
        String formattedDate=null;
        try {
            formatchnger = new SimpleDateFormat("dd-MMM-yyyy");
            myDate = new SimpleDateFormat("yyyy-MM-dd").parse("2023-04-18");
             formattedDate = formatchnger.format(myDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("NUEMP", 17);
        data.put("FECHA_CONTRATO", formattedDate);
        data.put("DNI", 1548740);
        data.put("NOMBRE_APELLIDO", "test from java");
        data.put("JEFE", 16);
        //System.out.println(data.keySet());
        //System.out.println(data.values());
        DB.insert("EMPLEADOS", data);
    }
}
