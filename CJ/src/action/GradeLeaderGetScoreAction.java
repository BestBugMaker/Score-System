package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.GradeLeaderGetScoreDao;
import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class GradeLeaderGetScoreAction extends ActionSupport {
    private String result;
    List list = new ArrayList();
    GradeLeaderGetScoreDao dao1 = new GradeLeaderGetScoreDao();

    public String execute() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        String batchno = request.getParameter("batch");
        list = dao1.SelGradeScore(batchno);
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
