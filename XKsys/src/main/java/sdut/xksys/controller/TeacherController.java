package sdut.xksys.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sdut.xksys.bean.Student;
import sdut.xksys.bean.Teacher;
import sdut.xksys.dao.TeacherDao;
import sdut.xksys.util.RestResult;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherDao teacherDao;

    @RequestMapping("/list")
    public Object getAllTeachers(Teacher teacher){
        List<Teacher> list = teacherDao.checkTeachers(teacher);
        int count = teacherDao.getTeacherCount();
        return new RestResult(count, list);
    }

    @RequestMapping("/add")
    public Object addTeacher(@RequestBody Teacher teacher) {
        try {
            return teacherDao.adminAddTeacher(teacher);
        } catch (Exception e) {
            e.printStackTrace();
            return "/add error";
        }
    }

    @RequestMapping("/edit")
    public Object updateTeacher(Teacher teacher) {
        try {
            return teacherDao.editUpdateTeacher(teacher);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("/delete")
    public Object deleteTeacher(Teacher teacher) {
        try {
            return teacherDao.deleteTeacher(teacher.getTeacherno());
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }


}
