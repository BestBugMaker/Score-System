package dao;

import entity.Student;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class StudentVerifyLoginDao {
    public int Verify(String account, String password) throws Exception{
        Connection conn = new dbDao().initConnection();
        Statement stat = conn.createStatement();
        String sql = "SELECT * FROM V_CJ1_Student";
        ResultSet rs = stat.executeQuery(sql);
        int isExist = 0;
        while(rs.next()){
            Student student = new Student();
            student.setSno(rs.getString("Sno"));
            student.setPassword(rs.getString("Password"));
            if(student.getSno().equals(account) && student.getPassword().equals(password)){
                isExist = 100;
            }
        }
        stat.close();
        conn.close();
        return isExist;
    }
}
