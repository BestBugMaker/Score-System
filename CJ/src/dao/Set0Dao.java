package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Set0Dao {

    public void Set(String ExamPaperNo, String ClassNo) throws Exception{
        Connection conn = new dbDao().initConnection();
        String sql = "insert into cj1_paperstate values(?,?,?)";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ExamPaperNo);
            ps.setString(2, ClassNo);
            ps.setString(3, "0");
            ps.executeUpdate();
            ps.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
