package kzero.java.hibernate;

/**
 * PostgresJDBC
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.sql.Timestamp;

public class PostgresJDBC {

    public static void main(String[] args) {
        System.out.println("Postgres JDBC Example");
        //initCategory();
        deleteCategory();
        getCategory();
    }

    static void initCategory() {
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());

        String sql = "insert into category (cate_name,cate_last_update)values(?,?)";

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db-tutorial", "postgres",
                "admin")) {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, "Comedy");
            pstm.setTimestamp(2, ts);
            int result = pstm.executeUpdate();
            System.out.println("Record success : " + result);

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    static void getCategory() {
        String sql = "select * from category";

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db-tutorial", "postgres",
                "admin")) {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet result = pstm.executeQuery();
            while (result.next()) {
                System.out.println("CateName : " + result.getString("cate_name"));
            }

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    static void deleteCategory() {
        String sql = "delete from category where cate_id=?";

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db-tutorial", "postgres",
                "admin")) {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, 4);
            int result = pstm.executeUpdate();
            System.out.println("record deleted : "+result);

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }
    
    

    

}