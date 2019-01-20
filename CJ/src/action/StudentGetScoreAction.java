package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.StudentGetScoreDao;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

public class StudentGetScoreAction extends ActionSupport {
    private String result;
    private Map scoremap= new LinkedHashMap();
    StudentGetScoreDao dao = new StudentGetScoreDao();

    @Override
    public String execute() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        String BatchNo = request.getParameter("batch");
        String StudentNo = (String)session.getAttribute("studentId");
        scoremap = dao.Sel(StudentNo, BatchNo);
        result = JSONObject.fromObject(scoremap).toString();
        return SUCCESS;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
