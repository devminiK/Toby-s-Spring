package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/* 1-3 */
// 상속을 이용한 확장 방식 사용필요x ->추상 클래스로 만들 필요x
public class SimpleConnectionMaker {
    public Connection makeNewConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3305/study2021",
                "root",
                "950907");
        return c;
    }
}
