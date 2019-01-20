package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.CheckSubmitDao;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class CheckSubmitAction extends ActionSupport {
    private String pinjie;
    private String result="";
    List nosubmitlist = new ArrayList();
    CheckSubmitDao dao = new CheckSubmitDao();

    @Override
    public String execute() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        pinjie = (String)session.getAttribute("pinjie");
        nosubmitlist = dao.Sel(pinjie);
        for(int i=0; i<nosubmitlist.size(); i++){
            List subteacherlist = new ArrayList();
            subteacherlist = (List) nosubmitlist.get(i);
            result += i + 1 + "." + subteacherlist.get(0) + ":" + subteacherlist.get(1) + "  任教老师:" + subteacherlist.get(2) + "\n";
        }
        return SUCCESS;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
