package dao;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class TeacherGetClassDao
{
    public Map SelClass(String TeacherNo) throws Exception{    //查询任课老师授课的班级
        Connection conn = new dbDao().initConnection();
        Map map = new LinkedHashMap();
        String classno;
        String classname;
        String sql = "select * from V_CJ1_Teacher_Program where TeacherNo=?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, TeacherNo);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                classno = rs.getString("ClassNo");
                classname = rs.getString(   "ClassName");
                map.put(classno,classname);
            }
            conn.close();
            rs.close();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return map;
    }

    public Map SelGradeClass(String teacherno) throws Exception{    //查询年级组长所在年级的所有班级
        Connection conn = new dbDao().initConnection();
        Map map = new LinkedHashMap();
        String teachergrade = "";
        String sql1 = "select * from V_CJ1_Teacher where TeacherNo=?";
        String sql2 = "select * from V_CJ1_Class where GradeNo=?";
        try {
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1, teacherno);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()){
                teachergrade = String.valueOf(Integer.parseInt(rs1.getString("TeacherGrade")) + 6);
            }
            rs1.close();
            ps1.close();
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setString(1, teachergrade);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()){
                String classno = rs2.getString("ClassNo");
                String classname = rs2.getString("ClassName");
                map.put(classno, classname);
            }
            conn.close();
            rs2.close();
            ps2.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return map;
    }
}
