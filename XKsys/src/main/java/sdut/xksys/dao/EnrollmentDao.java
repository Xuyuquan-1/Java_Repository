package sdut.xksys.dao;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import sdut.xksys.bean.Course;
import sdut.xksys.bean.Student;
import sdut.xksys.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EnrollmentDao {

    public int getSelectedCount(String studentno) throws SQLException {
        // 统计所有数据的行数
        String sql = "select count(*) from enrollments where studentno=? and status='ENROLLED'";
        // 返回的结果集是一个包含一行一列的结果集
        ResultSet rs = JdbcUtil.query(sql, studentno); // 传递 studentno 参数
        rs.next();
        int result = rs.getInt(1);
        return result;
    }

    public int getAvailableCount(String studentno) throws SQLException {
        // 统计所有数据的行数
        String sql = "select count(*) from enrollments where studentno=? and status='PENDING'";
        // 返回的结果集是一个包含一行一列的结果集
        ResultSet rs = JdbcUtil.query(sql, studentno); // 传递 studentno 参数
        rs.next();
        int result = rs.getInt(1);
        return result;
    }

    public List<Course> getSelectedCourses(String studentno) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "SELECT c.* " +
                "FROM students s " +
                "JOIN enrollments e ON s.studentno = e.studentno " +
                "JOIN courses c ON e.courseid = c.courseid " +
                "WHERE s.studentno = ? AND e.status = 'ENROLLED' " +
                "ORDER BY c.coursename;";
        ResultSet rs = JdbcUtil.query(sql, studentno); // 传递 studentno 参数
        List<Course> courseList = JdbcUtil.convertResultSetToList(rs, Course.class);
        JdbcUtil.close(rs);
        return courseList;
    }


    public List<Course> getAvailableCourses(String studentno) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "SELECT c.* " +
                "FROM students s " +
                "JOIN enrollments e ON s.studentno = e.studentno " +
                "JOIN courses c ON e.courseid = c.courseid " +
                "WHERE s.studentno = ? AND e.status = 'PENDING' " +
                "ORDER BY c.coursename;";
        ResultSet rs = JdbcUtil.query(sql, studentno); // 传递 studentno 参数
        List<Course> courseList = JdbcUtil.convertResultSetToList(rs, Course.class);
        JdbcUtil.close(rs);
        return courseList;
    }


    public int selectCourse(String studentno, int courseid) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "UPDATE enrollments SET status = 'ENROLLED' WHERE studentno = ? AND courseid = ?";
        return JdbcUtil.update(sql, studentno, courseid);
    }
    public int exitCourse(String studentno, int courseid) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "UPDATE enrollments SET status = 'PENDING' WHERE studentno = ? AND courseid = ?";
        return JdbcUtil.update(sql, studentno, courseid);
    }

}