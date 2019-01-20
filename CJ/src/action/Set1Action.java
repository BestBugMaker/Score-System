package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.Set1Dao;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class Set1Action extends ActionSupport {
    private String result;
    Set1Dao dao = new Set1Dao();

    @Override
    public String execute() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        String classno = request.getParameter("classno");
        String exampaperno = request.getParameter("exampaperno");
        System.out.print(classno+exampaperno);
        dao.Set(exampaperno, classno);
        return SUCCESS;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
