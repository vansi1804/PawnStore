
package Support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionSupport {
    
    private static final String dbURL = "jdbc:MSSQL_SERVER://localhost:1434;"
                                    + "databaseName=PawnShop;"
                                    + "integratedSecurity=true";
    private static final String UserName = "sa";
    private static final String PassWord = "sa";
    
    
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(dbURL, UserName, PassWord);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }
}
