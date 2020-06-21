import org.apache.commons.io.FilenameUtils;

import java.sql.*;
import java.util.ArrayList;


public class DB {
    private static Connection connection = null;
    private static String url = "jdbc:sqlite::resource:DB/course_data.db";
    private static ArrayList<CourseData> courseDataList =new ArrayList<CourseData>();

    /**
     * helping method for all DB method , establish the Connection between DB and program
     * @return
     */
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

    /**
     * helping method for all DB method , cancel the Statement and Connection between DB and program
     * @param stmt
     * @return
     */
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

    /**
     * Multiple conditions searching implement by generating the SQL command according to different input.
     * @param searchWord the keyword for search, if it is null, dump all course
     * @param semester the semester now searching at
     * @param type is the course 選修 必修 必帶
     * @return all course fit the conditions
     */
    public static ArrayList<CourseData> getCourse(String searchWord,String semester,int type){
        connectToDB();

        //generate command
        String sql = "SELECT * FROM \'" + semester+"\'";
        Statement stmt = null;
        boolean firstConstraint = true;
        if(searchWord != null){
            if(firstConstraint){
                firstConstraint = false;
                sql += " WHERE ";
            }
            else{
                sql += " AND ";
            }
            sql += "course_name LIKE \'%" + searchWord+"%\' ";
        }
        if(type != 0){
            if(firstConstraint){
                firstConstraint = false;
                sql += " WHERE ";
            }
            else{
                sql += " AND ";
            }
            sql += "isMust = \'" + (type-1) + "\'";
        }

        //create statement and call helping method to handle data
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

    /**
     * helping method of getCourse(), extract data and put in to the static Arraylist .
     * @param rs ResultSet given from database
     * @throws SQLException
     */
    private static void extractData(ResultSet rs) throws SQLException {
        courseDataList.clear();
        while (rs.next()){
            CourseData cdTemp = new CourseData(rs.getString("rand_num"),rs.getString("course_code"),rs.getString("course_name"),rs.getInt("credit"),rs.getInt("duration"),rs.getInt("isMust"),rs.getString("teacher"),rs.getInt("method"),rs.getString("time"),rs.getString("location"),rs.getInt("student_bound"),rs.getString("ps"));
            courseDataList.add(cdTemp);
        }
    }


}