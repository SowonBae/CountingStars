# 아이디어 확정 및 구체화



## 서비스 : 명품 중고 거래

### 1. 필수 기능

- 로그인 / 로그아웃
  - 카카오 로그인 (security, refresh token)

### **회원가입 / 회원 탈퇴**

- 휴대폰 문자 인증
  - https://charlie-choi.tistory.com/225
- 일회성 코드 참조
  - https://developers.google.com/identity/sms-retriever/verify#generating_a_one-time_code
- 서비스 인증
  - 서비스 이용 약관(필수)
  - 개인정보 수집 및 이용(필수),
  - 만 14세 이상(필수),
  - 마케팅 정보 수신 동의(선택)

### 유저 정보

- 거래 기록
- 평점

### 마이페이지

- 개인 정보 (이름, 아이디, 비번) - 비밀번호 수정 가능
- 등록 상품 관리

### 메인페이지

- 네브바
- 명품 중고 거래
- 명품 경매

### 관리자

- 회원 삭제



### 명품 중고거래

1. 판매 중 물품 리스트 출력

   - 판매 시
     - 본인이 소유한 NFT 중 팔 물건 선택
     - 물품 사진 및 설명 등록
     - 판매 금액 등록
     - 판매완료 → 물품 리스트에서 삭제(NFT소유권 변경) 및 거래완료 변경
   - 구매 시
     - 상품 검색 : 카테고리 별(브랜드, 가방,지갑, 등등)
     - 찜
     - 판매자와 채팅 및 거래

2. 중고 거래 시

   1. 구매자가 상품 구매

      1-1. 판매자와 구매자의 1:1 채팅 개설

      1-2. 서로 조율 후 주문

      1-3. 배송 or 만나서 거래

   2. 에스크로

      - 탈 에스크로가 아닌 임시적인 3자 에스크로

   3. 배송 구매

      1. 구매자에게 물품 배송 도착 후 3~7일 유예기간
      2. 구매자가 상품에 문제 발생시 홈페이지를 통해 환불 요청
         1. 여기서 어떻게 처리를 할지?
      3. 구매자가 구매 확정 시
         1. NFT가 판매자에서 구매자로 넘어가게
         2. 스마트 컨트랙트 발행

   4. 직접 거래

      1. Kakao Map // 추후 고민