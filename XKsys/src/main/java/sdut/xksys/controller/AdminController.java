package sdut.xksys.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sdut.xksys.bean.Admin;
import sdut.xksys.dao.AdminDao;
import sdut.xksys.util.RestResult;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminDao adminDao;

    @GetMapping("/getinfo")
    public Admin getAdminInfo(HttpSession session){

         Admin oldAdmin =  (Admin) session.getAttribute("admin");
         Admin newAdmin = adminDao.getAdminById(oldAdmin.getAdminid());
         try {
             adminDao.updateAdmin(newAdmin);
             session.setAttribute("admin", newAdmin);
             return (Admin) session.getAttribute("admin");
         }catch (Exception e){
             e.printStackTrace();
             throw new RuntimeException("/getinfo:error!");
         }finally {
             ;
         }
    }

    @RequestMapping("/edit")
    public Object updateAdmin(Admin admin) {
        try {
            int result = adminDao.updateAdmin(admin);
            if (result > 0) {
                return "success";
            } else {
                return "error";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("/getStudentCredits")
    public Object getStudentCredits() {
        try {
            List list = adminDao.getStudentCredits();
            int count = adminDao.getStudentCurrentCreditsCount();
            return new RestResult(count,list);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("/updateStudentCredits")
    public Object updateStudentCredits(String totalcredits) {
        try {
            int result = adminDao.updateStudentTotalCredits(totalcredits);
            if (result > 0) {
                return "success";
            } else {
                return "error";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
    }
}
