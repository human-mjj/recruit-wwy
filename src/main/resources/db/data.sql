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
-- 추가 구직자 회원 더미 데이터 (구직자회원 2명 추가)
insert into user_tb(username, email, phone, password, img_url, role, com_name, industry_id)
values ('김민지', 'mjkim@nate.com', '01077778888', '1234', null, 0, null, null);
insert into user_tb(username, email, phone, password, img_url, role, com_name, industry_id)
values ('박서준', 'sjpark@nate.com', '01088889999', '1234', null, 0, null, null);
insert into user_tb(username, email, phone, password, img_url, role, com_name, industry_id)
values ('문정준', 'a32176740@gmail.com', '01032176740', '1234', null, 1, 'WWY', 1);
insert into user_tb(username, email, phone, password, img_url, role, com_name, industry_id)
values ('홍길동', '1234@nate.com', '01022223333', '1234', null, 1, 'HOG', 1);
-- 추가 기업회원 더미 데이터 (기업회원 6명 추가)
insert into user_tb(username, email, phone, password, img_url, role, com_name, industry_id)
values ('이상훈', 'shlee@nate.com', '01011112222', '1234', null, 1, '에이펙스 테크놀로지', 2);
insert into user_tb(username, email, phone, password, img_url, role, com_name, industry_id)
values ('김수진', 'sjkim@nate.com', '01022223344', '1234', null, 1, '넥스트소프트', 3);
insert into user_tb(username, email, phone, password, img_url, role, com_name, industry_id)
values ('박정수', 'jspark@nate.com', '01033334455', '1234', null, 1, '씨에스 솔루션즈', 2);
insert into user_tb(username, email, phone, password, img_url, role, com_name, industry_id)
values ('최은지', 'ejchoi@nate.com', '01044445566', '1234', null, 1, '이지커머스', 4);
insert into user_tb(username, email, phone, password, img_url, role, com_name, industry_id)
values ('오세훈', 'shoh@nate.com', '01055556677', '1234', null, 1, '에버IT', 1);
insert into user_tb(username, email, phone, password, img_url, role, com_name, industry_id)
values ('강채은', 'cekang@nate.com', '01066667788', '1234', null, 1, '그림솔루션', 5);

