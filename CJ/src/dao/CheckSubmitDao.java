package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CheckSubmitDao {
    private List nosubmitlist = new ArrayList();

    public List Sel(String pinjie) throws Exception{
        Connection conn = new dbDao().initConnection();
        Statement stat = conn.createStatement();
        String sql1 = "select * from cj1_paperstate where ReleaseState=0";
        String sql2 ="select * from V_CJ1_Teacher_Program where TeacherSub=? and ClassNo =?";
        try{
            ResultSet rs1 = stat.executeQuery(sql1);
            while(rs1.next()){
                String ExamPaperNo = rs1.getString("ExamPaperNo");
                if(ExamPaperNo.substring(0,9).equals(pinjie)){
                    String SubNo = ExamPaperNo.substring(9);
                    String ClassNo = rs1.getString("ClassNo");
                    PreparedStatement ps = conn.prepareStatement(sql2);
                    ps.setString(1, SubNo);
                    ps.setString(2, ClassNo);
                    ResultSet rs2 = ps.executeQuery();
                    while (rs2.next()){
                        List subteacherlist = new ArrayList();
                        String TeacherName = rs2.getString("TeacherName");
                        String name = rs2.getString("name");
                        String ClassName = rs2.getString("ClassName");
                        subteacherlist.add(ClassName);
                        subteacherlist.add(name);
                        subteacherlist.add(TeacherName);
                        nosubmitlist.add(subteacherlist);
                    }
                    ps.close();
                    rs2.close();
                }
                else continue;
            }
            rs1.close();
            stat.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return nosubmitlist;
    }
}
