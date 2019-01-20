package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherUpdateSelectExamDao {
    private String result="0";

    public String Sel(String exampaperno, String classno) throws Exception{
        Connection conn = new dbDao().initConnection();
        String sql = "select * from cj1_paperstate where ExamPaperNo=? and ClassNo=? and ReleaseState=0";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, exampaperno);
            ps.setString(2, classno);
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
