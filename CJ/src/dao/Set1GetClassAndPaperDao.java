package dao;

import entity.Cl;
import entity.PaperState;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Set1GetClassAndPaperDao {
    public List<Cl> SelClass (String TeacherNo) throws Exception{
        Connection conn = new dbDao().initConnection();
        List list = new ArrayList();
        String sql = "select * from V_CJ1_Teacher_Program where TeacherNo =?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, TeacherNo);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Cl cl = new Cl();
                cl.setClassNo(rs.getString("classNo"));
                cl.setClassName(rs.getString("ClassName"));
                list.add(cl);
            }
            conn.close();
            rs.close();
            ps.close();
        }catch (SQLException e ){
            e.printStackTrace();
        }
        return list;
    }

    public List SelPaper(String ClassNo , String TeacherSub) throws Exception{
        Connection conn = new dbDao().initConnection();
        List list = new ArrayList();
        String sql = "select * from cj1_paperstate where ClassNo=? and ReleaseState=0";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ClassNo);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                PaperState paperstate = new PaperState();
                String ExamPaperNo = rs.getString("ExamPaperNo");
                if (ExamPaperNo.substring(9).equals(TeacherSub)){
                    paperstate.setExamPaperNo(ExamPaperNo);
                    paperstate.setClassNo(rs.getString("ClassNo"));
                    paperstate.setReleaseState(rs.getString("ReleaseState"));
                    list.add(paperstate);
                }
                else continue;
            }
            conn.close();
            rs.close();
            ps.close();
        }catch (SQLException e ){
            e.printStackTrace();
        }
        return list;
    }
}
