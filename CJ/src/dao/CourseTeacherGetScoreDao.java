package dao;

import com.opensymphony.xwork2.ActionContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CourseTeacherGetScoreDao {

    public List SelScore(String batchno,String classno) throws Exception{
        Connection conn = new dbDao().initConnection();
        List list = new ArrayList();                    //最后的输出
        Map session = ActionContext.getContext().getSession();
        String teacherid = (String)session.get("TeacherNo");
        String teachersub ="";
        String exampaperno;
        String studentno;
        String studentname;
        String singlrscore = "";


        String sql1 = "select * from V_CJ1_Teacher where TeacherNo =?";
        String sql2 = "select * from cj1_batchstate where BatchNo=? and BatchState=3";
        String sql3 = "select * from V_CJ1_Student where ClassNo=?";
        String sql4 = "select * from cj1_score where StudentNo=? and TestPaperNo =?";
        String sql5 = "select * from cj1_singlescore where StudentNo=? and TestPaperNo=?";

        try {
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1,teacherid);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()){
                teachersub = rs1.getString("TeacherSub");           //查询老师教的学科
            }
            exampaperno = batchno + teachersub;
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setString(1,batchno);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()){
                PreparedStatement ps3 = conn.prepareStatement(sql3);
                ps3.setString(1,classno);
                ResultSet rs3 = ps3.executeQuery();
                while (rs3.next()){
                    List list1 = new ArrayList();                           //存放学生小分
                    studentno = rs3.getString("Sno");
                    studentname = rs3.getString("Sname");
                    list1.add(studentno);
                    list1.add(studentname);

                    PreparedStatement ps4 = conn.prepareStatement(sql4);
                    ps4.setString(1,studentno);
                    ps4.setString(2,exampaperno);
                    ResultSet rs4 = ps4.executeQuery();
                    while (rs4.next()){
                        String smallscore = rs4.getString("SmallScore");
                        list1.add(smallscore);
                    }

                    if (list1.size() > 2){
                        PreparedStatement ps5 = conn.prepareStatement(sql5);
                        ps5.setString(1,studentno);
                        ps5.setString(2,exampaperno);
                        ResultSet rs5 = ps5.executeQuery();
                        while (rs5.next()){
                            singlrscore = rs5.getString("Score");
                        }
                        list1.add(singlrscore);
                        list.add(list1);
                    }
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }


        return list;
    }
}
