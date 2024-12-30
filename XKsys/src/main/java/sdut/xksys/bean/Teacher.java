package sdut.xksys.bean;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    int teacherid;
    String teachername;
    String teacherno;
    String email;
    String phone;
    String office;

    public int getTeacherid() {
        return teacherid;
    }

    public String getTeachername() {
        return teachername;
    }

    public String getTeacherno() {
        return teacherno;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getOffice() {
        return office;
    }

    public void setTeacherid(int teacherid) {
        this.teacherid = teacherid;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public void setTeacherno(String teacherno) {
        this.teacherno = teacherno;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOffice(String office) {
        this.office = office;
    }
}
