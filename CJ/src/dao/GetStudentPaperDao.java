package dao;

import com.opensymphony.xwork2.ActionContext;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class GetStudentPaperDao {
    String batchno;
    String TestPaperNo;
    String batchinfo;
    String studentid;
    Map map = new LinkedHashMap();

    public Map SelStudentPaperYear() throws Exception{

        Connection conn = new dbDao().initConnection();
        Map session = ActionContext.getContext().getSession();
        studentid = (String)session.get("studentId");
        System.out.print(studentid);
        String sql1 = "select * from cj1_singlescore where StudentNo=?";
        String sql2 = "select * from cj1_testpaperdetail where ExamPaperNo=?";
        String sql3 = "select * from V_CJ1_SchoolYear where SYearNo=?";
        String sql4 = "select * from V_CJ1_Semester where SemesNo=?";
        String sql5 = "select * from V_CJ1_Grade where GradeNo=?";
        try {
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1, studentid);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()){
                TestPaperNo = rs1.getString("TestPaperNo");
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ps2.setString(1, TestPaperNo);
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()){
                    batchno = rs2.getString("BatchNo"); //查询考试批次,实际上只做一次
                }
                String year = batchno.substring(0, 4);
                String semester = batchno.substring(4, 5);
                String grade = batchno.substring(5, 6);
                String tclass = batchno.substring(6, 8);
                String tquality = batchno.substring(8, 9);
                //查询学年中文
                PreparedStatement ps3 = conn.prepareStatement(sql3);
                ps3.setString(1, year);
                ResultSet rs3 = ps3.executeQuery();
                while (rs3.next()){
                    batchinfo = rs3.getString("Syear");    //将学年的中文存入batchinfo
                }
                ps3.close();
                rs3.close();
                //查询学期中文
                PreparedStatement ps4 = conn.prepareStatement(sql4);
                ps4.setString(1, semester);
                ResultSet rs4 = ps4.executeQuery();
                while (rs4.next()){
                    batchinfo += rs4.getString("SemesName");
                }
                ps4.close();
                rs4.close();
                //查询年级中文
                PreparedStatement ps5 = conn.prepareStatement(sql5);
                ps5.setString(1, grade);
                ResultSet rs5 = ps5.executeQuery();
                while (rs5.next()){
                    batchinfo += rs5.getString("GradeName");
                }
                ps5.close();
                rs5.close();
                //判断考试类型
                if (tclass.equals("00")){
                    batchinfo += "第一次月考";
                }
                else if(tclass.equals("01")){
                    batchinfo += "期中考试";
                }
                else if (tclass.equals("02")){
                    batchinfo += "第二次月考";
                }
                else{
                    batchinfo += "期末考试";
                }
                //判断考试性质
                if (tquality.equals("0")){
                    batchinfo += "(非统考)";
                }
                else{
                    batchinfo += "(统考)";
                }
                map.put(batchno,batchinfo);
            }
            conn.close();
            ps1.close();
            rs1.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return map;
    }
}
