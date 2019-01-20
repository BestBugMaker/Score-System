package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.InsertLargeDao;
import dao.InsertSmallDao;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class InsertLargeAndSmallAction extends ActionSupport {
    private String[] Small;
    private String[] Smalltotalscore;
    private String largeno;
    private String ExamPaperNo;
    private  String largename;
    private String smallcount;
    private String totalscore;
    InsertLargeDao largedao = new InsertLargeDao();
    InsertSmallDao smalldao = new InsertSmallDao();

    @Override
    public String  execute() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        ExamPaperNo = (String) session.getAttribute("ExamPaperNo");
        largeno = (String)session.getAttribute("largeno");
        largename = (String)session.getAttribute("largename");
        smallcount = (String)session.getAttribute("smallcount");
        totalscore = (String)session.getAttribute("totalscore");
        largedao.insert(ExamPaperNo, largeno, largename, smallcount, totalscore);
        smalldao.insert(ExamPaperNo, largeno, Small, Smalltotalscore);
        return SUCCESS;
    }

    public String[] getSmall() {
        return Small;
    }

    public void setSmall(String[] small) {
        Small = small;
    }

    public String[] getSmalltotalscore() {
        return Smalltotalscore;
    }

    public void setSmalltotalscore(String[] smalltotalscore) {
        Smalltotalscore = smalltotalscore;
    }

}
