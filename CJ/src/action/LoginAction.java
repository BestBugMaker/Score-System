package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.StudentVerifyLoginDao;
import dao.TeacherVerifyLoginDao;
import net.sf.json.JSONObject;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LoginAction extends ActionSupport {

    private String account;
    private String password;
    private Map jsondata;
    private String result;
    private int r;

    public String execute() throws Exception {
        if (account.length() == 4){
            List list = new TeacherVerifyLoginDao().Verify(account, password);
            Map session = ActionContext.getContext().getSession();
            jsondata = new LinkedHashMap();
            jsondata.put("return", list);
            session.put("jobno", list);
            result = JSONObject.fromObject(jsondata).toString();
            if (list.contains("-1")){
                return SUCCESS;
            }
            else{
                session.put("TeacherNo", account);
                return SUCCESS;
            }
        }
        else{
            r = new StudentVerifyLoginDao().Verify(account, password);
            Map session = ActionContext.getContext().getSession();
            jsondata = new LinkedHashMap();
            jsondata.put("return", r);
            result = JSONObject.fromObject(jsondata).toString();
            if (r != 0){
                session.put("studentId", account);
                return SUCCESS;
            }
            else
                return SUCCESS;
        }
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
