package sdut.xksys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sdut.xksys.bean.*;
import sdut.xksys.dao.CourseDao;
import sdut.xksys.dao.DetailCourseDao;
import sdut.xksys.util.JdbcUtil;
import sdut.xksys.util.RestResult;

import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseDao courseDao;
    @Autowired
    DetailCourseDao detailCourseDao;

    @RequestMapping("/list")
    public Object getAllCourses() {
        try {
            List list = courseDao.getAllCourses();
            int count = courseDao.getCourseCount();
            return new RestResult(count,list);

        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("/newlist")
    public Object checkStudent(Course course) {
        try {
            System.out.println(course.getCoursename());
            List<Course> list = courseDao.getCheckCourse(course);
            int count =  courseDao.getCourseCount();
            RestResult result = new RestResult(count,list);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Controller.getAllstudent:error");
        }
        finally {
            ;
        }
    }

    @RequestMapping("/coursewithdsplist")
    //初始化时course == null
    //空查询时course == ""
    //有效查询时course == "课程名"
    public Object getCourseWithDspList(String coursename) {
        try{
            //获取所有课程
            if(coursename==null || coursename==""){
                List<Course> courselist = courseDao.getAllCourses();
                int coursecount = courseDao.getCourseCount();
                List<CourseAndDsp> courseAndDspList = new ArrayList<>();
                for (Course ce : courselist) {
//                    System.out.println("*******"+ce.getCourseid()+"********");

                    DetailCourse detail = detailCourseDao.getDetailCourseByCourseid(ce.getCourseid());
                    CourseWithDsp courseWithDsp = new CourseWithDsp(ce, detail);
                    courseAndDspList.add(JdbcUtil.CwpConvertCAp(courseWithDsp));


                }
                return new RestResult(coursecount, courseAndDspList);
            }else if(coursename!=""){
                System.out.println("///////////////");
                System.out.println(coursename);
                System.out.println("///////////////");
                //获取查询课程
                Course course = courseDao.getCourseByName(coursename);
                System.out.println(course);
                //通过Coureid获取DetailCourse
                DetailCourse detailCourse = detailCourseDao.getDetailCourseByCourseid(course.getCourseid());
                System.out.println(detailCourse);
                //获取CourseWithDsp
                CourseWithDsp courseWithDsp = new CourseWithDsp(course, detailCourse);
                //获取CouresAndDsp
                CourseAndDsp courseAndDsp = JdbcUtil.CwpConvertCAp(courseWithDsp);
                List<CourseAndDsp> courseAndDspList = new ArrayList<>();
                courseAndDspList.add(courseAndDsp);
                //return
                return new RestResult(1, courseAndDspList);

            }else{
                return "error";
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("/coursewithdsplist:error");

        }
    }



    @RequestMapping("/getcoursebyid")
    public Object getCourseById(int courseid) {
        try {
            return courseDao.getCourseById(courseid);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
    @RequestMapping("/add")
    public Object addCourse(Course course) {
        try {
            return courseDao.addCourse(course);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    //用于 添加课程同时添加课程细节信息
    @RequestMapping("/addwithdetail")
    public Object newaddCourse(@RequestBody CourseWithDsp courseWithDsp) {
        try {
            System.out.println("-----------------");
            System.out.println(courseWithDsp);
            System.out.println("-----------------");
            courseDao.addCourse(courseWithDsp.getCourse());
            DetailCourse detailCourse = courseWithDsp.getDetailcourse();

            java.util.Date startdate = JdbcUtil.stringConvertToDate(detailCourse.getStrstartdate());
            java.util.Date enddate = JdbcUtil.stringConvertToDate(detailCourse.getStrenddate());

            //新增不需要预填信息，所以所有的数据都还是HH：mm的字符串，可以放心转换
            Time starttime = JdbcUtil.stringConvertToTime(detailCourse.getStrstarttime()+":00");
            Time endtime = JdbcUtil.stringConvertToTime(detailCourse.getStrendtime()+":00");


            detailCourse.setStartdate(startdate);
            detailCourse.setEnddate(enddate);
            detailCourse.setStarttime(starttime);
            detailCourse.setEndtime(endtime);

            System.out.println("-----------------");
            System.out.println(courseWithDsp);
            System.out.println("-----------------");

            Course tempCourse = courseDao.getCourseByName(courseWithDsp.getCourse().getCoursename());
            System.out.println("______________");
            System.out.println(tempCourse);
            System.out.println("______________");

            System.out.println(tempCourse.getCourseid());
            detailCourse.setCourseid(tempCourse.getCourseid());

            System.out.println("______________");
            System.out.println(detailCourse);
            System.out.println("______________");

            detailCourseDao.addDetailCourse(detailCourse);
            List<String> list = new ArrayList<>();
            list.add("添加成功");
            return new RestResult(1, list);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("/course/addwithdetail:error!");
        }
    }

    @RequestMapping("/edit")
    public Object updateCourse(Course course) {
        try {
            return courseDao.updateCourse(course);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("/newedit")
    public Object updateCourseAndDetail(CourseAndDsp cad) {


        cad.setStarttime(JdbcUtil.stringToTime(cad.getStrstarttime()));
        cad.setEndtime(JdbcUtil.stringToTime(cad.getStrendtime()));
//        System.out.println("_________");
//        System.out.println(cad);
//        System.out.println("_________");
        try {
            CourseWithDsp courseWithDsp = JdbcUtil.CAPConvertCWP(cad);
            System.out.println(courseWithDsp);
//            System.out.println("----------------");
            courseDao.updateCourse(courseWithDsp.getCourse());
            detailCourseDao.updateDetailCourse(courseWithDsp.getDetailcourse());
            List<String> list = new ArrayList<>();
            list.add("修改成功");
            return new RestResult(1, list);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("/course/newedit:error!");
        }
    }



    @RequestMapping("/delete")
    public Object deleteCourse(int courseid) {
        try {
            return courseDao.deleteCourse(courseid);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
