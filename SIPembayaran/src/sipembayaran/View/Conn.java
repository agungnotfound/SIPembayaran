package sipembayaran.View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author agungnotfound
 */
public class Conn {

    /**
     * @param args the command line arguments
     */
     public static Connection con;
    public static Statement stm;
    public static void main(String args[]){
        try {
            String url ="jdbc:mysql://localhost/penjualan_db";
            String user="root";
            String pass="";
            Class.forName("com.mysql.jdbc.Driver");
            con =DriverManager.getConnection(url,user,pass);
            stm = con.createStatement();
            System.out.println("koneksi berhasil;");
        } catch (Exception e) {
            System.err.println("koneksi gagal" +e.getMessage());
        }
    }
    
    
}
