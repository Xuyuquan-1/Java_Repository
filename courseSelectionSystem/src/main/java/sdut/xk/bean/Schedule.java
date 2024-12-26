package sdut.xk.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    private long scheduleid;
    private String dayofweek;
    private Time starttime;
    private Time endtime;
    private Long semesterid;
}