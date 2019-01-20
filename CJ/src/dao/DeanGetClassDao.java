package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class DeanGetClassDao {
    private Map map = new LinkedHashMap();

    public Map Sel(String grade) throws Exception{
        Connection conn = new dbDao().initConnection();
        String sql = "select * from V_CJ1_Class where GradeNo=? order by classno asc";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, grade);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String classno = rs.getString("ClassNo");
                String classname = rs.getString("ClassName");
                map.put(classno, classname);
            }
            conn.close();
            rs.close();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
}
