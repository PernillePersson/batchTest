import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author smsj
 */
public class DBConnection {
    Connection con;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://EASV-DB4:1433;database=BankAccount_PKP;userName=CSe2022t_t_1;password=CSe2022tT1#;encrypt=true;trustServerCertificate=true");
            System.out.println("Connected to database..");
            return con;
        } catch (SQLException e){
            System.err.println("Cannot create connection " + e.getMessage());
        }
        return null;
    }
}
