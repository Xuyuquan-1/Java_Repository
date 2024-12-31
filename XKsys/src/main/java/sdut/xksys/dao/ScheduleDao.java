package sdut.xksys.dao;

import org.springframework.stereotype.Repository;
import sdut.xksys.bean.Schedule;
import sdut.xksys.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ScheduleDao {

    // 插入数据到 schedules 表
    public void updateSchedules(String studentno) {
        String sql1 = "DELETE FROM schedules";
        String sql2 = "INSERT INTO schedules (coursename, classroomname, dayofweek, starttime, endtime, teachername) " +
                "SELECT " +
                "    c.coursename, " +
                "    cl.classroomname, " +
                "    dc.dayofweek, " +
                "    dc.starttime, " +
                "    dc.endtime, " +
                "    t.teachername " +
                "FROM " +
                "    enrollments e " +
                "JOIN " +
                "    courses c ON e.courseid = c.courseid " +
                "JOIN " +
                "    classrooms cl ON e.courseid = cl.courseid " +
                "JOIN " +
                "    detailcourse dc ON e.courseid = dc.courseid " +
                "JOIN " +
                "    course_teachers ct ON e.courseid = ct.courseid " +
                "JOIN " +
                "    teachers t ON ct.teacherid = t.teacherid " +
                "WHERE " +
                "   e.status='ENROLLED' AND e.studentno = ? ;";
        try (Connection conn = JdbcUtil.getConnection()) {
            // 开启事务
            conn.setAutoCommit(false);
            try {
                // 执行删除操作
                try (PreparedStatement pstmt1 = conn.prepareStatement(sql1)) {
                    pstmt1.executeUpdate();
                }
                // 执行插入操作
                try (PreparedStatement pstmt2 = conn.prepareStatement(sql2)) {
                    // 设置参数
                    pstmt2.setString(1, studentno);
                    // 执行更新或插入
                    int rowsAffected = pstmt2.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("成功为学生 " + studentno + " 插入 " + rowsAffected + " 条课程安排记录。");
                    } else {
                        System.out.println("未找到符合条件的选课记录，无法为学生 " + studentno + " 生成课程安排。");
                    }
                }
                // 提交事务
                conn.commit();
            } catch (SQLException e) {
                // 如果发生异常，回滚事务
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 可能需要更详细的错误处理或日志记录
        }
    }

    public List<Schedule> getSchedule() throws SQLException, IllegalAccessException, InstantiationException{
        String sql = "select * from schedules";
        ResultSet rs = JdbcUtil.query(sql);
        List<Schedule> list = JdbcUtil.convertResultSetToList(rs, Schedule.class);
        JdbcUtil.close(rs);
        return list;
    }
    public int getScheduleCount() throws SQLException {
        String sql = "select count(*) from schedules";
        ResultSet rs = JdbcUtil.query(sql);
        try {
            rs.next();
            int count = rs.getInt(1);
            return count;
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("CourseDao.getCourseCount:error");
        }
    }
}
