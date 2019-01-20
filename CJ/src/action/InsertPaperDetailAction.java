package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.InsertPaperDetailDao;

public class InsertPaperDetailAction extends ActionSupport {
    private String ExamPaperNo;
    private String LargeCount;
    private String ExamTotalScore;
    InsertPaperDetailDao dao = new InsertPaperDetailDao();

    @Override
    public String execute() throws Exception{
        dao.Insert(ExamPaperNo, LargeCount, ExamTotalScore);
        return SUCCESS;
    }

    public String getExamPaperNo() {
        return ExamPaperNo;
    }

    public void setExamPaperNo(String examPaperNo) {
        ExamPaperNo = examPaperNo;
    }

    public String getLargeCount() {
        return LargeCount;
    }

    public void setLargeCount(String largeCount) {
        LargeCount = largeCount;
    }

    public String getExamTotalScore() {
        return ExamTotalScore;
    }

    public void setExamTotalScore(String examTotalScore) {
        ExamTotalScore = examTotalScore;
    }

}
