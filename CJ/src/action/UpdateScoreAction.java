package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.StudentGetPaperDetailDao;
import dao.UpdateScoreDao;
import entity.ExamPaperDetail;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class UpdateScoreAction extends ActionSupport {
    private String result;
    private Map detailmap;
    StudentGetPaperDetailDao dao1 = new StudentGetPaperDetailDao();
    UpdateScoreDao dao2 = new UpdateScoreDao();

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        String exampaperno = (String)session.getAttribute("ExamPaperNo");
        detailmap = dao1.Sel(exampaperno);
        String scorelist = (String)request.getParameter("scorelist");
        String [] scorearr = scorelist.split(",");  //生成成绩字符串数组
        System.out.print(scorelist);
        dao2.Update(exampaperno, detailmap, scorearr);
        return SUCCESS;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
