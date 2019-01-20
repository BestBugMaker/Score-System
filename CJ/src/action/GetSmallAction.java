package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GetSmallAction extends ActionSupport {
    private String result="";

    @Override
    public String execute() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        String largeno = request.getParameter("largeno");
        String largename = request.getParameter("largename");
        String smallcount = request.getParameter("smallcount");
        String totalscore = request.getParameter("totalscore");
        HttpSession session = request.getSession();
        session.setAttribute("largeno", largeno);
        session.setAttribute("largename", largename);
        session.setAttribute("smallcount", smallcount);
        session.setAttribute("totalscore", totalscore);
        result += "大题号:<input name=\"" + largeno + "\" readonly=\"readonly\" value=\"" + largeno + "\"><br/><br/>";
        for(int i=1; i<=Integer.parseInt(smallcount); i++){
            result += "小题号:<input name=\"Small\" id=\"small"+ i + "\"  type=\"text\" " +
                    "readonly=\"readonly\" value=\"" + i + "\"/>&nbsp;小题总分:<input name=\"Smalltotalscore\" " +
                    "id=\"small" + i + "totalscore\" type=\"text\"><br/>";
        }
        result += "</br></br><input type=\"button\" value=\"完成小题设置\" onclick=\"submitlargeandsmall();CloseDiv('MyDiv','fade')\">";
        return SUCCESS;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
