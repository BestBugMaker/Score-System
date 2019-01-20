package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.TeacherGetClassDao;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class GradeLeaderGetClassAction extends ActionSupport {
    private String result;
    private String teacherno;
    private Map grademap;

    TeacherGetClassDao dao1 = new TeacherGetClassDao();
    public String execute() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        Map session = ActionContext.getContext().getSession();
        teacherno = (String)session.get("TeacherNo");
        grademap = dao1.SelGradeClass(teacherno);
        result = JSONObject.fromObject(grademap).toString();
        return SUCCESS;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
