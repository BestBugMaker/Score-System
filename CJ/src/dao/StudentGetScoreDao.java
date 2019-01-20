package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class StudentGetScoreDao extends Exception {
    private Map scoremap = new LinkedHashMap();

    public Map Sel(String StudentNo, String BatchNo) throws Exception{
        Connection conn = new dbDao().initConnection();
        String sql1 = "select * from cj1_batchstate where BatchNo=? and BatchState=3";
        String sql2 = "select * from cj1_singlescore where StudentNo=? and TestPaperNo like ?";
        String sql3 = "select * from V_CJ1_Program where subNo=? and gradeNo=?";
        String sql4 = "select (@rowNO:=@rowNO+1) as rowno, a.* from(select StudentNo,sum(Score) as totalscore  from " +
                "cj1_singlescore where TestPaperNo like ? and StudentNo like ? group by StudentNo)a, (SELECT " +
                "@rowNO:=0)b order by a.totalscore desc";   //查询总分和班级排名
        String sql5 = "select (@rowNO:=@rowNO+1) as rowno, a.* from(select StudentNo,sum(Score) as totalscore  from " +
                "cj1_singlescore where TestPaperNo like ? group by StudentNo)a, (SELECT @rowNO:=0)b order by " +
                "a.totalscore desc";   //查询年级排名
        try{
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1, BatchNo);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()){
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ps2.setString(1, StudentNo);
                ps2.setString(2, BatchNo + "%");
                ResultSet rs2 = ps2.executeQuery();
                while(rs2.next()){  //获取学生成绩
                    Map singlemap = new LinkedHashMap();
                    String exampaperno = rs2.getString("TestPaperNo");
                    String score = rs2.getString("Score");
                    PreparedStatement ps3 = conn.prepareStatement(sql3);
                    ps3.setString(1, exampaperno.substring(9));
                    ps3.setString(2, exampaperno.substring(5, 6));
                    ResultSet rs3 = ps3.executeQuery();
                    while(rs3.next()){  //查询成绩的中文
                        String name = rs3.getString("name");
                        name = name.substring(0,name.length()-1);
                        singlemap.put(name, score);
                        scoremap.put(exampaperno, singlemap);
                    }
                    ps3.close();
                    rs3.close();
                }
                ps2.close();
                rs2.close();
            }
            ps1.close();
            rs1.close();
            PreparedStatement ps4 = conn.prepareStatement(sql4);
            ps4.setString(1, BatchNo + "%");
            ps4.setString(2, StudentNo.substring(0, 6) + "%");
            ResultSet rs4 = ps4.executeQuery();
            List ranklist = new ArrayList();
            while (rs4.next()){
                if(rs4.getString("StudentNo").equals(StudentNo)){
                    ranklist.add(rs4.getString("totalscore"));
                    String rowno = rs4.getString("rowno");
                    ranklist.add(rowno.substring(0, rowno.length() - 2));
                }
            }
            ps4.close();
            rs4.close();
            PreparedStatement ps5 = conn.prepareStatement(sql5);
            ps5.setString(1, BatchNo + "%");
            ResultSet rs5 = ps5.executeQuery();
            while(rs5.next()){
                if(rs5.getString("StudentNo").equals(StudentNo)){
                    String rowno = rs5.getString("rowno");
                    ranklist.add(rowno.substring(0, rowno.length() - 2));
                }
            }
            ps5.close();
            rs5.close();
            scoremap.put("总分及排名", ranklist);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return scoremap;
    }
}
