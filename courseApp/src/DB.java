import org.apache.commons.io.FilenameUtils;

import java.sql.*;
import java.util.ArrayList;

public class DB {
    private static Connection connection = null;
    private static String url = FilenameUtils.separatorsToSystem( "jdbc:sqlite::resource:DB/course_data.db");
    private static ArrayList<CourseData> courseDataList =new ArrayList<CourseData>();

    public static boolean connectToDB() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            Debugger.showDebugMessage("[EXCEPTION] Database - "+e.getMessage());
        }
        try {
            connection = DriverManager.getConnection(url);
            Debugger.showDebugMessage("[Success] Database - Connection to SQLite has been established.");
        } catch (SQLException e) {
            Debugger.showDebugMessage("[EXCEPTION] Database - "+e.getMessage());
        }
        if (connection == null) {
            return false;
        }
        return true;
    }

    public static boolean closeConnection(Statement stmt){
        boolean flag = false;
        try {
            if(stmt != null){
                stmt.close();
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;
        }
        try {
            if(connection != null){
                connection.close();
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public static ArrayList<CourseData> getCourse(String searchWord,String semester){
        connectToDB();
        String sql = "SELECT * FROM \'" + semester+"\'";
        Statement stmt = null;
        if(searchWord != null){
            sql += "WHERE course_name LIKE \'%" + searchWord+"%\'";
        }
        try{
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            extractData(rs);
        }catch (SQLException e){
            Debugger.showDebugMessage("[EXCEPTION] Database - "+e.getMessage());
        }finally {
            if(!closeConnection(stmt)){
                return null;
            }
        }
        return courseDataList;
    }

    private static void extractData(ResultSet rs) throws SQLException {
        courseDataList.clear();
        while (rs.next()){
            CourseData cdTemp = new CourseData(rs.getString("rand_num"),rs.getString("course_code"),rs.getString("course_name"),rs.getInt("credit"),rs.getInt("duration"),rs.getInt("isMust"),rs.getString("teacher"),rs.getInt("method"),rs.getString("time"),rs.getString("location"),rs.getInt("student_bound"),rs.getString("ps"));
        }
    }


}