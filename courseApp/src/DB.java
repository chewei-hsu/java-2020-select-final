import org.apache.commons.io.FilenameUtils;

import java.sql.*;
import java.util.ArrayList;

public class DB {
    private static Connection connection = null;
    private static String url = FilenameUtils.separatorsToSystem( "jdbc:sqlite::resource:DB/course_data.db");
    private static ArrayList<CourseData> courseData =new ArrayList<CourseData>();

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
}
