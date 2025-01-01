package sdut.xksys.dao;

import org.springframework.stereotype.Repository;
import sdut.xksys.bean.Course;
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

    //插入详情课程信息
    public int addDetailCourse(DetailCourse detailCourse) {
        String sql = "insert into detailcourse (courseid, description, startdate, enddate, dayofweek, starttime, endtime) values(?,?,?,?,?,?,?)";
        int result = JdbcUtil.update(sql, detailCourse.getCourseid(), detailCourse.getDescription(),
                JdbcUtil.DateTimeToString(detailCourse.getStartdate()),
                JdbcUtil.DateTimeToString(detailCourse.getEnddate()),
                detailCourse.getDayofweek(),
                JdbcUtil.DateTimeToString(detailCourse.getStarttime()),
                JdbcUtil.DateTimeToString(detailCourse.getEndtime())
                );
        return result;
    }


    //更新数据库详情课程信息
    public int updateDetailCourse(DetailCourse detailCourse) {
        CourseDao courseDao = new CourseDao();
        //courseid不是主键需要单独处理
        if (detailCourse.getDetailcourseid() == 0){
            detailCourse.setDetailcourseid(detailCourse.getCourseid());
        }
        System.out.println("**********************");
        System.out.println(detailCourse.toString());
        System.out.println("**********************");
        String sql = "update detailcourse SET description = ?, startdate = ?, enddate = ?, dayofweek = ?, starttime = ?, endtime = ? WHERE courseid = ?";
        int result = JdbcUtil.update(sql,
                detailCourse.getDescription(),
                JdbcUtil.DateTimeToString(detailCourse.getStartdate()),
                JdbcUtil.DateTimeToString(detailCourse.getEnddate()),
                detailCourse.getDayofweek(),
                JdbcUtil.DateTimeToString(detailCourse.getStarttime()),
                JdbcUtil.DateTimeToString(detailCourse.getEndtime()),
                detailCourse.getDetailcourseid()
        );

        return result;
    }

    //用于课程与detail绑定，通过courseid获取detailcourse
    public DetailCourse getDetailCourseByCourseid(int courseid) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select * from detailcourse where courseid = ?";
        //数据库detailcourse是没有dcoursename的，所以需要手动获取
        ResultSet rs = JdbcUtil.query(sql, courseid);
//        rs.next();
        //一一对应，所有只有一条
        List<DetailCourse> list = JdbcUtil.convertResultSetToList(rs, DetailCourse.class);
        JdbcUtil.close(rs);
//        System.out.println(list.size()+"___________"+list.get(0)+"__________");
        DetailCourse  detailCourse =  list.get(0);
        if(detailCourse.getCoursename()==""){
        CourseDao courseDao = new CourseDao();
        detailCourse.setCoursename(courseDao.getCourseById(detailCourse.getCourseid()).getCoursename());
        return detailCourse;
        }
        return list.get(0);
    }




}
