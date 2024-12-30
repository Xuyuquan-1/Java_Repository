package sdut.xksys.controller;


import com.mysql.cj.xdevapi.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sdut.xksys.bean.Schedule;
import sdut.xksys.bean.Student;
import sdut.xksys.dao.AdminUpdatePwdDao;
import sdut.xksys.dao.StudentDao;
import sdut.xksys.util.JdbcUtil;
import sdut.xksys.util.RestResult;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/update")
public class AdminUpdatePwdController {

    @Autowired
    private AdminUpdatePwdDao updateDao;
    @Autowired
    private StudentDao studentDao;

    @RequestMapping("/list")
    public Object checkStudent(Student student) {
        try {
            List<Student> list = updateDao.getCheckStudent(student);
            if(list == null) {
                return new RestResult(0, null);
            }
            int count = updateDao.getStudentCount();
            RestResult result = new RestResult(count,list);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Controller.update/list:error");
        }
        finally {
            ;
        }
    }
    @RequestMapping("/pwdupdate")
    public Object updateStudentPwd(Student student) throws SQLException, IllegalAccessException, InstantiationException {

        if(student.getStudentno() == null || student.getStudentpwd()==null)
            return new RestResult(0,null);

        String newpwd = student.getStudentpwd();
        Student newstudent = studentDao.getStudentByStudentno(student.getStudentno());
        newstudent.setStudentpwd(student.getStudentpwd());
        studentDao.updateStudent(newstudent);
        List<Student> list = new ArrayList<>();
        list.add(newstudent);
        return new RestResult(1,list);
    }


}
