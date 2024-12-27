package sdut.xksys.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sdut.xksys.bean.Admin;
import sdut.xksys.dao.AdminDao;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminDao adminDao;

    /**
     * 管理员登录
     * @param adminname 管理员用户名
     * @param adminpwd 管理员密码
     * @param session 会话
     * @return 重定向URL或错误信息
     */
    @PostMapping("/login")
    public String loginAdmin(@RequestParam("adminname") String adminname,
                             @RequestParam("adminpwd") String adminpwd,
                             HttpSession session) {
        try {
            // 验证用户输入
            if (adminname == null || adminname.isEmpty() || adminpwd == null || adminpwd.isEmpty()) {
                return "redirect:/login?error=true";
            }

            // 查询数据库
            Admin admin = adminDao.getAdminByAdminname(adminname);

            // 检查是否找到管理员，并验证密码
            if (admin != null && adminpwd.equals(admin.getAdminpwd())) { // 建议使用更安全的密码验证方法
                // 登录成功，设置会话属性
                session.setAttribute("admin", admin);
                return "redirect:/admin/dashboard"; // 重定向到管理员主页
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
     * 获取当前登录的管理员信息
     * @param session 会话
     * @return 管理员对象
     */
    @GetMapping("/getinfo")
    public Admin getAdminInfo(HttpSession session) {
        return (Admin) session.getAttribute("admin");
    }

    /**
     * 更新管理员信息
     * @param admin 管理员对象
     * @param session 会话
     * @return 更新结果
     */
    @PostMapping("/edit")
    public Map<String, Object> updateAdmin(@RequestBody Admin admin, HttpSession session) {
        try {
            // 验证用户输入
            if (admin.getAdminname() == null || admin.getAdminname().isEmpty() ||
                    admin.getAdminpwd() == null || admin.getAdminpwd().isEmpty()) {
                return Map.of("success", false, "message", "请填写所有必填字段");
            }

            // 更新管理员信息
            int result = adminDao.updateAdmin(admin);
            if (result > 0) {
                // 更新会话中的管理员信息
                session.setAttribute("admin", admin);
                return Map.of("success", true, "message", "更新成功");
            } else {
                return Map.of("success", false, "message", "更新失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Map.of("success", false, "message", "数据库操作失败");
        }
    }
}
