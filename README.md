# RestController
RestController를 적용함으로써 ViewResolver가 아닌 HttpMessageConverter를 사용하기 시작했다.

## 주요 리팩토링
- RestController 정의
- 기존 Controller에서 Service Layer에 대한 직접적인 호출 제거, Controller 객체 내에서 RestController로 요청을 보냄으로써 비즈니스 로직 처리가 RestController를 경유하여 이루어지도록 하였다.
- Apache Commons Fileupload를 사용하던 파일 업로드 처리를 Spring에서 제공하는 MultiPartFile로 대체하여 multipart form 데이터 처리

## 추가 기능
- 회원 가입 시 이메일 인증

## 마주친 문제와 해결
- 같은 도메인 로직 처리에 대해 View를 반환하는 Controller가 있고 JSON을 반환하는 RestController가 있다. 상품 목록 데이터에 대한 요청을 처리하는 부분이 product 모듈의 Controller에도 있고 RestController에도 있다. Controller와 RestController 둘 다 상품 목록 데이터에 대해 같은 Service의 같은 메소드를 호출한다. 이 점은 적절하지 않은 설계라고 생각하여 ProductController를 ProductProxyController로 명명하고 ProductProxyController로 클라이언트 요청이 들어오면 이 안에서 Service Layer의 메소드를 호출하는 대신 ProductApi (RestController)로 새로 요청을 보내는 방식으로 변경하였다.