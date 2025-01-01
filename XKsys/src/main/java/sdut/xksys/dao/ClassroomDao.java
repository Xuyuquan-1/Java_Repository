package sdut.xksys.dao;

import org.springframework.stereotype.Repository;
import sdut.xksys.bean.Classroom;
import sdut.xksys.util.JdbcUtil;

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
        return JdbcUtil.convertResultSetToList(JdbcUtil.query(sql, classroomid), Classroom.class).get(0);
    }

    public int addClassroom(Classroom classroom) throws SQLException{
        String sql = "insert into classrooms(courseid, classroomname, capacity) values(?,?,?)";
        return JdbcUtil.update(sql, classroom.getCourseid(), classroom.getClassroomname(), classroom.getCapacity());
    }

    public int updateClassroom(Classroom classroom) throws SQLException{
        String sql = "update classrooms set classroomname = ?, capacity = ?, courseid = ? where classroomid = ?";
        return JdbcUtil.update(sql, classroom.getClassroomname(), classroom.getCapacity(),classroom.getCourseid() ,classroom.getClassroomid());
    }


    public int deleteClassroom(int classroomid) throws SQLException{
        String sql = "delete from classrooms where classroomid = ?";
        return JdbcUtil.update(sql, classroomid);
    }

    public Classroom getClassroomByCourseId(int courseid) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select * from classrooms where courseid = ?";
        return JdbcUtil.convertResultSetToList(JdbcUtil.query(sql, courseid), Classroom.class).get(0);
    }
}
