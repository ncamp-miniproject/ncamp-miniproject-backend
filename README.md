# URI Pattern
*.do로 끝나는 동사형 URL을 RESTful 원칙을 최대한 지킨 명사형 URL로 변경했다.

## 주요 리팩토링
- (불완전한) RESTful API
- Lombok 적용

## 추가 기능
- 파일 업로드
- 메일 전송

## 마주친 문제와 해결
- DispatcherServlet의 url 매핑을 ```/*```으로 하니까 JSP에 대한 요청까지 모두 오버라이딩해 버리는 문제가 발생했다. 처음에는 JSP에 대한 요청이 무시되는 원인에 대해서 헤맸으나, 검색을 하면서 기본적으로 ```*.jsp```에 대한 요청을 JSP에서 변환된 서블릿으로 dispatch하는 서블릿이 있다는 걸 알게 되었다. 그래서 DispatcherServlet의 url 매핑을 ```/```으로 변경했다.