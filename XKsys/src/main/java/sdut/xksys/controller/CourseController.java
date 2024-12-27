package sdut.xksys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sdut.xksys.bean.Course;
import sdut.xksys.dao.CourseDao;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseDao courseDao;

    @RequestMapping("/list")
    public Object getAllCourses() {
        try {
            return courseDao.getAllCourses();
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
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


    @RequestMapping("/getselectedcourse")
    public Object getSelectedCourses(String studentno) {
        try {
            return courseDao.getSelectedCourses(studentno);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("/getavailablecourse")
    public Object getAvailableCourses(String studentno) {
        try {
            return courseDao.getAvailableCourses(studentno);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("selectcourse")
    public Object selectCourse(int courseid, String studentno) {
        try {
            return courseDao.selectCourse(courseid, studentno);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("/exitcourse")
    public Object exitCourse(int courseid, String studentno) {
        try {
            return courseDao.exitCourse(courseid, studentno);
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
