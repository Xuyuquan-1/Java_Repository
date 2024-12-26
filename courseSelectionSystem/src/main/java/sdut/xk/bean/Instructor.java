package sdut.xk.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Instructor {
    private long instructorid;
    private String name;
    private String department;
    private String email;
    private String phonenumber;
    private Date hiredate;
}