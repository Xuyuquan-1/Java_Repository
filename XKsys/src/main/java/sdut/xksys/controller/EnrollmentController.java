package sdut.xksys.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sdut.xksys.bean.Student;
import sdut.xksys.dao.EnrollmentDao;
import sdut.xksys.util.RestResult;

import java.util.List;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {
    @Autowired
    EnrollmentDao enrollmentDao;

    @RequestMapping("/selectedlist")
    public Object getSelectedCourses(HttpSession session) {
        try {
            Student student = (Student) session.getAttribute("student");
            String studentno = student.getStudentno();
            List list = enrollmentDao.getSelectedCourses(studentno);
            int count = enrollmentDao.getSelectedCount(studentno);
            return new RestResult( count,list);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("/availablelist")
    public Object getAvailableCourses(HttpSession session) {
        try {
            Student student = (Student) session.getAttribute("student");
            String studentno = student.getStudentno();
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