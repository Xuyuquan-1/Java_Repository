package sdut.xksys.dao;


import org.springframework.stereotype.Repository;
import sdut.xksys.bean.Student;
import sdut.xksys.bean.Teacher;
import sdut.xksys.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TeacherDao {

    public int getTeacherCount()  {
        String sql = "select count(*) from teachers ";
        ResultSet rs = JdbcUtil.query(sql);
        try {
            rs.next();
            int count = rs.getInt(1);
            JdbcUtil.close(rs);
            return count;
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("TeacherDao.getTeacherCount-convertResultSetToList:error!");
        }finally {
            ;
        }
    }
    public List<Teacher> getTeachers()  {
        String sql = "select * from teachers ";
        ResultSet rs = JdbcUtil.query(sql);
        try {
            List<Teacher> list = JdbcUtil.convertResultSetToList(rs, Teacher.class);
            JdbcUtil.close(rs);
            return list;
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("TeacherDao.getTeachers-convertResultSetToList:error!");
        }finally {
            ;
        }
    }

    public List<Teacher> checkTeachers(Teacher teacher)  {
        String sql = "select * from teachers ";
        List<String> params = new ArrayList<>();
        if(teacher.getTeacherno()!=null)
        {
            sql = sql + " where teacherno like ?";
            params.add("%"+teacher.getTeacherno()+"%");
        }
        ResultSet rs = JdbcUtil.query(sql, params.toArray());
        try {
            List<Teacher> list = JdbcUtil.convertResultSetToList(rs, Teacher.class);
            return list;
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("TeacherDao.checkTeachers-convertResultSetToList:error!");
        }
        finally {

            try {
                JdbcUtil.close(rs);
            } catch (SQLException e){
                e.printStackTrace();
                throw new RuntimeException("TeacherDao.getTeachers-close:error!");
            }
        }

    }

    public int editUpdateTeacher(Teacher teacher){
        String sql = "update teachers set teachername = ?, email = ?, office = ?, phone = ? where teacherno = ?";
        int result = JdbcUtil.update(sql, teacher.getTeachername(),teacher.getEmail(),teacher.getOffice(),teacher.getPhone(),teacher.getTeacherno());
        return result;
    }

    public int adminAddTeacher(Teacher teacher){
        String sql = "insert into teachers (teachername, teacherno,  email, office, phone) values(?, ?, ?, ?, ?)";
        int result = JdbcUtil.update(sql, teacher.getTeachername(),teacher.getTeacherno(),teacher.getEmail(),teacher.getOffice(),teacher.getPhone());
        return result;
    }

    public int deleteTeacher(String teacherno){
        String sql = "delete from teachers where teacherno = ?";
        int result = JdbcUtil.update(sql, teacherno);
        return result;
    }



}
