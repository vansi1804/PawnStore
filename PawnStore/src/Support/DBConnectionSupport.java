package Support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressWarnings({"ClassWithoutLogger", "UtilityClassWithoutPrivateConstructor"})
public class DBConnectionSupport {

    private static final String CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DB_NAME = "pawn_store";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "rootroot";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME;

    @SuppressWarnings("CallToPrintStackTrace")
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(CLASS_NAME);
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            MessageSupport.Message("Message", "Connect failded");
        }
        return conn;
    }
}
