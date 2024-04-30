# CSS Bootstrap

## 주요 리팩토링
- Bootstrap 적용
- Oracle에서 PostgreSQL로 이전
- 상품 목록 페이지에 부분적으로 SPA (Single Page Application) 적용
- 이메일 인증 시 인증키를 Session에 저장하던 것을 Repository 객체에 저장하는 방식으로 변경

## 추가 기능
- 상품 목록 정렬
- Java Reflection을 이용한 동적 객체 바인딩
- 다중 파일 업로드

## 마주친 문제와 해결
- DTO의 데이터를 도메인 객체로 바인딩할 때 DTO와 도메인 객체의 각 필드에 대해 일일이 Getter와 Setter를 호출해야 했다. 이로 인해 bolierplate 코드가 너무 발생했다. 그래서 Java Reflection API를 이용해서 두 객체의 각각 이름이 같은 필드를 동적으로 바인딩하는 객체 (com.model2.mvc.common.util.BeanUtil)를 정의했다.