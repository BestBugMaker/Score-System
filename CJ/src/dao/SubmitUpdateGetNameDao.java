package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubmitUpdateGetNameDao {
    private String name;

    public String Sel(String TeacherNo) throws Exception{
        Connection conn = new dbDao().initConnection();
        String sql1 = "select * from V_CJ1_Teacher where TeacherNo =?";
        try{
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1,TeacherNo);
            ResultSet rs1 = ps1.executeQuery();
            while(rs1.next()){
                name = rs1.getString("TeacherName");
            }
         ps1.close();
         rs1.close();
         conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return name;
    }
}
