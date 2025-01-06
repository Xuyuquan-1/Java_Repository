package sdut.xksys.dao;

import org.springframework.stereotype.Repository;
import sdut.xksys.bean.Student;
import sdut.xksys.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AdminUpdatePwdDao {
    public List<Student> getCheckStudent(Student student) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select * from students ";

        List<String> params = new ArrayList<>();
        if(student.getStudentno()==null)
            student.setStudentno("");
        //完美的控制！！！
        //第一次加载为null，后面加载都是”“
        if(student.getStudentno()!="") {
            sql = sql + "where studentno =  ?";
            params.add(student.getStudentno());
        }

            ResultSet rs = JdbcUtil.query(sql, params.toArray());
            List<Student> list = JdbcUtil.convertResultSetToList(rs, Student.class);
            JdbcUtil.close(rs);
            return list;
    }
    public int getStudentCount() throws SQLException {
        //统计所有数据的行数
        String sql = "select count(*) from students";
        //返回的结果集是一个包含一行一列的结果集
        ResultSet rs = JdbcUtil.query(sql);
        rs.next();
        int result = rs.getInt(1);
        JdbcUtil.close(rs);
        return result;
    }

}
