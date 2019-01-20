package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class HeadTeacherGetAvgDao {
    private Map avgmap = new LinkedHashMap();
    private String classno;

    public Map Sel(String batchno, String teacherno) throws Exception{
        Connection conn = new dbDao().initConnection();
        String sql1 = "select ClassNo from V_CJ1_Class where TeacherNo=?";  //查询班级号
        String sql2 = "select TestPaperNo, avg(score) as avgscore from cj1_singlescore where TestPaperNo like ? and " +
                "StudentNo like ? group by TestPaperNo";
        String sql3 = "select name from V_CJ1_Program where subNo=? and gradeNo=?"; //查询科目名
        try{
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1, teacherno);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()){
                classno = rs1.getString("ClassNo");
                System.out.print(classno);
            }
            ps1.close();
            rs1.close();
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setString(1, batchno + "%");
            ps2.setString(2, classno + "%");
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()){
                String avgscore = rs2.getString("avgscore");
                PreparedStatement ps3 = conn.prepareStatement(sql3);
                ps3.setString(1, rs2.getString("TestPaperNo").substring(9));
                ps3.setString(2, rs2.getString("TestPaperNo").substring(5, 6));
                ResultSet rs3 = ps3.executeQuery();
                while (rs3.next()){
                    String name = rs3.getString("name");
                    name = name.substring(0,name.length()-1);
                    avgmap.put(name, avgscore);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return avgmap;
    }
}
