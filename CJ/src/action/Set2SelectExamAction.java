package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.Set2SelectExamDao;
import entity.PaperState;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class Set2SelectExamAction extends ActionSupport {
    private String result="";
    private String schoolyear;
    private String semester;
    private String grade;
    private String tclass;
    private String tquality;
    private String pinjie;
    private List<PaperState> list;
    Set2SelectExamDao dao = new Set2SelectExamDao();

    @Override
    public String execute() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        schoolyear = request.getParameter("schoolyear");
        semester = request.getParameter("semester");
        grade = request.getParameter("grade");
        tclass = request.getParameter("tclass");
        tquality = request.getParameter("tquality");
        pinjie = schoolyear + semester + grade + tclass + tquality;
        HttpSession session = request.getSession();
        session.setAttribute("pinjie", pinjie);
        list = dao.Sel(pinjie);
        for(int i=0; i<list.size(); i++){
            result += "<tr><td>" + list.get(i).getExamPaperNo() +"</td><td>" +list.get(i).getClassNo() +"</td></tr>";
        }
        return SUCCESS;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
