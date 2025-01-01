package sdut.xksys.bean;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CourseidWithTeacherid {
    private int courseid;
    private int teacherid;

    public CourseidWithTeacherid(int courseid, int teacherid) {
        this.courseid = courseid;
        this.teacherid = teacherid;
    }

    public CourseidWithTeacherid() {
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public void setTeacherid(int teacherid) {
        this.teacherid = teacherid;
    }

    public int getCourseid() {
        return courseid;
    }

    public int getTeacherid() {
        return teacherid;
    }
}
