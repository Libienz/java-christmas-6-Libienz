# 🎄 크리스마스 프로모션 이벤트 플래너

- 우테코 식당에서 12월 한달 동안 개최하는 크리스마스 이벤트를 개최한다.
- 다양한 할인과 증정 정책이 있으며 각 정책은 다중으로 적용될 수 있다.
- 해당 프로그램 ```이벤트 플래너```는 ```특정 날짜```에 방문하여 ```특정 메뉴들```을 주문하면 어떤 혜택을 받을 수 있는지 계산해준다.

# 🎅 크리스마스 이벤트 상세

### 🪙 할인 이벤트

- 크리스마스 디데이 할인
    - 이벤트 기간: 2023.12.1 ~ 2023.12.25
    - 1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가
    - 총주문 금액에서 해당 금액만큼 할인  
      (e.g. 시작일인 12월 1일에 1,000원, 2일에 1,100원, ..., 25일엔 3,400원 할인)
- 평일 할인(일요일~목요일): 평일에는 디저트 메뉴를 메뉴 1개당 2,023원 할인
- 주말 할인(금요일, 토요일): 주말에는 메인 메뉴를 메뉴 1개당 2,023원 할인
- 특별 할인: 이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인
- 이벤트 기간: '크리스마스 디데이 할인'을 제외한 다른 이벤트는 2023.12.1 ~ 2023.12.31 동안 적용

### 🎁 증정 이벤트

- 할인 전 총주문 금액이 12만 원 이상일 때, 샴페인 1개 증정
- 총혜택 금액에 따라 다른 이벤트 배지를 증정
    - 이 배지는 2024 새해 이벤트에서 활용할 예정
    - 5천 원 이상: 별
    - 1만 원 이상: 트리
    - 2만 원 이상: 산타

### 주의 사항

- 총주문 금액 10,000원 이상부터 이벤트가 적용
- 음료만 주문 시, 주문할 수 없음
- 메뉴는 한 번에 최대 20개까지만 주문할 수 있음  
  (e.g. 시저샐러드-1, 티본스테이크-1, 크리스마스파스타-1, 제로콜라-3, 아이스크림-1의 총개수는 7개)

# ▶️ 프로그램 실행 예시

#### 적용되는 이벤트 없음 예시

```
안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.
12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)
26 
주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)
타파스-1,제로콜라-1 
12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!
 
<주문 메뉴>
타파스 1개
제로콜라 1개

<할인 전 총주문 금액>
8,500원
 
<증정 메뉴>
없음
 
<혜택 내역>
없음
 
<총혜택 금액>
0원
 
<할인 후 예상 결제 금액>
8,500원
 
<12월 이벤트 배지>
없음
```

#### 이벤트 적용 예시

```
안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.
12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)
3
주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)
티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1
12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!
 
<주문 메뉴>
티본스테이크 1개
바비큐립 1개
초코케이크 2개
제로콜라 1개
 
<할인 전 총주문 금액>
142,000원
 
<증정 메뉴>
샴페인 1개
 
<혜택 내역>
크리스마스 디데이 할인: -1,200원
평일 할인: -4,046원
특별 할인: -1,000원
증정 이벤트: -25,000원
 
<총혜택 금액>
-31,246원
 
<할인 후 예상 결제 금액>
135,754원
 
<12월 이벤트 배지>
산타
```

# 🛠️ 구현 기능 목록

## ⌨️ 입력

- [x] 날짜를 입력 받을 수 있다.
- [x] 주문 내역(메뉴 및 개수)을 입력 받을 수 있다.

## ✅ 입력 검증

- [x] 날짜 입력을 검증할 수 있다.
    - [x] 1이상 31 이하의 숫자인지 검증할 수 있다.
- [x] 주문 내역을 검증할 수 있다.
    - [x] 메뉴판에 없는 메뉴를 입력한 경우를 검증할 수 있다.
    - [x] 메뉴의 개수가 1 이상의 숫자인지 검증할 수 있다.
    - [x] 각 메뉴의 주문 개수가 20을 넘어가지 않는지 검증할 수 있다.
    - [x] 주문내역 형식이 올바른지 검증할 수 있다.
    - [x] 주문내역에 중복 메뉴가 없는지 검증할 수 있다.
    - [x] 음료만 주문 시 주문할 수 없다.
