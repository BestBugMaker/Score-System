package dao;

import entity.PaperState;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Set2SelectExamDao {

    public List<PaperState> Sel(String pinjie) throws Exception{
        Connection conn = new dbDao().initConnection();
        Statement stat = conn.createStatement();
        List<PaperState> list =  new ArrayList<PaperState>();
        String sql = "select * from cj1_paperstate where ReleaseState=1";
        try{
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()){
                String ExamPaperNo = rs.getString("ExamPaperNo");
                if(ExamPaperNo.substring(0,9).equals(pinjie)){
                    PaperState paperstate = new PaperState();
                    paperstate.setExamPaperNo(ExamPaperNo);
                    paperstate.setClassNo(rs.getString("ClassNo"));
                    paperstate.setReleaseState(rs.getString("ReleaseState"));
                    list.add(paperstate);
                }
                else continue;
            }
            conn.close();
            rs.close();
            stat.close();
        }catch (SQLException e ){
            e.printStackTrace();
        }
        return list;
    }
}
