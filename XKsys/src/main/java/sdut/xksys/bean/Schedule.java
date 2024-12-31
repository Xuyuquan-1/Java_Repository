package sdut.xksys.bean;

import lombok.Data;

import java.sql.Time;

@Data
public class Schedule {
    Long scheduleid; // 上课时间表ID
    String coursename;
    String teachername; // 教师姓名
    String classroomname; // 教室名称
    String dayofweek; // 上课星期
    Time starttime; // 上课开始时间
    Time endtime; // 上课结束时间

    public Schedule() {
    }

    public Schedule(String coursename, Long scheduleid, String teachername, String classroomname, String dayofweek, Time starttime, Time endtime) {
        this.coursename = coursename;
        this.scheduleid = scheduleid;
        this.teachername = teachername;
        this.classroomname = classroomname;
        this.dayofweek = dayofweek;
        this.starttime = starttime;
        this.endtime = endtime;
    }


    public Long getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(Long scheduleid) {
        this.scheduleid = scheduleid;
    }

    public void setCoursename() {
        this.coursename = coursename;
    }
    public String getCoursename() {
        return coursename;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getClassroomname() {
        return classroomname;
    }

    public void setClassroomname(String classroomname) {
        this.classroomname = classroomname;
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
}