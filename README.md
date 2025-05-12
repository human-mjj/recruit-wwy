<img src="https://capsule-render.vercel.app/api?type=waving&color=DCDCDC&height=300&section=header&text=We%20Want%20You.&fontSize=90&fontColor=0099FF" />

# 휴먼클라우드 웹 - 미니 프로젝트 1 : WWY

- 자바와 스프링부트, HTML/CSS를 활용한 웹 서버 제작 프로젝트입니다.

<br>

# 휴먼클라우드 웹 - 미니 프로젝트 2 : REST API 전환

- JSON을 활용해서 데이터만 전송하는 REST 서버 전환 프로젝트입니다.

### 변경점 & TODO

- 모든 응답은 ResponseEntity로 반환
- 모든 예외 처리 진행
- Unit Test 적극 활용
- 모든 데이터 전달은 DTO로 수행 : DTO 미흡 부분 수정
- 화면 삭제
- 더미 데이터 일치화
- 필터 설정
- 유효성 검사
- 인터셉터 보완
- enum (범주) 활용해보기
- JWT : Access Token만 사용
- BCrypt : 비밀번호 암호화
- 주소 재설정
- GET, POST, PUT, DELETE 활용
- 이미지 전송 : JSON (Base64) - 이미지를 직접 저장하지 말고 String으로 전송

# Members

