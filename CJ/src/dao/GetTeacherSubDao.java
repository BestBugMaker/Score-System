package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GetTeacherSubDao {

    public String SelTeacherGrade(String teacherno) throws Exception{
        Connection conn = new dbDao().initConnection();
        String teachersub = "";
        String sql = "select * from V_CJ1_Teacher where TeacherNo=?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,teacherno);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            teachersub = rs.getString("TeacherSub");
        }
        return teachersub;
    }
}
