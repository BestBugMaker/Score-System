package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.CourseTeacherGetAvgDao;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class CourseTeacherGetAvgAction extends ActionSupport {
     private String result;
    CourseTeacherGetAvgDao dao = new CourseTeacherGetAvgDao();

     @Override
    public String execute() throws Exception{
         HttpServletRequest request = ServletActionContext.getRequest();
         Map session = ActionContext.getContext().getSession();
         String exampaperno = (String)session.get("ExamPaperNo");
         String classno = (String)session.get("ClassNo");
         String avgscore = dao.Sel(exampaperno, classno);
         result = avgscore;
         return SUCCESS;
     }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
