package dao;

import entity.Grade;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeanGetGradeDao {
    public List<Grade> SelGrade() throws Exception{
        Connection conn = new dbDao().initConnection();
        Statement stat = conn.createStatement();
        List<Grade> list = new ArrayList<Grade>();
        String sql = "select * from XJ_GradeNum order by GradeNo desc";
        try{
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()){
                Grade grade = new Grade();
                grade.setGradeNo(rs.getString("GradeNo"));
                grade.setGradeName(rs.getString(   "GradeName"));
                list.add(grade);
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
