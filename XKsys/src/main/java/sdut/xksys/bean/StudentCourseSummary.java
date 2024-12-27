package sdut.xksys.bean;

// 辅助类：用于存储学生的选课总数和总学分
public class StudentCourseSummary {
    private int courseCount;
    private double totalCredits;

    // Getters and Setters
    public int getCourseCount() {
        return courseCount;
    }

    public void setCourseCount(int courseCount) {
        this.courseCount = courseCount;
    }

    public double getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(double totalCredits) {
        this.totalCredits = totalCredits;
    }
}