-- 채용 공고 테이블 dummy
INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('Spring 백엔드 개발자 모집', 6, '1년 이상', '대졸 이상',
        '정규직', 3,
        'REST API 개발 및 유지보수$기존 서비스 코드 리팩토링$MySQL 기반 데이터 처리$협업을 위한 문서화',
        'Java/Spring 경력 1년 이상$MySQL 경험 우대$협업 및 커뮤니케이션 능력$Git 사용 가능자',
        4200, '09:30 ~ 18:30', '서울특별시 강남구', '2025-05-31', NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('프론트엔드 React 개발자 채용', 6, '신입~3년', '무관',
        '정규직', 2,
        '웹 서비스 프론트 개발$반응형 UI 구현$API 연동 작업$컴포넌트 단위 개발 및 유지보수',
        'React 사용 가능자$포트폴리오 필수$HTML/CSS/JS 기본 지식$Git 사용 가능자',
        3800, '10:00 ~ 19:00', '서울특별시 마포구', '2025-06-15', NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('신입 백엔드 인턴 모집', 6, '신입', '초대졸 이상',
        '인턴', 2,
        'Spring 기반 서비스 API 개발 및 문서화$기초적인 테스트 코드 작성$코드 리뷰 참여$팀 프로젝트 참여',
        '개발 열정$깃허브 활동 확인 예정$기초적인 Java/Spring 이해$학습 능력',
        2500, '09:00 ~ 17:00', '서울특별시 종로구', '2025-06-01', NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('데이터 분석가 채용', 6, '1년 이상', '대졸 이상',
        '정규직', 1,
        '데이터 시각화 및 분석$비즈니스 인사이트 도출$통계 기반 리포트 작성$대시보드 개발 및 유지',
        'Python, Pandas 경험$SQL 활용 가능자$통계적 분석 이해$문제 해결 능력',
        4500, '09:00 ~ 18:00', '서울특별시 성동구', '2025-06-30', NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('AI 엔지니어 팀원 채용합니다', 6, '3년 이상', '학력무관', '정규직', 5,
        'AWS 클라우드 인프라 관리', 'Python, Pandas 경험자$SQL 활용 가능자', 4649, '09:00 ~ 18:00', '서울특별시 강서구', '2025-08-17', NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('함께 성장할 DevOps 엔지니어 구인', 6, '1년 이상', '대졸 이상', '인턴', 9,
        'React 기반 웹 서비스 개발$UI/UX 개선 작업', '정보보안 자격증 보유자 우대', 3429, '09:00 ~ 18:00', '서울특별시 마포구', '2025-06-06', NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('신입 기획자 채용', 6, '신입', '학력무관', '정규직', 15,
        'React 기반 웹 서비스 개발$UI/UX 개선 작업', 'Tensorflow, PyTorch 경험자', 3486, '09:00 ~ 18:00', '서울특별시 용산구', '2025-07-10',
        NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('UX 디자이너 팀원 채용합니다', 6, '3년 이상', '학력무관', '계약직', 13,
        'Spring 기반 서비스 API 개발$DB 관리 및 최적화', 'iOS/Android 앱 개발 경험자', 3608, '09:00 ~ 18:00', '서울특별시 강서구', '2025-07-15',
        NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('신입 QA 엔지니어 채용', 6, '신입', '대졸 이상', '인턴', 8,
        '머신러닝 모델 개발 및 최적화', 'Python, Pandas 경험자$SQL 활용 가능자', 4878, '09:00 ~ 18:00', '서울특별시 마포구', '2025-06-05', NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('신입 데이터 분석가 채용', 6, '신입', '대졸 이상', '인턴', 14,
        '머신러닝 모델 개발 및 최적화', 'iOS/Android 앱 개발 경험자', 4732, '09:00 ~ 18:00', '서울특별시 강남구', '2025-07-04', NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('데이터 분석가 팀원 채용합니다', 6, '3년 이상', '초대졸 이상', '정규직', 14,
        '모바일 앱 개발 및 유지보수', 'Node.js 경험자$AWS 환경 경험자', 5073, '09:00 ~ 18:00', '서울특별시 성동구', '2025-06-01', NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('백엔드 개발자 팀원 채용합니다', 6, '3년 이상', '초대졸 이상', '인턴', 1,
        'Python을 이용한 데이터 분석 및 리포트 작성', 'Node.js 경험자$AWS 환경 경험자', 5740, '09:00 ~ 18:00', '서울특별시 종로구', '2025-08-18',
        NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('WWY에서 DevOps 엔지니어 찾습니다', 6, '3년 이상', '초대졸 이상', '정규직', 9,
        '서비스 테스트 및 품질 관리', 'Python, Pandas 경험자$SQL 활용 가능자', 4043, '09:00 ~ 18:00', '서울특별시 종로구', '2025-07-26', NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('보안 엔지니어 팀원 채용합니다', 6, '신입', '초대졸 이상', '정규직', 10,
        '모바일 앱 개발 및 유지보수', 'iOS/Android 앱 개발 경험자', 4593, '09:00 ~ 18:00', '서울특별시 은평구', '2025-08-01', NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('웹 프론트엔드 인턴 채용', 7, '신입', '학력무관', '인턴', 3,
        'React 기반 UI 개발 및 유지보수', '포트폴리오 제출 필수$협업 경험 우대', 2200,
        '10:00 ~ 18:00', '서울특별시 강남구', '2025-06-10', NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('데이터 분석 인턴 모집', 7, '신입', '대졸 이상', '인턴', 4,
        'Python을 이용한 데이터 수집 및 시각화', 'SQL, Pandas 사용 경험자 우대', 2300,
        '09:30 ~ 18:30', '서울특별시 마포구', '2025-05-20', NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('3년 이상 풀스택 개발자 채용', 7, '3년 이상', '초대졸 이상', '정규직', 3,
        '보안 시스템 설계 및 운영', 'iOS/Android 앱 개발 경험자', 3870, '09:00 ~ 18:00', '서울특별시 마포구', '2025-06-07', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('HOG에서 데이터 분석가 찾습니다', 7, '1년 이상', '학력무관', '정규직', 14,
        'Python을 이용한 데이터 분석 및 리포트 작성', 'Tensorflow, PyTorch 경험자', 4225, '09:00 ~ 18:00', '서울특별시 노원구', '2025-08-06',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('백엔드 개발자 팀원 채용합니다', 7, '1년 이상', '대졸 이상', '계약직', 1,
        'Spring 기반 서비스 API 개발$DB 관리 및 최적화', 'iOS/Android 앱 개발 경험자', 3345, '09:00 ~ 18:00', '서울특별시 중구', '2025-08-04',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('HOG에서 UX 디자이너 찾습니다', 7, '1년 이상', '대졸 이상', '정규직', 13,
        'React 기반 웹 서비스 개발$UI/UX 개선 작업', 'AWS, Docker 경험자', 5449, '09:00 ~ 18:00', '서울특별시 마포구', '2025-06-14', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('HOG에서 UX 디자이너 찾습니다', 7, '1년 이상', '대졸 이상', '인턴', 13,
        'AWS 클라우드 인프라 관리', 'Python, Pandas 경험자$SQL 활용 가능자', 2776, '09:00 ~ 18:00', '서울특별시 성동구', '2025-08-02', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('보안 엔지니어 팀원 채용합니다', 7, '1년 이상', '학력무관', '인턴', 10,
        '서비스 테스트 및 품질 관리', 'AWS, Docker 경험자', 4941, '09:00 ~ 18:00', '서울특별시 중구', '2025-07-11', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('데이터 분석가 신입/경력자 모집', 7, '1년 이상', '대졸 이상', '인턴', 14,
        'AWS 클라우드 인프라 관리', 'Java/Spring 경험 필수$MySQL 경험 우대', 5980, '09:00 ~ 18:00', '서울특별시 강남구', '2025-06-19', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('신입 프로덕트 매니저 채용', 7, '신입', '학력무관', '인턴', 11,
        'Node.js 백엔드 서비스 구축$서버 관리 및 유지보수', 'Python, Pandas 경험자$SQL 활용 가능자', 5178, '09:00 ~ 18:00', '서울특별시 종로구',
        '2025-08-12', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('데이터 분석가 팀원 채용합니다', 7, '1년 이상', '대졸 이상', '정규직', 14,
        '모바일 앱 개발 및 유지보수', 'HTML5, CSS3, Javascript 능숙자', 4512, '09:00 ~ 18:00', '서울특별시 용산구', '2025-07-08', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('QA 엔지니어 신입/경력자 모집', 7, '신입', '초대졸 이상', '계약직', 8,
        'React 기반 웹 서비스 개발$UI/UX 개선 작업', 'HTML5, CSS3, Javascript 능숙자', 3870, '09:00 ~ 18:00', '서울특별시 은평구',
        '2025-07-12', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('풀스택 개발자 팀원 채용합니다', 8, '1년 이상', '초대졸 이상', '정규직', 3,
        '머신러닝 모델 개발 및 최적화', 'Java/Spring 경험 필수$MySQL 경험 우대', 5365, '09:00 ~ 18:00', '서울특별시 마포구', '2025-06-10', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('백엔드 개발자 신입/경력자 모집', 8, '신입', '대졸 이상', '정규직', 1,
        'Node.js 백엔드 서비스 구축$서버 관리 및 유지보수', 'AWS, Docker 경험자', 4881, '09:00 ~ 18:00', '서울특별시 강서구', '2025-07-17', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('프론트엔드 개발자 신입/경력자 모집', 8, '3년 이상', '학력무관', '인턴', 2,
        'React 기반 웹 서비스 개발$UI/UX 개선 작업', 'Tensorflow, PyTorch 경험자', 4597, '09:00 ~ 18:00', '서울특별시 중구', '2025-06-02',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('UI 디자이너 팀원 채용합니다', 8, '신입', '대졸 이상', '정규직', 12,
        'Node.js 백엔드 서비스 구축$서버 관리 및 유지보수', 'iOS/Android 앱 개발 경험자', 5790, '09:00 ~ 18:00', '서울특별시 은평구', '2025-08-24',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('에이펙스 테크놀로지에서 UX 디자이너 찾습니다', 8, '1년 이상', '학력무관', '정규직', 13,
        '머신러닝 모델 개발 및 최적화', 'Tensorflow, PyTorch 경험자', 3379, '09:00 ~ 18:00', '서울특별시 마포구', '2025-08-08', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('보안 엔지니어 팀원 채용합니다', 8, '1년 이상', '학력무관', '인턴', 10,
        'Python을 이용한 데이터 분석 및 리포트 작성', 'Java/Spring 경험 필수$MySQL 경험 우대', 3370, '09:00 ~ 18:00', '서울특별시 노원구',
        '2025-08-25', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('프론트엔드 개발자 팀원 채용합니다', 8, '신입', '학력무관', '계약직', 2,
        '머신러닝 모델 개발 및 최적화', '정보보안 자격증 보유자 우대', 5543, '09:00 ~ 18:00', '서울특별시 종로구', '2025-08-10', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('함께 성장할 DevOps 엔지니어 구인', 8, '3년 이상', '학력무관', '계약직', 9,
        '모바일 앱 개발 및 유지보수', 'iOS/Android 앱 개발 경험자', 4554, '09:00 ~ 18:00', '서울특별시 중구', '2025-07-27', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('웹 퍼블리셔 신입/경력자 모집', 8, '신입', '대졸 이상', '계약직', 6,
        'Node.js 백엔드 서비스 구축$서버 관리 및 유지보수', 'iOS/Android 앱 개발 경험자', 2899, '09:00 ~ 18:00', '서울특별시 강서구', '2025-08-02',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('함께 성장할 풀스택 개발자 구인', 8, '3년 이상', '대졸 이상', '계약직', 3,
        'React 기반 웹 서비스 개발$UI/UX 개선 작업', 'iOS/Android 앱 개발 경험자', 2833, '09:00 ~ 18:00', '서울특별시 마포구', '2025-08-10',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('신입 데이터 엔지니어 채용', 9, '신입', '대졸 이상', '정규직', 4,
        'Spring 기반 서비스 API 개발$DB 관리 및 최적화', 'iOS/Android 앱 개발 경험자', 4910, '09:00 ~ 18:00', '서울특별시 용산구', '2025-07-17',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('신입 AI 엔지니어 채용', 9, '신입', '대졸 이상', '인턴', 5,
        '보안 시스템 설계 및 운영', 'HTML5, CSS3, Javascript 능숙자', 4283, '09:00 ~ 18:00', '서울특별시 마포구', '2025-07-12', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('3년 이상 DevOps 엔지니어 채용', 9, '3년 이상', '대졸 이상', '정규직', 9,
        'Spring 기반 서비스 API 개발$DB 관리 및 최적화', 'Node.js 경험자$AWS 환경 경험자', 4072, '09:00 ~ 18:00', '서울특별시 노원구', '2025-07-14',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('UX 디자이너 신입/경력자 모집', 9, '3년 이상', '대졸 이상', '인턴', 13,
        '머신러닝 모델 개발 및 최적화', 'QA 경험자 우대$커뮤니케이션 능력', 3188, '09:00 ~ 18:00', '서울특별시 강서구', '2025-07-06', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('프론트엔드 개발자 신입/경력자 모집', 9, '1년 이상', '초대졸 이상', '인턴', 2,
        'AWS 클라우드 인프라 관리', 'QA 경험자 우대$커뮤니케이션 능력', 4834, '09:00 ~ 18:00', '서울특별시 은평구', '2025-07-16', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('QA 엔지니어 신입/경력자 모집', 9, '신입', '학력무관', '인턴', 8,
        'Node.js 백엔드 서비스 구축$서버 관리 및 유지보수', 'Node.js 경험자$AWS 환경 경험자', 3145, '09:00 ~ 18:00', '서울특별시 강서구', '2025-07-25',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('수진소프트에서 보안 엔지니어 찾습니다', 9, '3년 이상', '학력무관', '계약직', 10,
        'React 기반 웹 서비스 개발$UI/UX 개선 작업', 'HTML/CSS 능숙자$React 경험자 우대', 2630, '09:00 ~ 18:00', '서울특별시 강서구', '2025-07-20',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('함께 성장할 풀스택 개발자 구인', 9, '3년 이상', '학력무관', '계약직', 3,
        'Node.js 백엔드 서비스 구축$서버 관리 및 유지보수', 'HTML5, CSS3, Javascript 능숙자', 4308, '09:00 ~ 18:00', '서울특별시 노원구',
        '2025-08-18', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('앱 개발자 신입/경력자 모집', 9, '3년 이상', '대졸 이상', '계약직', 7,
        'Node.js 백엔드 서비스 구축$서버 관리 및 유지보수', 'Python, Pandas 경험자$SQL 활용 가능자', 4784, '09:00 ~ 18:00', '서울특별시 성동구',
        '2025-07-13', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('웹 퍼블리셔 신입/경력자 모집', 9, '3년 이상', '초대졸 이상', '계약직', 6,
        'Python을 이용한 데이터 분석 및 리포트 작성', 'AWS, Docker 경험자', 4532, '09:00 ~ 18:00', '서울특별시 중구', '2025-06-28', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('1년 이상 프로덕트 매니저 채용', 10, '1년 이상', '초대졸 이상', '인턴', 11,
        '머신러닝 모델 개발 및 최적화', 'Java/Spring 경험 필수$MySQL 경험 우대', 5701, '09:00 ~ 18:00', '서울특별시 강서구', '2025-06-09', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('3년 이상 앱 개발자 채용', 10, '3년 이상', '학력무관', '인턴', 7,
        '서비스 테스트 및 품질 관리', 'Java/Spring 경험 필수$MySQL 경험 우대', 5709, '09:00 ~ 18:00', '서울특별시 서초구', '2025-07-06', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('신입 DevOps 엔지니어 채용', 10, '신입', '대졸 이상', '인턴', 9,
        'Spring 기반 서비스 API 개발$DB 관리 및 최적화', 'Tensorflow, PyTorch 경험자', 5254, '09:00 ~ 18:00', '서울특별시 은평구', '2025-08-14',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('3년 이상 데이터 분석가 채용', 10, '3년 이상', '학력무관', '인턴', 14,
        'HTML/CSS 퍼블리싱$반응형 웹사이트 구축', 'HTML5, CSS3, Javascript 능숙자', 3438, '09:00 ~ 18:00', '서울특별시 강서구', '2025-08-05',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('데이터 엔지니어 신입/경력자 모집', 10, '3년 이상', '대졸 이상', '인턴', 4,
        '모바일 앱 개발 및 유지보수', 'HTML5, CSS3, Javascript 능숙자', 5761, '09:00 ~ 18:00', '서울특별시 노원구', '2025-07-14', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('프로덕트 매니저 팀원 채용합니다', 10, '3년 이상', '초대졸 이상', '계약직', 11,
        'Python을 이용한 데이터 분석 및 리포트 작성', 'Python, Pandas 경험자$SQL 활용 가능자', 5868, '09:00 ~ 18:00', '서울특별시 마포구',
        '2025-08-02', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('AI 엔지니어 신입/경력자 모집', 10, '3년 이상', '대졸 이상', '계약직', 5,
        'Node.js 백엔드 서비스 구축$서버 관리 및 유지보수', 'AWS, Docker 경험자', 5329, '09:00 ~ 18:00', '서울특별시 서초구', '2025-07-26', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('웹 퍼블리셔 팀원 채용합니다', 10, '신입', '초대졸 이상', '계약직', 6,
        'AWS 클라우드 인프라 관리', 'HTML/CSS 능숙자$React 경험자 우대', 2755, '09:00 ~ 18:00', '서울특별시 은평구', '2025-08-04', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('씨에스 솔루션즈에서 기획자 찾습니다', 10, '3년 이상', '학력무관', '인턴', 15,
        '머신러닝 모델 개발 및 최적화', '정보보안 자격증 보유자 우대', 3287, '09:00 ~ 18:00', '서울특별시 은평구', '2025-07-26', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('함께 성장할 데이터 엔지니어 구인', 10, '신입', '대졸 이상', '인턴', 4,
        'HTML/CSS 퍼블리싱$반응형 웹사이트 구축', 'iOS/Android 앱 개발 경험자', 3994, '09:00 ~ 18:00', '서울특별시 용산구', '2025-08-18', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('UX 디자이너 팀원 채용합니다', 11, '신입', '대졸 이상', '인턴', 13,
        'Spring 기반 서비스 API 개발$DB 관리 및 최적화', 'Node.js 경험자$AWS 환경 경험자', 4295, '09:00 ~ 18:00', '서울특별시 성동구', '2025-07-27',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('이지커머스에서 DevOps 엔지니어 찾습니다', 11, '신입', '대졸 이상', '정규직', 9,
        'React 기반 웹 서비스 개발$UI/UX 개선 작업', 'Node.js 경험자$AWS 환경 경험자', 4663, '09:00 ~ 18:00', '서울특별시 서초구', '2025-05-28',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('함께 성장할 QA 엔지니어 구인', 11, '1년 이상', '학력무관', '정규직', 8,
        'Python을 이용한 데이터 분석 및 리포트 작성', 'HTML5, CSS3, Javascript 능숙자', 3641, '09:00 ~ 18:00', '서울특별시 노원구', '2025-06-06',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('기획자 신입/경력자 모집', 11, '1년 이상', '학력무관', '정규직', 15,
        'React 기반 웹 서비스 개발$UI/UX 개선 작업', '정보보안 자격증 보유자 우대', 3666, '09:00 ~ 18:00', '서울특별시 성동구', '2025-06-27', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('이지커머스에서 데이터 엔지니어 찾습니다', 11, '신입', '학력무관', '계약직', 4,
        'Python을 이용한 데이터 분석 및 리포트 작성', 'Node.js 경험자$AWS 환경 경험자', 4156, '09:00 ~ 18:00', '서울특별시 성동구', '2025-06-21',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('이지커머스에서 DevOps 엔지니어 찾습니다', 11, '1년 이상', '학력무관', '정규직', 9,
        'Node.js 백엔드 서비스 구축$서버 관리 및 유지보수', 'Node.js 경험자$AWS 환경 경험자', 3209, '09:00 ~ 18:00', '서울특별시 성동구', '2025-06-06',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('UX 디자이너 신입/경력자 모집', 11, '1년 이상', '초대졸 이상', '인턴', 13,
        '보안 시스템 설계 및 운영', 'HTML/CSS 능숙자$React 경험자 우대', 3125, '09:00 ~ 18:00', '서울특별시 중구', '2025-07-07', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('웹 퍼블리셔 신입/경력자 모집', 11, '1년 이상', '초대졸 이상', '계약직', 6,
        'React 기반 웹 서비스 개발$UI/UX 개선 작업', 'iOS/Android 앱 개발 경험자', 2739, '09:00 ~ 18:00', '서울특별시 종로구', '2025-06-20',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('프론트엔드 개발자 신입/경력자 모집', 11, '신입', '대졸 이상', '정규직', 2,
        'AWS 클라우드 인프라 관리', 'Tensorflow, PyTorch 경험자', 3744, '09:00 ~ 18:00', '서울특별시 강서구', '2025-06-16', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('함께 성장할 DevOps 엔지니어 구인', 11, '1년 이상', '초대졸 이상', '인턴', 9,
        'Spring 기반 서비스 API 개발$DB 관리 및 최적화', 'Java/Spring 경험 필수$MySQL 경험 우대', 2736, '09:00 ~ 18:00', '서울특별시 중구',
        '2025-06-05', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('프론트엔드 개발자 신입/경력자 모집', 12, '1년 이상', '초대졸 이상', '계약직', 2,
        'AWS 클라우드 인프라 관리', 'iOS/Android 앱 개발 경험자', 5925, '09:00 ~ 18:00', '서울특별시 중구', '2025-08-06', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('에버IT에서 웹 퍼블리셔 찾습니다', 12, '신입', '대졸 이상', '인턴', 6,
        'Node.js 백엔드 서비스 구축$서버 관리 및 유지보수', 'Java/Spring 경험 필수$MySQL 경험 우대', 5910, '09:00 ~ 18:00', '서울특별시 노원구',
        '2025-06-05', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('에버IT에서 데이터 분석가 찾습니다', 12, '신입', '초대졸 이상', '계약직', 14,
        '서비스 테스트 및 품질 관리', 'HTML5, CSS3, Javascript 능숙자', 2927, '09:00 ~ 18:00', '서울특별시 용산구', '2025-07-14', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('함께 성장할 보안 엔지니어 구인', 12, '1년 이상', '초대졸 이상', '인턴', 10,
        'Python을 이용한 데이터 분석 및 리포트 작성', 'iOS/Android 앱 개발 경험자', 5805, '09:00 ~ 18:00', '서울특별시 종로구', '2025-06-03', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('에버IT에서 웹 퍼블리셔 찾습니다', 12, '3년 이상', '학력무관', '정규직', 6,
        '머신러닝 모델 개발 및 최적화', 'HTML5, CSS3, Javascript 능숙자', 3258, '09:00 ~ 18:00', '서울특별시 용산구', '2025-08-10', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('함께 성장할 앱 개발자 구인', 12, '1년 이상', '초대졸 이상', '계약직', 7,
        '모바일 앱 개발 및 유지보수', 'Python, Pandas 경험자$SQL 활용 가능자', 2933, '09:00 ~ 18:00', '서울특별시 마포구', '2025-06-27', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('데이터 엔지니어 팀원 채용합니다', 12, '1년 이상', '대졸 이상', '계약직', 4,
        'Python을 이용한 데이터 분석 및 리포트 작성', 'QA 경험자 우대$커뮤니케이션 능력', 3354, '09:00 ~ 18:00', '서울특별시 중구', '2025-06-06', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('3년 이상 앱 개발자 채용', 12, '3년 이상', '대졸 이상', '계약직', 7,
        '보안 시스템 설계 및 운영', 'Python, Pandas 경험자$SQL 활용 가능자', 2531, '09:00 ~ 18:00', '서울특별시 종로구', '2025-07-11', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('에버IT에서 앱 개발자 찾습니다', 12, '1년 이상', '대졸 이상', '인턴', 7,
        'Python을 이용한 데이터 분석 및 리포트 작성', 'Tensorflow, PyTorch 경험자', 5226, '09:00 ~ 18:00', '서울특별시 용산구', '2025-06-18',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('함께 성장할 AI 엔지니어 구인', 12, '신입', '대졸 이상', '정규직', 5,
        '보안 시스템 설계 및 운영', 'HTML/CSS 능숙자$React 경험자 우대', 4687, '09:00 ~ 18:00', '서울특별시 은평구', '2025-08-09', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('DevOps 엔지니어 팀원 채용합니다', 13, '신입', '대졸 이상', '인턴', 9,
        '머신러닝 모델 개발 및 최적화', 'iOS/Android 앱 개발 경험자', 5348, '09:00 ~ 18:00', '서울특별시 강남구', '2025-08-12', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('AI 엔지니어 팀원 채용합니다', 13, '신입', '학력무관', '계약직', 5,
        'Node.js 백엔드 서비스 구축$서버 관리 및 유지보수', 'HTML5, CSS3, Javascript 능숙자', 5557, '09:00 ~ 18:00', '서울특별시 마포구',
        '2025-08-15', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('함께 성장할 기획자 구인', 13, '신입', '학력무관', '정규직', 15,
        'AWS 클라우드 인프라 관리', 'Java/Spring 경험 필수$MySQL 경험 우대', 3897, '09:00 ~ 18:00', '서울특별시 강남구', '2025-08-07', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('신입 백엔드 개발자 채용', 13, '신입', '초대졸 이상', '정규직', 1,
        'Python을 이용한 데이터 분석 및 리포트 작성', 'AWS, Docker 경험자', 3572, '09:00 ~ 18:00', '서울특별시 종로구', '2025-07-03', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('신입 웹 퍼블리셔 채용', 13, '신입', '학력무관', '계약직', 6,
        'Spring 기반 서비스 API 개발$DB 관리 및 최적화', 'HTML/CSS 능숙자$React 경험자 우대', 4327, '09:00 ~ 18:00', '서울특별시 노원구',
        '2025-07-27', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('DevOps 엔지니어 팀원 채용합니다', 13, '3년 이상', '학력무관', '인턴', 9,
        'Spring 기반 서비스 API 개발$DB 관리 및 최적화', 'QA 경험자 우대$커뮤니케이션 능력', 4663, '09:00 ~ 18:00', '서울특별시 서초구', '2025-07-20',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('풀스택 개발자 신입/경력자 모집', 13, '신입', '대졸 이상', '인턴', 3,
        'Spring 기반 서비스 API 개발$DB 관리 및 최적화', 'AWS, Docker 경험자', 3375, '09:00 ~ 18:00', '서울특별시 노원구', '2025-07-09', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('그림솔루션에서 DevOps 엔지니어 찾습니다', 13, '3년 이상', '학력무관', '인턴', 9,
        'Spring 기반 서비스 API 개발$DB 관리 및 최적화', 'AWS, Docker 경험자', 4139, '09:00 ~ 18:00', '서울특별시 강남구', '2025-06-17', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('UI 디자이너 신입/경력자 모집', 13, '1년 이상', '대졸 이상', '정규직', 12,
        'AWS 클라우드 인프라 관리', 'HTML5, CSS3, Javascript 능숙자', 4436, '09:00 ~ 18:00', '서울특별시 노원구', '2025-06-04', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('데이터 분석가 팀원 채용합니다', 13, '1년 이상', '초대졸 이상', '정규직', 14,
        'Python을 이용한 데이터 분석 및 리포트 작성', '정보보안 자격증 보유자 우대', 4124, '09:00 ~ 18:00', '서울특별시 강서구', '2025-05-31', NULL);

-- 이력서 테이블 dummy
insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('백엔드 신입 개발자 지원서', 1, '컴퓨터공학 전공, 인턴 경험 3개월', '부산대학교 컴퓨터공학과 졸업', 1, '서울', '정보처리기사', '멋쟁이사자처럼 10기 활동', NULL);
insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('Spring 기반 개발 프로젝트 경험', 1, '팀 프로젝트 백엔드 담당', '부산대학교 컴퓨터공학과 졸업', 1, '경기', 'SQLD', '사이드 프로젝트 2건', NULL);
insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('AWS 클라우드 기반 백엔드 프로젝트', 1, 'AWS EC2 서버 구축 및 운영 경험', '부산대학교 컴퓨터공학과 졸업', 1, '부산', 'AWS Certified Developer',
        '교내 클라우드 해커톤 수상', NULL);
insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('Maven, Gradle 빌드 경험 중심 이력서', 1, 'Spring Boot 환경 구축 경험', '부산대학교 컴퓨터공학과 졸업', 1, '서울', '정보처리기사', '멋쟁이사자처럼 11기 활동',
        NULL);
insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('Redis, Kafka 활용 경험', 1, '대용량 데이터 처리 경험', '부산대학교 컴퓨터공학과 졸업', 3, '경기', 'SQLD', '사이드 프로젝트 리더 경험', NULL);
insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('스프링 시큐리티 인증 인가 구축', 1, 'JWT 인증 구현 경험', '부산대학교 컴퓨터공학과 졸업', 1, '대구', '정보보안기사', '보안 동아리 활동', NULL);

insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('프론트엔드 포지션 지원 이력서', 2, 'React 학습 및 개인 포트폴리오 제작', '동서대학교 미디어학부 재학', 2, '서울', '웹디자인 기능사', 'UX/UI 디자인 스터디', NULL);
insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('웹 퍼블리셔 + 프론트엔드 복합 지원서', 2, 'HTML/CSS 퍼블리싱 1년 경험', '동서대학교 미디어학부 재학', 2, '인천', 'GTQ 1급', '디자인 공모전 참가', NULL);
insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('Figma 활용한 UX/UI 디자인 포트폴리오', 2, '프로덕트 디자인 경험', '동서대학교 미디어학부 재학', 2, '서울', 'UX 디자인 전문가 과정', 'UX 캠프 참가', NULL);
insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('Vue.js SPA 프로젝트 개발 이력서', 2, 'Vue 3 Composition API 사용 경험', '동서대학교 미디어학부 재학', 3, '경기', '웹디자인 기능사',
        '프론트엔드 부트캠프 수료', NULL);
insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('반응형 웹 구축 프로젝트', 2, 'Bootstrap, Media Query 사용', '동서대학교 미디어학부 재학', 2, '부산', 'GTQ 1급', '디자인 공모전 입상', NULL);
insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('웹 표준, 접근성 준수 개발 이력서', 2, 'WAI-ARIA 적용 경험', '동서대학교 미디어학부 재학', 2, '대전', '웹 접근성 전문가', '웹 접근성 캠페인 참여', NULL);

insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('프론트와 백엔드를 넘나드는 풀스택 꿈나무', 3, 'Node.js와 Vue.js 활용 토이 프로젝트', '부경대학교 컴퓨터공학과 졸업', 3, '대전', '정보처리기사', '교내 개발동아리 회장',
        NULL);
insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('REST API 설계 및 DB 모델링 경험 중심', 3, 'Django + PostgreSQL 프로젝트', '부경대학교 컴퓨터공학과 졸업', 1, '부산', 'SQLD',
        '캡스톤디자인 우수상 수상', NULL);
insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('Vue.js 기반 대시보드 프로젝트 경험', 3, '고객관리 시스템 프론트 개발', '부경대학교 컴퓨터공학과 졸업', 3, '서울', '정보처리기사', '캡스톤디자인 최우수상', NULL);
insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('Node.js와 Express.js를 활용한 REST API', 3, '백엔드 API 설계 및 개발', '부경대학교 컴퓨터공학과 졸업', 1, '경기', 'SQLD', '교내 개발 경진대회 수상',
        NULL);
insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('MongoDB와 MySQL 데이터베이스 설계 경험', 3, 'NoSQL, RDBMS 비교 분석', '부경대학교 컴퓨터공학과 졸업', 1, '부산', 'DBA 전문가 과정', 'DB 모델링 스터디',
        NULL);
insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('PM 경험을 통한 협업 리더십 강조 이력서', 3, '애자일 방법론 기반 팀 프로젝트', '부경대학교 컴퓨터공학과 졸업', 3, '대전', 'PMP 준비 과정', 'PM 커뮤니티 활동', NULL);

insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('신입 백엔드 개발자 포지션 지원서', 4, 'Java, Spring Boot 집중 학습', '경성대학교 컴퓨터공학과 졸업', 1, '서울', '정보처리기사', '멋쟁이사자처럼 12기', NULL);
insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('DB 설계 및 최적화 경험', 4, 'MySQL 튜닝 경험', '경성대학교 컴퓨터공학과 졸업', 1, '부산', 'SQLD', 'DB 최적화 동아리 활동', NULL);
insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('AWS 인프라 구축 및 운영 경험', 4, 'EC2, RDS 세팅 경험', '경성대학교 컴퓨터공학과 졸업', 1, '경기', 'AWS Certified Cloud Practitioner',
        'AWS 해커톤 참가', NULL);
insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('Spring Security + OAuth2.0 로그인 구현', 4, '소셜 로그인 연동 경험', '경성대학교 컴퓨터공학과 졸업', 1, '서울', '정보보안기사', '보안 세미나 참석',
        NULL);
insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('ElasticSearch 연동 검색 서비스 개발', 4, '검색 최적화 경험', '경성대학교 컴퓨터공학과 졸업', 3, '대전', 'BigData 전문가 과정', '빅데이터 해커톤 참가',
        NULL);
insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('Redis 세션 클러스터링 프로젝트', 4, '분산 캐시 구축 경험', '경성대학교 컴퓨터공학과 졸업', 1, '부산', 'Redis 전문가 과정', 'Redis 활용 연구회', NULL);

insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('프론트엔드 신입 지원 이력서', 5, 'React, Next.js 기반 프로젝트', '경남대학교 미디어학부 재학', 2, '서울', '웹디자인 기능사', '웹 퍼블리싱 캠프 참가', NULL);
insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('UI/UX 포트폴리오 기반 이력서', 5, 'Adobe XD, Figma 작업 경험', '경남대학교 미디어학부 재학', 2, '경기', 'UX 전문가 과정', 'UI/UX 스터디 리더', NULL);
insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('Typescript 기반 React 개발 경험', 5, 'TSX 문법 적용 프로젝트 경험', '경남대학교 미디어학부 재학', 2, '부산', '웹프로그래밍 과정 수료', '웹앱 개발 경진대회',
        NULL);
insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('반응형 웹사이트 구축 실습 중심', 5, 'Bootstrap, TailwindCSS 활용', '경남대학교 미디어학부 재학', 2, '대전', '웹 접근성 인증', '반응형 웹사이트 공모전',
        NULL);
insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('React Native 앱 개발 경험', 5, '모바일 앱 클론코딩 프로젝트', '경남대학교 미디어학부 재학', 3, '서울', '앱 개발 전문가 과정', '모바일 해커톤 참가', NULL);
insert into resume_tb(title, user_id, exp, edu, job_id, location, qualified, activity, img_url)
values ('GraphQL API 연동 경험', 5, 'Apollo Client 사용 경험', '경남대학교 미디어학부 재학', 2, '경기', '프론트엔드 전문가 과정', 'GraphQL 세미나 참가',
        NULL);

-- 스크랩 테이블 dummy
insert into scrap_tb(user_id, employment_id, resume_id)
values (1, 1, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (1, 5, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (1, 8, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (1, 10, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (1, 11, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (1, 18, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (1, 21, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (1, 29, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (1, 32, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (1, 47, null);

insert into scrap_tb(user_id, employment_id, resume_id)
values (2, 5, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (2, 8, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (2, 12, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (2, 29, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (2, 41, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (2, 45, null);

insert into scrap_tb(user_id, employment_id, resume_id)
values (3, 3, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (3, 6, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (3, 9, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (3, 17, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (3, 26, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (3, 35, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (3, 41, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (3, 45, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (3, 51, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (3, 52, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (3, 64, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (3, 78, null);

insert into scrap_tb(user_id, employment_id, resume_id)
values (4, 3, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (4, 6, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (4, 9, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (4, 17, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (4, 26, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (4, 35, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (4, 41, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (4, 45, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (4, 51, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (4, 52, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (4, 64, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (4, 78, null);

insert into scrap_tb(user_id, employment_id, resume_id)
values (5, 3, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (5, 6, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (5, 9, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (5, 17, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (5, 26, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (5, 35, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (5, 41, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (5, 45, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (5, 51, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (5, 52, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (5, 64, null);
insert into scrap_tb(user_id, employment_id, resume_id)
values (5, 78, null);

insert into scrap_tb(user_id, employment_id, resume_id)
values (6, null, 5);
insert into scrap_tb(user_id, employment_id, resume_id)
values (6, null, 12);
insert into scrap_tb(user_id, employment_id, resume_id)
values (6, null, 22);
insert into scrap_tb(user_id, employment_id, resume_id)
values (6, null, 7);
insert into scrap_tb(user_id, employment_id, resume_id)
values (6, null, 18);
insert into scrap_tb(user_id, employment_id, resume_id)
values (6, null, 29);

insert into scrap_tb(user_id, employment_id, resume_id)
values (7, null, 9);
insert into scrap_tb(user_id, employment_id, resume_id)
values (7, null, 24);
insert into scrap_tb(user_id, employment_id, resume_id)
values (7, null, 1);
insert into scrap_tb(user_id, employment_id, resume_id)
values (7, null, 17);
insert into scrap_tb(user_id, employment_id, resume_id)
values (7, null, 6);
insert into scrap_tb(user_id, employment_id, resume_id)
values (7, null, 14);

insert into scrap_tb(user_id, employment_id, resume_id)
values (8, null, 11);
insert into scrap_tb(user_id, employment_id, resume_id)
values (8, null, 2);
insert into scrap_tb(user_id, employment_id, resume_id)
values (8, null, 30);
insert into scrap_tb(user_id, employment_id, resume_id)
values (8, null, 8);
insert into scrap_tb(user_id, employment_id, resume_id)
values (8, null, 26);
insert into scrap_tb(user_id, employment_id, resume_id)
values (8, null, 19);

insert into scrap_tb(user_id, employment_id, resume_id)
values (9, null, 3);
insert into scrap_tb(user_id, employment_id, resume_id)
values (9, null, 21);
insert into scrap_tb(user_id, employment_id, resume_id)
values (9, null, 10);
insert into scrap_tb(user_id, employment_id, resume_id)
values (9, null, 27);
insert into scrap_tb(user_id, employment_id, resume_id)
values (9, null, 16);
insert into scrap_tb(user_id, employment_id, resume_id)
values (9, null, 13);

insert into scrap_tb(user_id, employment_id, resume_id)
values (10, null, 4);
insert into scrap_tb(user_id, employment_id, resume_id)
values (10, null, 20);
insert into scrap_tb(user_id, employment_id, resume_id)
values (10, null, 28);
insert into scrap_tb(user_id, employment_id, resume_id)
values (10, null, 23);
insert into scrap_tb(user_id, employment_id, resume_id)
values (10, null, 1);
insert into scrap_tb(user_id, employment_id, resume_id)
values (10, null, 15);

insert into scrap_tb(user_id, employment_id, resume_id)
values (11, null, 25);
insert into scrap_tb(user_id, employment_id, resume_id)
values (11, null, 7);
insert into scrap_tb(user_id, employment_id, resume_id)
values (11, null, 17);
insert into scrap_tb(user_id, employment_id, resume_id)
values (11, null, 29);
insert into scrap_tb(user_id, employment_id, resume_id)
values (11, null, 18);
insert into scrap_tb(user_id, employment_id, resume_id)
values (11, null, 6);

insert into scrap_tb(user_id, employment_id, resume_id)
values (12, null, 2);
insert into scrap_tb(user_id, employment_id, resume_id)
values (12, null, 11);
insert into scrap_tb(user_id, employment_id, resume_id)
values (12, null, 8);
insert into scrap_tb(user_id, employment_id, resume_id)
values (12, null, 26);
insert into scrap_tb(user_id, employment_id, resume_id)
values (12, null, 24);
insert into scrap_tb(user_id, employment_id, resume_id)
values (12, null, 12);

insert into scrap_tb(user_id, employment_id, resume_id)
values (13, null, 5);
insert into scrap_tb(user_id, employment_id, resume_id)
values (13, null, 15);
insert into scrap_tb(user_id, employment_id, resume_id)
values (13, null, 20);
insert into scrap_tb(user_id, employment_id, resume_id)
values (13, null, 28);
insert into scrap_tb(user_id, employment_id, resume_id)
values (13, null, 9);
insert into scrap_tb(user_id, employment_id, resume_id)
values (13, null, 22);


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
-- 게시글 테이블 dummy (구직자 user_id 1~5)
insert into board_tb(title, content, user_id, created_at)
values ('첫 이직 준비 넘 힘든데 포폴 꼭 해야 하나요?', '첫 이직 준비 중인데 포트폴리오 꼭 만들어야 하나요 ㅠㅠ 아무리 해도 부족한 느낌이라 너무 스트레스받아요..', 1, now());
insert into board_tb(title, content, user_id, created_at)
values ('면접 때 연봉 얘기 꺼내는 타이밍.. 알려줘요ㅠ', '면접 막바지에 연봉 얘기 어떻게 꺼내야 자연스럽나요..? 저 혼자 고민하다 폭망할까봐 무섭습니다ㅠ', 2, now());
insert into board_tb(title, content, user_id, created_at)
values ('비전공자 개발자 가능성 있나요..?', '비전공자로 부트캠프 수료했는데 진짜 취업 가능할까요? 주변에 비전공자는 힘들다는 얘기만 들어서 너무 걱정돼요ㅠㅠ', 3, now());
insert into board_tb(title, content, user_id, created_at)
values ('신입 자기소개서 개막막해요 진짜', '자소서 쓸 때 뭘 강조해야 할지 모르겠어요... 그냥 지원 동기 적으라는데 뭘 써야 감동 줄 수 있을지 모르겠음 ㅠ', 4, now());
insert into board_tb(title, content, user_id, created_at)
values ('잡페어 가볼까 하는데.. 괜찮나요?', '잡페어 참가해볼까 고민중인데 진짜 도움 되나요? 시간 버리는 건 아닌지 궁금합니다 ㅠㅠ 가본 사람 후기좀 부탁드려요!', 5, now());

-- 게시글 테이블 dummy (기업회원 user_id 6~13)
insert into board_tb(title, content, user_id, created_at)
values ('신입 개발자 채용 시 가장 중요하게 보는 역량은?', '신입 채용 시 스펙보다는 실질적인 역량을 평가하려고 합니다. 실무 역량을 어떤 식으로 검증하는 게 좋을지 고민입니다.', 6, now());
insert into board_tb(title, content, user_id, created_at)
values ('인턴 채용 후 정규직 전환율 높이는 방법?', '인턴 채용은 많은데 정규직 전환까지 이어지는 경우가 드뭅니다. 전환율을 높이려면 어떤 관리 포인트가 필요한지 궁금합니다.', 7, now());
insert into board_tb(title, content, user_id, created_at)
values ('스타트업 기업 문화 매력 어필하는 방법?', '복지나 연봉이 대기업에 비해 약한 스타트업입니다. 대신 성장 기회를 어필하고 싶은데 효과적인 방법이 있을까요?', 8, now());
insert into board_tb(title, content, user_id, created_at)
values ('광고비 아껴서 채용 홍보하는 방법 있을까요?', '광고 예산이 한정되어 있는데 채용 공고를 널리 알릴 수 있는 방법이 있을지 고민하고 있습니다. 아이디어가 필요합니다.', 9, now());
insert into board_tb(title, content, user_id, created_at)
values ('중소기업 채용 어려운 이유, 어떻게 극복할까요?', '최근 구직자들의 지원율이 너무 낮아 고민입니다. 우리 같은 중소기업이 매력적으로 보일 방법이 궁금합니다.', 10, now());
insert into board_tb(title, content, user_id, created_at)
values ('비대면 면접 시 체크해야 할 부분?', '요즘 화상 면접을 주로 진행하는데, 지원자 평가 시 놓치기 쉬운 부분이 있을까 걱정입니다. 조언 부탁드립니다.', 11, now());
insert into board_tb(title, content, user_id, created_at)
values ('수습 기간 평가 기준 세우는 방법?', '수습 기간 종료 후 정규직 전환 여부를 판단할 때 명확한 기준을 세워야 할 것 같은데 어떻게 설정하는 게 좋을지 궁금합니다.', 12, now());
insert into board_tb(title, content, user_id, created_at)
values ('MZ세대 직원과 소통하는 방법 있을까요?', '최근 입사한 젊은 직원들과 소통하는 데 애를 먹고 있습니다. 요즘 세대에 맞는 소통법이 있을까요?', 13, now());
insert into board_tb(title, content, user_id, created_at)
values ('경력직 이직 시장 분위기 요즘 어떤가요?', '경력직 채용을 준비 중인데, 최근 이직 시장 동향이나 주요 트렌드가 어떻게 변했는지 궁금합니다.', 6, now());
insert into board_tb(title, content, user_id, created_at)
values ('AI 면접 시스템 도입해보신 분 있나요?', 'AI 면접을 도입하는 것을 검토하고 있습니다. 실제 도입한 기업의 경험담이나 주의사항을 듣고 싶습니다.', 7, now());

-- 댓글 테이블 dummy
insert into reply_tb(board_id, user_id, content, created_at)
values (1, 2, '저도 포폴 준비중인데 개빡세요ㅠㅠ 같이 힘내요!', now());
insert into reply_tb(board_id, user_id, content, created_at)
values (1, 3, '포폴은 진짜 필수 같아요 ㅠ 저 포폴 없어서 떨어진 적 있음..', now());
insert into reply_tb(board_id, user_id, content, created_at)
values (2, 1, '연봉 얘기 맨 마지막에 조심스럽게 꺼내는 게 무난해요!', now());
insert into reply_tb(board_id, user_id, content, created_at)
values (3, 4, '비전공자도 충분히 가능해요! 저도 비전공인데 붙음ㅎㅎ', now());
insert into reply_tb(board_id, user_id, content, created_at)
values (5, 2, '잡페어 사람 많긴 한데 잘 뚫으면 기회 있음! 가보셈 ㅋㅋ', now());
insert into reply_tb(board_id, user_id, content, created_at)
values (6, 6, '실무 역량 테스트를 간단하게라도 넣는 걸 추천합니다.', now());
insert into reply_tb(board_id, user_id, content, created_at)
values (8, 7, '성장 스토리를 강조하면 구직자들이 관심 갖더라고요.', now());
insert into reply_tb(board_id, user_id, content, created_at)
values (10, 8, '복지보다 워라밸을 강조하는 것도 좋은 방법입니다.', now());
insert into reply_tb(board_id, user_id, content, created_at)
values (15, 9, '비추합니다.현실적으로 변별력이 떨어져요', now());
insert into reply_tb(board_id, user_id, content, created_at)
values (15, 3, '저도 비추', now());
insert into reply_tb(board_id, user_id, content, created_at)
values (15, 13, '울회사는 이미 도입했는데 나쁘지 않은듯 합니다.', now());

-- 채용공고-스택 테이블 dummy
insert into employ_stack_tb(employment_id, skill)
values (1, 'java'),
       (1, 'spring boot'),
       (1, 'mysql'),

       (2, 'javascript'),
       (2, 'react'),
       (2, 'html'),

       (3, 'react'),
       (3, 'javascript'),
       (3, 'css'),

       (4, 'python'),
       (4, 'mysql'),
       (4, 'git'),

       (5, 'java'),
       (5, 'spring boot'),
       (5, 'git'),

       (6, 'python'),
       (6, 'mysql'),
       (6, 'git');

-- 이력서-스택 테이블 dummy
insert into resume_stack_tb(resume_id, skill)
values (1, 'java'),
       (1, 'spring boot'),
       (1, 'mysql'),

       (2, 'html'),
       (2, 'css'),
       (2, 'javascript'),

       (3, 'javascript'),
       (3, 'react'),
       (3, 'git'),

       (4, 'html'),
       (4, 'css'),
       (4, 'javascript'),

       (5, 'java'),
       (5, 'spring boot'),
       (5, 'mysql'),

       (6, 'python'),
       (6, 'mysql'),
       (6, 'git');