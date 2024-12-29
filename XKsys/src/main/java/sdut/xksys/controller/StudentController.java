package sdut.xksys.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sdut.xksys.bean.Course;
import sdut.xksys.bean.Student;
import sdut.xksys.dao.StudentDao;
import sdut.xksys.util.RestResult;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentDao studentDao;

    @GetMapping("/info")
    public Student getStudentInfo(HttpSession session) {
        return (Student) session.getAttribute("student");
    }

    @RequestMapping("/list")
    public Object getAllStudents() {
        try {
            List<Student> list = studentDao.getAllStudents();
            int count = studentDao.getStudentCount();
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

    @RequestMapping("/add")
    public Object addStudent(Student student) {
        try {
            return studentDao.addStudent(student);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("/edit")
    public Object updateStudent(Student student) {
        try {
            return studentDao.updateStudent(student);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("/delete")
    public Object deleteStudent(String studentno) {
        try {
            return studentDao.deleteStudent(studentno);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

}