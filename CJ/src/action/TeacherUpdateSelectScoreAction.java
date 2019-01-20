package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.StudentGetPaperDetailDao;
import dao.TeacherUpdateSelectScoreDao;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class TeacherUpdateSelectScoreAction extends ActionSupport{
    private String result;
    private Map detailmap;
    private List list;
    StudentGetPaperDetailDao dao1 = new StudentGetPaperDetailDao();
    TeacherUpdateSelectScoreDao dao2 = new TeacherUpdateSelectScoreDao();

    public String execute() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        String exampaperno = (String)session.getAttribute("ExamPaperNo");
        String classno = (String)session.getAttribute("ClassNo");
        detailmap = dao1.Sel(exampaperno);
        list = dao2.Sel(exampaperno, classno);
        detailmap.put("成绩", list);
        result = JSONObject.fromObject(detailmap).toString();
        return SUCCESS;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
