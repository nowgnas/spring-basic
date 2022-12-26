package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean {


    private String url;

    public NetworkClient() {
        System.out.println("url 생성자 호출 ");

    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call : " + url + " message : " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("close url" + url);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // property setting이 끝나면: 의존관계 주입이 끝나면
        connect();
        call("초기화 연결 메세지");
    }

    @Override
    public void destroy() throws Exception {
        disconnect();
    }
}
