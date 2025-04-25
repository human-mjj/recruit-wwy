-- 기술 스택 테이블 dummy
insert into stack_tb(skill)
values ('java');
insert into stack_tb(skill)
values ('spring boot');
insert into stack_tb(skill)
values ('mysql');
insert into stack_tb(skill)
values ('javascript');
insert into stack_tb(skill)
values ('react');
insert into stack_tb(skill)
values ('html');
insert into stack_tb(skill)
values ('css');
insert into stack_tb(skill)
values ('node.js');
insert into stack_tb(skill)
values ('typescript');
insert into stack_tb(skill)
values ('python');
insert into stack_tb(skill)
values ('postgresql');
insert into stack_tb(skill)
values ('aws');
insert into stack_tb(skill)
values ('git');
insert into stack_tb(skill)
values ('figma');
insert into stack_tb(skill)
values ('next.js');
insert into stack_tb(skill)
values ('tailwind css');

-- 직무 테이블 dummy
insert into job_tb(name)
values ('백엔드 개발자');
insert into job_tb(name)
values ('프론트엔드 개발자');
insert into job_tb(name)
values ('풀스택 개발자');
insert into job_tb(name)
values ('데이터 엔지니어');
insert into job_tb(name)
values ('ai 엔지니어');
insert into job_tb(name)
values ('웹 퍼블리셔');
insert into job_tb(name)
values ('앱 개발자');
insert into job_tb(name)
values ('qa 엔지니어');
insert into job_tb(name)
values ('devops 엔지니어');
insert into job_tb(name)
values ('보안 엔지니어');
insert into job_tb(name)
values ('프로덕트 매니저');
insert into job_tb(name)
values ('ui 디자이너');
insert into job_tb(name)
values ('ux 디자이너');
insert into job_tb(name)
values ('데이터 분석가');
insert into job_tb(name)
values ('기획자');

-- 산업명 테이블 dummy
insert into industry_tb(name)
values ('IT, 콘텐츠');
insert into industry_tb(name)
values ('판매, 유통');
insert into industry_tb(name)
values ('제조');
insert into industry_tb(name)
values ('기타 서비스업');
insert into industry_tb(name)
values ('전문, 과학기술');
insert into industry_tb(name)
values ('금융');
insert into industry_tb(name)
values ('교육');
insert into industry_tb(name)
values ('예술, 스포츠, 여가');
insert into industry_tb(name)
values ('물류, 운송');
insert into industry_tb(name)
values ('부동산');
insert into industry_tb(name)
values ('게임');
insert into industry_tb(name)
values ('보건, 사회복지');
insert into industry_tb(name)
values ('건설');
insert into industry_tb(name)
values ('사업지원');
insert into industry_tb(name)
values ('숙박, 음식점');
insert into industry_tb(name)
values ('상수도, 환경');
insert into industry_tb(name)
values ('농림어업');
insert into industry_tb(name)
values ('공공행정, 국방');
insert into industry_tb(name)
values ('전기, 가스');
insert into industry_tb(name)
values ('가사, 가정');
insert into industry_tb(name)
values ('국제, 외국기관');

-- 유저 테이블 dummy
insert into user_tb(username, email, phone, password, img_url, role, com_name, industry_id)
values ('손영민', 'ssar@nate.com', '01012345678', '1234', null, 0, null, null);
insert into user_tb(username, email, phone, password, img_url, role, com_name, industry_id)
values ('서회정', 'cos@nate.com', '01087654321', '1234', null, 0, null, null);
insert into user_tb(username, email, phone, password, img_url, role, com_name, industry_id)
values ('편준민', 'love@nate.com', '01013572468', '1234', null, 0, null, null);
insert into user_tb(username, email, phone, password, img_url, role, com_name, industry_id)
values ('문정준', 'a32176740@gmail.com', '01032176740', '1234', null, 1, 'WWY', 1);
insert into user_tb(username, email, phone, password, img_url, role, com_name, industry_id)
values ('홍길동', '1234@nate.com', '01022223333', '1234', null, 1, 'HOG', 1);

