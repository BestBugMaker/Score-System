package dao;

import java.sql.*;

public class Set1GetSubjectDao {
    private String TeacherSub;

    public String Sel(String TeacherNo) throws Exception{
        Connection conn = new dbDao().initConnection();
        String sql = "select * from V_CJ1_Teacher_Program where TeacherNo=?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, TeacherNo);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                TeacherSub = rs.getString("TeacherSub");
            }
            conn.close();
            rs.close();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return TeacherSub;
    }
}
