1. Servlet URL 매핑
2. HttpSerlvet 라이프사이클
3. 서블릿 관련 객체, 주요 메소드
```
 - Servlet(interface)
 - GenericServlet
 - HttpServlet
```
    
```
- HttpServletRequest/Response
- HttpSession
- ServletContext - application
```
```
- getAttribute()
- setAttribute("name", object) -> Object객체
```
```
queryParameter : hobby=1&hobby=2;
- getParameter("hobby"); 
    >> 1만 출력 <- 맨 앞에 하나만 받아옴 :
- getParameterValues("hobby"); 
    >> hobby인거 전부 받아옴, 사용예시 체크박스
```
    
4. 페이지 이동 관련 객체 및 메소드
    - redirect - root경로 필요함, forward - root경로 불
5. 