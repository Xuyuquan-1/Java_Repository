package sdut.xksys.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sdut.xksys.bean.Student;
import sdut.xksys.dao.DetailCourseDao;
import sdut.xksys.util.RestResult;

import java.util.List;

@RestController
@RequestMapping("/detailcourse")
public class DetailCourseController {

    @Autowired
    DetailCourseDao detailCourseDao;

    @RequestMapping("/list")
    public Object getDetailCourse(HttpSession session) {
        try {
            Student student = (Student) session.getAttribute("student");
            String studentno = student.getStudentno();
            List list = detailCourseDao.getDetailCourse(studentno);
            int count = detailCourseDao.getDetailCourseCount(studentno);
            return new RestResult(count, list);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }




}