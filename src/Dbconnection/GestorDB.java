package Dbconnection;
import Windows.CompanyCreationFrame;

import javax.swing.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

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
    private ResultSetMetaData resumeta;

    public GestorDB() {
        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeDB() {
        try {
            conn.close();

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

            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void update(String table_name , String whereStAt , Map<String,Object> data){
        String updateStat="update " + table_name + " set ";
        String values="";
        String where = " where "+whereStAt ;
        PreparedStatement ps;
        for (String column : data.keySet()){
            values = values + column + "=?" + ",";
        }
        values=values.substring(0,(values.length()-1));
        updateStat=updateStat + values +where ;

        int index=1;
        try {

            ps= conn.prepareStatement(updateStat);
            for (Object valor:data.values()){

                ps.setObject(index , valor);
            }

            ps.executeUpdate();
            ps.close();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int delete(String tablename , String WHERE_condition){
        String statment="delete from "+ tablename +" where " + WHERE_condition;
        int rowsDeleted=0;
        try {
            st= conn.createStatement();
             rowsDeleted = st.executeUpdate(statment);
            st.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rowsDeleted;
    }

    public  String[] selectFromTable(String tableName, String[] columnNames, String[] whereConditions) {
        ArrayList<String> data=new ArrayList<>();



        try {


            // Build the SQL query
            String sql = "SELECT DISTINCT ";
            if (columnNames.length == 0) {
                sql += "*";
            } else {
                for (int i = 0; i < columnNames.length; i++) {
                    sql += columnNames[i];
                    if (i < columnNames.length - 1) {
                        sql += ", ";
                    }
                }
            }
            sql += " FROM " + tableName;
            if (whereConditions.length > 0) {
                sql += " WHERE ";
                for (int i = 0; i < whereConditions.length; i++) {
                    sql += whereConditions[i];
                    if (i < whereConditions.length - 1) {
                        sql += " AND ";
                    }
                }
            }


            st = conn.createStatement();
            resu = st.executeQuery(sql);


            while (resu.next()) {
                for (int i = 0; i < columnNames.length; i++) {
                    String columnName = columnNames[i];
                    String columnValue = resu.getString(columnName);
                    //System.out.println(columnName + ": " + columnValue);

                    data.add(columnValue);
                }
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (resu != null) {
                    resu.close();
                }
                if (st != null) {
                    st.close();
                }

            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return data.toArray(new String[data.size()]);
    }
    public String [] logindata(String email){
        String [] data=new String[2];


        try {
            int count = Integer.parseInt(selectFromTable("USERSACCS" , new String[]{"count(EMAIL)"}, new String[]{"EMAIL='"+email+"'"})[0]);
            if (count==0){
                return null;
            }else{
                data=selectFromTable("USERSACCS",new String[]{"TIPO_USER","CONTRASENA"},new String[]{"EMAIL='"+email+"'"} );

            }
        }catch (Exception x){
            throw x;
        }
        return data;
    }


    public static void main(String[] arg) {
        GestorDB DB = new GestorDB();
        //------------------------insert testing
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
        data.put("NUEMP", 16);
        data.put("FECHA_CONTRATO", formattedDate);
        data.put("DNI", 1548740);
        data.put("NOMBRE_APELLIDO", "goat");
        data.put("JEFE", 16);
        //System.out.println(data.keySet());
        //System.out.println(data.values());
        DB.insert("EMPLEADOS", data);
        /*//-------------------------------------update testing----------------------------------
        Map<String, Object> updata = new LinkedHashMap<>();
        updata.put("NOMBRE_APELLIDO" , "lionel messi");
        /*for (Map.Entry<String,Object> datageter : updata .entrySet()){
            //values = values + datageter.getKey() + "=" + datageter.getValue() + ",";
            System.out.println(datageter.getKey() + "=" + datageter.getValue() + ",");
        }

        //DB.update("EMPLEADOS" , "NUEMP=16" , updata);
        //---------------------- delete method testing
        //DB.delete("EMPLEADOS" , "NUEMP in (16 , 17)");
        //------------select method testing
        String[] columnNames = {"NUEMP" ,"NOMBRE_APELLIDO" };
        String[] whereConditions = {"JEFE=3"};*/
        //DB.selectFromTable("EMPLEADOS" , new String[]{"DNI"} , new String[]{});


        System.out.println(Arrays.toString(DB.selectFromTable("EMPLEADOS", new String[]{"DNI"}, new String[]{})));


    }
}
