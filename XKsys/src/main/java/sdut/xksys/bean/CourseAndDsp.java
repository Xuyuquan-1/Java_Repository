package sdut.xksys.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.util.Date;

public class CourseAndDsp {
    int detailcourseid;
    int courseid;
    String coursename;
    String credits;
    String semester;
    int maxenrollment;
    String description;
    //加上这个注解才能解析Date类
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date startdate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date enddate;
    String dayofweek;
    Time starttime;
    Time endtime;


    String strstarttime;
    String strendtime;


    public String getCoursename() {
        return coursename;
    }

    public int getDetailcourseid() {
        return detailcourseid;
    }

    public int getCourseid() {
        return courseid;
    }

    public int getMaxenrollment() {
        return maxenrollment;
    }

    public String getSemester() {
        return semester;
    }

    public String getCredits() {
        return credits;
    }

    public String getDescription() {
        return description;
    }

    public Date getStartdate() {
        return startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public String getDayofweek() {
        return dayofweek;
    }

    public Time getStarttime() {
        return starttime;
    }

    public Time getEndtime() {
        return endtime;
    }

    public String getStrstarttime() {
        return strstarttime;
    }

    public String getStrendtime() {
        return strendtime;
    }

    public void setDetailcourseid(int detailcourseid) {
        this.detailcourseid = detailcourseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setMaxenrollment(int maxenrollment) {
        this.maxenrollment = maxenrollment;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDayofweek(String dayofweek) {
        this.dayofweek = dayofweek;
    }

    public void setStarttime(Time starttime) {
        this.starttime = starttime;
    }

    public void setEndtime(Time endtime) {
        this.endtime = endtime;
    }

    public void setStrstarttime(String strstarttime) {
        this.strstarttime = strstarttime;
    }

    public void setStrendtime(String strendtime) {
        this.strendtime = strendtime;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    @Override
    public String toString() {
        return "CourseAndDsp{" +
                "detailcourseid=" + detailcourseid +
                ", courseid=" + courseid +
                ", coursename='" + coursename + '\'' +
                ", credits='" + credits + '\'' +
                ", semester='" + semester + '\'' +
                ", maxenrollment=" + maxenrollment +
                ", description='" + description + '\'' +
                ", startdate=" + startdate +
                ", enddate=" + enddate +
                ", dayofweek='" + dayofweek + '\'' +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", strstarttime='" + strstarttime + '\'' +
                ", strendtime='" + strendtime + '\'' +
                '}';
    }
}
