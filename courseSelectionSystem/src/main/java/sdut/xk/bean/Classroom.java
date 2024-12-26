package sdut.xk.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classroom {
    private long classroomid;
    private String buildingname;
    private String roomnumber;
    private int capacity;
}