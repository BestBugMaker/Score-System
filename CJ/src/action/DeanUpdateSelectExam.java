package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.DeanUpdateSelectExamDao;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DeanUpdateSelectExam extends ActionSupport {
    private String result;
    DeanUpdateSelectExamDao dao = new DeanUpdateSelectExamDao();

    @Override
    public String execute() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        String exampaperno = request.getParameter("exampaperno");
        String classno = request.getParameter("classno");
        result = dao.Sel(exampaperno);
        session.setAttribute("ExamPaperNo", exampaperno);   //将试卷编号放入session中
        session.setAttribute("ClassNo", classno);
        return SUCCESS;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
