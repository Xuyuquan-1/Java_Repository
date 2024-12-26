package sdut.xk.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Semester {
    private long semesterid;
    private String semestername;
    private Date startdate;
    private Date enddate;
}