package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UpdateScoreDao {

    public void Update(String ExamPaperNo, Map paperdetailmaps, String[] scorearr) throws Exception{
        Connection conn = new dbDao().initConnection();
        String sql1 = "update cj1_score set SmallScore=? where StudentNo=? and TestPaperNo=? and LargeNo=? " +
                "and SmallNo=?";    //更新成绩至单科成绩表
        String sql2 = "update cj1_singlescore set Score=? where StudentNo=? and TestPaperNo=?";  //更新成绩至小题成绩表
        try{
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            List keylist = new ArrayList((Set) paperdetailmaps.keySet());   //生成试卷大题名list
            int j = 1;  //j为成绩数组指数，0为学生学号，从1开始为学生的小题成绩
            float totalscore = 0;   //学生该试卷的总分
            for(int i=0; i<keylist.size(); i++){    //计算大题数，从试卷详情map的1开始，0为试卷编号
                Map valuemap = (Map)paperdetailmaps.get(keylist.get(i));    //获取该大题的小题map
                String valuesize = String.valueOf(valuemap.size()); //获取每一大题的小题总数
                for(int k=1 ; k<=Integer.parseInt(valuesize); k++){
                    ps1.setString(1, scorearr[j]);
                    ps1.setString(2, scorearr[0]);
                    ps1.setString(3, ExamPaperNo);
                    ps1.setString(4, String.valueOf(i + 1));
                    ps1.setString(5, String.valueOf(k));
                    totalscore += Float.parseFloat(scorearr[j]);
                    j++;    //scorearr每插入一次j就往后移动一个
                    ps1.executeUpdate();
                }
             }
            ps1.close();
            ps2.setString(1, String.valueOf(totalscore));
            ps2.setString(2, scorearr[0]);
            ps2.setString(3, ExamPaperNo);
            ps2.executeUpdate();
            ps2.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
