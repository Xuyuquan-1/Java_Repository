package sdut.xksys.dao;

import org.springframework.stereotype.Repository;
import sdut.xksys.bean.Course;
import sdut.xksys.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EnrollmentDao {

    public int getSelectedCount(String studentno) throws SQLException {
        // 统计所有数据的行数
        String sql = "select count(*) from enrollments where studentno=? and status='ENROLLED'";
        // 返回的结果集是一个包含一行一列的结果集
        ResultSet rs = JdbcUtil.query(sql, studentno); // 传递 studentno 参数
        try {
            rs.next();
            return rs.getInt(1);
        } finally {
            JdbcUtil.close(rs);
        }
    }

    public int getAvailableCount(String studentno) throws SQLException {
        // 统计所有数据的行数
        String sql = "select count(*) from enrollments where studentno=? and status='PENDING'";
        // 返回的结果集是一个包含一行一列的结果集
        ResultSet rs = JdbcUtil.query(sql, studentno); // 传递 studentno 参数
        try {
            rs.next();
            return rs.getInt(1);
        } finally {
            JdbcUtil.close(rs);
        }
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
        int currentCount = getCurrentCount(courseid);
        currentCount = currentCount + 1;
        int maxEnrollment = getMaxEnrollment(courseid);
        if (currentCount > maxEnrollment) {
            currentCount = currentCount - 1;
            return -1;
        } else {
            String sql1 = "UPDATE currentenrollments SET currentenrollment = ? WHERE courseid = ?";
            JdbcUtil.update(sql1, currentCount, courseid);

            int currentCredits = getStudentCurrentCredits(studentno);
            int newCredits = getStudentNewCredits(courseid);
            currentCredits = currentCredits + newCredits;

            String sql2 = "UPDATE studentcredits SET currentcredits = ? WHERE studentno = ?";
            JdbcUtil.update(sql2, currentCredits, studentno);

            String sql3 = "UPDATE enrollments SET status = 'ENROLLED' WHERE studentno = ? AND courseid = ?";
            return JdbcUtil.update(sql3, studentno, courseid);
        }
    }

    public int exitCourse(String studentno, int courseid) throws SQLException, IllegalAccessException, InstantiationException {
        int currentCount = getCurrentCount(courseid);
        currentCount = currentCount - 1;
        String sql1 = "UPDATE currentenrollments SET currentenrollment = ? WHERE courseid = ?";
        JdbcUtil.update(sql1, currentCount, courseid);

        int currentCredits = getStudentCurrentCredits(studentno);
        int newCredits = getStudentNewCredits(courseid);
        currentCredits = currentCredits - newCredits;

        String sql2 = "UPDATE studentcredits SET currentcredits = ? WHERE studentno = ?";
        JdbcUtil.update(sql2, currentCredits, studentno);

        String sql3 = "UPDATE enrollments SET status = 'PENDING' WHERE studentno = ? AND courseid = ?";
        return JdbcUtil.update(sql3, studentno, courseid);
    }

    //////////////add

    public int addEnrollment(String studentno, int courseid) {
        String sql = "INSERT INTO enrollments (studentno, courseid, status) VALUES (?, ?, 'PENDING')";
        return JdbcUtil.update(sql, studentno, courseid);
    }

    public int getCurrentCount(int courseid) throws SQLException {
        String sql = "SELECT currentenrollment FROM currentenrollments WHERE courseid = ?";
        ResultSet rs = JdbcUtil.query(sql, courseid);
        try {
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("No current enrollment found for courseid: " + courseid);
            }
        } finally {
            JdbcUtil.close(rs);
        }
    }

    public int getMaxEnrollment(int courseid) throws SQLException {
        String sql = "SELECT maxenrollment FROM courses WHERE courseid = ?";
        ResultSet rs = JdbcUtil.query(sql, courseid);
        try {
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("No max enrollment found for courseid: " + courseid);
            }
        } finally {
            JdbcUtil.close(rs);
        }
    }

    public int getStudentNewCredits(int courseid) throws SQLException {
        String sql = "select credits from courses where courseid = ?";
        ResultSet rs = JdbcUtil.query(sql, courseid);
        try {
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("No credits found for courseid: " + courseid);
            }
        } finally {
            JdbcUtil.close(rs);
        }
    }

    public int getStudentCurrentCredits(String studentno) throws SQLException {
        String sql = "select currentcredits from studentcredits where studentno = ?";
        ResultSet rs = JdbcUtil.query(sql, studentno);
        try {
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("No current credits found for studentno: " + studentno);
            }
        } finally {
            JdbcUtil.close(rs);
        }
    }

    public int getStudentTotalCredits(String studentno) throws SQLException {
        String sql = "select totalcredits from studentcredits where studentno = ?";
        ResultSet rs = JdbcUtil.query(sql, studentno);
        try {
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("No total credits found for studentno: " + studentno);
            }
        } finally {
            JdbcUtil.close(rs);
        }
    }

    public int deleteEnrollment(int courseid) {
        String sql = "DELETE FROM enrollments WHERE courseid = ?";
        return JdbcUtil.update(sql, courseid);
    }
}
