package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/* 1-2 */
//public class AUserDao  extends UserDao {
//    public Connection getConnection() throws ClassNotFoundException, SQLException {
//        // A사 DB Connection 생성 코드
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection c = DriverManager.getConnection(
//                "jdbc:mysql://localhost:3305/study2021",
//                "root",
//                "950907");
//        return c;
//    }
//}