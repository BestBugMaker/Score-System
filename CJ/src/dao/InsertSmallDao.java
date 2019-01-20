package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertSmallDao {
    public void insert(String ExamPaperNo, String largeno, String[] Small, String[] Smalltotalscore) throws Exception{
        Connection conn = new dbDao().initConnection();
        String sql = "insert into cj1_smalldetail values(?,?,?,?)";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            for(int i=0; i<Small.length; i++){
                ps.setString(1, ExamPaperNo);
                ps.setString(2, largeno);
                ps.setString(3, Small[i]);
                ps.setString(4, Smalltotalscore[i]);
                ps.executeUpdate();
            }
            ps.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
