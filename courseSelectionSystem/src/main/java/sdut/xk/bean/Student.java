package sdut.xk.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private int studentid;
    private String studentname;
    private String studentno;
    private String studentpwd;
    private String email;
    private String major;
    private String grade;
}