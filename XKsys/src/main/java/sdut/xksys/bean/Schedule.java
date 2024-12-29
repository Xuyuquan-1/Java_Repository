package sdut.xksys.bean;

import lombok.Data;

import java.sql.Time;

@Data
public class Schedule {
    Long scheduleid; // 上课时间表ID
    String teachername; // 教师姓名
    String classroom; // 教室名称
    String dayofweek; // 上课星期
    Time starttime; // 上课开始时间
    Time endtime; // 上课结束时间
    String studentno; // 学生学号
    Long courseid; // 课程ID

    public Long getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(Long scheduleid) {
        this.scheduleid = scheduleid;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getDayofweek() {
        return dayofweek;
    }

    public void setDayofweek(String dayofweek) {
        this.dayofweek = dayofweek;
    }

    public Time getStarttime() {
        return starttime;
    }

    public void setStarttime(Time starttime) {
        this.starttime = starttime;
    }

    public Time getEndtime() {
        return endtime;
    }

    public void setEndtime(Time endtime) {
        this.endtime = endtime;
    }

    public String getStudentno() {
        return studentno;
    }

    public void setStudentno(String studentno) {
        this.studentno = studentno;
    }

    public Long getCourseid() {
        return courseid;
    }

    public void setCourseid(Long courseid) {
        this.courseid = courseid;
    }
}