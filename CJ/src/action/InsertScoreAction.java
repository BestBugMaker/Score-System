package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.InsertScoreDao;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

public class InsertScoreAction extends ActionSupport {
    private String result;
    private String scorelist;
    InsertScoreDao dao = new InsertScoreDao();

    @Override
    public String execute() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        String paperdetail = (String)session.getAttribute("paperdetail");   //获取生成scoretable时放入session中的试卷详情
        Map paperdetailmaps = (Map)JSONObject.fromObject(paperdetail);  //生成试卷详情map
        scorelist = (String)request.getParameter("scorelist");  //获取ajax传来的成绩数组字符串
        //去除成绩list中的分隔符
        String [] scorearr = scorelist.split(",");  //生成成绩字符串数组
        dao.insert(paperdetailmaps, scorearr);  //调用插入成绩dao
        return SUCCESS;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


}
