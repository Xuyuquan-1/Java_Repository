package sdut.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    int studentid;
    String studentname;
    String studentno;
    String studentpwd;
    String email;
    String major;
    String gender;
}
