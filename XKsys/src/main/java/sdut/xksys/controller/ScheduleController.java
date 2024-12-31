package sdut.xksys.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sdut.xksys.bean.Student;
import sdut.xksys.dao.ScheduleDao;
import sdut.xksys.util.RestResult;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleDao scheduleDao;

    @RequestMapping("/list")
    public Object getAllSchedules(HttpSession session) {
        Student student = (Student) session.getAttribute("student");
        String studentno = student.getStudentno();
        try {
            scheduleDao.updateSchedules(studentno);
            List list = scheduleDao.getSchedule();
            int count = scheduleDao.getScheduleCount();
            return new RestResult( count,list);

        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
