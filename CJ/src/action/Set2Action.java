package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.Set2Dao;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Set2Action extends ActionSupport {
    private String pinjie;
    private String result;
    Set2Dao dao = new Set2Dao();

    @Override
    public String execute() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        pinjie = (String)session.getAttribute("pinjie");
        dao.Set(pinjie);
        return SUCCESS;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
