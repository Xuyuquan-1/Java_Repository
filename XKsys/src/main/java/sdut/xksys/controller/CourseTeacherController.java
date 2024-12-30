package sdut.xksys.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sdut.xksys.bean.Student;
import sdut.xksys.dao.CourseTeacherDao;
import sdut.xksys.util.RestResult;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/courseteacher")
public class CourseTeacherController {

    @Autowired
    CourseTeacherDao courseTeacherDao;

    @RequestMapping("/list")
    public Object getAllSchedules(HttpSession session){
        try {

            Student student = (Student) session.getAttribute("student");
            String studentno = student.getStudentno();
            List list = courseTeacherDao.getselectCourseTeacher(studentno);
            int count = courseTeacherDao.getCourseTeacherCount(studentno);
            return new RestResult(count, list);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }
}