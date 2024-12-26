package sdut.xk.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private long courseid;
    private String coursename;
    private float credits;
    private String semester;
    private Integer maxenrollment;
    private Long instructorid;
    private Long classroomid;
    private Long scheduleid;
}