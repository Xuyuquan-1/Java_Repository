package sdut.xksys.bean;

import java.util.Date;

public class DetailCourse {
    int courseid;
    int detailcourseid;
    String coursename;
    String description;
    Date startdate;
    Date enddate;

    public DetailCourse() {
    }

    public DetailCourse(int courseid, int detailcourseid, String coursename, String description, Date startdate, Date enddate) {
        this.courseid = courseid;
        this.detailcourseid = detailcourseid;
        this.coursename = coursename;
        this.description = description;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public int getDetailcourseid() {
        return detailcourseid;
    }

    public void setDetailcourseid(int detailcourseid) {
        this.detailcourseid = detailcourseid;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
}