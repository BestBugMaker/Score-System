package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.GetLargeDao;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GetLargeAction extends ActionSupport {
    private String ExamPaperNo;
    private int LargeCount;
    private String ExamTotalScore;
    private String result = "";
    GetLargeDao dao = new GetLargeDao();
    private String[] largelist;

    public String GetLarge() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        ExamPaperNo = (String) session.getAttribute("ExamPaperNo");
        largelist = dao.GetLargeDetail(ExamPaperNo);
        LargeCount = Integer.parseInt(largelist[0]);
        ExamTotalScore = largelist[1];
        for(int i=1; i <= LargeCount; i++ ){
            result += "大题号:<input style=\"font-size:12px;letter-spacing:2px;border-bottom:1px solid #dddddd;padding:5px 0px 3px 0px;margin:4px 0px 4px 0px;text-aling:center;height:24px;FLITER:progid;DXImageTransform.Microsoft.Gradient(startColorStr='#f5f5f5',endColorStr='#eeeeee',gradientType='0'\"  name=\"Large" + i + "\" id=\"large"+ i + "\"  type=\"text\" " +
                    "readonly=\"readonly\" value=\"" + i + "\">&nbsp;大题名:<input style=\"font-size:12px;letter-spacing:2px;border:1px solid #66ccff;padding:5px 0px 3px 0px;margin:4px 0px 4px 0px;text-aling:center;height:24px;FLITER:progid;DXImageTransform.Microsoft.Gradient(startColorStr='rgba(241,158,194)',endColorStr='rgba(241,158,194)',gradientType='0'\" name=\"Lagre" + i + "name\" " +
                    "id=\"large" + i + "name\" type=\"text\">&nbsp;" + "小题数:<input style=\"font-size:12px;letter-spacing:2px;border:1px solid #66ccff;padding:5px 0px 3px 0px;margin:4px 0px 4px 0px;text-aling:center;height:24px;FLITER:progid;DXImageTransform.Microsoft.Gradient(startColorStr='rgba(241,158,194)',endColorStr='rgba(241,158,194)',gradientType='0'\" name=\"Large" + i + "smallcount\" " +
                    "id=\"large"+ i +"smallcount\" type=\"text\">&nbsp;大题总分:<input style=\"font-size:12px;letter-spacing:2px;border:1px solid #66ccff;padding:5px 0px 3px 0px;margin:4px 0px 4px 0px;text-aling:center;height:24px;FLITER:progid;DXImageTransform.Microsoft.Gradient(startColorStr='rgba(241,158,194)',endColorStr='rgba(241,158,194)',gradientType='0'\"  name=\"Large"+ i +"totalscore\" " +
                    "id=\"large"+ i + "totalscore\" type=\"text\">&nbsp;<input style=\"background-color:lightblue;border-color:rgb(28,4,50);height:30px\"  type=\"button\" id=\""+ i +"\" value" +
                    "=\"设置小题\" onclick=\"insertsmall(this);ShowDiv('MyDiv','fade')\"><br/><br/>";
        }
        return SUCCESS;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
