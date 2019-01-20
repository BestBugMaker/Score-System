package dao;

import java.sql.*;

public class GetLargeDao {
    private String[] largelist = new String[2];

    public String[] GetLargeDetail(String ExamPaperNo) throws Exception{
        Connection conn = new dbDao().initConnection();
        String sql = "select LargeCount, ExamTotalScore from cj1_testpaperdetail where ExamPaperNo=?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ExamPaperNo);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
               String largecount = rs.getString("LargeCount");
               String examtotalscore = rs.getString("ExamTotalScore");
               largelist[0] = largecount;
               largelist[1] = examtotalscore;
            }
            conn.close();
            rs.close();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return largelist;
    }
}
