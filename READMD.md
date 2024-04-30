# Controller
Spring MVC를 도입했다.

## 주요 리팩토링
- Spring Controller 적용
- 도메인 객체에서 java.sql.Date 객체를 사용하던 것을 java.time.LocalDate로 변경
- DAO라는 이름을 사용하던 Persistence Layer의 객체들을 Repository로 변경
- JSP 파일들을 view 디렉토리 안에 모아 놓음
- ProductService에 Strategy Pattern 적용

## 추가 기능
- 상품 카테고리

## 마주친 문제점과 해결
- DTO에 enum 타입 등 Spring MVC에서 바인딩하지 못하는 객체를 연관(Association)시켰다. 이 때문에 핸들러를 매핑하는 과정에서 문제가 발생했다. 이를 해결하기 위해 컨트롤러에 InitBinder를 추가하여 커스텀 타입을 매핑시켰다.
- 각 모듈에 사용되는 JSP 파일이 Context Root에 위치했다. 그러다 보니 JSP 파일들을 관리하기 번거로웠다. 그래서 JSP 파일들을 view 디렉토리 안에 모아 놓았다.
- 클라이언트는 상품명, 가격 범위 등 검색 조건에 따라 상품을 검색할 수 있다. 각 검색 조건에 따라 다른 비즈니스 로직을 수행해야 했다. 검색 조건에 따라 코드를 분기하다 보니 ProductServiceImpl의 코드가 매우 길어졌다. 이에 검색 조건을 key로 하고 비즈니스 로직 코드가 정의된 Functional Interface 구현 객체를 value로 하는 Map을 정의하여 Strategy Pattern을 적용했다. 이 덕분에 더 유연한 설계를 할 수 있었으나 ProductServiceImpl의 길이가 길어지는 문제는 해결하지 못했다. 그래서 상품 목록 query를 담당하는 ListQueryHelper 객체를 적용하여 이 부분을 분리했다.