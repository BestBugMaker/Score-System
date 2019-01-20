package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Set2Dao {
    public void Set(String pinjie) throws Exception{
        Connection conn = new dbDao().initConnection();
        String sql1 = "update cj1_paperstate set ReleaseState=2 where ExamPaperNo like ? ";
        String sql2 = "insert into cj1_batchstate values(?,?)";
        try{
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1, pinjie+"%");
            ps1.executeUpdate();
            ps1.close();
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setString(1, pinjie);
            ps2.setString(2, "1");
            ps2.executeUpdate();
            ps2.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
