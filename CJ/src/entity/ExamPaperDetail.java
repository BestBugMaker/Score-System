package entity;

public class ExamPaperDetail {
    private String ExamPaperNo;
    private String SubjectNo;
    private String GradeNo;
    private String BatchNo;
    private String LargeCount;
    private String ExamTotalScore;

    public String getExamPaperNo() {
        return ExamPaperNo;
    }

    public void setExamPaperNo(String examPaperNo) {
        ExamPaperNo = examPaperNo;
    }

    public String getSubjectNo() {
        return SubjectNo;
    }

    public void setSubjectNo(String subjectNo) {
        SubjectNo = subjectNo;
    }

    public String getGradeNo() {
        return GradeNo;
    }

    public void setGradeNo(String gradeNo) {
        GradeNo = gradeNo;
    }

    public String getBatchNo() {
        return BatchNo;
    }

    public void setBatchNo(String batchNo) {
        BatchNo = batchNo;
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
