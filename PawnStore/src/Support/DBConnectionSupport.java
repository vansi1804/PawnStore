
package Support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressWarnings({"ClassWithoutLogger", "UtilityClassWithoutPrivateConstructor"})
public class DBConnectionSupport {
    
//    private static final String dbURL = EncodingSupport.decrypt("XhkyIDs7xAYcdL+nR/w/0mjEIUBLDYb3NjMKn9hXELtSb9bCUhpCQjDia7"
//            + "JqnWpFm3hg5IVGji8WqzHMQ+0HGpRARjiz0ht3O2TcDMn4Ccmyhog/ClLFatSiTrjKVHqbEkc9FJZgdY4JP/VVRu5sbtRPaZPwcUvlVMWbgsHY9mM=");
//    private static final String Username = EncodingSupport.decrypt("KVsdBApkCcl1kSIlQ6Bung==");
//    private static final String Password = EncodingSupport.decrypt("KVsdBApkCcl1kSIlQ6Bung==");
    
    private static final String dbURL = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=PawnStores;"
            + "encrypt=true;"
            + "trustServerCertificate=true;"
            + "sslProtocal=TLSv1.2";
    private static final String Username = "sa";
    private static final String Password = "sa";
    
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
