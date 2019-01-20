package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BatchStateSet2SelectScoreDao {
    private List scorelist = new ArrayList();

    public List Sel(String batchno) throws Exception{
        Connection conn = new dbDao().initConnection();
        String sql1 = "select * from cj1_batchstate where BatchNo=? and BatchState=1";
        String sql2 = "select StudentNo,sum(Score) as totalscore  from cj1_singlescore where TestPaperNo like ? group by StudentNo order by totalscore desc ";
        String sql3 = "select Sname from V_CJ1_Student where Sno=?";
        try{
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1, batchno);
            ResultSet rs1 = ps1.executeQuery();
            while(rs1.next()){
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ps2.setString(1, batchno + "%");
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()){
                    String studentno = rs2.getString("StudentNo");
                    String totalscore = rs2.getString("totalscore");
                    PreparedStatement ps3 = conn.prepareStatement(sql3);
                    ps3.setString(1, studentno);
                    ResultSet rs3 = ps3.executeQuery();
                    while(rs3.next()){  //获取学生成绩
                        List singlelist = new ArrayList();
                        String sname = rs3.getString("Sname");
                        singlelist.add(studentno);
                        singlelist.add(sname);
                        singlelist.add(totalscore);
                        scorelist.add(singlelist);
                    }
                    ps3.close();
                    rs3.close();
                }
                ps2.close();
                rs2.close();
            }
            ps1.close();
            rs1.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return scorelist;
    }
}
