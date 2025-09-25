# 🛒 HELLO SHOP 프로젝트

회원, 상품, 주문 기능을 제공하는 간단한 쇼핑몰 웹 애플리케이션입니다.  
JWT 기반 로그인 같은 보안 로직 없이, 학습 목적을 위해 최소한의 회원/상품/주문 흐름을 구현하였습니다.

---

## ⚙️ 기술 스택
- Java 21
- Spring Framework
- H2 Database
- Thymeleaf

---

## 📌 주요 기능

### 👤 회원 기능
- **회원 가입**: 이름, 주소 입력  
- **회원 목록 조회**: 가입된 회원의 `id`, `이름`, `도시`, `주소` 출력

### 📚 상품 기능
- **상품 등록**  
  - 도서(Book) 등록: `상품명`, `가격`, `수량`, `저자`, `ISBN`  
  - (Item은 interface로 설계되었고 Album, Movie 도메인이 있으나 Book만 구현)
- **상품 목록 조회 및 수정**  
  - 등록된 상품의 `id`, `상품명`, `가격`, `재고수량` 출력 및 수정 가능

### 🛍️ 주문 기능
- **상품 주문**  
  - 주문 회원 선택 (회원 목록에서)  
  - 상품 선택 (상품 목록에서)  
  - 주문 수량 입력  
  - 재고보다 많은 수량 입력 시 오류 처리
- **주문 내역 조회**  
  - `id`, `회원명`, `상품명`, `주문가격`, `주문수량`, `상태`, `일시` 출력  
  - 상태(`Status`)는 `ORDERED` 기본값  
  - 취소 버튼 클릭 시 `CANCEL`로 변경 (enum으로 관리)

---

## 🗄️ 데이터베이스 구조

### MEMBER (회원)
| 컬럼명    | 설명     | 비고           |
|-----------|----------|----------------|
| member_id | 회원 ID  | 자동 생성 (PK) |
| name      | 이름     |                |
| city      | 도시     |                |
| street    | 거리     |                |
| zipcode   | 우편번호 |                |

### ITEM (상품)
| 컬럼명         | 설명         | 비고                         |
|----------------|--------------|------------------------------|
| item_id        | 상품 ID      | 자동 생성 (PK)               |
| name           | 상품명       |                              |
| price          | 가격         |                              |
| stock_quantity | 재고 수량    |                              |
| dtype          | 구분         | `B`=Book, `M`=Movie, `A`=Album 등 (Book만 구현) |
| author         | 저자 (Book)  |                              |
| isbn           | ISBN (Book)  |                              |
| artist         | 아티스트(Album)| 미구현                      |
| actor          | 배우(Movie)  | 미구현                       |
| director       | 감독(Movie)  | 미구현                       |

### ORDERS (주문)
| 컬럼명     | 설명       | 비고                            |
|------------|------------|---------------------------------|
| order_id   | 주문 ID    | 자동 생성 (PK)                  |
| member_id  | 회원 ID    | FK → MEMBER                     |
| delivery_id| 배송 ID    | FK → DELIVERY                   |
| order_date | 주문 일시  |                                 |
| status     | 주문 상태  | ENUM(`ORDER`, `CANCEL`)         |

### ORDER_ITEM (주문 상품)
| 컬럼명       | 설명          | 비고                    |
|--------------|---------------|-------------------------|
| order_item_id| 주문상품 ID   | 자동 생성 (PK)          |
| order_id     | 주문 ID       | FK → ORDERS             |
| item_id      | 상품 ID       | FK → ITEM               |
| order_price  | 주문 가격     |                          |
| count        | 주문 수량     |                          |

### DELIVERY (배송)
| 컬럼명     | 설명       | 비고                            |
|------------|------------|---------------------------------|
| delivery_id| 배송 ID    | 자동 생성 (PK)                  |
| city       | 도시       |                                 |
| street     | 거리       |                                 |
| zipcode    | 우편번호   |                                 |
| status     | 배송 상태  | (현재 미구현, 기본 null)        |

## 📸 화면 예시


- 메인 화면

![메인 화면](https://github.com/user-attachments/assets/6071386a-c072-4776-b649-bcb4cbccec3b)

- 회원 가입

![회원 가입](https://github.com/user-attachments/assets/6176fc30-0485-49d4-aa9e-93b55a783964)

- 회원 목록

![회원 목록](https://github.com/user-attachments/assets/e3a06294-e0a5-450e-8f27-281044b31f1e)

- 상품 등록

![상품 등록](https://github.com/user-attachments/assets/21ef6efd-dd21-4f31-bb9a-ebb14a85242c)

- 상품 목록

![상품 목록](https://github.com/user-attachments/assets/a3c36796-144a-44d3-b466-e6d6c73e676c)

- 상품 주문

![상품 주문](https://github.com/user-attachments/assets/a614fd34-1c58-4ead-9c9d-630a2de43088)

- 주문 내역

![주문 내역](https://github.com/user-attachments/assets/f957195b-9f97-41f3-a8bc-abd006aac471)

---

## 🎯 개발 목적 & 배운 점

- Spring MVC를 통한 회원/상품/주문 도메인 설계 및 구현 경험

- Thymeleaf를 활용한 서버 사이드 렌더링

- 간단한 비즈니스 로직(재고 확인, 주문 취소, 상태 관리 등) 구현

- 계층형 아키텍처(Controller, Service, Repository)의 역할 이해
  
