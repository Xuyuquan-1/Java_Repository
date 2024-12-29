package sdut.xksys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sdut.xksys.dao.EnrollmentDao;
import sdut.xksys.util.RestResult;

import java.util.List;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {
    @Autowired
    EnrollmentDao enrollmentDao;

    @RequestMapping("/selectedlist")
    public Object getSelectedCourses(String studentno) {
        try {
            List list = enrollmentDao.getSelectedCourses(studentno);
            int count = enrollmentDao.getSelectedCount(studentno);
            return new RestResult( count,list);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("/availablelist")
    public Object getAvailableCourses(String studentno) {
        try {
            List list = enrollmentDao.getAvailableCourses(studentno);
            int count = enrollmentDao.getAvailableCount(studentno);
            return new RestResult(count, list);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("/selectcourse")
    public Object selectCourse(String studentno, int courseid) {
        try {
            return enrollmentDao.selectCourse(studentno, courseid);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("/exitcourse")
    public Object exitCourse(String studentno, int courseid) {
        try {
            return enrollmentDao.exitCourse(studentno, courseid);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

}