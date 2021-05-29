package springbook.user.dao;

public class DaoFactory {

    // 팩토리 메소드는 UserDao타입의 obj를 어떻게 만들고, 어떻게 준비시킬지 결정한다.
    public UserDao userDao(){
//        ConnectionMaker aConnectionMaker = new AConnectionMaker(); // UserDao가 사용할 ConnectionMaker구현 클래스 결정, obj생성
//        UserDao dao = new UserDao(aConnectionMaker); //사용할 connectionMaker타입의 obj제공. ->결국 두 obj사이의 의존관계 설정 효과.
        return new UserDao(connectionMaker());
    }

//    public AccountDao accountDao() {
//        return new AccountDao(connectionMaker());
//    }
//
//    public MessageDao messageDao() {
//        return new MessageDao(connectionMaker());
//    }

    // AccoutnDao, MessageDao등 생성하면, 중복코드가 발생하므로,
    // 분리해서 중복 제거한 ConnectionMasker 타입 obj생성 함
    public ConnectionMaker connectionMaker() {
        return new AConnectionMaker();
    }
}
