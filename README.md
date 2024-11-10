# kotlin-convenience-store-precourse

### convenience-store Project

- 편의점 상황을 가정하여 프로모션, 할인, 구매, 영수증 출력의 기능을 하는 프로젝트

### 주요 기능

- 재고 계산
- 할인 계산
- 프로모션 계산
- 영수증 출력

## 기능 구현 목록

### 출력

- [x] 편의점 소개
- [ ] 현재 재고 출력
    - [ ] 재고가 0이라면 재고 없음 출력
- [ ] 올바른 수량 입력시 할인혜택에 대한 여부
- [ ] 구매 하고 싶은 다른 상품에 대한 여부

### 입력

- [ ] 구매할 상품과 수량 입력
    - [ ] 정확한 서식 검사
        - [ ] 검사 미통과시 [ERROR] 발생 후 1번으로 리턴
- [ ] 멤버십 할인 적용 여부 입력
    - [ ] 정확한 서식 검사
        - [ ] 검사 미통과시 [ERROR] 발생 후 1번으로 리턴
- [ ] 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 일부 수량에 대해 정가로 결제할지 여부
    - [ ] 정확한 서식 검사
        - [ ] 검사 미통과시 [ERROR] 발생 후 1번으로 리턴
- [ ] 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량만큼 가져오지 않았을 경우 혜택을 받을 것인가에 대한 여부
    - [ ] 정확한 서식 검사
        - [ ] 검사 미통과시 [ERROR] 발생 후 1번으로 리턴
- [ ] 구매하고 싶은 다른 상품에 대한 여부
    - [ ] 정확한 서식 검사
        - [ ] 검사 미통과시 [ERROR] 발생 후 1번으로 리턴

### 로직

- [ ] 현재 재고 확인
- [ ] 총 구매액 계산
- [ ] 행사 할인 계산
- [ ] 멤버쉽 할인 계산
- [ ] 총금액 계산

### 예외 상황

- 상품 이름과 수량 입력시 서식을 준수하지 않았을 경우
- Y,N 외의 문자를 입력한 경우

### 공통 피드백

함수(메서드) 라인에 대한 기준도 적용한다
예외 상황에 대한 고민한다
비즈니스 로직과 UI 로직의 분리한다
연관성이 있는 상수는 static final 대신 enum을 활용한다
val 키워드를 사용해 값의 변경을 막는다
객체의 상태 접근을 제한한다
객체는 객체답게 사용한다
필드(인스턴스 변수)의 수를 줄이기 위해 노력한다
성공하는 케이스 뿐만 아니라 예외 케이스도 테스트한다
테스트 코드도 코드다
테스트를 위한 코드는 구현 코드에서 분리되어야 한다
단위 테스트하기 어려운 코드를 단위 테스트하기
private 함수를 테스트 하고 싶다면 클래스(객체) 분리를 고려한다

### 자체 패드백

companion object 사용
객체와 클래스의 사용 기준 확립
자원 관리 및 불필요한 코드 삭제
README.md 파일 개선
단일 책임 원칙 적용

