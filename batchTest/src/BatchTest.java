import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author smsj
 */
public class BatchTest {

    public static void main(String[] args) throws Exception {
        //doBatchProcPrepared(10);
        //doCSV();
    }

    private static void doBatchProcPrepared(int iterations) {
        try(Connection con = DBConnection.getConnection()) {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO [User] VALUES (?,?)");
            con.setAutoCommit(false);

            for (int i = 0; i < iterations; i++) {
                stmt.setString(1, "Username" + i);
                stmt.setString(2, "Password" + i);

                stmt.addBatch();
            }
            stmt.executeBatch();
            con.commit();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void doCSV() {
        try(Connection con = DBConnection.getConnection()) {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO [User] VALUES (?,?)");
            con.setAutoCommit(false);
            Scanner sc = new Scanner(new File("/Users/pernillepersson/IdeaProjects/batchTest/src/cvsTest.csv"));
            sc.useDelimiter(",");

            while (sc.hasNext()){
                //System.out.println("test" + sc.next());
                //System.out.println("test1" + sc.next());
                stmt.setString(1, sc.next());
                stmt.setString(2, sc.next());
                stmt.addBatch();
            }
            sc.close();
            stmt.executeBatch();
            con.commit();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

}