|                                                          문정준                                                           |                                                           손영민                                                            |                                                       서회정                                                        |                                                        편준민                                                        |
|:----------------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------:|
| <img src="https://github.com/human-mjj/recruit-wwy/blob/master/README/240125_ynn1.png?raw=true" alt="문정준" width="150"> | <img src="https://github.com/human-mjj/recruit-wwy/blob/master/README/Screenshot_38.png?raw=true" alt="손영민" width="150"> | <img src="https://github.com/human-mjj/recruit-wwy/blob/master/README/image.png?raw=true" alt="서회정" width="150"> | <img src="https://github.com/user-attachments/assets/beea8c64-19de-4d91-955f-ed24b813a638" alt="편준민" width="150"> |
|                                                           PL                                                           |                                                            FE                                                            |                                                        FE                                                        |                                                        FE                                                         |
|                                           [GitHub](https://github.com/Sxias)                                           |                                           [GitHub](https://github.com/son7571)                                           |                                      [GitHub](https://github.com/clubnerdy)                                      |                                       [GitHub](https://github.com/PJumMin)                                        |

<br>

# Developed With

<a href="https://www.jetbrains.com/ko-kr/idea/">
<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/IntelliJ_IDEA_Icon.svg/800px-IntelliJ_IDEA_Icon.svg.png" width="200">
</a>

# Stacks Used

<div>
<img src="https://github.com/yewon-Noh/readme-template/blob/main/skills/Java.png?raw=true" width="80">
<img src="https://github.com/yewon-Noh/readme-template/blob/main/skills/Bootstrap.png?raw=true" width="80">
<img src="https://github.com/yewon-Noh/readme-template/blob/main/skills/JavaScript.png?raw=true" width="80">
<img src="https://github.com/yewon-Noh/readme-template/blob/main/skills/HTMLCSS.png?raw=true" width="80">
<img src="https://github.com/yewon-Noh/readme-template/blob/main/skills/Ajax.png?raw=true" width="80">
</div>

# Cooperated With

<div>
<img src="https://github.com/yewon-Noh/readme-template/blob/main/skills/Github.png?raw=true" width="80">
<img src="https://github.com/yewon-Noh/readme-template/blob/main/skills/Notion.png?raw=true" width="80">
</div>

<br>

# Tasks & Responsibilities

|     |                                                                                                                          |                                                                                                         |
|-----|--------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------|
| 문정준 | <img src="https://github.com/human-mjj/recruit-wwy/blob/master/README/240125_ynn1.png?raw=true" alt="문정준" width="100">   | <ul><li>프로젝트 계획 및 관리</li><li>팀 리딩 및 커뮤니케이션</li><li>제안, 지원, 매칭 기능 개발</li><li>전체 DTO 수정 및 디버깅</li></ul>   |
| 손영민 | <img src="https://github.com/human-mjj/recruit-wwy/blob/master/README/Screenshot_38.png?raw=true" alt="손영민" width="100"> | <ul><li>추천 페이지 제작</li><li>이력서 관련 페이지 및 기능 개발</li><li>커뮤니티 관련 페이지 및 기능 개발</li></ul>                      |
| 서회정 | <img src="https://github.com/human-mjj/recruit-wwy/blob/master/README/image.png?raw=true" alt="서회정" width="100">         | <ul><li>홈 페이지 제작</li><li>채용공고 관련 페이지 및 기능 개발</li><li>페이지네이션 기능 개발</li></ul>                             |
| 편준민 | <img src="https://github.com/user-attachments/assets/beea8c64-19de-4d91-955f-ed24b813a638" alt="편준민" width="100">        | <ul><li>로그인 관련 페이지 및 기능 개발</li><li> 마이페이지 및 회원 정보 수정 개발</li><li>매칭, 지원 페이지 제작</li><li>인터셉터 개발</li></ul> |

<br>

# Information Architecture

- 기업과 구직자 역할은 구분되어 있음
- 검정색 기능은 공통 기능

<img src="https://github.com/human-mjj/recruit-wwy/blob/master/README/projectia.png?raw=true">

<br>

# Table Setting

## user_tb

```sql
create table user_tb
(
    id          integer generated by default as identity,
    industry_id integer,
    role        integer not null,
    com_name    varchar(255),
    email       varchar(255),
    img_url     varchar(255),
    password    varchar(255),
    phone       varchar(255),
    username    varchar(255),
    primary key (id)
)
```

## employment_tb

```sql
create table employment_tb
(
    id            integer generated by default as identity,
    job_id        integer not null,
    sal           integer,
    user_id       integer not null,
    end_date      timestamp(6),
    duty          varchar(255),
    edu           varchar(255),
    exp           varchar(255),
    img_url       varchar(255),
    location      varchar(255),
    qualification varchar(255),
    shift         varchar(255),
    title         varchar(255),
    working_time  varchar(255),
    primary key (id)
)
```

## resume_tb

```sql
create table resume_tb
(
    id        integer generated by default as identity,
    job_id    integer not null,
    user_id   integer not null,
    activity  varchar(255),
    edu       varchar(255),
    exp       varchar(255),
    img_url   varchar(255),
    letter    varchar(255),
    location  varchar(255),
    qualified varchar(255),
    title     varchar(255),
    primary key (id)
)
```

## scrap_tb

```sql
create table scrap_tb
(
    employment_id integer,
    id            integer generated by default as identity,
    resume_id     integer,
    user_id       integer not null,
    primary key (id)
)
```

## apply_tb

```sql
create table apply_tb
(
    employment_id integer not null,
    id            integer generated by default as identity,
    resume_id     integer not null,
    user_id       integer not null,
    created_at    timestamp(6),
    progress      varchar(255),
    primary key (id)
)
```

## proposal_tb

```sql
create table proposal_tb
(
    employment_id integer not null,
    id            integer generated by default as identity,
    resume_id     integer not null,
    user_id       integer not null,
    created_at    timestamp(6),
    primary key (id)
)
```

## board_tb

```sql
create table board_tb
(
    id         integer generated by default as identity,
    user_id    integer not null,
    created_at timestamp(6),
    content    varchar(255),
    title      varchar(255),
    primary key (id)
)
```

## reply_tb

```sql
create table reply_tb
(
    board_id   integer not null,
    id         integer generated by default as identity,
    user_id    integer not null,
    created_at timestamp(6),
    content    varchar(255),
    primary key (id)
)
```

## stack_tb

```sql
create table stack_tb
(
    id    integer generated by default as identity,
    skill varchar(255),
    primary key (id)
)
```

## job_tb

```sql
create table job_tb
(
    id   integer generated by default as identity,
    name varchar(255),
    primary key (id)
)
```

## industry_tb

```sql
create table industry_tb
(
    id   integer generated by default as identity,
    name varchar(255),
    primary key (id)
)
```

## resume_stack_tb

```sql
create table resume_stack_tb
(
    id        integer generated by default as identity,
    resume_id integer not null,
    skill     varchar(255),
    primary key (id)
)
```

## employ_stack_tb

```sql
create table employ_stack_tb
(
    employment_id integer not null,
    id            integer generated by default as identity,
    skill         varchar(255),
    primary key (id)
)
```