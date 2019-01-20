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

public class CreateScoreTableDao {
    private Map papermap = new LinkedHashMap();
    private int tdcount = 0; //scoretable中所需的列数

    public Map SelDetail(String ExamPaperNo, String ClassNo)throws Exception{
        papermap.put("试卷编号", ExamPaperNo);
        Connection conn = new dbDao().initConnection();
        String sql1 = "select * from cj1_largedetail where TestPaperNo=?"; //获取大题详情
        String sql2 = "select * from cj1_smalldetail where TestPaperNo=? and LargeNo=?";    //获取小题详情
        String sql3 = "select * from V_CJ1_Student where ClassNo=?";    //获取学生名单
        try{
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1, ExamPaperNo);
            ResultSet rs1 = ps1.executeQuery();
            while(rs1.next()){
                String largeno = rs1.getString("LargeNo");
                String largename = rs1.getString("LargeName");
                String smallcount = rs1.getString("SmallCount");
                tdcount += Integer.parseInt(smallcount);
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
            //获取学生map(包含学号和姓名)
            PreparedStatement ps3 = conn.prepareStatement(sql3);
            ps3.setString(1, ClassNo);
            ResultSet rs3 = ps3.executeQuery();
            Map studentmap = new LinkedHashMap();
            while(rs3.next()){
                String sno = rs3.getString("Sno");
                String sname = rs3.getString("Sname");
                studentmap.put(sno, sname);
            }
            ps3.close();
            conn.close();
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpSession session = request.getSession();
            String result = JSONObject.fromObject(papermap).toString();
            session.setAttribute("paperdetail", result); //将试卷详情添加至session，为后续插入成绩提供便利
            tdcount += 2;
            papermap.put("列数", tdcount);
            papermap.put("学生", studentmap);
        }catch (SQLException e ){
            e.printStackTrace();
        }
        return papermap;
    }
}
