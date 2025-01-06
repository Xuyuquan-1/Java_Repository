package sdut.xksys.dao;

import org.springframework.stereotype.Repository;
import sdut.xksys.bean.CurrentEnrollment;
import sdut.xksys.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CurrentEnrollmentDao {
    public CurrentEnrollment getCurrentEnrollmentById(int courseid) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select * from currentenrollments where courseid = ?";
        ResultSet rs = JdbcUtil.query(sql, courseid);
        List<CurrentEnrollment> list = JdbcUtil.convertResultSetToList(rs, CurrentEnrollment.class);
        JdbcUtil.close(rs);
        return list.get(0);
    }

    public int addInitCurrentEnrollment(int courseid) {
        String sql = "insert into currentenrollments(courseid,currentenrollment) values(?,?)";
        return JdbcUtil.update(sql, courseid, 0);
    }

    public int addCurrentEnrollment(CurrentEnrollment currentEnrollment) {
        String sql = "insert into currentenrollments(courseid,currentenrollment) values(?,?)";
        return JdbcUtil.update(sql, currentEnrollment.getCourseid(), currentEnrollment.getCurrentenrollment());
    }

    public int updateCurrentEnrollmentById(CurrentEnrollment currentEnrollment) {
        String sql = "update currentenrollments set currentenrollment = ? where courseid = ?";
        return JdbcUtil.update(sql, currentEnrollment.getCurrentenrollment(), currentEnrollment.getCourseid());
    }

    public int deleteCurrentEnrollmentById(int courseid) {
        String sql = "delete from currentenrollments where courseid = ?";
        return JdbcUtil.update(sql, courseid);
    }
}