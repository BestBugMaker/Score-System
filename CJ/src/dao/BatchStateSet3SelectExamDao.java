package dao;

import entity.PaperState;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BatchStateSet3SelectExamDao {
    public List<PaperState> Sel(String batchno) throws Exception{
        Connection conn = new dbDao().initConnection();
        Statement stat = conn.createStatement();
        List<PaperState> list =  new ArrayList<PaperState>();
        String sql1 = "select * from cj1_batchstate where BatchNo=? and BatchState=2";
        String sql2 = "select * from cj1_paperstate where ExamPaperNo like ?";
        try{
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1,batchno);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()){
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ps2.setString(1,batchno + "%");
                ResultSet rs2 = ps2.executeQuery();
                while(rs2.next()){
                    PaperState paperstate = new PaperState();
                    paperstate.setExamPaperNo(rs2.getString("ExamPaperNo"));
                    paperstate.setClassNo(rs2.getString("ClassNo"));
                    paperstate.setReleaseState(rs2.getString("ReleaseState"));
                    list.add(paperstate);
                }
                ps2.close();
                rs2.close();
            }
            ps1.close();
            rs1.close();
            conn.close();
        }catch (SQLException e ){
            e.printStackTrace();
        }
        return list;
    }
}
