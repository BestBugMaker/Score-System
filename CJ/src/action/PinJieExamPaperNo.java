package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PinJieExamPaperNo extends ActionSupport {
    private String SchoolYear;
    private String Semester;
    private String Tclass;
    private String Grade;
    private String Subject;
    private String Tquality;
    private String ExamPaperNo;

    @Override
    public String execute() throws Exception{
        ExamPaperNo = SchoolYear + Semester + Grade + Tclass + Tquality + Subject;
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("ExamPaperNo", ExamPaperNo);
        return SUCCESS;
    }

    public String getSchoolYear() {
        return SchoolYear;
    }
    public void setSchoolYear(String schoolYear) {
        SchoolYear = schoolYear;
    }
    public String getSemester() {
        return Semester;
    }
    public void setSemester(String semester) {
        Semester = semester;
    }
    public String getTclass() {
        return Tclass;
    }
    public void setTclass(String tclass) {
        Tclass = tclass;
    }
    public String getGrade() {
        return Grade;
    }
    public void setGrade(String grade) {
        Grade = grade;
    }
    public String getSubject() {
        return Subject;
    }
    public void setSubject(String subject) {
        Subject = subject;
    }
    public String getTquality() {
        return Tquality;
    }
    public void setTquality(String tquality) {
        Tquality = tquality;
    }
    public String getExamPaperNo() {
        return ExamPaperNo;
    }
    public void setExamPaperNo(String examPaperNo) {
        ExamPaperNo = examPaperNo;
    }

}

