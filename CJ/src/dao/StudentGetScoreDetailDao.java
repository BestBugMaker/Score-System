package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentGetScoreDetailDao {
    private List scorelist = new ArrayList();

    public ArrayList Sel(String studentno, String exempaperno) throws Exception{
        Connection conn = new dbDao().initConnection();
        String sql = "select * from cj1_score where StudentNo=? and TestPaperNo=?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, studentno);
            ps.setString(2, exempaperno);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String score = rs.getString("SmallScore");
                scorelist.add(score);
            }
            conn.close();
            rs.close();
            ps.close();
        }catch (SQLException e ){
            e.printStackTrace();
        }
        return (ArrayList) scorelist;
    }
}
