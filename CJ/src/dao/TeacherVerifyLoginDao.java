package dao;

import entity.Teacher;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TeacherVerifyLoginDao {

    public List Verify(String account, String password) throws Exception{
        Connection conn = new dbDao().initConnection();
        Statement stat = conn.createStatement();
        String sql = "SELECT * FROM V_CJ1_Teacher";
        ResultSet rs = stat.executeQuery(sql);
        int isExist = -1;
        List list = new ArrayList();
        while (rs.next()){
            Teacher teacher = new Teacher();
            teacher.setTeacherno(rs.getString("TeacherNo"));
            teacher.setTeacherkey(rs.getString("TeacherKey"));
            teacher.setTeacherjob(rs.getString("TeacherJob"));
            if(teacher.getTeacherno().equals(account) && teacher.getTeacherkey().equals(password)){
                isExist = Integer.valueOf(teacher.getTeacherjob());
                list.add(isExist);
            }
        }
        if (list.isEmpty())
            list.add(isExist);
        stat.close();
        conn.close();
        return list;
    }
}
