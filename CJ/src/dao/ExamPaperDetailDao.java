package dao;

import entity.ExamPaperDetail;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExamPaperDetailDao {
    public List<ExamPaperDetail> SelExamPaperDetail() throws Exception{
        Connection conn = new dbDao().initConnection();
        Statement stat = conn.createStatement();
        List<ExamPaperDetail> list = new ArrayList<ExamPaperDetail>();
        String sql = "select * from cj1_testpaperdetail";
        try{
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()){
                ExamPaperDetail exampaperdetail = new ExamPaperDetail();
                exampaperdetail.setExamPaperNo(rs.getString("ExamPaperNo"));
                exampaperdetail.setSubjectNo(rs.getString("SubjectNo"));
                exampaperdetail.setGradeNo(rs.getString("GradeNo"));
                exampaperdetail.setBatchNo(rs.getString("BatchNo"));
                exampaperdetail.setLargeCount(rs.getString("LargeCount"));
                exampaperdetail.setExamTotalScore(rs.getString("ExamTotalScore"));
                list.add(exampaperdetail);
            }
            conn.close();
            rs.close();
            stat.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
