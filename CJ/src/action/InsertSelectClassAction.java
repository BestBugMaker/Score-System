package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.Set1GetClassAndPaperDao;
import entity.Cl;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class InsertSelectClassAction extends ActionSupport {
    private String result;
    private List<Cl> classlist;
    Set1GetClassAndPaperDao dao = new Set1GetClassAndPaperDao();

    @Override
    public String execute() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        String TeacherNo = (String) session.getAttribute("TeacherNo");
        classlist = dao.SelClass(TeacherNo);
        result = "<option>选择班级</option>";
        for(int i=0; i<classlist.size(); i++){
            result += "<option value=" + classlist.get(i).getClassNo() +
                    ">" + classlist.get(i).getClassName() + "</option>";
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
