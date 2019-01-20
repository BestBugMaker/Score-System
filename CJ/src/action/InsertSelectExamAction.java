package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.InsertSelectExamDao;
import dao.Set1GetSubjectDao;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class InsertSelectExamAction extends ActionSupport {
    private String result;
    Set1GetSubjectDao dao1 = new Set1GetSubjectDao();
    InsertSelectExamDao dao2 = new InsertSelectExamDao();

    @Override
    public String execute() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        String TeacherNo = (String) session.getAttribute("TeacherNo");
        String schoolyear = request.getParameter("schoolyear");
        String semester = request.getParameter("semester");
        String grade = request.getParameter("grade");
        String tclass = request.getParameter("tclass");
        String tquality = request.getParameter("tquality");
        String subno = dao1.Sel(TeacherNo);
        String exampaperno = schoolyear + semester + grade + tclass + tquality + subno;
        session.setAttribute("ExamPaperNo", exampaperno);   //将试卷编号放入session中
        result = dao2.Sel(exampaperno);
        return SUCCESS;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
