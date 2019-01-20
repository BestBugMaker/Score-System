package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.BatchStateSet3Dao;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BatchStateSet3Action extends ActionSupport {
    private String batchno;
    private String result;
    BatchStateSet3Dao dao = new BatchStateSet3Dao();

    @Override
    public String execute() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        batchno = (String)session.getAttribute("BatchNo");
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
