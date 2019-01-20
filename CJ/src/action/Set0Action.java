package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.Set0Dao;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Set0Action extends ActionSupport {
    private String result;
    Set0Dao dao = new Set0Dao();

    @Override
    public String execute() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        String ExamPaperNo = (String)session.getAttribute("ExamPaperNo");
        String ClassNo = (String)session.getAttribute("ClassNo");
        dao.Set(ExamPaperNo, ClassNo);
        return SUCCESS;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
