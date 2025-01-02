package sdut.xksys.dao;

import org.springframework.stereotype.Repository;
import sdut.xksys.bean.Classroom;
import sdut.xksys.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClassroomDao {

    public List<Classroom> getAllClassrooms() throws SQLException, IllegalAccessException, InstantiationException{
        String sql = "select * from classrooms";
        return JdbcUtil.convertResultSetToList(JdbcUtil.query(sql), Classroom.class);
    }

    public int getClassroomCount() throws SQLException{
        String sql = "select count(*) from classrooms";
        return JdbcUtil.query(sql).getInt(1);
    }


    public Classroom getClassroomById(int classroomid) throws SQLException, IllegalAccessException, InstantiationException{
        String sql = "select * from classrooms where classroomid = ?";
        ResultSet rs = JdbcUtil.query(sql, classroomid);
        List<Classroom> list = JdbcUtil.convertResultSetToList(rs, Classroom.class);
        JdbcUtil.close(rs);
        return list.get(0);
    }


    public Classroom getClassroomByClassroomName(String classroomname) throws SQLException, IllegalAccessException, InstantiationException{
        String sql = "select * from classrooms where classroomname = ?";
        return JdbcUtil.convertResultSetToList(JdbcUtil.query(sql, classroomname), Classroom.class).get(0);
    }

    public int addClassroom(Classroom classroom){
        String sql = "insert into classrooms(courseid, classroomname, capacity) values(?,?,?)";
        return JdbcUtil.update(sql, classroom.getCourseid(), classroom.getClassroomname(), classroom.getCapacity());
    }

    public int updateClassroom(Classroom classroom) {
        String sql = "update classrooms set classroomname = ?, capacity = ?, courseid = ? where classroomid = ?";
        return JdbcUtil.update(sql, classroom.getClassroomname(), classroom.getCapacity(),classroom.getCourseid() ,classroom.getClassroomid());
    }


    public int deleteClassroom(int courseid) {
        String sql = "delete from classrooms where courseid = ?";
        return JdbcUtil.update(sql, courseid);
    }

    public Classroom getClassroomByCourseId(int courseid) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select * from classrooms where courseid = ?";
        return JdbcUtil.convertResultSetToList(JdbcUtil.query(sql, courseid), Classroom.class).get(0);
    }
}
