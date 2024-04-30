# Spring Boot

## 주요 리팩토링
- Maven 프로젝트 적용
- Spring Boot 적용
- XML 기반 스프링 configuration을 어노테이션 기반 configuration으로 변경
- XML 기반 서블릿 웹 애플리케이션 구성을 Java 클래스 기반 구성으로 변경 (com.model2.mvc.config.web.WebAppInit)
  - 스프링 부트를 적용시킨 후 WebAppInit 클래스를 제거하고 SpringBootServletInitializer를 적용했다.
- JUnit4에서 JUnit Jupiter (JUnit5)로 이전