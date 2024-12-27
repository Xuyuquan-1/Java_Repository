package sdut.xksys.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sdut.xksys.bean.Course;
import sdut.xksys.bean.Student;
import sdut.xksys.dao.StudentDao;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentDao studentDao;

    /**
     * 学生登录
     * @param studentno 学号
     * @param studentpwd 密码
     * @param session 会话
     * @return 重定向URL或错误信息
     */
    @PostMapping("/login")
    public String loginStudent(@RequestParam("studentno") String studentno,
                               @RequestParam("studentpwd") String studentpwd,
                               HttpSession session) {
        try {
            // 验证用户输入
            if (studentno == null || studentno.isEmpty() || studentpwd == null || studentpwd.isEmpty()) {
                return "redirect:/login?error=true";
            }

            // 查询数据库
            Student student = studentDao.getStudentByStudentno(studentno);

            // 检查是否找到学生，并验证密码
            if (student != null && studentpwd.equals(student.getStudentpwd())) { // 建议使用更安全的密码验证方法
                // 登录成功，设置会话属性
                session.setAttribute("student", student);
                return "redirect:/student/dashboard"; // 重定向到学生主页
            } else {
                // 登录失败，返回登录页面并显示错误信息
                return "redirect:/login?error=true";
            }
        } catch (SQLException | IllegalAccessException | InstantiationException e) {
            // 处理异常
            e.printStackTrace();
            return "redirect:/login?error=true";
        }
    }


    /**
     * 获取当前登录的学生信息
     * @param session 会话
     * @return 学生对象
     */
    @GetMapping("/info")
    public Student getStudentInfo(HttpSession session) {
        return (Student) session.getAttribute("student");
    }

    @RequestMapping("/list")
    public Object getAllStudents() {
        try {
            return studentDao.getAllStudents();
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }


    /**
     * 更新学生信息
     * @param student 学生对象
     * @param session 会话
     * @return 更新结果
     */
    @PostMapping("/edit")
    public Map<String, Object> updateStudent(@RequestBody Student student, HttpSession session) {
        try {
            // 验证用户输入
            if (student.getStudentname() == null || student.getStudentname().isEmpty() ||
                    student.getStudentpwd() == null || student.getStudentpwd().isEmpty()) {
                return Map.of("success", false, "message", "请填写所有必填字段");
            }

            // 更新学生信息
            int result = studentDao.updateStudent(student);
            if (result > 0) {
                // 更新会话中的学生信息
                session.setAttribute("student", student);
                return Map.of("success", true, "message", "更新成功");
            } else {
                return Map.of("success", false, "message", "更新失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Map.of("success", false, "message", "数据库操作失败");
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