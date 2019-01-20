package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherUpdateSelectScoreDao {
    List list = new ArrayList();

    public List Sel(String ExamPaperNo, String ClassNo) throws Exception{
        Connection conn = new dbDao().initConnection();
        String sql1 = "select * from V_CJ1_Student where ClassNo=?";
        String sql2 = "select * from cj1_score where StudentNo=? and TestPaperNo =?";
        try{
            PreparedStatement ps1 = conn.prepareStatement(sql1);    //查询学生信息
            ps1.setString(1, ClassNo);
            ResultSet rs1 = ps1.executeQuery();
            while(rs1.next()){
                List list1 = new ArrayList();
                String studentno = rs1.getString("Sno");
                String studentname = rs1.getString("Sname");
                list1.add(studentno);
                list1.add(studentname);
                PreparedStatement ps2 = conn.prepareStatement(sql2);    //查询学生小分
                ps2.setString(1,studentno);
                ps2.setString(2, ExamPaperNo);
                ResultSet rs2 = ps2.executeQuery();
                while(rs2.next()){
                    String smallscore = rs2.getString("SmallScore");
                    list1.add(smallscore);
                }
                list.add(list1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
