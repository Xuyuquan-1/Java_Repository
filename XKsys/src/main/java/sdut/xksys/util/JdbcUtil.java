package sdut.xksys.util;

import com.alibaba.druid.pool.DruidDataSource;
import sdut.xksys.bean.Course;
import sdut.xksys.bean.CourseAndDsp;
import sdut.xksys.bean.CourseWithDsp;
import sdut.xksys.bean.DetailCourse;

import java.lang.reflect.Field;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JdbcUtil {

    // Druid 数据源单例  用来管理数据库链接对象
    private static DruidDataSource dataSource;

    static {
        // 初始化数据源
        dataSource = new DruidDataSource();
        // 配置数据库连接属性
        dataSource.setUrl("jdbc:mysql://localhost:3306/courseSelectionSystem?useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        // 配置连接池属性
        dataSource.setInitialSize(30);
        dataSource.setMinIdle(30);
        dataSource.setMaxActive(150);
        dataSource.setMaxWait(60000);


        // 配置监控统计拦截的filters，stat:监控统计，log4j:日志记录，wall:防御sql注入
        //dataSource.setFilters("stat,wall,log4j");

        // 配置其他属性，如连接超时时间等
//        Properties properties = new Properties();
//        properties.setProperty("validationQuery", "SELECT 1");
//        properties.setProperty("testWhileIdle", "true");
//        properties.setProperty("testOnBorrow", "true");
//        properties.setProperty("testOnReturn", "true");

//        dataSource.setConnectionProperties(properties);

//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setRemoveAbandoned(true);
//        dataSource.setRemoveAbandonedTimeout(1800);
//        dataSource.setLogAbandoned(true);
    }

    // 获取数据库连接
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static ResultSet query(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    stmt.setObject(i + 1, params[i]);
                }
            }
            rs = stmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error executing query", e);
        } finally {

//            close(conn, stmt, rs);
        }
    }

    public static int update(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    stmt.setObject(i + 1, params[i]);
                }
            }
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error executing update", e);
        } finally {
            close(conn, stmt, null);
        }
    }

    /**
     * 将 ResultSet 转换成指定类型的对象列表。
     *
     * @param <T>       对象的类型
     * @param resultSet 数据库查询结果集
     * @param clazz     对象的类
     * @return 转换后的对象列表
     * @throws SQLException 如果数据库访问失败
     * @throws IllegalAccessException 如果无法访问类的属性
     * @throws InstantiationException 如果无法实例化类
     */
    public static <T> List<T> convertResultSetToList(ResultSet resultSet, Class<T> clazz) throws SQLException, IllegalAccessException, InstantiationException {
        List<T> list = new ArrayList<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (resultSet.next()) {
            T object = clazz.newInstance();

            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i).toLowerCase(); // 假设列名不区分大小写
                Object columnValue = resultSet.getObject(i);

                // 通过反射设置对象的属性
                try {
                    Field field = clazz.getDeclaredField(convertToCamelCase(columnName)); // 转换为驼峰命名法（如果需要）
                    field.setAccessible(true); // 允许访问私有字段（仅用于演示，实际项目中应谨慎使用）

                    // 转换字段值到目标类型
                    Object convertedValue = convertValue(columnValue, field.getType());

                    // 设置字段值
                    field.set(object, convertedValue);

                } catch (NoSuchFieldException e) {
                    // 如果对象中没有与数据库列名匹配的字段（转换为驼峰命名后），则记录日志并忽略该列
                    System.err.println("No such field: " + columnName + " in class " + clazz.getName());
                } catch (ClassCastException | IllegalArgumentException e) {
                    // 类型转换失败或参数非法时，记录日志并忽略该字段
                    System.err.println("Failed to set field value: " + columnName + " in class " + clazz.getName() + ". Error: " + e.getMessage());
                }
            }

            list.add(object);
        }

        return list;
    }

    /**
     * 将下划线分隔的字符串转换为驼峰命名法。
     *
     * @param s 下划线分隔的字符串
     * @return 驼峰命名法的字符串
     */
    private static String convertToCamelCase(String s) {
        StringBuilder result = new StringBuilder();
        boolean nextIsUpper = false;

        for (char c : s.toCharArray()) {
            if (c == '_') {
                nextIsUpper = true;
            } else {
                if (nextIsUpper) {
                    result.append(Character.toUpperCase(c));
                    nextIsUpper = false;
                } else {
                    result.append(Character.toLowerCase(c));
                }
            }
        }

        return result.toString();
    }
    //课程与课程描述的绑定
    public static CourseWithDsp getCourseWithDspList(DetailCourse detail, Course course) throws SQLException, IllegalAccessException, InstantiationException {
        CourseWithDsp cwd = new CourseWithDsp(course, detail);
        return cwd;
    }


    //CourseWithDSP 和 CourseAndDSP的相互转换
    public static CourseAndDsp CwpConvertCAp(CourseWithDsp cwp){
        CourseAndDsp cad = new CourseAndDsp();
        cad.setCourseid(cwp.getCourse().getCourseid());
        cad.setCoursename(cwp.getCourse().getCoursename());
        cad.setCredits(cwp.getCourse().getCredits());
        cad.setSemester(cwp.getCourse().getSemester());
        cad.setMaxenrollment(cwp.getCourse().getMaxenrollment());
        cad.setDescription(cwp.getDetailcourse().getDescription());
        cad.setStartdate(cwp.getDetailcourse().getStartdate());
        cad.setEnddate(cwp.getDetailcourse().getEnddate());
        cad.setDayofweek(cwp.getDetailcourse().getDayofweek());
        cad.setStarttime(cwp.getDetailcourse().getStarttime());
        cad.setEndtime(cwp.getDetailcourse().getEndtime());
        cad.setDetailcourseid(cwp.getDetailcourse().getDetailcourseid());
        cad.setClassroom(cwp.getDetailcourse().getClassroom());
        cad.setTeacherid(cwp.getDetailcourse().getTeacherid());
        cad.setOldclassroom(cwp.getDetailcourse().getOldclassroom());
        cad.setOldteacherid(cwp.getDetailcourse().getOldteacherid());
        return cad;
    }

    public static CourseWithDsp CAPConvertCWP(CourseAndDsp cap){
        DetailCourse detail = new DetailCourse(cap.getCourseid(), cap.getDetailcourseid(), cap.getCoursename(), cap.getOldclassroom(),cap.getOldteacherid(), cap.getClassroom(), cap.getTeacherid(), cap.getDescription(), cap.getStartdate(), cap.getEnddate(), cap.getDayofweek(), cap.getStarttime(), cap.getEndtime());
        Course course = new Course();
        course.setCourseid(cap.getCourseid());
        course.setCoursename(cap.getCoursename());
        course.setCredits(cap.getCredits());
        course.setSemester(cap.getSemester());
        course.setMaxenrollment(cap.getMaxenrollment());
        return new CourseWithDsp(course, detail);
    }

    public static String DateTimeToString(Object obj) {
        if (obj instanceof java.util.Date) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format((java.util.Date) obj);
        } else if (obj instanceof java.sql.Time) {
            SimpleDateFormat sdftime = new SimpleDateFormat("HH:mm:ss");
            return sdftime.format((java.sql.Time) obj);
        } else {
            throw new IllegalArgumentException("Unsupported object type: " + obj.getClass().getName());
        }
    }

    public static Time stringToTime(String timeString) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        try {
            LocalTime localTime = LocalTime.parse(timeString, timeFormatter);
            return Time.valueOf(localTime);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // 或者抛出自定义异常
        }
    }

    /**
     * 将对象值转换为指定类型。
     *
     * @param value   要转换的值
     * @param targetType 目标类型
     * @return 转换后的值，如果转换失败则返回null
     */
    private static Object convertValue(Object value, Class<?> targetType) {
        if (value == null) {
            return null; // 空值直接返回
        }

        if (targetType.isAssignableFrom(value.getClass())) {
            return value; // 如果类型已经匹配，直接返回
        }

        if (targetType == String.class) {
            return value.toString(); // 转换为String
        } else if (targetType == Integer.class || targetType == int.class) {
            try {
                return Integer.parseInt(value.toString());
            } catch (NumberFormatException e) {
                return null; // 转换失败返回null
            }
        } else if (targetType == Long.class || targetType == long.class) {
            try {
                return Long.parseLong(value.toString());
            } catch (NumberFormatException e) {
                return null; // 转换失败返回null
            }
        } else if (targetType == Double.class || targetType == double.class) {
            try {
                return Double.parseDouble(value.toString());
            } catch (NumberFormatException e) {
                return null; // 转换失败返回null
            }
        } else if (targetType == Boolean.class || targetType == boolean.class) {
            // 这里简单地将"true"（不区分大小写）转换为true，其他情况转换为false
            return Boolean.parseBoolean(value.toString().toLowerCase());
        } else if (targetType == Date.class) {
            // 这里需要额外的逻辑来将String或其他类型转换为Date
            // 例如，使用SimpleDateFormat来解析日期字符串
            // 为了简化，这里不实现Date的转换
            return null; // 转换失败返回null
        }

        // 对于其他类型，这里简单地返回null（实际项目中可能需要添加更多的类型转换逻辑）
        return null;
    }

    //字符串解析日期
    public static java.util.Date stringConvertToDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    //字符串解析时间
    public static Time stringConvertToTime(String timeString) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        try {
            return new Time(timeFormat.parse(timeString).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 关闭连接（注意：这里只是归还连接池，并非真正关闭连接）
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 关闭资源（连接、语句、结果集）
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(conn);
    }

    // 关闭资源（连接、语句、结果集）
    public static void close( ResultSet rs) throws SQLException {
        Statement stmt=rs.getStatement();
        Connection conn=stmt.getConnection();

        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(conn);
    }
}