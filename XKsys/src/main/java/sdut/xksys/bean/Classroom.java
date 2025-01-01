package sdut.xksys.bean;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Classroom {
    private int classroomid;
    private String classroomname;
    private int capacity;
    private int courseid;


    public int getClassroomid() {
        return classroomid;
    }

    public String getClassroomname() {
        return classroomname;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCourseid() {
        return courseid;
    }

    public void setClassroomid(int classroomid) {
        this.classroomid = classroomid;
    }

    public void setClassroomname(String classroomname) {
        this.classroomname = classroomname;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }
}
