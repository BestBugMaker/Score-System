package dao;

import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class StudentGetPaperDetailDao {
    private Map papermap = new LinkedHashMap();

    public Map Sel(String ExamPaperNo )throws Exception{
        Connection conn = new dbDao().initConnection();
        String sql1 = "select * from cj1_largedetail where TestPaperNo=?"; //获取大题详情
        String sql2 = "select * from cj1_smalldetail where TestPaperNo=? and LargeNo=?";    //获取小题详情
        try{
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1, ExamPaperNo);
            ResultSet rs1 = ps1.executeQuery();
            while(rs1.next()){
                String largeno = rs1.getString("LargeNo");
                String largename = rs1.getString("LargeName");
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ps2.setString(1, ExamPaperNo);
                ps2.setString(2, largeno);
                ResultSet rs2 = ps2.executeQuery();
                Map largemap = new LinkedHashMap();
                while(rs2.next()){
                    String smallno = rs2.getString("SmallNo");
                    String smalltotalscore = rs2.getString("SmallTotalScore");
                    largemap.put(smallno, smalltotalscore);
                }
                ps2.close();
                papermap.put(largename, largemap);
            }
            ps1.close();
            conn.close();
        }catch (SQLException e ){
            e.printStackTrace();
        }
        return papermap;
    }
}
