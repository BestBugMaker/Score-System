package dao;

import com.opensymphony.xwork2.ActionContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

public class SubjectLeaderGetScoreDao {

    public List SelScore(String batchno) throws Exception{
        Connection conn = new dbDao().initConnection();
        List list = new ArrayList();                //整体输出
        Map session = ActionContext.getContext().getSession();
        String teacherid = (String)session.get("TeacherNo");
        String teachersub = "";
        String teachergrade = "";
        String studentno;
        String studentname;
        String exampaperno;
        String singlescore = "";

        String sql1 = "select * from V_CJ1_Teacher where TeacherNo =?";
        String sql2 = "select * from cj1_score where StudentNo=? and TestPaperNo =?";
        String sql4 = "select * from cj1_batchstate where BatchNo=? and BatchState=3";
        String sql5 = "select * from V_CJ1_Student where GradeNo=?";
        String sql6 = "select * from cj1_singlescore where StudentNo=? and TestPaperNo=?";

        try{
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1,teacherid);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()){
                teachersub = rs1.getString("TeacherSub");           //查询老师教的学科
                teachergrade = String.valueOf(Integer.parseInt(rs1.getString("TeacherGrade")) + 6);     //查询教师年级
            }
            exampaperno = batchno + teachersub;

            PreparedStatement ps4 = conn.prepareStatement(sql4);
            ps4.setString(1,batchno);
            ResultSet rs4 = ps4.executeQuery();
            while (rs4.next()) {
                PreparedStatement ps5 = conn.prepareStatement(sql5);
                ps5.setString(1, teachergrade);
                ResultSet rs5 = ps5.executeQuery();
                while (rs5.next()) {
                    List list2 = new ArrayList();                           //存放学生小分
                    studentno = rs5.getString("Sno");
                    studentname = rs5.getString("Sname");
                    list2.add(studentno);
                    list2.add(studentname);

                    PreparedStatement ps2 = conn.prepareStatement(sql2);
                    ps2.setString(1, studentno);
                    ps2.setString(2, exampaperno);
                    ResultSet rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        String smallscore = rs2.getString("SmallScore");
                        list2.add(smallscore);

                    }
                    if (list2.size() > 2){                                  //说明该学生参加了考试（除了学号、姓名之外还有分数）
                        PreparedStatement ps6 = conn.prepareStatement(sql6);
                        ps6.setString(1,studentno);
                        ps6.setString(2,exampaperno);
                        ResultSet rs6 = ps6.executeQuery();
                        while (rs6.next()){
                            singlescore = rs6.getString("Score");           //获取学生单科总分
                        }
                        list2.add(singlescore);

                        list.add(list2);
                    }
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return list;
    }
}
