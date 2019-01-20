package dao;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HeadTeacherGetScoreDao {

    public List SelScore(String batchno) throws Exception{
        Connection conn = new dbDao().initConnection();
        Map session = ActionContext.getContext().getSession();
        String classno = "";
        String studentid;
        String studentname;
        String singlescore;
        String exampaperno;
        List list = new ArrayList();                //最后返回的总list
        List list1 = new ArrayList();               //存放列名 如：学号 姓名 语文 数学 英语
        list1.add("学号");
        list1.add("姓名");
        String teacherid = (String)session.get("TeacherNo");
        String sql1 = "select * from cj1_batchstate where BatchNo=? and BatchState=3";
        String sql2 = "select * from XJ_Class where TeacherNo=?";
        String sql3 = "select * from V_CJ1_Student where ClassNo=?";
        String sql4 = "select * from cj1_singlescore where StudentNo=? and TestPaperNo like ?";
        String sql5 = "select * from V_CJ1_Program where subNo=? and gradeNo=?";
        String sql6 = "select (@rowNO:=@rowNO+1) as rowno, a.* from(select StudentNo,sum(Score) as totalscore  from " +
                "cj1_singlescore where TestPaperNo like ? and StudentNo like ? group by StudentNo)a, (SELECT " +
                "@rowNO:=0)b order by a.totalscore desc";   //查询总分和班级排名
        String sql7 = "select (@rowNO:=@rowNO+1) as rowno, a.* from(select StudentNo,sum(Score) as totalscore  from " +
                "cj1_singlescore where TestPaperNo like ? group by StudentNo)a, (SELECT @rowNO:=0)b order by " +
                "a.totalscore desc";   //查询年级排名
        try {
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1, batchno);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()){
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ps2.setString(1, teacherid);
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()){
                    classno = rs2.getString("ClassNo");         //获取班级 只做一次
                }
                ps2.close();
                rs2.close();
                PreparedStatement ps3 = conn.prepareStatement(sql3);
                ps3.setString(1,classno);
                ResultSet rs3 = ps3.executeQuery();
                while (rs3.next()){                                     //查询班级里的学生
                    List list2 = new ArrayList();                       //存放每个学生具体分数
                    Double totalscore = 0.0;                            //学生总分
                    studentid = rs3.getString("Sno");
                    list2.add(studentid);
                    studentname = rs3.getString("Sname");
                    list2.add(studentname);
                    PreparedStatement ps4 = conn.prepareStatement(sql4);
                    ps4.setString(1, studentid);
                    ps4.setString(2, batchno+"%");
                    ResultSet rs4 = ps4.executeQuery();
                    while(rs4.next()){                                  //查询每个学生的成绩
                        singlescore = rs4.getString("Score");
                        exampaperno = rs4.getString("TestPaperNo");
                        list2.add(singlescore);
                        totalscore += Double.parseDouble(singlescore);
                        PreparedStatement ps5 = conn.prepareStatement(sql5);
                        ps5.setString(1, exampaperno.substring(9, 11));
                        ps5.setString(2, exampaperno.substring(5, 6));
                        ResultSet rs5 = ps5.executeQuery();
                        while (rs5.next()){                             //查询成绩中文
                            String subname = rs5.getString("name");
                            if (list1.contains(subname)){               //判断list1知否已存在同样的科目名
                            }
                            else {
                                list1.add(subname);
                            }
                        }
                        ps5.close();
                        rs5.close();
                    }
                    DecimalFormat df = new DecimalFormat("0.0");
                    list2.add(df.format(totalscore));
                    if (list.contains(list1)){                          //判断list1是否已在list中 因为每一个学生都会做一次这个循环
                    }
                    else {
                        list1.add("总分");
                        list1.add("班级排名");
                        list1.add("年级排名");
                        list.add(list1);
                    }
                    PreparedStatement ps6 = conn.prepareStatement(sql6);
                    ps6.setString(1,batchno + "%");
                    ps6.setString(2,classno +"%");
                    ResultSet rs6 = ps6.executeQuery();
                    while (rs6.next()){
                        if (rs6.getString("StudentNo").equals(studentid)){
                            String rowno = rs6.getString("rowno");
                            list2.add(rowno.substring(0, rowno.length() - 2));
                            break;
                        }
                    }
                    ps6.close();
                    rs6.close();
                    PreparedStatement ps7 = conn.prepareStatement(sql7);
                    ps7.setString(1,batchno + "%");
                    ResultSet rs7 = ps7.executeQuery();
                    while (rs7.next()){
                        if (rs7.getString("StudentNo").equals(studentid)){
                            String rowno = rs7.getString("rowno");
                            list2.add(rowno.substring(0, rowno.length() - 2));
                            break;
                        }
                    }
                    ps7.close();
                    rs7.close();
                    list.add(list2);
                    ps4.close();
                    rs4.close();
                }
                ps3.close();
                rs3.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
