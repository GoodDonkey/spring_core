package hi.core.beanFind;

import hi.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName); // 빈 이름으로 빈 객체를 가져온다.
            System.out.println("beanDefinitionName = " + beanDefinitionName + ", object = " + bean);
        }
    }
    @Test @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName); // 빈 이름으로 빈 정의를 가져온다.

            // ROLE_APPLICATION : 스프링 내부가 아니라 개발을 위해 직접 등록한 빈들을 의미한다.
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName); // 빈 이름으로 빈 객체를 가져온다.
                System.out.println("beanDefinitionName = " + beanDefinitionName + ", object = " + bean);
            }

            // ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
//            if (beanDefinition.getRole() == BeanDefinition.ROLE_INFRASTRUCTURE) {
//                Object bean = ac.getBean(beanDefinitionName); // 빈 이름으로 빈 객체를 가져온다.
//                System.out.println("beanDefinitionName = " + beanDefinitionName + ", object = " + bean);
//            }
        }
    }
}
