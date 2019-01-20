package dao;

import entity.SchoolYear;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SchoolYearDao {
    public List<SchoolYear> SelSchoolYear() throws Exception{
        Connection conn = new dbDao().initConnection();
        Statement stat = conn.createStatement();
        List<SchoolYear> list = new ArrayList<SchoolYear>();
        String sql = "select * from XJ_SchoolYear order by SYearNo desc";
        try{
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()){
                SchoolYear schoolyear = new SchoolYear();
                schoolyear.setSYearNo(rs.getString("SYearNo"));
                schoolyear.setSYear(rs.getString(   "SYear"));
                list.add(schoolyear);
            }
            conn.close();
            rs.close();
            stat.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