-- 채용 공고 테이블 dummy
INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('Spring 백엔드 개발자 모집', 4, '1년 이상', '대졸 이상', '정규직', 3,
        'REST API 개발 및 유지보수', 'Java/Spring 경력 1년 이상, MySQL 경험 우대', 4200,
        '09:30 ~ 18:30', '서울특별시 강남구', '2025-05-31', NULL);
INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('프론트엔드 React 개발자 채용', 4, '신입~3년', '무관', '정규직', 2,
        '웹 서비스 프론트 개발', 'React 사용 가능자, 포트폴리오 필수', 3800,
        '10:00 ~ 19:00', '서울특별시 마포구', '2025-06-15', NULL);
INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('신입 백엔드 인턴 모집', 4, '신입', '초대졸 이상', '인턴', 2,
        'Spring 기반 서비스 API 개발 및 문서화', '개발 열정, 깃허브 활동 확인 예정', 2500,
        '09:00 ~ 17:00', '서울특별시 종로구', '2025-06-01', NULL);
INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('웹 프론트엔드 인턴 채용', 5, '신입', '학력무관', '인턴', 3,
        'React 기반 UI 개발 및 유지보수', '포트폴리오 제출 필수, 협업 경험 우대', 2200,
        '10:00 ~ 18:00', '서울특별시 강남구', '2025-06-10', NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('데이터 분석 인턴 모집', 5, '신입', '대졸 이상', '인턴', 4,
        'Python을 이용한 데이터 수집 및 시각화', 'SQL, Pandas 사용 경험자 우대', 2300,
        '09:30 ~ 18:30', '서울특별시 마포구', '2025-05-20', NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('데이터 분석가 채용', 4, '1년 이상', '대졸 이상', '정규직', 1, '데이터 시각화 및 분석', 'Python, Pandas 경험', 4500, '09:00 ~ 18:00',
        '서울특별시 성동구', '2025-06-30', NULL);

-- 이력서 테이블 dummy
insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('백엔드 신입 개발자 지원서', 1, '컴퓨터공학 전공, 인턴 경험 3개월', '부산대학교 컴퓨터공학과 졸업', 1, '서울', '정보처리기사', '멋쟁이사자처럼 10기 활동', NULL);

insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('Spring 기반 개발 프로젝트 경험', 1, '팀 프로젝트 백엔드 담당', '부산대학교 컴퓨터공학과 졸업', 1, '경기', 'SQLD', '사이드 프로젝트 2건', NULL);

insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('프론트엔드 포지션 지원 이력서', 2, 'React 학습 및 개인 포트폴리오 제작', '동서대학교 미디어학부 재학', 2, '서울', '웹디자인 기능사', 'UX/UI 디자인 스터디', NULL);

insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('웹 퍼블리셔 + 프론트엔드 복합 지원서', 2, 'HTML/CSS 퍼블리싱 1년 경험', '동서대학교 미디어학부 재학', 2, '인천', 'GTQ 1급', '디자인 공모전 참가', NULL);

insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('프론트와 백엔드를 넘나드는 풀스택 꿈나무', 3, 'Node.js와 Vue.js 활용 토이 프로젝트', '부경대학교 컴퓨터공학과 졸업', 3, '대전', '정보처리기사', '교내 개발동아리 회장',
        NULL);

insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('REST API 설계 및 DB 모델링 경험 중심', 3, 'Django + PostgreSQL 프로젝트', '부경대학교 컴퓨터공학과 졸업', 1, '부산', 'SQLD',
        '캡스톤디자인 우수상 수상', NULL);


-- 스크랩 테이블 dummy
insert into scrap_tb(user_id, employment_id, resume_id)
values (1, 1, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (1, 2, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (1, 3, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (2, 1, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (3, 2, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (3, 3, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (4, null, 1);
insert into scrap_tb(user_id, employment_id, resume_id)
values (4, null, 2);
insert into scrap_tb(user_id, employment_id, resume_id)
values (4, null, 3);

-- 지원 테이블 dummy
INSERT INTO apply_tb (user_id, resume_id, employment_id, created_at, progress)
VALUES (1, 1, 1, NOW(), '대기');
INSERT INTO apply_tb (user_id, resume_id, employment_id, created_at, progress)
VALUES (2, 2, 2, NOW(), '진행 중');
INSERT INTO apply_tb (user_id, resume_id, employment_id, created_at, progress)
VALUES (3, 3, 3, NOW(), '진행 중');
INSERT INTO apply_tb (user_id, resume_id, employment_id, created_at, progress)
VALUES (1, 1, 2, NOW(), '완료');
INSERT INTO apply_tb (user_id, resume_id, employment_id, created_at, progress)
VALUES (1, 1, 5, NOW(), '대기');

-- 제안 테이블 dummy
insert into proposal_tb(user_id, resume_id, employment_id, created_at)
values (4, 1, 1, now());
insert into proposal_tb(user_id, resume_id, employment_id, created_at)
values (4, 2, 2, now());
insert into proposal_tb(user_id, resume_id, employment_id, created_at)
values (4, 3, 3, now());

-- 게시글 테이블 dummy
insert into board_tb(title, content, user_id, created_at)
values ('내용1', '제목1', 1, now());
insert into board_tb(title, content, user_id, created_at)
values ('내용2', '제목2', 1, now());
insert into board_tb(title, content, user_id, created_at)
values ('내용3', '제목3', 2, now());
insert into board_tb(title, content, user_id, created_at)
values ('내용4', '제목4', 3, now());
insert into board_tb(title, content, user_id, created_at)
values ('내용5', '제목5', 4, now());

-- 댓글 테이블 dummy
insert into reply_tb(board_id, user_id, content, created_at)
values (1, 1, '댓글1', now());
insert into reply_tb(board_id, user_id, content, created_at)
values (1, 2, '댓글2', now());
insert into reply_tb(board_id, user_id, content, created_at)
values (2, 2, '댓글3', now());
insert into reply_tb(board_id, user_id, content, created_at)
values (3, 3, '댓글4', now());
insert into reply_tb(board_id, user_id, content, created_at)
values (4, 4, '댓글5', now());
insert into reply_tb(board_id, user_id, content, created_at)
values (5, 4, '댓글6', now());

-- 채용공고-스택 테이블 dummy
insert into employ_stack_tb(employment_id, skill)
values
--     (1, 'java'),
--        (1, 'spring boot'),
--        (1, 'mysql'),
(1, 'react'),
(2, 'html'),
(2, 'css'),
(2, 'javascript'),
(2, 'figma'),
(3, 'javascript'),
(3, 'mysql'),
(3, 'node.js'),
(3, 'git'),
(4, 'python'),
(4, 'pandas'),
(4, 'powerbi'),
(4, 'excel');

-- 이력서-스택 테이블 dummy
insert into resume_stack_tb(resume_id, skill)
values (1, 'java'),
       (1, 'spring boot'),
       (1, 'mysql'),
       (1, 'git'),
       (2, 'html'),
       (2, 'css'),
       (2, 'javascript'),
       (2, 'react'),
       (2, 'figma'),
       (3, 'javascript'),
       (3, 'mysql'),
       (3, 'node.js'),
       (3, 'python'),
       (3, 'git'),
       (4, 'html'),
       (4, 'css'),
       (4, 'javascript'),
       (5, 'java'),
       (5, 'spring boot'),
       (5, 'mysql'),
       (5, 'django'),
       (5, 'mysql'),
       (5, 'git'),
       (6, 'java'),
       (6, 'spring boot'),
       (6, 'mysql'),
       (6, 'aws'),
       (6, 'git');