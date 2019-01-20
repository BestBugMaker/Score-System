package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.TeacherGetClassDao;
import entity.Cl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TeacherGetClassAction extends ActionSupport {
    private String teacherid;
    private String result;
    TeacherGetClassDao dao = new TeacherGetClassDao();

    @Override
    public String execute() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Map map = new LinkedHashMap();
        teacherid = (String) session.getAttribute("TeacherNo");
        map = dao.SelClass(teacherid);
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
