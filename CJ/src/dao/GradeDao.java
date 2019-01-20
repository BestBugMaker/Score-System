package dao;

import entity.Grade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradeDao {
    public List<Grade> SelGrade(String TeacherNo) throws Exception{
        Connection conn = new dbDao().initConnection();
        Statement stat = conn.createStatement();
        List<Grade> list = new ArrayList<Grade>();
        String sql1 = "select * from XJ_GradeNum order by GradeNo desc";
        String sql2 = "select distinct  TeacherGrade from V_CJ1_Teacher where TeacherNo=? ";
        try{
            ResultSet rs1 = stat.executeQuery(sql1);
            while(rs1.next()){
                PreparedStatement ps = conn.prepareStatement(sql2);
                ps.setString(1, TeacherNo);
                ResultSet rs2 = ps.executeQuery();
                 while(rs2.next()){
                     if(Integer.parseInt(rs2.getString("TeacherGrade"))+6 == Integer.parseInt(rs1.getString("GradeNo"))){
                         Grade grade = new Grade();
                         grade.setGradeNo(rs1.getString("GradeNo"));
                         grade.setGradeName(rs1.getString(   "GradeName"));
                         list.add(grade);
                     }
                 }
            }
            conn.close();
            rs1.close();
            stat.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
