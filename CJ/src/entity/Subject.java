package entity;

public class Subject {
    private String id;
    private String year;
    private String gradeNo;
    private String name;
    private String subNo;
    public Subject(){}
    public Subject(String id, String year, String gradeNo, String name, String subNo){
        this.id = id;
        this.year = year;
        this.gradeNo = gradeNo;
        this.name = name;
        this.subNo = subNo;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setGradeNo(String gradeNo) {
        this.gradeNo = gradeNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubNo(String subNo) {
        this.subNo = subNo;
    }

    public String getId() {
        return id;
    }

    public String getYear() {
        return year;
    }

    public String getGradeNo() {
        return gradeNo;
    }

    public String getName() {
        return name;
    }

    public String getSubNo() {
        return subNo;
    }
}
