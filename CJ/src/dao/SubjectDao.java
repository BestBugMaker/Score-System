package dao;

import com.opensymphony.xwork2.ActionContext;
import entity.Subject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SubjectDao {   //获取所有学科
    public List<Subject> SelSubject(String SYearNo, String gradeNo) throws Exception{
        Connection conn = new dbDao().initConnection();
        List<Subject> list =  new ArrayList<Subject>();
        String sql = "select * from pke_program where year=? and gradeNo=? and exam=1";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, SYearNo);
            ps.setString(2, gradeNo);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Subject subject = new Subject();
                subject.setId(rs.getString("id"));
                subject.setYear(rs.getString("year"));
                subject.setGradeNo(rs.getString("gradeNo"));
                subject.setName(rs.getString("name"));
                subject.setSubNo(rs.getString("subNo"));
                list.add(subject);
            }
            conn.close();
            rs.close();
            ps.close();
        }catch (SQLException e ){
            e.printStackTrace();
        }
        return list;
    }

    public List SelTeacherSubject(String SYearNo, String gradeNo) throws Exception{ //获取任课教师所教科目
        Connection conn = new dbDao().initConnection();
        List list =  new ArrayList();
        Map session = ActionContext.getContext().getSession();
        String teacherid = (String)session.get("TeacherNo");
        String subno = null;
        String sql = "select * from V_CJ1_Teacher where TeacherNo=?";
        String sql2 = "select * from V_CJ1_Program where year=? and gradeNo=? and subNo=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,teacherid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                subno = rs.getString("TeacherSub");
            }
            rs.close();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setString(1,SYearNo);
            ps2.setString(2,gradeNo);
            ps2.setString(3,subno);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()){
                Subject subject = new Subject();
                subject.setSubNo(rs2.getString("subNo"));
                String name = rs2.getString("name");
                subject.setName(name.substring(0,name.length()-1));
                list.add(subject);
            }
            conn.close();
            rs2.close();
            ps2.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

}
