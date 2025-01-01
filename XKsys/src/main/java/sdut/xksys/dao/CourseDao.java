package sdut.xksys.dao;

import org.springframework.stereotype.Repository;
import sdut.xksys.bean.Course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sdut.xksys.bean.Student;
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

    public List<Course> getCheckCourse(Course course) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select * from courses ";
        List<String> params = new ArrayList<>();
        if(course.getCoursename()!=null){
            sql = sql+"where coursename like ?";
            //模糊查询
            params.add("%"+course.getCoursename()+"%");
        }
//        sql=sql+" order by status asc ";
        ResultSet rs = JdbcUtil.query(sql, params.toArray());
        List<Course> list=JdbcUtil.convertResultSetToList(rs, Course.class);

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

    public Course getCourseByName(String coursename){
        String sql = "select * from courses where coursename = ?";
        ResultSet rs = JdbcUtil.query(sql, coursename);
        try {
            List<Course> list = JdbcUtil.convertResultSetToList(rs, Course.class);
            Course course = list.get(0);
            return course;
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("CourseDao.getCourseByName:error");
        }
    }


    //专门用来模糊查询
    public List<Course> getCheckCourseByName(String coursename){
        String sql = "select * from courses where coursename like ?";
        ResultSet rs = JdbcUtil.query(sql, "%"+coursename+"%");
        try {
            List<Course> list = JdbcUtil.convertResultSetToList(rs, Course.class);
            return list;
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("CourseDao.getCheckCourseByName:error");
        }
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