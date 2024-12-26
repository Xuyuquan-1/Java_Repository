package sdut.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    int courseid;
    String coursename;
    String credits;
    String semester;
    int maxenrollment;
}
