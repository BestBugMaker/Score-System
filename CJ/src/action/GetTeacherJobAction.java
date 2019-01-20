package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;

public class GetTeacherJobAction extends ActionSupport {
    private List result;

    public String execute() throws Exception{
        Map session = ActionContext.getContext().getSession();
        result = (List)session.get("jobno");
        return SUCCESS;
    }

    public List getResult() {
        return result;
    }

    public void setResult(List result) {
        this.result = result;
    }

}
