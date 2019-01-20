package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.Set1GetClassAndPaperDao;
import dao.Set1GetSubjectDao;
import entity.Cl;
import entity.PaperState;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class Set1GetClassAndPaperAction extends ActionSupport {
    private String TeacherNo;
    private String TeacherSub;
    private List<Cl> classlist;
    private List<PaperState> paperlist;
    Set1GetClassAndPaperDao dao = new Set1GetClassAndPaperDao();
    Set1GetSubjectDao subjectDao = new Set1GetSubjectDao();
    private String result = "";

    public String getclassno() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        TeacherNo = (String)session.getAttribute("TeacherNo");
        classlist = dao.SelClass(TeacherNo);
        result = "<option>选择班级</option>";
        for(int i=0; i<classlist.size(); i++){
            result += "<option value=" + classlist.get(i).getClassNo() +
                    ">" + classlist.get(i).getClassName() + "</option>";
        }
        return SUCCESS;
    }

    public String getexampaperno() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        String classno = request.getParameter("classno");
        HttpSession session = request.getSession();
        TeacherNo = (String)session.getAttribute("TeacherNo");
        TeacherSub = subjectDao.Sel(TeacherNo);
        paperlist = dao.SelPaper(classno, TeacherSub);
        result = "<option>选择试卷</option>";
        for(int i=0; i<paperlist.size(); i++){
            result += "<option value=" + paperlist.get(i).getExamPaperNo() +
                    ">" + paperlist.get(i).getExamPaperNo() + "</option>";
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
