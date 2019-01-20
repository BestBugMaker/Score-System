package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.GetStudentPaperDao;
import net.sf.json.JSONObject;
import java.util.LinkedHashMap;
import java.util.Map;

public class GetStudentPaperAction extends ActionSupport {
    private String result;
    private Map map = new LinkedHashMap();
    GetStudentPaperDao dao = new GetStudentPaperDao();

    @Override
    public String execute() throws Exception{
        map = dao.SelStudentPaperYear();    //生成考试批次map
        result = JSONObject.fromObject(map).toString(); //将map转为json字符串传递给ajax
        return SUCCESS;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
