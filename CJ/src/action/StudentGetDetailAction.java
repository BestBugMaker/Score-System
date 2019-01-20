package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.StudentGetPaperDetailDao;
import dao.StudentGetScoreDetailDao;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StudentGetDetailAction extends ActionSupport {
    private String result;
    private Map detailmap = new LinkedHashMap();
    private List scorelist = new ArrayList();
    StudentGetPaperDetailDao dao1 = new StudentGetPaperDetailDao();
    StudentGetScoreDetailDao dao2 = new StudentGetScoreDetailDao();

    @Override
    public String execute() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        String studentno = (String) session.getAttribute("studentId");
        String exampaperno = request.getParameter("exampaperno");
        detailmap = dao1.Sel(exampaperno);
        scorelist = dao2.Sel(studentno, exampaperno);
        detailmap.put("分数", scorelist);
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
