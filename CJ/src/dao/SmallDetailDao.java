package dao;

import entity.SmallDetail;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SmallDetailDao {

    public List SelSmallDetail() throws Exception {
        Connection conn = new dbDao().initConnection();
        Statement stat = conn.createStatement();
        List list = new ArrayList();
        String sql = "select * from cj1_smalldetail ";
        try {
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                SmallDetail smallDetail = new SmallDetail();
                smallDetail.setExamPaperNo(rs.getString("ExamPaperNo"));
                smallDetail.setLargeNo(rs.getString("LargeNo"));
                smallDetail.setSmallNo(rs.getString("SmallNo"));
                smallDetail.setSmallTotalScore(rs.getString("SmallTotalScore"));

                list.add(smallDetail);
            }
            conn.close();
            rs.close();
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
