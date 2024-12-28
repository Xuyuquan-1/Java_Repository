package sdut.xksys.dao;

import org.springframework.stereotype.Repository;
import sdut.xksys.bean.Admin;
import sdut.xksys.bean.Course;
import sdut.xksys.bean.Student;
import sdut.xksys.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AdminDao {
    public Admin getAdminByAdminaccount(String account) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select * from admins where adminaccount = ?";
        ResultSet rs = JdbcUtil.query(sql, account);
        List<Admin> list = JdbcUtil.convertResultSetToList(rs, Admin.class);
        Admin admin = list.get(0);
        JdbcUtil.close(rs);
        return admin;
    }

    public int updateAdmin(Admin admin) throws SQLException {
        String sql = "update admins set adminaccount = ?, adminpwd = ?,adminaccount = ?, email = ?, phone = ? where adminid = ?";
        int result = JdbcUtil.update(sql, admin.getAdminaccount(), admin.getAdminpwd(), admin.getAdminname(), admin.getEmail(), admin.getPhone(), admin.getAdminid());
        return result;
    }

    //    查询某个学生的全部选课记录
    public List<Course> getStudentCourses(String studentno) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "SELECT c.* FROM students s JOIN enrollments e ON s.studentno = e.studentno JOIN courses c ON e.courseid = c.courseid WHERE s.studentno = ? ORDER BY e.status DESC, c.coursename";
        ResultSet rs = JdbcUtil.query(sql, studentno);
        List<Course> courseList = JdbcUtil.convertResultSetToList(rs, Course.class);
        JdbcUtil.close(rs);
        return courseList;
    }

    // 查询某个课程的选课学生列表
    public List<Student> getCourseStudents(int courseId) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "SELECT s.* FROM courses c JOIN enrollments e ON c.courseid = e.courseid JOIN students s ON e.studentno = s.studentno WHERE c.courseid = ? ORDER BY e.status DESC, s.studentname";
        ResultSet rs = JdbcUtil.query(sql, courseId);
        List<Student> studentList = JdbcUtil.convertResultSetToList(rs, Student.class);
        JdbcUtil.close(rs);
        return studentList;
    }
}
