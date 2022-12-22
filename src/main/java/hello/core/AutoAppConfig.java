package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// @Component 어노테이션이 붙은 클래스를 모두 스프링 빈으로 등록해 준다
@ComponentScan(
        // AppConfig를 제외하기 위한 옵션
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
