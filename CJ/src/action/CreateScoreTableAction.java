package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.CreateScoreTableDao;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

public class CreateScoreTableAction extends ActionSupport {
    private String ExamPaperNo;
    private String ClassNo;
    CreateScoreTableDao dao = new CreateScoreTableDao();
    private Map papermap = new LinkedHashMap();
    private String result;

    @Override
    public String execute() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        ExamPaperNo = (String) session.getAttribute("ExamPaperNo"); //获取上一个页面所得到的试卷编号
        ClassNo = (String)session.getAttribute("ClassNo");  //获取上一个页面所得到的班级编号
        //页面1写了后在页面1放入session
        papermap = dao.SelDetail(ExamPaperNo, ClassNo); //生成试卷详情map
        result = JSONObject.fromObject(papermap).toString();    //将map转为json字符串传递给前端
        return SUCCESS;
    }

    //result必须有set和get函数
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
