package sdut.xksys.dao;

import org.springframework.stereotype.Repository;
import sdut.xksys.bean.Course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import sdut.xksys.util.JdbcUtil;
import sdut.xksys.util.RestResult;

@Repository
public class CourseDao {

    public List<Course> getAllCourses() throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "SELECT * FROM courses";
        ResultSet rs = JdbcUtil.query(sql);
        List<Course> list = JdbcUtil.convertResultSetToList(rs, Course.class);
        JdbcUtil.close(rs);
        return list;
    }

    public Course getCourseById(int courseid) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "SELECT * FROM courses WHERE courseid = ?";
        ResultSet rs = JdbcUtil.query(sql, courseid);
        List<Course> list = JdbcUtil.convertResultSetToList(rs, Course.class);
        Course course = list.get(0);
        JdbcUtil.close(rs);
        return course;
    }

    public List<Course> getSelectedCourses(String studentno) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "SELECT c.* FROM courses c JOIN enrollments e ON c.courseid = e.courseid WHERE e.studentno = ?";
        ResultSet rs = JdbcUtil.query(sql, studentno);
        List<Course> list = JdbcUtil.convertResultSetToList(rs, Course.class);
        JdbcUtil.close(rs);
        return list;
    }

    public List<Course> getAvailableCourses(String studentno) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "SELECT c.* FROM courses c LEFT JOIN enrollments e ON c.courseid = e.courseid WHERE e.studentno IS NULL AND c.maxenrollment > (SELECT COUNT(*) FROM enrollments WHERE courseid = c.courseid)";
        ResultSet rs = JdbcUtil.query(sql, studentno);
        List<Course> list = JdbcUtil.convertResultSetToList(rs, Course.class);
        JdbcUtil.close(rs);
        return list;
    }

    public Object selectCourse(int courseid, String studentno) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "INSERT INTO enrollments (studentno, courseid, status) VALUES (?, ?, 'PENDING')";
        int rowsAffected = JdbcUtil.update(sql, studentno, courseid);
        return rowsAffected;
    }

    public int getCourseCount(){
        String sql = "select count(*) from courses";
        ResultSet rs = JdbcUtil.query(sql);
        try {
            rs.next();
            int count = rs.getInt(1);
            return count;
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("CourseDao.getCourseCount:error");
        }
        finally {
            ;
        }

    }

    public Object exitCourse(int courseid, String studentno) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "DELETE FROM enrollments WHERE studentno = ? AND courseid = ?";
        int rowsAffected = JdbcUtil.update(sql, studentno, courseid);
        return rowsAffected;
    }

    public int addCourse(Course course) throws SQLException {
        String sql = "INSERT INTO courses (coursename, credits, semester, maxenrollment) VALUES (?, ?, ?, ?)";
        int rowsAffected = JdbcUtil.update(sql, course.getCoursename(), course.getCredits(), course.getSemester(), course.getMaxenrollment());
        return rowsAffected;
    }

    public int updateCourse(Course course) throws SQLException {
        String sql = "UPDATE courses SET coursename = ?, credits = ?, semester = ?, maxenrollment = ? WHERE courseid = ?";
        int rowsAffected = JdbcUtil.update(sql, course.getCoursename(), course.getCredits(), course.getSemester(), course.getMaxenrollment(), course.getCourseid());
        return rowsAffected;
    }

    public int deleteCourse(int courseid) throws SQLException {
        String sql = "DELETE FROM courses WHERE courseid = ?";
        int rowsAffected = JdbcUtil.update(sql, courseid);
        return rowsAffected;
    }
}