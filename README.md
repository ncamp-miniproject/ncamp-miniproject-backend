# MyBatis, Spring Framework
MyBatis와 Spring Framework를 적용했다. Spring MVC는 적용하지 않았다.


## 주요 리팩토링
- DAO에 MyBatis 적용
  - 기존 DAOTemplate deprecated
- Spring Framework 적용
  - 요청 URL에 대응하는 Action을 ApplicationContext에서 찾아서 비즈니스 로직 처리 (com.model2.mvc.framework.WiringRequestMapping)
- 화면에 보여지는 page 데이터를 객체화
  - Bitwise 연산을 통해 조건문 없이 page 데이터 추출
- DAO interface를 정의, Dependency Inversion Principle에 따른 프로그래밍

## 추가 기능
- 장바구니
  - 기존 시스템에서는 하나의 상품에 대해서 한 번의 구매 행위만 일어날 수 있었다. (상품과 구매 사이의 1대1 관계)
  - 상품 테이블에 stock attribute 추가
  - 한 번의 구매 행위에 대해 여러 개의 상품을 구매할 수 있도록 transaction_prod entity 추가 (transaction 테이블과 transaction_prod 테이블은 1:N 관계)

## 마주쳤던 문제
- 상품 목록에 페이지가 있다. 기존 시스템에서는 페이지를 JSP에서 계산했기에 MVC 아키텍처를 위반한 상태였다. JSP 코드에 있는 페이지 계산을 Business Logic Layer의 Service Layer로 옮겼다. 이렇게 하고 보니 Service 객체가 맡은 역할이 너무 커졌다 (코드 길이도 길어졌다). 그래서 정수 계산을 담당하는 객체(com.model2.mvc.common.util.IntBitwiseUtil), 페이지 연산을 담당하는 객체(com.model2.mvc.common.util.ListPageUtil)를 새로 만들어 역할을 분리했다.