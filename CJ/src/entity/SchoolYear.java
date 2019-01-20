package entity;

public class SchoolYear {
    private String SYearNo;
    private String SYear;
    public SchoolYear() {
    }
    public SchoolYear(String SYearNo, String SYear) {
        this.SYearNo = SYearNo;
        this.SYear = SYear;
    }
    public void setSYearNo(String SYearNo) {
        this.SYearNo = SYearNo;
    }
    public String getSYearNo() {
        return SYearNo;
    }
    public void setSYear(String SYear) {
        this.SYear = SYear;
    }
    public String getSYear() {
        return SYear;
    }

}
