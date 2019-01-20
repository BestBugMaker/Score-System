package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertPaperDetailDao {
    public void Insert(String ExamPaperNo, String LargeCount, String ExamTotalScore) throws Exception{
        Connection conn = new dbDao().initConnection();
        String sql = "insert into cj1_testpaperdetail values(?,?,?,?,?,?)";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ExamPaperNo);
            ps.setString(2, ExamPaperNo.substring(9));
            ps.setString(3, ExamPaperNo.substring(5, 6));
            ps.setString(4, ExamPaperNo.substring(0, 9));
            ps.setString(5, LargeCount);
            ps.setString(6, ExamTotalScore);
            ps.executeUpdate();
            ps.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
