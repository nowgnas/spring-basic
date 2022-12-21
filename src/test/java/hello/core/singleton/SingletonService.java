package hello.core.singleton;

public class SingletonService {
    // static에 하나만 올라가게 됨
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance; // 자기 자신 객체 인스턴스 생성해서 반환
    }

    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱클톤 호출");
    }
}
