package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.GetTeacherSubDao;
import dao.StudentGetPaperDetailDao;
import dao.SubjectLeaderGetScoreDao;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SubjectLeaderGetScoreAction extends ActionSupport {
    private String result;
    private String teachersub;
    private String teacherno;
    private List list = new ArrayList();
    private Map detailmap = new LinkedHashMap();
    StudentGetPaperDetailDao dao1 = new StudentGetPaperDetailDao();
    SubjectLeaderGetScoreDao dao2 = new SubjectLeaderGetScoreDao();
    GetTeacherSubDao dao3 = new GetTeacherSubDao();

    public String execute() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        Map session = ActionContext.getContext().getSession();
        teacherno = (String)session.get("TeacherNo");
        String batchno = request.getParameter("batch");
        teachersub = dao3.SelTeacherGrade(teacherno);
        String exampaperno = batchno + teachersub;
        detailmap = dao1.Sel(exampaperno);                   //存放考卷明细

        list = dao2.SelScore(batchno);

        detailmap.put("成绩",list);
        //System.out.print(detailmap);
        result = JSONObject.fromObject(detailmap).toString();
        System.out.print(result);

        return SUCCESS;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
