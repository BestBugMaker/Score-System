package entity;

public class Grade {
    private String GradeNo;
    private String GradeName;
    public Grade(){}
    public Grade(String GradeNo, String GradeName){
        this.GradeNo = GradeNo;
        this.GradeName = GradeName;
    }
    public void setGradeNo(String GradeNo) {
        this.GradeNo = GradeNo;
    }
    public String getGradeNo(){
        return GradeNo;
    }
    public void setGradeName(String GradeName){
        this.GradeName = GradeName;
    }
    public String getGradeName(){
        return GradeName;
    }
}
