package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.HeadTeacherGetScoreDao;
import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class HeadTeacherGetScoreAction  extends ActionSupport {
    private String result;
    List list = new ArrayList();
    HeadTeacherGetScoreDao dao = new HeadTeacherGetScoreDao();

    public String execute() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        String batchno = request.getParameter("batch");
        HttpSession session = request.getSession();
        session.setAttribute("BatchNo", batchno);
        list = dao.SelScore(batchno);
        result = JSONArray.fromObject(list).toString();
        return SUCCESS;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
