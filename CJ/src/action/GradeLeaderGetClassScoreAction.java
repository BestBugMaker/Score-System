package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.GradeLeaderGetClassScoreDao;
import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class GradeLeaderGetClassScoreAction extends ActionSupport {
    private String result;
    private List list = new ArrayList();
    GradeLeaderGetClassScoreDao dao1 = new GradeLeaderGetClassScoreDao();

    public String execute() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        String batchno = request.getParameter("batch");
        String classno = request.getParameter("classno");
        list = dao1.SelClassScore(batchno, classno);
        result = JSONArray.fromObject(list).toString();
        return SUCCESS;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
