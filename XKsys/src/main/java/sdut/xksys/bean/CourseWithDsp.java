package sdut.xksys.bean;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CourseWithDsp {
    Course course;
    String dsp;

    public Course getCourse() {
        return course;
    }

    public String getDsp() {
        return dsp;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setDsp(String dsp) {
        this.dsp = dsp;
    }
}
