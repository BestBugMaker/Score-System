package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchStateSet2Dao {

    public void Set(String batchno) throws Exception{
        Connection conn = new dbDao().initConnection();
        String sql1 = "update cj1_batchstate set BatchState=2 where BatchNo=? ";
        try{
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1, batchno);
            ps1.executeUpdate();
            ps1.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}

