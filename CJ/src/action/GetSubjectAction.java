package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.SubjectDao;
import entity.Subject;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetSubjectAction extends ActionSupport {
    private String result;
    private List<Subject> subjectlist;
    SubjectDao subjectdao = new SubjectDao();

    @Override
    public String execute() throws Exception{   //获取所有学科
        HttpServletRequest request = ServletActionContext.getRequest();
        String grade = request.getParameter("grade");
        String schoolyear = request.getParameter("schoolyear");
        subjectlist = subjectdao.SelSubject(schoolyear, grade);
        for(int i=0; i<subjectlist.size(); i++){
            result += "<option value=" + subjectlist.get(i).getSubNo() +
                    ">" + subjectlist.get(i).getName() + "</option>";
        }
        return SUCCESS;
    }

    public String getTeacherSubject() throws Exception{ //获取任课老师所教科目
        HttpServletRequest request = ServletActionContext.getRequest();
        String schoolyear = request.getParameter("schoolyear");
        String grade = request.getParameter("grade");
        subjectlist = subjectdao.SelTeacherSubject(schoolyear,grade);
        for(int i=0; i<subjectlist.size(); i++){
            result += "<option value=" + subjectlist.get(i).getSubNo() +
                    ">" + subjectlist.get(i).getName() + "</option>";
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
