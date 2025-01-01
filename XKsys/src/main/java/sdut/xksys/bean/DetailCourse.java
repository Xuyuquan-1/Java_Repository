package sdut.xksys.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailCourse {
    int courseid;
    int detailcourseid;
    String coursename;
    String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date startdate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date enddate;
    String dayofweek;
    @DateTimeFormat(pattern = "HH:mm")
    Time starttime;
    @DateTimeFormat(pattern = "HH:mm")
    Time endtime;

    String strstartdate;
    String strenddate;
    String strstarttime;
    String strendtime;


    public DetailCourse() {
    }


    public DetailCourse(int courseid, int detailcourseid, String coursename, String description, Date startdate, Date enddate, String dayofweek, Time starttime, Time endtime) {
        this.courseid = courseid;
        this.detailcourseid = detailcourseid;
        this.coursename = coursename;
        this.description = description;
        this.startdate = startdate;
        this.enddate = enddate;
        this.dayofweek = dayofweek;
        this.starttime = starttime;
        this.endtime = endtime;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.strstartdate = sdf.format(startdate);
        this.strenddate = sdf.format(enddate);

        SimpleDateFormat sdft = new SimpleDateFormat("HH:mm");
        this.strstarttime = sdft.format(starttime);
        this.strendtime = sdft.format(endtime);


    }

    public int getCourseid() {
        return courseid;
    }

    public int getDetailcourseid() {
        return detailcourseid;
    }

    public String getCoursename() {
        return coursename;
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

    public String getStrenddate() {
        return strenddate;
    }

    public String getStrstartdate() {
        return strstartdate;
    }

    public String getStrstarttime() {
        return strstarttime;
    }

    public String getStrendtime() {
        return strendtime;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public void setDetailcourseid(int detailcourseid) {
        this.detailcourseid = detailcourseid;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
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

    public void setStrstartdate(String strstartdate) {
        this.strstartdate = strstartdate;
    }

    public void setStrenddate(String strenddate) {
        this.strenddate = strenddate;
    }

    public void setStrstarttime(String strstarttime) {
        this.strstarttime = strstarttime;
    }

    public void setStrendtime(String strendtime) {
        this.strendtime = strendtime;
    }

    @Override
    public String toString() {
        return "DetailCourse{" +
                "courseid=" + courseid +
                ", detailcourseid=" + detailcourseid +
                ", coursename='" + coursename + '\'' +
                ", description='" + description + '\'' +
                ", startdate=" + startdate +
                ", enddate=" + enddate +
                ", dayofweek='" + dayofweek + '\'' +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", strstartdate='" + strstartdate + '\'' +
                ", strenddate='" + strenddate + '\'' +
                ", strstarttime='" + strstarttime + '\'' +
                ", strendtime='" + strendtime + '\'' +
                '}';
    }
}