- [x] 검증에 실패한다면 ```[ERROR]```메시지를 출력하고 재입력하도록 유도한다.

## 🖨️ 출력

- [x] 날짜 입력 유도 메시지를 출력할 수 있다 ```12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)```
- [x] 메뉴와 주문 수량 입력 유도 메시지를 출력한다 ```주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)```
- [x] 입력 검증에 실패한 경우 ```[ERROR]```로 시작하는 메시지를 출력한다.
    - [x] 날짜 입력 검증 실패 ```[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.```
    - [x] 주문 내역 입력 검증 실패 ```[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.```
- [x] 주문 내역을 출력할 수 있다. (출력 순서 상관 없음)

```
<주문 메뉴>
타파스 1개
제로콜라 1개
```

- [x] 할인 전 총주문 금액을 출력할 수 있다.

```
<할인 전 총주문 금액>
142,000원
```

- [x] 증정 메뉴를 출력할 수 있다.

```
<증정 메뉴>
샴페인 1개
```

- [x] 혜택 내역을 출력할 수 있다.

```
<혜택 내역>
크리스마스 디데이 할인: -1,200원
평일 할인: -4,046원
특별 할인: -1,000원
증정 이벤트: -25,000원
```

- [x] 총혜택 금액을 출력할 수 있다.

```
<총혜택 금액>
-31,246원
```

- [x] 할인 후 예상 결제 금액을 출력할 수 있다

```
<할인 후 예상 결제 금액>
135,754원
```

- [x] 증정되는 12월 이벤트 배지 내역을 출력할 수 있다.

```
<12월 이벤트 배지>
산타
```

## 📑 비즈니스 로직

### 메뉴 로직

```
<애피타이저>
양송이수프(6,000), 타파스(5,500), 시저샐러드(8,000)

<메인>
티본스테이크(55,000), 바비큐립(54,000), 해산물파스타(35,000), 크리스마스파스타(25,000)

<디저트>
초코케이크(15,000), 아이스크림(5,000)

<음료>
제로콜라(3,000), 레드와인(60,000), 샴페인(25,000)
```

- [x] 메뉴를 위와 같이 구성한다

### 이벤트 로직

- [x] 모든 이벤트는 총 주문 금액 10,000 이상인 경우 적용된다.

#### 할인 정책

- [x] 크리스마스 디데이 할인
    - [x] 2023.12.1 ~ 2023.12.25 기간에만 크리스마스 디데이 할인을 적용한다.
    - [x] 1,000원부터 시작해 크리스마스에 가까워질 수록 하루에 100원씩 할인 금액을 증가시켜 적용할 수 있다.
- [x] 평일 할인
    - [x] 일요일 ~ 목요일 기간에 평일 할인을 적용한다.
    - [x] 평일 할인은 디저트 메뉴만 메뉴 1개당 2,023원 할인한다.
- [x] 주말 할인
    - [x] 금요일 ~ 토요일만 할인을 적용한다.
    - [x] 주말 할인은 메인 메뉴를 1개당 2,023원 할인한다.
- [x] 특별 할인
    - [x] 이벤트 달력에 별이 있는 경우만 할인을 적용한다.
    - [x] 총 주문 금액에서 1,000원 할인한다.

#### 증정 정책

-  [x] 할인 전 총 주문 금액이 12만원 이상일 때, 샴페인 1개 증정한다.
- [x] 총혜택 금액이 5천원 이상이면 별 배지를 증정한다.
- [x] 총혜택 금액이 1만원 이상이면 트리 배지를 증정한다.
- [x] 총혜택 금액이 2만원 이상이면 산타 배지를 증정한다.

### 계산 로직

- [x] 할인 정책에 맞추어 혜택 내역을 계산할 수 있다.
- [x] 증정 정책에 맞추어 배지를 포함한 증정 내역을 계산할 수 있다.
- [x] 할인 후 예상 결제 금액을 계산할 수 있다. 