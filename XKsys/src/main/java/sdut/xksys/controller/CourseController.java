package sdut.xksys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sdut.xksys.bean.Course;
import sdut.xksys.bean.CourseWithDsp;
import sdut.xksys.bean.Student;
import sdut.xksys.dao.CourseDao;
import sdut.xksys.dao.DetailCourseDao;
import sdut.xksys.util.RestResult;

import java.sql.ResultSet;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseDao courseDao;
    @Autowired
    DetailCourseDao detailCourseDao;

    @RequestMapping("/list")
    public Object getAllCourses() {
        try {
            List list = courseDao.getAllCourses();
            int count = courseDao.getCourseCount();
            return new RestResult( count,list);

        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("/newlist")
    public Object checkStudent(Course course) {
        try {
            System.out.println(course.getCoursename());
            List<Course> list = courseDao.getCheckCourse(course);
            int count =  courseDao.getCourseCount();
            RestResult result = new RestResult(count,list);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Controller.getAllstudent:error");
        }
        finally {
            ;
        }
    }
    @RequestMapping("/getcoursebyid")
    public Object getCourseById(int courseid) {
        try {
            return courseDao.getCourseById(courseid);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
    @RequestMapping("/add")
    public Object addCourse(Course course) {
        try {
            return courseDao.addCourse(course);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("/newadd")
    public Object newaddCourse(@RequestBody CourseWithDsp data) {
        try {
            courseDao.addCourse(data.getCourse());
            String coursename = data.getCourse().getCoursename();
            detailCourseDao.addDetailCourse(coursename);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("/edit")
    public Object updateCourse(Course course) {
        try {
            return courseDao.updateCourse(course);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("/delete")
    public Object deleteCourse(int courseid) {
        try {
            return courseDao.deleteCourse(courseid);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
