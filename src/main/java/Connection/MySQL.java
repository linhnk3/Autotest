package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {
        public static Connection getMySQLConnection() {
            String hostName = "10.0.0.23";
            String dbName = "vnsc_datafeed";
            String userName = "finhay";
            String password = "finhay1414";
            return getMySQLConnection(hostName, dbName, userName, password);

        }

        public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password) {
            Connection conn = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
                conn = DriverManager.getConnection(connectionURL, userName, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return conn;
        }


    public Connection extracted(MySQL query) throws SQLException, ClassNotFoundException {
        String hostName = "10.0.0.23";
        String userName = "finhay";
        String password = "finhay1414";
        String dbName = "vnsc_datafeed";
        return query.getMySQLConnection(hostName, dbName, userName, password);
    }
        // Send data query to Database or data input to DB ==> Create_Query
        public static void CreateQuery(String sql) throws Exception {
            MySQL.getMySQLConnection().createStatement();
            System.out.println(sql);

        }

        // Excutes_query --> from created query
        public static void ExcuteQuery(String sql) throws Exception {

            ResultSet result = MySQL.getMySQLConnection().createStatement().executeQuery(sql);
            result.beforeFirst();
            result.next();
            String foundType = result.getString(1);
            System.out.println(foundType);

        }
    }


