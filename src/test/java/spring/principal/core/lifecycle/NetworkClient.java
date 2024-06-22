package spring.principal.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);

    }

    public void setUrl(String url){
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("close " + url);
    }

    /**
     * 인터페이스 구현을 통한 빈 라이프 사이클 테스트
     *
     * 단점:
     * *스프링 전용 인터페이스다. => 스프링을 벗어나면 사용 불가능
     * *초기화, 소멸 메서드 이름이 고정되어 있다.(수정 불가)
     * *외부 라이브러리 객체에는 적용할 수 없다.
     *
     * *스프링 초창기 방법으로 현재는 거의 사용하지 않는다.
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메세지");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
