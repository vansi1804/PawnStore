
package Support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionSupport {
    
    private static final String dbURL = Encoding.decrypt("XhkyIDs7xAYcdL+nR/w/0mjEIUBLDYb3NjMKn9hXELtSb9bCUhpCQjDia7JqnWpFJLHOrAVyUXQb95zeIA65/APo73NKJWBsuXECEE+iW+Y=");
    private static final String Username = Encoding.decrypt("KVsdBApkCcl1kSIlQ6Bung==");
    private static final String Password = Encoding.decrypt("KVsdBApkCcl1kSIlQ6Bung==");
    
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(dbURL, Username, Password);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }
}
