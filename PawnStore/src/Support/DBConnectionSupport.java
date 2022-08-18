
package Support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressWarnings({"ClassWithoutLogger", "UtilityClassWithoutPrivateConstructor"})
public class DBConnectionSupport {
    
    private static final String dbURL = EncodingSupport.decrypt("XhkyIDs7xAYcdL+nR/w/0mjEIUBLDYb3NjMKn9hXELtSb9bCUhpCQjDia7JqnWpFJLHOrAVyUXQb95zeIA65/APo73NKJWBsuXECEE+iW+Y=");
    private static final String Username = EncodingSupport.decrypt("KVsdBApkCcl1kSIlQ6Bung==");
    private static final String Password = EncodingSupport.decrypt("KVsdBApkCcl1kSIlQ6Bung==");
    
    @SuppressWarnings("CallToPrintStackTrace")
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(dbURL, Username, Password);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            MessageSupport.Message("Message", "Connect failded");
        }
        return conn;
    }
}
