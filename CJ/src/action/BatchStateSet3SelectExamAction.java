package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.BatchStateSet3SelectExamDao;
import entity.PaperState;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class BatchStateSet3SelectExamAction extends ActionSupport {
    private String result="";
    private String schoolyear;
    private String semester;
    private String grade;
    private String tclass;
    private String tquality;
    private String batchno;
    private List<PaperState> list;
    BatchStateSet3SelectExamDao dao = new BatchStateSet3SelectExamDao();

    @Override
    public String execute() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        schoolyear = request.getParameter("schoolyear");
        semester = request.getParameter("semester");
        grade = request.getParameter("grade");
        tclass = request.getParameter("tclass");
        tquality = request.getParameter("tquality");
        batchno = schoolyear + semester + grade + tclass + tquality;
        HttpSession session = request.getSession();
        session.setAttribute("BatchNo", batchno);
        list = dao.Sel(batchno);
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
