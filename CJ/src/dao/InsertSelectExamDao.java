package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertSelectExamDao {
    private String result="0";

    public String Sel(String exampaperno) throws Exception{
        Connection conn = new dbDao().initConnection();
        String sql = "select * from cj1_testpaperdetail where ExamPaperNo=?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, exampaperno);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                result = "1";
            }
            conn.close();
            ps.close();
            rs.close();
        }catch (SQLException e ){
            e.printStackTrace();
        }
        return result;
    }
}
