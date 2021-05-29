package springbook.user.domain;

import springbook.user.dao.*;

import java.sql.Connection;
import java.sql.SQLException;
/** UserDao와 ConnectionMaker 구현 클래스와의 runtime 오브젝트 의존관계를 설정하는 책임을 담당해야한다. **/
public class UserDaoTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //        UserDao aDao = new AUserDao();
        //        UserDao bDao = new BUserDao();

        // test로써 역할만 집중하기위해, 팩토리로부터 UserDao obj를 받아다가 쓰기만 하도록 수정한다.
//        ConnectionMaker aConnectionMaker = new AConnectionMaker(); // UserDao가 사용할 ConnectionMaker구현 클래스 결정, obj생성
//        UserDao dao = new UserDao(aConnectionMaker); //사용할 connectionMaker타입의 obj제공. ->결국 두 obj사이의 의존관계 설정 효과.
        UserDao dao = new DaoFactory().userDao();
        User user1 = dao.get("hun");
        System.out.println(user1.getName());

    }
}
