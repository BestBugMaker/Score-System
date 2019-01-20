package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.DeanGetGradeDao;
import dao.SchoolYearDao;
import entity.Grade;
import entity.SchoolYear;
import java.util.List;

public class DeanGetSYAndGradeAction extends ActionSupport {
    private String result = "";
    private List<SchoolYear> schoolyearlist;
    private List<Grade> gradelist;
    SchoolYearDao schoolyeardao = new SchoolYearDao();
    DeanGetGradeDao gradedao = new DeanGetGradeDao();

    public String getschoolyear() throws Exception{
        schoolyearlist = schoolyeardao.SelSchoolYear();
        result = "<option>选择学年</option>";
        for(int i=0; i<schoolyearlist.size(); i++){
            result += "<option value="+schoolyearlist.get(i).getSYearNo() +
                    ">" + schoolyearlist.get(i).getSYear() + "</option>";
        }
        return SUCCESS;
    }

    public String getgrade() throws Exception{
        gradelist = gradedao.SelGrade();
        result = "<option>选择年级</option>";
        for(int i=0; i<gradelist.size(); i++){
            result += "<option value=" + gradelist.get(i).getGradeNo() +
                    ">" + gradelist.get(i).getGradeName() + "</option>";
        }
        return SUCCESS;
    }

    public void setSchoolyearlist(List<SchoolYear> schoolyearlist){
        this.schoolyearlist = schoolyearlist;
    }
    public List<SchoolYear> getSchoolyearlist(){
        return schoolyearlist;
    }
    public void setGradelist(List<Grade> gradelist){
        this.gradelist = gradelist;
    }
    public List<Grade> getGradelist(){
        return gradelist;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
}
