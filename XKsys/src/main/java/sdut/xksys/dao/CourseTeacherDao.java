package sdut.xksys.dao;

import org.springframework.stereotype.Repository;
import sdut.xksys.bean.CourseTeacher;
import sdut.xksys.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CourseTeacherDao {

    public List<CourseTeacher> getselectCourseTeacher(String studentno) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "SELECT " +
                "c.coursename, " +
                "t.teachername, " +
                "t.teacherno, " +
                "t.email, " +
                "t.phone, " +
                "t.office " +
                "FROM " +
                "enrollments e " +
                "JOIN " +
                "courses c ON e.courseid = c.courseid " +
                "JOIN " +
                "course_teachers ct ON e.courseid = ct.courseid " +
                "JOIN " +
                "teachers t ON ct.teacherid = t.teacherid " +
                "WHERE " +
                "e.studentno = ? " +
                "AND e.status = 'ENROLLED';";
        ResultSet rs= JdbcUtil.query(sql, studentno);
        List<CourseTeacher> list=JdbcUtil.convertResultSetToList(rs, CourseTeacher.class);
        JdbcUtil.close(rs);
        return list;
    }

    public int getCourseTeacherCount(String studentno) throws SQLException, IllegalAccessException, InstantiationException {
        return getselectCourseTeacher(studentno).size();
    }
}
