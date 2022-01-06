package hi.core.singleton;

public class SingletonService {

    // 단 하나의 객체를 필드에 생성한다.
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    // 생성자를 private 으로 선언해서 외부에서 객체를 생성하지 못하도록 막는다.
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
