package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InsertScoreDao {
    public void insert(Map paperdetailmaps, String[] scorearr) throws Exception{
        Connection conn = new dbDao().initConnection();
        String sql1 = "insert into cj1_score values(?,?,?,?,?)";    //插入成绩至单科成绩表
        String sql2 = "insert into cj1_singlescore values(?,?,?)";  //插入成绩至小题成绩表
        try{
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            String ExamPaperNo = (String)paperdetailmaps.get("试卷编号");
            List keylist = new ArrayList((Set) paperdetailmaps.keySet());   //生成试卷大题名list
            int j = 1;  //j为成绩数组指数，0为学生学号，从1开始为学生的小题成绩
            float totalscore = 0;   //学生该试卷的总分
            for(int i=1; i<keylist.size(); i++){    //计算大题数，从试卷详情map的1开始，0为试卷编号
                Map valuemap = (Map)paperdetailmaps.get(keylist.get(i));    //获取该大题的小题map
                String valuesize = String.valueOf(valuemap.size()); //获取每一大题的小题总数
                for(int k=1 ; k<=Integer.parseInt(valuesize); k++){
                    ps1.setString(1, scorearr[0]);
                    ps1.setString(2, ExamPaperNo);
                    ps1.setString(3, String.valueOf(i));
                    ps1.setString(4, String.valueOf(k));
                    ps1.setString(5, scorearr[j]);
                    totalscore += Float.parseFloat(scorearr[j]);
                    j++;    //scorearr每插入一次j就往后移动一个
                    ps1.executeUpdate();
                }
            }
            ps1.close();
            ps2.setString(1, scorearr[0]);
            ps2.setString(2, ExamPaperNo);
            ps2.setString(3, String.valueOf(totalscore));
            ps2.executeUpdate();
            ps2.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
