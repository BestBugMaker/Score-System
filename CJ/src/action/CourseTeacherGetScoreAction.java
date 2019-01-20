package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.CourseTeacherGetScoreDao;
import dao.GetTeacherSubDao;
import dao.StudentGetPaperDetailDao;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class CourseTeacherGetScoreAction extends ActionSupport {
    private String result;
    private Map detailmap;
    private List list;
    private String teacherno;
    private String teachersub;
    GetTeacherSubDao dao1 = new GetTeacherSubDao();
    StudentGetPaperDetailDao dao2 = new StudentGetPaperDetailDao();
    CourseTeacherGetScoreDao dao3 = new CourseTeacherGetScoreDao();

    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        Map session = ActionContext.getContext().getSession();
        teacherno = (String)session.get("TeacherNo");
        String batchno = request.getParameter("batch");
        String classno = request.getParameter("classno");
        teachersub = dao1.SelTeacherGrade(teacherno);
        String exampaperno = batchno + teachersub;
        session.put("ExamPaperNo", exampaperno);    //为了查询平均分
        session.put("ClassNo", classno);    //为了查询平均分
        detailmap = dao2.Sel(exampaperno);
        list = dao3.SelScore(batchno,classno);
        detailmap.put("成绩",list);
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
