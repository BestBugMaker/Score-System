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

public class GradeLeaderGetClassScoreDao {

    public List SelClassScore(String batchno, String classno) throws Exception{
        Connection conn = new dbDao().initConnection();
        Map session = ActionContext.getContext().getSession();
        String teacherid = (String)session.get("TeacherNo");
        String studentno;
        String studentname;
        String singlescore;
        String exampaperno;
        List list = new ArrayList();    //最后返回的总list
        List list1 = new ArrayList();    //存放列名 如：学号 姓名 语文 数学 英语
        list1.add("学号");
        list1.add("姓名");
        String sql1 = "select * from cj1_batchstate where BatchNo=? and BatchState=3";
        String sql2 = "select * from V_CJ1_Student where ClassNo=?";
        String sql3 = "select * from cj1_singlescore where StudentNo=? and TestPaperNo like ?";
        String sql4 = "select * from V_CJ1_Program where subNo=? and gradeNo=?";
        String sql5 = "select (@rowNO:=@rowNO+1) as rowno, a.* from(select StudentNo,sum(Score) as totalscore  from " +
                "cj1_singlescore where TestPaperNo like ? group by StudentNo)a, (SELECT @rowNO:=0)b order by " +
                "a.totalscore desc";   //查询年级排名
        try {
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1, batchno);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()){
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ps2.setString(1, classno);
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()){
                    List list2 = new ArrayList();                       //存放每个学生具体分数
                    Double totalscore = 0.0;                            //学生总分
                    studentno = rs2.getString("Sno");
                    list2.add(studentno);
                    studentname = rs2.getString("Sname");
                    list2.add(studentname);
                    PreparedStatement ps3 = conn.prepareStatement(sql3);
                    ps3.setString(1, studentno);
                    ps3.setString(2,batchno + "%");
                    ResultSet rs3 = ps3.executeQuery();
                    while (rs3.next()){    //查询每个学生的成绩
                        singlescore = rs3.getString("Score");
                        exampaperno = rs3.getString("TestPaperNo");
                        list2.add(singlescore);
                        totalscore += Double.parseDouble(singlescore);
                        PreparedStatement ps4 = conn.prepareStatement(sql4);
                        ps4.setString(1, exampaperno.substring(9, 11));
                        ps4.setString(2, exampaperno.substring(5, 6));
                        ResultSet rs4 = ps4.executeQuery();
                        while (rs4.next()){
                            String subname = rs4.getString("name");
                            if (list1.contains(subname)){    //判断list1知否已存在同样的科目名
                            }
                            else {
                                list1.add(subname);
                            }
                        }
                        rs4.close();
                        ps4.close();
                    }
                    rs3.close();
                    ps3.close();
                    DecimalFormat df = new DecimalFormat("0.0");
                    list2.add(df.format(totalscore));
                    if (list.contains(list1)){    //判断list1是否已在list中 因为每一个学生都会做一次这个循环
                    }
                    else {
                        list1.add("总分");
                        list1.add("年级排名");
                        list.add(list1);
                    }
                    PreparedStatement ps5 = conn.prepareStatement(sql5);
                    ps5.setString(1,batchno + "%");
                    ResultSet rs5 = ps5.executeQuery();
                    while (rs5.next()){
                        if (rs5.getString("StudentNo"). equals(studentno)){
                            list2.add(rs5.getString("rowno"));
                            break;
                        }
                    }
                    ps5.close();
                    rs5.close();
                    list.add(list2);
                }
                rs2.close();
                ps2.close();
            }
            conn.close();
            rs1.close();
            ps1.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

}
