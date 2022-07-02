# epl_scheduler_2021-22

2022/06/10
-------------
동아리장님이 인스타그램 클론코딩은 백엔드 공부에 큰 도움이 안될거라고 하셔서 급히 방향을 조그만 나만의 웹 서비스 만들기로 방향을 틀게 됨. 뭐하지뭐하지 하다가 최근 내가 흥미있게 보고 있는 리그인 epl에 대해서 하면 어떨까 했고, 2021-2022 시즌 epl 기록을 조회할 수 있는 서비스를 만들어보기로 생각했다. 일단 처음엔 일별로 경기결과를 조회할 수 있는 기능을 만들고 점차 기능을 늘려갈 계획이다.

### 한 것
1. 환경설정(DB 연결, 가볍게 사용할 타임리프 테이블 템플릿 적용)

2022/06/11
-------------
앞으로의 계획을 조금 정리함.

1. Entity 코드 작성
2. Naver Sports에서 개막일(8/14)부터 차근차근 스케줄 크롤링하기(python Beautiful Soup)
3. repository, service, controller 코드 짜기(프론트 포함)

일단은 개막일인 8/14일 하루만 해보고 pagination -> 8월 한달(ajax 통신) 등등 해서 점점 기능을 추가할 생각이다.

### 한 것
1. 게시판의 들어갈 Entity인 Board.java 설계.

### Trouble Shooting
1. BeautifulSoup을 이용하여 크롤링하려고 했지만 어째서인지 파싱이 안됨. print로 값을 파싱해보니 div 안의 값이 출력이 되지 않음. 아마 동적으로 움직이는 javascript 때문인 것으로 예측됨.
   + 해결: 구글링을 한 결과 예상한 대로 html페이지가 브라우저에 노출된 후 자바스크립트로 값이 이후에 제어되어 원하는 데이터를 조회하기가 어렵다고 함. 이러한 상황에서 쓰는 Selenium이라는 라이브러리가 있음. 이걸 발견한게 새벽 5시라 다음에 고치겠음 ㅠㅠ.(해결)

2022/06/17~20
-------------
### 한 것
1. 6월 11일에 못 다했던 트러블 슈팅 해결(BeautifulSoup 크롤링 안되던 문제)
2. repository, service, controller 코드 짜기
3. DTO(BoardResponseDTO, BoardRequestDTO) 만들기
4. 부트스트랩 테이블 UI 찾아서 타임리프에 적용하기
5. 프론트에 달력 기능을 쉽게 사용할 수 있는 DatePicker 적용
6. 크롤링한 데이터 테이블에 바인딩하기 -> 8월 14일 하루가 아닌 시각적으로 보기 쉽게 하기 위하여 8월 한달로 바인딩

### Trouble Shooting
1. @GetMapping "list/{startDate}"으로 설정하였는데, 화면이 startDate로 전환되지 않음. 백엔드가 문제가 아닌 ajax 문제일 것으로 예상됨.
   + 해결: 프론트 ajax 코드에 location.replace(url) 추가. -> Success: console.log(res)를 통해 화면이 바뀌진 않지만 응답으로선 결과가 잘 출력되는 것을 알 수 있었음. 응답은 잘 되지만 url이 바뀌지 않는 모습에 그것을 해결하는 코드인 location.replace(url)을 구글링을 통해 발견.(해결시간 약 5시간)

2022/06/22
-------------
### 한 것
1. 스프링부트 jpa 페이징 코드 추가

### Trouble Shooting
1. jpa 쿼리문에서 limit이 뜬 게 보이는데 프론트에는 전혀 되지 않은 모습을 보임.
   + 해결: Page<> 안에 지네릭을 Entity에서 DTO로 바꾸었다. 기존 코드에선 return값이 return new PageImpl<>(List<BoardResponseDTO>, pageable, pageBoards.getTotalElements())여서 첫번째 파라미터와 세번째 파라미터의 타입이 달랐었다. 혹시 이 부분이 문제인건가 싶어서 첫 번째 파라미터를 Page<BoardResponseDTO>.getContent()로 바꾸었더니 해결. 사실 처음부터 Page<> 타입을 써도 됬지만 기존에 있던 코드들을 변형하고 싶었다.. ㅠㅠ 앞으로 고집 부리지 말고 안되면 여러 방면으로 해결해보자!

### 다음 할 것
1. 백엔드 페이징 api를 이용하여 프론트 ui 코드 및 ajax 코드 추가(생각보다 머리써야 할 것 같아서 다음으로 기약.)

2022/06/24
-------------
### 한 것
1. 타임리프 페이징 코드 추가

### Trouble Shooting
1. 구글링한 페이징 코드가 계속 500에러가 떴다.
   + 해결: th:with="start=${T(Math).floor(boards.number / 10) * 10 + 1},
      last=(${start + 9 < boards.totalPages ? start + 9 : boards.totalPages} 이 코드 중 버림 함수 Math.floor이 타임리프 문법상 에러가 떴다. 그닥 페이지가 많지 않아서 없애버림.

### 다음 할 것
1. 날짜별로 페이징 되는 코드 만들기. 잘 떠오르지가 않는데 일단은 2021-08-14일만 페이징 구현을 했다.

2022/06/25
-------------
### 한 것
1. 타임리프 페이징 날짜별로 구현하기

### Trouble Shooting
1. 날짜별 파라미터를 어떻게 끌고 올지 고민했다.
   + 해결: model.addattribute 값을 끌고 올라했지만 그렇게하면 리스트 값대로 페이징이 5줄이나 출력이 되었다. 리스트 중 하나만 출력할 수 있는 타임리프 문법을 폭풍 구글링한 결과 th:object를 발견하였고 이를 통해 파싱하여 해결할 수 었다.

2022/07/02
-------------
### 한 것
1. SSR을 CSR로 바꾸기(타임리프 없애고 @RestController와 ResponseEntity 기반으로 리팩토링) -> 동아리 셀장님 조언에 따라 백엔드 api 짜는 것에 집중하기 위해 csr 방식으로 모두 바꿈. 타임리프 코드를 없앰. 이 과정에서 CSR과 SSRD의 차이점을 알게 됨. https://proglish.tistory.com/216 
2. 다중 검색 조건에 앞서 단일 조건 검색 기능(홈팀) 추가
3. 페이징 기능 추가

### Trouble Shooting
1. SQL의 like 문법을 jpa에선 어떤 코드인지 찾아봄.
   + 해결: containing을 추가하면 like sql문이 나옴.

### 다음 할 것
1. 단일 조건 검색 기능만 구현했는데, jpa criteria를 이용하여 다중 조건 검색 기능 구현해보기