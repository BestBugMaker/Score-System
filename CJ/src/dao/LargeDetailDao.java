package dao;

import entity.LargeDetail;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LargeDetailDao {

    public List SelLargeDetail() throws Exception{
        Connection conn = new dbDao().initConnection();
        Statement stat = conn.createStatement();
        List list = new ArrayList();
        String sql = "select * from cj1_LargeDetail ";
        try{
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()){
                LargeDetail largeDetail = new LargeDetail();
                largeDetail.setExamPaperNo(rs.getString("ExamPaperNo"));
                largeDetail.setLargeName(rs.getString("LargeName"));
                largeDetail.setLargeNo(rs.getString("LargeNo"));
                largeDetail.setSmallCount(rs.getString("SmallCount"));
                largeDetail.setLargeTotalScore(rs.getString("LargeTotalScore"));
                list.add(largeDetail);
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
