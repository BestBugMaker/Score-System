package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PinJieBatchAction extends ActionSupport {
    private String SchoolYear;
    private String Semester;
    private String Grade;
    private String Tclass;
    private String Tquality;
    private String BatchNo;

    @Override
    public String execute() throws Exception{
        BatchNo = SchoolYear + Semester + Grade + Tclass + Tquality;
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("BatchNo", BatchNo);
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

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public String getTclass() {
        return Tclass;
    }

    public void setTclass(String tclass) {
        Tclass = tclass;
    }

    public String getTquality() {
        return Tquality;
    }

    public void setTquality(String tquality) {
        Tquality = tquality;
    }

    public String getBatchNo() {
        return BatchNo;
    }

    public void setBatchNo(String batchNo) {
        BatchNo = batchNo;
    }
}
