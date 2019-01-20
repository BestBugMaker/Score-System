package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertLargeDao {
    public void insert(String ExamPaperNo, String largeno, String largename, String smallcount, String totalscore) throws Exception{
        Connection conn = new dbDao().initConnection();
        String sql = "insert into cj1_largedetail values(?,?,?,?,?)";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ExamPaperNo);
            ps.setString(2, largeno);
            ps.setString(3, largename);
            ps.setString(4, smallcount);
            ps.setString(5, totalscore);
            ps.executeUpdate();
            ps.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
