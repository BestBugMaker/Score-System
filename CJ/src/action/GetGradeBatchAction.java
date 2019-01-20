package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.GetGradeBatchDao;
import net.sf.json.JSONObject;
import java.util.LinkedHashMap;
import java.util.Map;

public class GetGradeBatchAction extends ActionSupport {
    String result;
    Map map = new LinkedHashMap();
    GetGradeBatchDao dao = new GetGradeBatchDao();

    public String execute() throws Exception{
        map = dao.SelGradeBatch();
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
