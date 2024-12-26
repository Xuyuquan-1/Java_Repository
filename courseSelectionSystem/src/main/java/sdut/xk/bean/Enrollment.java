package sdut.xk.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {
    private int enrollmentid;
    private int studentid;
    private long courseid;
    private String semester;
}