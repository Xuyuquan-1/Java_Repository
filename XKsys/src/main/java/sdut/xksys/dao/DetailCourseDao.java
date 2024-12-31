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
                "    c.coursename, " +
                "    dc.description, " +
                "    dc.startdate, " +
                "    dc.enddate, " +
                "    dc.dayofweek, " +
                "    dc.starttime, " +
                "    dc.endtime " +
                "FROM " +
                "    enrollments e " +
                "JOIN " +
                "    courses c ON e.courseid = c.courseid " +
                "JOIN " +
                "    detailcourse dc ON e.courseid = dc.courseid " +
                "WHERE " +
                "    e.studentno = ?;";
        ResultSet rs = JdbcUtil.query(sql, studentno);
        List<DetailCourse> list = JdbcUtil.convertResultSetToList(rs, DetailCourse.class);
        JdbcUtil.close(rs);
        return list;
    }

    public int getDetailCourseCount(String studentno) throws SQLException, IllegalAccessException, InstantiationException {
        return getDetailCourse(studentno).size();
    }
}
