package dao;

import com.opensymphony.xwork2.ActionContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GradeLeaderGetScoreDao {

    public List SelGradeScore(String batchno) throws Exception{
        Connection conn = new dbDao().initConnection();
        Map session = ActionContext.getContext().getSession();

        String teachergrade = "";
        String studentno;
        String studentname;
        String singlescore;
        String exampaperno;
        List list = new ArrayList();            //最后的输出
        List list1 = new ArrayList();           //存放列名 如：学号 姓名 语文 数学 英语
        list1.add("学号");
        list1.add("姓名");

        String sql1 = "select * from cj1_batchstate where BatchNo=? and BatchState=3";
        String sql2 = "select * from V_CJ1_Teacher where TeacherNo=?";
        String sql3 = "select * from V_CJ1_Student where GradeNo=?";
        String sql4 = "select * from cj1_singlescore where StudentNo=? and TestPaperNo like ?";
        String sql5 = "select * from V_CJ1_Program where subNo=? and gradeNo=?";
        String sql7 = "select (@rowNO:=@rowNO+1) as rowno, a.* from(select StudentNo,sum(Score) as totalscore  from " +
                "cj1_singlescore where TestPaperNo like ? group by StudentNo)a, (SELECT @rowNO:=0)b order by " +
                "a.totalscore desc";   //查询年级排名

        String teacherno = (String)session.get("TeacherNo");
        try {
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1,batchno);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()){
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ps2.setString(1,teacherno);
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()){
                    teachergrade = String.valueOf(Integer.parseInt(rs2.getString("TeacherGrade")) + 6);
                }
                PreparedStatement ps3 = conn.prepareStatement(sql3);
                ps3.setString(1,teachergrade);
                ResultSet rs3 = ps3.executeQuery();
                while (rs3.next()){                             //遍历年级里的所有学生
                    List list2 = new ArrayList();               //存放每个学生的具体分数
                    Double totalscore = 0.0;                            //学生总分
                    studentno = rs3.getString("Sno");
                    studentname = rs3.getString("Sname");
                    list2.add(studentno);
                    list2.add(studentname);

                    PreparedStatement ps4 = conn.prepareStatement(sql4);
                    ps4.setString(1,studentno);
                    ps4.setString(2,batchno+"%");
                    ResultSet rs4 = ps4.executeQuery();
                    while (rs4.next()){                         //查询每个学生每一科的成绩
                        singlescore = rs4.getString("Score");
                        exampaperno = rs4.getString("TestPaperNo");
                        list2.add(singlescore);

                        totalscore += Double.parseDouble(singlescore);

                        PreparedStatement ps5 = conn.prepareStatement(sql5);
                        ps5.setString(1, exampaperno.substring(9, 11));
                        ps5.setString(2, exampaperno.substring(5, 6));
                        ResultSet rs5 = ps5.executeQuery();
                        while (rs5.next()){                     //查询成绩中文
                            String subname = rs5.getString("name");
                            if (list1.contains(subname)){               //判断list1知否已存在同样的科目名

                            }
                            else {
                                list1.add(subname);
                            }
                        }
                        rs5.close();
                        ps5.close();
                    }
                    DecimalFormat df = new DecimalFormat("0.0");
                    list2.add(df.format(totalscore));

                    if (list.contains(list1)){                          //判断list1是否已在list中 因为每一个学生都会做一次这个循环

                    }
                    else {
                        list1.add("总分");
                        //list1.add("班级排名");
                        list1.add("年级排名");
                        list.add(list1);
                    }

                    PreparedStatement ps7 = conn.prepareStatement(sql7);
                    ps7.setString(1,batchno + "%");
                    ResultSet rs7 = ps7.executeQuery();
                    while (rs7.next()){
                        if (rs7.getString("StudentNo").equals(studentno)){
                            list2.add(rs7.getString("rowno"));
                            break;
                        }
                    }
                    ps7.close();
                    rs7.close();

                    if (list2.size() > 3){                          //除了 学号、姓名、总分外 存在成绩才插入
                        list.add(list2);
                    }

                    ps4.close();
                    rs4.close();
                }
                rs3.close();
                ps3.close();

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;

    }
}
