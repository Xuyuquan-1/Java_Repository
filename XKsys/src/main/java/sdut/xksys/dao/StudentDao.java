package sdut.xksys.dao;

import org.springframework.stereotype.Repository;
import sdut.xksys.bean.Course;
import sdut.xksys.bean.Student;
import sdut.xksys.util.JdbcUtil;
import sdut.xksys.util.RestResult;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDao {
    public Student getStudentByStudentno(String studentno) throws SQLException, IllegalAccessException, InstantiationException, SQLException {
        String sql = "select * from students where studentno = ?";
        ResultSet rs = JdbcUtil.query(sql, studentno);
        List<Student> list = JdbcUtil.convertResultSetToList(rs, Student.class);
        Student student = list.get(0);
        JdbcUtil.close(rs);
        return student;
    }

    public List<Student> getAllStudents() throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select * from students";
        ResultSet rs = JdbcUtil.query(sql);
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

    public List<Student> getCheckStudent(Student student) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select * from students ";
        List<String> params = new ArrayList<>();
        if(student.getStudentname()!=null){
            sql = sql+"where studentname like ?";
            params.add("%"+student.getStudentname()+"%");
        }
//        sql=sql+" order by status asc ";
        ResultSet rs = JdbcUtil.query(sql, params.toArray());
        List<Student> list=JdbcUtil.convertResultSetToList(rs, Student.class);

        JdbcUtil.close(rs);
        return list;
    }

    public int addStudent(Student student) {
        String sql = "insert into students(studentname, studentno, studentpwd, email, major, grade) values(?, ?, ?, ?, ?, ?)";
        int result = JdbcUtil.update(sql, student.getStudentname(), student.getStudentno(), student.getStudentpwd(), student.getEmail(), student.getMajor(), student.getGrade());
        return result;
    }



    public int updateStudent(Student student) throws SQLException {
        String sql = "update students set studentname = ?,studentno = ?,studentpwd = ?, email = ?, major = ?, grade = ? where studentid = ?";
        int result = JdbcUtil.update(sql, student.getStudentname(), student.getStudentno(), student.getStudentpwd(), student.getEmail(), student.getMajor(), student.getGrade(), student.getStudentid());
        return result;
    }

    //仅用于admin增加学生
    public int adminAddStudent(Student student) {
        String sql = "insert into students(studentname, studentno, studentpwd, email, major, grade) values(?, ?, ?, ?, ?, ?)";
        int result = JdbcUtil.update(sql, student.getStudentname(), student.getStudentno(), "123456", student.getEmail(), student.getMajor(), student.getGrade());

        return result;
    }

    //仅用于admin编辑学生
    public int editUpdateStudent(Student student) {
        String sql = "update students set studentname = ?, email = ?, major = ?, grade = ? where studentid = ?";
        int result = JdbcUtil.update(sql, student.getStudentname(), student.getEmail(), student.getMajor(), student.getGrade(), student.getStudentid());
        return result;
    }

    public int deleteStudent(String studentid) throws SQLException {
        String sql = "delete from students where studentno = ?";
        int result = JdbcUtil.update(sql, studentid);
        return result;
    }

}