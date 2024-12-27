package sdut.xksys.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sdut.xksys.bean.Admin;
import sdut.xksys.bean.Student;
import sdut.xksys.dao.AdminDao;
import sdut.xksys.dao.StudentDao;

import java.sql.SQLException;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AdminDao adminDao;
    @Autowired
    private StudentDao studentDao;

    @PostMapping("/adminlogin")
    public String loginAdmin(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             HttpSession session) throws SQLException, IllegalAccessException, InstantiationException {
        // 验证用户输入
        if (isInputEmpty(username, password)) {
            return "error";
        }

        // 查询数据库
        Admin admin = adminDao.getAdminByAdminname(username);

        // 检查是否找到管理员，并验证密码
        if (admin != null && password.equals(admin.getAdminpwd())) { // 建议使用更安全的密码验证方法
            // 登录成功，设置会话属性
            session.setAttribute("admin", admin);
            return "success"; // 返回成功标识
        } else {
            // 登录失败
            return "error";
        }
    }

    @PostMapping("/studentlogin")
    public String loginStudent(@RequestParam("studentno") String studentno,
                               @RequestParam("studentpwd") String studentpwd,
                               HttpSession session) throws SQLException, IllegalAccessException, InstantiationException {
        // 验证用户输入
        if (isInputEmpty(studentno, studentpwd)) {
            return "error";
        }

        // 查询数据库
        Student student = studentDao.getStudentByStudentno(studentno);

        // 检查是否找到学生，并验证密码
        if (student != null && studentpwd.equals(student.getStudentpwd())) {
            // 登录成功，设置会话属性
            session.setAttribute("student", student);
            return "success"; // 返回成功标识
        } else {
            // 登录失败
            return "error";
        }
    }

    @RequestMapping("/islogin")
    public String isLogin(HttpSession session) {
        // 检查会话中是否存在用户信息
        if (session.getAttribute("admin") != null || session.getAttribute("student") != null) {
            return "success"; // 已登录
        } else {
            return "error"; // 未登录
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        // 清除会话属性
        session.removeAttribute("admin");
        session.removeAttribute("student");
        // 重定向到登录页面
        return "redirect:/login.html";
    }

    private boolean isInputEmpty(String... inputs) {
        for (String input : inputs) {
            if (input == null || input.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}