package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.BatchStateSet2SelectScoreDao;
import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

public class BatchStateSet2SelectScoreAction extends ActionSupport {
    private String result;
    private List scorelist = new ArrayList();
    BatchStateSet2SelectScoreDao dao = new BatchStateSet2SelectScoreDao();

    @Override
    public String execute() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        String schoolyear = request.getParameter("schoolyear");
        String semester = request.getParameter("semester");
        String grade = request.getParameter("grade");
        String tclass =  request.getParameter("tclass");
        String tquality = request.getParameter("tquality");
        String batchno = schoolyear + semester + grade + tclass + tquality;
        HttpSession session = request.getSession();
        session.setAttribute("BatchNo", batchno);
        scorelist = dao.Sel(batchno);
        result = JSONArray.fromObject(scorelist).toString();
        return SUCCESS;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
