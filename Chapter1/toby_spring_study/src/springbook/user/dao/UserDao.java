package springbook.user.dao;

import java.sql.*;

import springbook.user.domain.User;
import javax.xml.transform.Result;

// 2021.05.28 21:53 사용자 정보를 DB에 넣고 관리할 수 있는 DAO 클래스
/*
* • DB 연결을 위한 Connection을 가져온다 .
* • SQL을 담은 Statement 또는 PreparedStatement)를 만든다.
* • 만들어진 Statement를 실행한다.
* • 조회의 경우 SQL 쿼리의 실행 결과를 ResultSet으로 받아서 정보를 저장할 오브젝트 여기서는 User 에 옮겨준다.
* • 작업 중에 생성된 Connection. Statement. ResultSet 같은 리소스는 작업을 마친 후 반드시닫아준다 .
* • JDBC API가 만들어내는 예외exception를 잡아서 직접 처리하거나， 메소드에 throws를 선언해서 예외가 발생하면 메소드 밖으로 던지게 한다.
* */
public class UserDao {
    /**
     * // 1-2. 구현 코드는 제거 후 추상 메소드로 변경. 구현은 서브클래스가 담당한다.
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection(server, user_name, password);
        return c;
    }
    * */

    // Connection오브젝트 종류가 달라지는것을 위한 추상화
    //UserDao는 어떤 기능을 사용하는것에만 관심, NUser,AUser에서 어떤식으로 Connection기능을 제공하는지 관심x
    // 1-2에 추가, 1-3에 주석
    //public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
    // 1-3에 추가, 1-4에 주석
    // private SimpleConnectionMaker simpleConnectionMaker;


    /** 1-3. 상태관리x, 한번만 만들어서 인스턴스 변수에 저장해두고, 메소드에서 사용하게 한다.
     * **/

//    public UserDao() {
//        //1-3에 추가, 1-4에 주석
//        // simpleConnectionMaker = new SimpleConnectionMaker();
//        connectionMaker = new NConnectionMaker(); //구체적 클래스를 알아야하는 문제 발생.
//    }

    // 1-4에 추가
    private ConnectionMaker connectionMaker;
    public UserDao(ConnectionMaker connectionMaker) { //ConnectionMaker 타입으로 받음
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        // 관심1) DB연결에 관련된 관심 : (수정 필) get()에서 중복된 코드발생,(추가 필) 예외상황에 대한 처리가 없다.
        //Class.forName("com.mysql.cj.jdbc.Driver");
        //Connection c = DriverManager.getConnection("jdbc:mysql://" + server, user_name, password);

        // 1-3. //Connection c = getConnection();
        // 1-4  //Connection c = simpleConnectionMaker.makeNewConnection();
        Connection c = connectionMaker.makeNewConnection();

        // 관심2) sql을 담은 Statement 생성, 어떤 sql을 사용할지 관심
        PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());
        ps.execute();   // statement 실행

        // 관심3) 리소스 닫기 관심
        ps.close(); // statement 닫기
        c.close();  // connection 닫기
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
//        Connection c = getConnection();
//        Connection c = simpleConnectionMaker.makeNewConnection();
        Connection c = connectionMaker.makeNewConnection();
        PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
        ps.setString(1 , id);

        ResultSet rs = ps.executeQuery();
        rs .next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs . close ();
        ps . close();
        c. close();
        return user;
    }



}
