# JSP EL/JSTL
JSP에 EL, JSTL을 적용하였다.

## 주요 리팩토링
- JSP EL, JSTL을 적용함으로써 JSP를 View로서의 기능에 집중시켰다.
- 도메인 객체에 Builder 패턴 적용
- 요청 URL과 Action 객체 매핑 정보를 메타데이터화

## 마주친 문제점과 그 해결
- Plain JDBC 코드를 DAO에서 그대로 사용한 만큼 DAO 코드가 난잡하고 중복 코드가 많았다. 이를 해결하기 위해 DAOTemplate이라는 abstract class를 선언하고 DAO 클래스들이 이를 상속받음으로써 코드 중복을 해소하기 위해 노력했다.
- SQL이 Java 코드에 그대로 드러나는 문제가 있었다. 그래서 SQL문을 webapp 폴더 안에 따로 빼 놓고 이를 읽어들여서 Properties 객체 안에 담아 놓으려 했다. 이것을 관장하는 게 com.model2.mvc.common.db.SQLContainer 객체이다.
- Builder 패턴을 적용할 때 각 Builder 단계마다 새로운 Builder 객체를 생성함으로써 Builder 객체의 불변성을 보장하려 했다. 이것을 Object.clone() 메소드로 실현하려 했는데 너무 코드 중복이 컸다. 그래서 BuilderTemplate 객체를 정의하여 Builder 패턴 적용한 객체를 BuilerTemplate을 상속시키고 중복된 코드를 Super Class에서 공유하려고 했다.
- 요청 URL에 대해 비즈니스 로직을 처리하는 Action 객체를 매핑하는 코드가 Java 코드에 하드코딩되어 있었다. 이를 메타데이터화해서 webapp 디렉토리의 META-INF/config 디렉토리 안에 빼 놓았다.