package hi.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    @DisplayName("프로토 타입 빈 찾기")
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        // 1
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();

        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        // 2
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();

        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("싱글톤이 prototype bean을 사용하는 경우")
    void singletonClientUsesPrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class,
                                                                                       PrototypeBean.class);
        ClientBean client1 = ac.getBean(ClientBean.class);
        int count1 = client1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean client2 = ac.getBean(ClientBean.class);
        int count2 = client1.logic();
        assertThat(count2).isEqualTo(2);

    }

    @Scope("singleton")
    static class ClientBean {
        private final PrototypeBean prototypeBean;

        @Autowired // 의존관계를 주입받는다.
        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public int logic() {
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
