package hi.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComponentFilterAppConfigTest {

    @Test
    @DisplayName("필터 어노테이션이 컴포넌트 스캔에 포함되는지 확인")
    void filterScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        // beanA 조회
        BeanA beanA = ac.getBean("beanA", BeanA.class);

        assertThat(beanA).isNotNull();

        // beanB 조회: 제외했으므로 실패
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("beanB", BeanB.class));
    }

    @Configuration
    @ComponentScan(includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
                   excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class))
    static class ComponentFilterAppConfig {
    }
}
