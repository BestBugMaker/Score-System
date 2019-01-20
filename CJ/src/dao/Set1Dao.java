package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Set1Dao {

    public void Set(String ExamPaperNo, String ClassNo) throws Exception{
        Connection conn = new dbDao().initConnection();
        String sql = "update cj1_paperstate set ReleaseState=1 where ExamPaperNo=? and ClassNo=?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ExamPaperNo);
            ps.setString(2, ClassNo);
            ps.executeUpdate();
            ps.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
