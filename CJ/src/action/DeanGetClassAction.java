package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.DeanGetClassDao;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

public class DeanGetClassAction extends ActionSupport {
    private String result;
    private Map map = new LinkedHashMap();
    DeanGetClassDao dao = new DeanGetClassDao();

    @Override
    public String execute() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        String grade = request.getParameter("grade");
        map = dao.Sel(grade);
        result = JSONObject.fromObject(map).toString();
        return SUCCESS;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
