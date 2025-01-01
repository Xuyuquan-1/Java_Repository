package sdut.xksys.bean;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public class CourseWithDsp {
    Course course;
    DetailCourse detailcourse;

    public Course getCourse() {
        return course;
    }

    public DetailCourse getDetailcourse() {
        return detailcourse;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setDetailcourse(DetailCourse detailcourse) {
        this.detailcourse = detailcourse;
    }
    public CourseWithDsp() {
        ;
    }

    public CourseWithDsp(Course course, DetailCourse detailcourse) {
        this.course = course;
        this.detailcourse = detailcourse;
    }

    @Override
    public String toString() {
        return "CourseWithDsp{" +
                "course=" + course +
                ", detailcourse=" + detailcourse +
                '}';
    }
}
