package sdut.xksys.bean;

public class ClassroomEnum {
    private static final  String classroom1 = "教室1";
    private static final  String classroom2 = "教室2";
    private static final  String classroom3 = "教室3";
    private static final  String classroom4 = "教室4";
    private static final  String classroom5 = "教室5";
    private static final  String classroom6 = "教室6";
    private static final  String classroom7 = "教室7";
    private static final  String classroom8 = "教室8";


    public String getClassroom1() {
        return classroom1;
    }

    public String getClassroom2() {
        return classroom2;
    }

    public String getClassroom3() {
        return classroom3;
    }

    public String getClassroom4() {
        return classroom4;
    }

    public String getClassroom5() {
        return classroom5;
    }

    public String getClassroom6() {
        return classroom6;
    }

    public String getClassroom7() {
        return classroom7;
    }

    public String getClassroom8() {
        return classroom8;
    }


    public static int getCapcity(String classroom) {
        if (classroom.equals(classroom1)) {
            return 10;
        }
        else if (classroom.equals(classroom2)) {
            return 20;
        }
        else if (classroom.equals(classroom3)) {
            return 30;
        }
        else if (classroom.equals(classroom4)) {
            return 40;
        }
        else if (classroom.equals(classroom5)) {
            return 50;
        }
        else if (classroom.equals(classroom6)) {
            return 60;
        }
        else if (classroom.equals(classroom7)) {
            return 70;
        }
        else if(classroom.equals(classroom8)) {
            return 80;
        }
        else{
            return 0;
        }
    }
}
