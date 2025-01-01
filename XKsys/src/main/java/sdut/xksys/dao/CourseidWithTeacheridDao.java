package sdut.xksys.dao;

import org.springframework.stereotype.Repository;
import sdut.xksys.bean.CourseidWithTeacherid;
import sdut.xksys.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CourseidWithTeacheridDao {
    public List<CourseidWithTeacherid> getCourseidWithTeacheridByCourseid(int courseid) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select * from course_teachers where courseid = ?";
        ResultSet rs = JdbcUtil.query(sql, courseid);
        List<CourseidWithTeacherid> list = JdbcUtil.convertResultSetToList(rs, CourseidWithTeacherid.class);
        rs.close();
        return list;
    }

    public List<CourseidWithTeacherid> getCourseidWithTeacheridByTeacherid(int teacherid) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select * from course_teachers where teacherid = ?";
        ResultSet rs = JdbcUtil.query(sql, teacherid);
        List<CourseidWithTeacherid> list = JdbcUtil.convertResultSetToList(rs, CourseidWithTeacherid.class);
        rs.close();
        return list;
    }

    public int addCourseidWithTeacherid(CourseidWithTeacherid courseidWithTeacherid) {
        String sql = "insert into course_teachers(courseid,teacherid) values(?,?)";
        return JdbcUtil.update(sql, courseidWithTeacherid.getCourseid(), courseidWithTeacherid.getTeacherid());
    }

    public int deleteCourseidWithTeacheridBycourseid(int courseid) {
        String sql = "delete from course_teachers where courseid = ?";
        return JdbcUtil.update(sql, courseid);
    }

    public int deleteCourseidWithTeacheridByteacherid(int teacherid) {
        String sql = "delete from course_teachers where teacherid = ?";
        return JdbcUtil.update(sql, teacherid);
    }

    public int updateCourseidWithTeacherBycourseid(CourseidWithTeacherid cwt) {
        String sql = "update course_teachers set teacherid = ? where courseid = ? ";
        return JdbcUtil.update(sql, cwt.getTeacherid(), cwt.getCourseid());
    }

    public int updateCourseidWithTeacherByteacherid(CourseidWithTeacherid cwt) {
        String sql = "update course_teachers set courseid = ? where teacherid = ? ";
        return JdbcUtil.update(sql, cwt.getCourseid(), cwt.getTeacherid());
    }


}
