package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.BatchStateSet2Dao;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BatchStateSet2Action extends ActionSupport {
    private String result;
    BatchStateSet2Dao dao = new BatchStateSet2Dao();

    @Override
    public String execute() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        String batchno = (String)session.getAttribute("BatchNo");
        dao.Set(batchno);
        return SUCCESS;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
