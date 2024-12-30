package sdut.xksys.dao;

import org.springframework.stereotype.Repository;
import sdut.xksys.bean.DetailCourse;
import sdut.xksys.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DetailCourseDao {
    public List<DetailCourse> getDetailCourse(String studentno) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "SELECT " +
                "c.coursename, " +
                "d.description, " +
                "d.startdate, " +
                "d.enddate, " +
                "t.dayofweek, " +
                "t.starttime, " +
                "t.endtime " +
                "FROM " +
                "enrollments e " +
                "JOIN " +
                "detailcourse d ON e.courseid = d.courseid " +
                "JOIN " +
                "courses c ON d.courseid = c.courseid " +
                "JOIN " +
                "course_times ct ON d.courseid = ct.courseid " +
                "JOIN " +
                "times t ON ct.timeid = t.timeid " +
                "WHERE " +
                "e.studentno = ? ;";
        ResultSet rs = JdbcUtil.query(sql, studentno);
        List<DetailCourse> list = JdbcUtil.convertResultSetToList(rs, DetailCourse.class);
        JdbcUtil.close(rs);
        return list;
    }

    public int getDetailCourseCount(String studentno) throws SQLException, IllegalAccessException, InstantiationException {
        return getDetailCourse(studentno).size();
    }
}
