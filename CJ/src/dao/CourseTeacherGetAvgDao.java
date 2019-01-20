package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseTeacherGetAvgDao {
    private String avgscore;

    public String Sel(String exampaperno, String classno) throws Exception{
        Connection conn = new dbDao().initConnection();
        String sql = "SELECT TestPaperNo, avg(score) as avgscore from cj1_singlescore where TestPaperNo=? and StudentNo LIKE ?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, exampaperno);
            ps.setString(2, classno + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                avgscore = rs.getString("avgscore");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return avgscore;
    }
}
