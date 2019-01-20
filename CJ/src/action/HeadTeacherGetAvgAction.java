package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.HeadTeacherGetAvgDao;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

public class HeadTeacherGetAvgAction extends ActionSupport {
    private String result;
    HeadTeacherGetAvgDao dao = new HeadTeacherGetAvgDao();
    private Map avgmap = new LinkedHashMap();

    public String execute() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        String batchno = (String)session.getAttribute("BatchNo");
        String teacherno = (String)session.getAttribute("TeacherNo");
        System.out.print(batchno + " " + teacherno);
        avgmap = dao.Sel(batchno, teacherno);
        result = JSONObject.fromObject(avgmap).toString();
        return SUCCESS;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
