package database.driver;


import java.sql.Connection;
import java.sql.DriverManager;

public class Driver {

    private static Driver driver = null;
    private Connection connection = null;

    private static final String MYSQL_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost/QueryFilter?user=root&password=MySQLroot";

    private Driver() {}

    public static Driver getInstance() {
        if(driver==null) {
            synchronized (Driver.class) {
                if(driver==null) {
                    driver = new Driver();
                    try {
                        Class.forName(MYSQL_JDBC_DRIVER);
                    } catch(Exception e) {
                        //
                    }
                }
            }
        }
        return driver;
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DATABASE_URL);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
