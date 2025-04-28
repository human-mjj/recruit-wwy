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
-- 추가 기업회원 더미 데이터 (기업회원 6명 추가)
insert into user_tb(username, email, phone, password, img_url, role, com_name, industry_id)
values ('이상훈', 'shlee@nate.com', '01011112222', '1234', null, 1, '이상훈테크', 2);
insert into user_tb(username, email, phone, password, img_url, role, com_name, industry_id)
values ('김수진', 'sjkim@nate.com', '01022223344', '1234', null, 1, '수진소프트', 3);
insert into user_tb(username, email, phone, password, img_url, role, com_name, industry_id)
values ('박정수', 'jspark@nate.com', '01033334455', '1234', null, 1, '정수솔루션', 2);
insert into user_tb(username, email, phone, password, img_url, role, com_name, industry_id)
values ('최은지', 'ejchoi@nate.com', '01044445566', '1234', null, 1, '은지네컴퍼니', 4);
insert into user_tb(username, email, phone, password, img_url, role, com_name, industry_id)
values ('오세훈', 'shoh@nate.com', '01055556677', '1234', null, 1, '세훈IT', 1);
insert into user_tb(username, email, phone, password, img_url, role, com_name, industry_id)
values ('강채은', 'cekang@nate.com', '01066667788', '1234', null, 1, '채은모바일', 5);

-- 채용 공고 테이블 dummy
INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('Spring 백엔드 개발자 모집', 4, '1년 이상', '대졸 이상',
        '정규직', 3,
        'REST API 개발 및 유지보수$기존 서비스 코드 리팩토링$MySQL 기반 데이터 처리$협업을 위한 문서화',
        'Java/Spring 경력 1년 이상$MySQL 경험 우대$협업 및 커뮤니케이션 능력$Git 사용 가능자',
        4200, '09:30 ~ 18:30', '서울특별시 강남구', '2025-05-31', NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('프론트엔드 React 개발자 채용', 4, '신입~3년', '무관',
        '정규직', 2,
        '웹 서비스 프론트 개발$반응형 UI 구현$API 연동 작업$컴포넌트 단위 개발 및 유지보수',
        'React 사용 가능자$포트폴리오 필수$HTML/CSS/JS 기본 지식$Git 사용 가능자',
        3800, '10:00 ~ 19:00', '서울특별시 마포구', '2025-06-15', NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('신입 백엔드 인턴 모집', 4, '신입', '초대졸 이상',
        '인턴', 2,
        'Spring 기반 서비스 API 개발 및 문서화$기초적인 테스트 코드 작성$코드 리뷰 참여$팀 프로젝트 참여',
        '개발 열정$깃허브 활동 확인 예정$기초적인 Java/Spring 이해$학습 능력',
        2500, '09:00 ~ 17:00', '서울특별시 종로구', '2025-06-01', NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('데이터 분석가 채용', 4, '1년 이상', '대졸 이상',
        '정규직', 1,
        '데이터 시각화 및 분석$비즈니스 인사이트 도출$통계 기반 리포트 작성$대시보드 개발 및 유지',
        'Python, Pandas 경험$SQL 활용 가능자$통계적 분석 이해$문제 해결 능력',
        4500, '09:00 ~ 18:00', '서울특별시 성동구', '2025-06-30', NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('AI 엔지니어 팀원 채용합니다', 4, '3년 이상', '학력무관', '정규직', 5,
        'AWS 클라우드 인프라 관리', 'Python, Pandas 경험자$SQL 활용 가능자', 4649, '09:00 ~ 18:00', '서울특별시 강서구', '2025-08-17', NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('함께 성장할 DevOps 엔지니어 구인', 4, '1년 이상', '대졸 이상', '인턴', 9,
        'React 기반 웹 서비스 개발$UI/UX 개선 작업', '정보보안 자격증 보유자 우대', 3429, '09:00 ~ 18:00', '서울특별시 마포구', '2025-06-06', NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('신입 기획자 채용', 4, '신입', '학력무관', '정규직', 15,
        'React 기반 웹 서비스 개발$UI/UX 개선 작업', 'Tensorflow, PyTorch 경험자', 3486, '09:00 ~ 18:00', '서울특별시 용산구', '2025-07-10',
        NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('UX 디자이너 팀원 채용합니다', 4, '3년 이상', '학력무관', '계약직', 13,
        'Spring 기반 서비스 API 개발$DB 관리 및 최적화', 'iOS/Android 앱 개발 경험자', 3608, '09:00 ~ 18:00', '서울특별시 강서구', '2025-07-15',
        NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('신입 QA 엔지니어 채용', 4, '신입', '대졸 이상', '인턴', 8,
        '머신러닝 모델 개발 및 최적화', 'Python, Pandas 경험자$SQL 활용 가능자', 4878, '09:00 ~ 18:00', '서울특별시 마포구', '2025-06-05', NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('신입 데이터 분석가 채용', 4, '신입', '대졸 이상', '인턴', 14,
        '머신러닝 모델 개발 및 최적화', 'iOS/Android 앱 개발 경험자', 4732, '09:00 ~ 18:00', '서울특별시 강남구', '2025-07-04', NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('데이터 분석가 팀원 채용합니다', 4, '3년 이상', '초대졸 이상', '정규직', 14,
        '모바일 앱 개발 및 유지보수', 'Node.js 경험자$AWS 환경 경험자', 5073, '09:00 ~ 18:00', '서울특별시 성동구', '2025-06-01', NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('백엔드 개발자 팀원 채용합니다', 4, '3년 이상', '초대졸 이상', '인턴', 1,
        'Python을 이용한 데이터 분석 및 리포트 작성', 'Node.js 경험자$AWS 환경 경험자', 5740, '09:00 ~ 18:00', '서울특별시 종로구', '2025-08-18',
        NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('WWY에서 DevOps 엔지니어 찾습니다', 4, '3년 이상', '초대졸 이상', '정규직', 9,
        '서비스 테스트 및 품질 관리', 'Python, Pandas 경험자$SQL 활용 가능자', 4043, '09:00 ~ 18:00', '서울특별시 종로구', '2025-07-26', NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('보안 엔지니어 팀원 채용합니다', 4, '신입', '초대졸 이상', '정규직', 10,
        '모바일 앱 개발 및 유지보수', 'iOS/Android 앱 개발 경험자', 4593, '09:00 ~ 18:00', '서울특별시 은평구', '2025-08-01', NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('웹 프론트엔드 인턴 채용', 5, '신입', '학력무관', '인턴', 3,
        'React 기반 UI 개발 및 유지보수', '포트폴리오 제출 필수$협업 경험 우대', 2200,
        '10:00 ~ 18:00', '서울특별시 강남구', '2025-06-10', NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('데이터 분석 인턴 모집', 5, '신입', '대졸 이상', '인턴', 4,
        'Python을 이용한 데이터 수집 및 시각화', 'SQL, Pandas 사용 경험자 우대', 2300,
        '09:30 ~ 18:30', '서울특별시 마포구', '2025-05-20', NULL);

INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('3년 이상 풀스택 개발자 채용', 5, '3년 이상', '초대졸 이상', '정규직', 3,
        '보안 시스템 설계 및 운영', 'iOS/Android 앱 개발 경험자', 3870, '09:00 ~ 18:00', '서울특별시 마포구', '2025-06-07', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('HOG에서 데이터 분석가 찾습니다', 5, '1년 이상', '학력무관', '정규직', 14,
        'Python을 이용한 데이터 분석 및 리포트 작성', 'Tensorflow, PyTorch 경험자', 4225, '09:00 ~ 18:00', '서울특별시 노원구', '2025-08-06',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('백엔드 개발자 팀원 채용합니다', 5, '1년 이상', '대졸 이상', '계약직', 1,
        'Spring 기반 서비스 API 개발$DB 관리 및 최적화', 'iOS/Android 앱 개발 경험자', 3345, '09:00 ~ 18:00', '서울특별시 중구', '2025-08-04',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('HOG에서 UX 디자이너 찾습니다', 5, '1년 이상', '대졸 이상', '정규직', 13,
        'React 기반 웹 서비스 개발$UI/UX 개선 작업', 'AWS, Docker 경험자', 5449, '09:00 ~ 18:00', '서울특별시 마포구', '2025-06-14', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('HOG에서 UX 디자이너 찾습니다', 5, '1년 이상', '대졸 이상', '인턴', 13,
        'AWS 클라우드 인프라 관리', 'Python, Pandas 경험자$SQL 활용 가능자', 2776, '09:00 ~ 18:00', '서울특별시 성동구', '2025-08-02', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('보안 엔지니어 팀원 채용합니다', 5, '1년 이상', '학력무관', '인턴', 10,
        '서비스 테스트 및 품질 관리', 'AWS, Docker 경험자', 4941, '09:00 ~ 18:00', '서울특별시 중구', '2025-07-11', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('데이터 분석가 신입/경력자 모집', 5, '1년 이상', '대졸 이상', '인턴', 14,
        'AWS 클라우드 인프라 관리', 'Java/Spring 경험 필수$MySQL 경험 우대', 5980, '09:00 ~ 18:00', '서울특별시 강남구', '2025-06-19', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('신입 프로덕트 매니저 채용', 5, '신입', '학력무관', '인턴', 11,
        'Node.js 백엔드 서비스 구축$서버 관리 및 유지보수', 'Python, Pandas 경험자$SQL 활용 가능자', 5178, '09:00 ~ 18:00', '서울특별시 종로구',
        '2025-08-12', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('데이터 분석가 팀원 채용합니다', 5, '1년 이상', '대졸 이상', '정규직', 14,
        '모바일 앱 개발 및 유지보수', 'HTML5, CSS3, Javascript 능숙자', 4512, '09:00 ~ 18:00', '서울특별시 용산구', '2025-07-08', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('QA 엔지니어 신입/경력자 모집', 5, '신입', '초대졸 이상', '계약직', 8,
        'React 기반 웹 서비스 개발$UI/UX 개선 작업', 'HTML5, CSS3, Javascript 능숙자', 3870, '09:00 ~ 18:00', '서울특별시 은평구',
        '2025-07-12', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('풀스택 개발자 팀원 채용합니다', 6, '1년 이상', '초대졸 이상', '정규직', 3,
        '머신러닝 모델 개발 및 최적화', 'Java/Spring 경험 필수$MySQL 경험 우대', 5365, '09:00 ~ 18:00', '서울특별시 마포구', '2025-06-10', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('백엔드 개발자 신입/경력자 모집', 6, '신입', '대졸 이상', '정규직', 1,
        'Node.js 백엔드 서비스 구축$서버 관리 및 유지보수', 'AWS, Docker 경험자', 4881, '09:00 ~ 18:00', '서울특별시 강서구', '2025-07-17', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('프론트엔드 개발자 신입/경력자 모집', 6, '3년 이상', '학력무관', '인턴', 2,
        'React 기반 웹 서비스 개발$UI/UX 개선 작업', 'Tensorflow, PyTorch 경험자', 4597, '09:00 ~ 18:00', '서울특별시 중구', '2025-06-02',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('UI 디자이너 팀원 채용합니다', 6, '신입', '대졸 이상', '정규직', 12,
        'Node.js 백엔드 서비스 구축$서버 관리 및 유지보수', 'iOS/Android 앱 개발 경험자', 5790, '09:00 ~ 18:00', '서울특별시 은평구', '2025-08-24',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('이상훈테크에서 UX 디자이너 찾습니다', 6, '1년 이상', '학력무관', '정규직', 13,
        '머신러닝 모델 개발 및 최적화', 'Tensorflow, PyTorch 경험자', 3379, '09:00 ~ 18:00', '서울특별시 마포구', '2025-08-08', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('보안 엔지니어 팀원 채용합니다', 6, '1년 이상', '학력무관', '인턴', 10,
        'Python을 이용한 데이터 분석 및 리포트 작성', 'Java/Spring 경험 필수$MySQL 경험 우대', 3370, '09:00 ~ 18:00', '서울특별시 노원구',
        '2025-08-25', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('프론트엔드 개발자 팀원 채용합니다', 6, '신입', '학력무관', '계약직', 2,
        '머신러닝 모델 개발 및 최적화', '정보보안 자격증 보유자 우대', 5543, '09:00 ~ 18:00', '서울특별시 종로구', '2025-08-10', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('함께 성장할 DevOps 엔지니어 구인', 6, '3년 이상', '학력무관', '계약직', 9,
        '모바일 앱 개발 및 유지보수', 'iOS/Android 앱 개발 경험자', 4554, '09:00 ~ 18:00', '서울특별시 중구', '2025-07-27', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('웹 퍼블리셔 신입/경력자 모집', 6, '신입', '대졸 이상', '계약직', 6,
        'Node.js 백엔드 서비스 구축$서버 관리 및 유지보수', 'iOS/Android 앱 개발 경험자', 2899, '09:00 ~ 18:00', '서울특별시 강서구', '2025-08-02',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('함께 성장할 풀스택 개발자 구인', 6, '3년 이상', '대졸 이상', '계약직', 3,
        'React 기반 웹 서비스 개발$UI/UX 개선 작업', 'iOS/Android 앱 개발 경험자', 2833, '09:00 ~ 18:00', '서울특별시 마포구', '2025-08-10',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('신입 데이터 엔지니어 채용', 7, '신입', '대졸 이상', '정규직', 4,
        'Spring 기반 서비스 API 개발$DB 관리 및 최적화', 'iOS/Android 앱 개발 경험자', 4910, '09:00 ~ 18:00', '서울특별시 용산구', '2025-07-17',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('신입 AI 엔지니어 채용', 7, '신입', '대졸 이상', '인턴', 5,
        '보안 시스템 설계 및 운영', 'HTML5, CSS3, Javascript 능숙자', 4283, '09:00 ~ 18:00', '서울특별시 마포구', '2025-07-12', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('3년 이상 DevOps 엔지니어 채용', 7, '3년 이상', '대졸 이상', '정규직', 9,
        'Spring 기반 서비스 API 개발$DB 관리 및 최적화', 'Node.js 경험자$AWS 환경 경험자', 4072, '09:00 ~ 18:00', '서울특별시 노원구', '2025-07-14',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('UX 디자이너 신입/경력자 모집', 7, '3년 이상', '대졸 이상', '인턴', 13,
        '머신러닝 모델 개발 및 최적화', 'QA 경험자 우대$커뮤니케이션 능력', 3188, '09:00 ~ 18:00', '서울특별시 강서구', '2025-07-06', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('프론트엔드 개발자 신입/경력자 모집', 7, '1년 이상', '초대졸 이상', '인턴', 2,
        'AWS 클라우드 인프라 관리', 'QA 경험자 우대$커뮤니케이션 능력', 4834, '09:00 ~ 18:00', '서울특별시 은평구', '2025-07-16', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('QA 엔지니어 신입/경력자 모집', 7, '신입', '학력무관', '인턴', 8,
        'Node.js 백엔드 서비스 구축$서버 관리 및 유지보수', 'Node.js 경험자$AWS 환경 경험자', 3145, '09:00 ~ 18:00', '서울특별시 강서구', '2025-07-25',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('수진소프트에서 보안 엔지니어 찾습니다', 7, '3년 이상', '학력무관', '계약직', 10,
        'React 기반 웹 서비스 개발$UI/UX 개선 작업', 'HTML/CSS 능숙자$React 경험자 우대', 2630, '09:00 ~ 18:00', '서울특별시 강서구', '2025-07-20',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('함께 성장할 풀스택 개발자 구인', 7, '3년 이상', '학력무관', '계약직', 3,
        'Node.js 백엔드 서비스 구축$서버 관리 및 유지보수', 'HTML5, CSS3, Javascript 능숙자', 4308, '09:00 ~ 18:00', '서울특별시 노원구',
        '2025-08-18', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('앱 개발자 신입/경력자 모집', 7, '3년 이상', '대졸 이상', '계약직', 7,
        'Node.js 백엔드 서비스 구축$서버 관리 및 유지보수', 'Python, Pandas 경험자$SQL 활용 가능자', 4784, '09:00 ~ 18:00', '서울특별시 성동구',
        '2025-07-13', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('웹 퍼블리셔 신입/경력자 모집', 7, '3년 이상', '초대졸 이상', '계약직', 6,
        'Python을 이용한 데이터 분석 및 리포트 작성', 'AWS, Docker 경험자', 4532, '09:00 ~ 18:00', '서울특별시 중구', '2025-06-28', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('1년 이상 프로덕트 매니저 채용', 8, '1년 이상', '초대졸 이상', '인턴', 11,
        '머신러닝 모델 개발 및 최적화', 'Java/Spring 경험 필수$MySQL 경험 우대', 5701, '09:00 ~ 18:00', '서울특별시 강서구', '2025-06-09', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('3년 이상 앱 개발자 채용', 8, '3년 이상', '학력무관', '인턴', 7,
        '서비스 테스트 및 품질 관리', 'Java/Spring 경험 필수$MySQL 경험 우대', 5709, '09:00 ~ 18:00', '서울특별시 서초구', '2025-07-06', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('신입 DevOps 엔지니어 채용', 8, '신입', '대졸 이상', '인턴', 9,
        'Spring 기반 서비스 API 개발$DB 관리 및 최적화', 'Tensorflow, PyTorch 경험자', 5254, '09:00 ~ 18:00', '서울특별시 은평구', '2025-08-14',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('3년 이상 데이터 분석가 채용', 8, '3년 이상', '학력무관', '인턴', 14,
        'HTML/CSS 퍼블리싱$반응형 웹사이트 구축', 'HTML5, CSS3, Javascript 능숙자', 3438, '09:00 ~ 18:00', '서울특별시 강서구', '2025-08-05',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('데이터 엔지니어 신입/경력자 모집', 8, '3년 이상', '대졸 이상', '인턴', 4,
        '모바일 앱 개발 및 유지보수', 'HTML5, CSS3, Javascript 능숙자', 5761, '09:00 ~ 18:00', '서울특별시 노원구', '2025-07-14', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('프로덕트 매니저 팀원 채용합니다', 8, '3년 이상', '초대졸 이상', '계약직', 11,
        'Python을 이용한 데이터 분석 및 리포트 작성', 'Python, Pandas 경험자$SQL 활용 가능자', 5868, '09:00 ~ 18:00', '서울특별시 마포구',
        '2025-08-02', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('AI 엔지니어 신입/경력자 모집', 8, '3년 이상', '대졸 이상', '계약직', 5,
        'Node.js 백엔드 서비스 구축$서버 관리 및 유지보수', 'AWS, Docker 경험자', 5329, '09:00 ~ 18:00', '서울특별시 서초구', '2025-07-26', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('웹 퍼블리셔 팀원 채용합니다', 8, '신입', '초대졸 이상', '계약직', 6,
        'AWS 클라우드 인프라 관리', 'HTML/CSS 능숙자$React 경험자 우대', 2755, '09:00 ~ 18:00', '서울특별시 은평구', '2025-08-04', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('정수솔루션에서 기획자 찾습니다', 8, '3년 이상', '학력무관', '인턴', 15,
        '머신러닝 모델 개발 및 최적화', '정보보안 자격증 보유자 우대', 3287, '09:00 ~ 18:00', '서울특별시 은평구', '2025-07-26', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('함께 성장할 데이터 엔지니어 구인', 8, '신입', '대졸 이상', '인턴', 4,
        'HTML/CSS 퍼블리싱$반응형 웹사이트 구축', 'iOS/Android 앱 개발 경험자', 3994, '09:00 ~ 18:00', '서울특별시 용산구', '2025-08-18', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('UX 디자이너 팀원 채용합니다', 9, '신입', '대졸 이상', '인턴', 13,
        'Spring 기반 서비스 API 개발$DB 관리 및 최적화', 'Node.js 경험자$AWS 환경 경험자', 4295, '09:00 ~ 18:00', '서울특별시 성동구', '2025-07-27',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('은지네컴퍼니에서 DevOps 엔지니어 찾습니다', 9, '신입', '대졸 이상', '정규직', 9,
        'React 기반 웹 서비스 개발$UI/UX 개선 작업', 'Node.js 경험자$AWS 환경 경험자', 4663, '09:00 ~ 18:00', '서울특별시 서초구', '2025-05-28',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('함께 성장할 QA 엔지니어 구인', 9, '1년 이상', '학력무관', '정규직', 8,
        'Python을 이용한 데이터 분석 및 리포트 작성', 'HTML5, CSS3, Javascript 능숙자', 3641, '09:00 ~ 18:00', '서울특별시 노원구', '2025-06-06',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('기획자 신입/경력자 모집', 9, '1년 이상', '학력무관', '정규직', 15,
        'React 기반 웹 서비스 개발$UI/UX 개선 작업', '정보보안 자격증 보유자 우대', 3666, '09:00 ~ 18:00', '서울특별시 성동구', '2025-06-27', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('은지네컴퍼니에서 데이터 엔지니어 찾습니다', 9, '신입', '학력무관', '계약직', 4,
        'Python을 이용한 데이터 분석 및 리포트 작성', 'Node.js 경험자$AWS 환경 경험자', 4156, '09:00 ~ 18:00', '서울특별시 성동구', '2025-06-21',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('은지네컴퍼니에서 DevOps 엔지니어 찾습니다', 9, '1년 이상', '학력무관', '정규직', 9,
        'Node.js 백엔드 서비스 구축$서버 관리 및 유지보수', 'Node.js 경험자$AWS 환경 경험자', 3209, '09:00 ~ 18:00', '서울특별시 성동구', '2025-06-06',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('UX 디자이너 신입/경력자 모집', 9, '1년 이상', '초대졸 이상', '인턴', 13,
        '보안 시스템 설계 및 운영', 'HTML/CSS 능숙자$React 경험자 우대', 3125, '09:00 ~ 18:00', '서울특별시 중구', '2025-07-07', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('웹 퍼블리셔 신입/경력자 모집', 9, '1년 이상', '초대졸 이상', '계약직', 6,
        'React 기반 웹 서비스 개발$UI/UX 개선 작업', 'iOS/Android 앱 개발 경험자', 2739, '09:00 ~ 18:00', '서울특별시 종로구', '2025-06-20',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('프론트엔드 개발자 신입/경력자 모집', 9, '신입', '대졸 이상', '정규직', 2,
        'AWS 클라우드 인프라 관리', 'Tensorflow, PyTorch 경험자', 3744, '09:00 ~ 18:00', '서울특별시 강서구', '2025-06-16', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('함께 성장할 DevOps 엔지니어 구인', 9, '1년 이상', '초대졸 이상', '인턴', 9,
        'Spring 기반 서비스 API 개발$DB 관리 및 최적화', 'Java/Spring 경험 필수$MySQL 경험 우대', 2736, '09:00 ~ 18:00', '서울특별시 중구',
        '2025-06-05', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('프론트엔드 개발자 신입/경력자 모집', 10, '1년 이상', '초대졸 이상', '계약직', 2,
        'AWS 클라우드 인프라 관리', 'iOS/Android 앱 개발 경험자', 5925, '09:00 ~ 18:00', '서울특별시 중구', '2025-08-06', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('세훈IT에서 웹 퍼블리셔 찾습니다', 10, '신입', '대졸 이상', '인턴', 6,
        'Node.js 백엔드 서비스 구축$서버 관리 및 유지보수', 'Java/Spring 경험 필수$MySQL 경험 우대', 5910, '09:00 ~ 18:00', '서울특별시 노원구',
        '2025-06-05', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('세훈IT에서 데이터 분석가 찾습니다', 10, '신입', '초대졸 이상', '계약직', 14,
        '서비스 테스트 및 품질 관리', 'HTML5, CSS3, Javascript 능숙자', 2927, '09:00 ~ 18:00', '서울특별시 용산구', '2025-07-14', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('함께 성장할 보안 엔지니어 구인', 10, '1년 이상', '초대졸 이상', '인턴', 10,
        'Python을 이용한 데이터 분석 및 리포트 작성', 'iOS/Android 앱 개발 경험자', 5805, '09:00 ~ 18:00', '서울특별시 종로구', '2025-06-03', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('세훈IT에서 웹 퍼블리셔 찾습니다', 10, '3년 이상', '학력무관', '정규직', 6,
        '머신러닝 모델 개발 및 최적화', 'HTML5, CSS3, Javascript 능숙자', 3258, '09:00 ~ 18:00', '서울특별시 용산구', '2025-08-10', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('함께 성장할 앱 개발자 구인', 10, '1년 이상', '초대졸 이상', '계약직', 7,
        '모바일 앱 개발 및 유지보수', 'Python, Pandas 경험자$SQL 활용 가능자', 2933, '09:00 ~ 18:00', '서울특별시 마포구', '2025-06-27', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('데이터 엔지니어 팀원 채용합니다', 10, '1년 이상', '대졸 이상', '계약직', 4,
        'Python을 이용한 데이터 분석 및 리포트 작성', 'QA 경험자 우대$커뮤니케이션 능력', 3354, '09:00 ~ 18:00', '서울특별시 중구', '2025-06-06', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('3년 이상 앱 개발자 채용', 10, '3년 이상', '대졸 이상', '계약직', 7,
        '보안 시스템 설계 및 운영', 'Python, Pandas 경험자$SQL 활용 가능자', 2531, '09:00 ~ 18:00', '서울특별시 종로구', '2025-07-11', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('세훈IT에서 앱 개발자 찾습니다', 10, '1년 이상', '대졸 이상', '인턴', 7,
        'Python을 이용한 데이터 분석 및 리포트 작성', 'Tensorflow, PyTorch 경험자', 5226, '09:00 ~ 18:00', '서울특별시 용산구', '2025-06-18',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('함께 성장할 AI 엔지니어 구인', 10, '신입', '대졸 이상', '정규직', 5,
        '보안 시스템 설계 및 운영', 'HTML/CSS 능숙자$React 경험자 우대', 4687, '09:00 ~ 18:00', '서울특별시 은평구', '2025-08-09', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('DevOps 엔지니어 팀원 채용합니다', 11, '신입', '대졸 이상', '인턴', 9,
        '머신러닝 모델 개발 및 최적화', 'iOS/Android 앱 개발 경험자', 5348, '09:00 ~ 18:00', '서울특별시 강남구', '2025-08-12', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('AI 엔지니어 팀원 채용합니다', 11, '신입', '학력무관', '계약직', 5,
        'Node.js 백엔드 서비스 구축$서버 관리 및 유지보수', 'HTML5, CSS3, Javascript 능숙자', 5557, '09:00 ~ 18:00', '서울특별시 마포구',
        '2025-08-15', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('함께 성장할 기획자 구인', 11, '신입', '학력무관', '정규직', 15,
        'AWS 클라우드 인프라 관리', 'Java/Spring 경험 필수$MySQL 경험 우대', 3897, '09:00 ~ 18:00', '서울특별시 강남구', '2025-08-07', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('신입 백엔드 개발자 채용', 11, '신입', '초대졸 이상', '정규직', 1,
        'Python을 이용한 데이터 분석 및 리포트 작성', 'AWS, Docker 경험자', 3572, '09:00 ~ 18:00', '서울특별시 종로구', '2025-07-03', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('신입 웹 퍼블리셔 채용', 11, '신입', '학력무관', '계약직', 6,
        'Spring 기반 서비스 API 개발$DB 관리 및 최적화', 'HTML/CSS 능숙자$React 경험자 우대', 4327, '09:00 ~ 18:00', '서울특별시 노원구',
        '2025-07-27', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('DevOps 엔지니어 팀원 채용합니다', 11, '3년 이상', '학력무관', '인턴', 9,
        'Spring 기반 서비스 API 개발$DB 관리 및 최적화', 'QA 경험자 우대$커뮤니케이션 능력', 4663, '09:00 ~ 18:00', '서울특별시 서초구', '2025-07-20',
        NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('풀스택 개발자 신입/경력자 모집', 11, '신입', '대졸 이상', '인턴', 3,
        'Spring 기반 서비스 API 개발$DB 관리 및 최적화', 'AWS, Docker 경험자', 3375, '09:00 ~ 18:00', '서울특별시 노원구', '2025-07-09', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('채은모바일에서 DevOps 엔지니어 찾습니다', 11, '3년 이상', '학력무관', '인턴', 9,
        'Spring 기반 서비스 API 개발$DB 관리 및 최적화', 'AWS, Docker 경험자', 4139, '09:00 ~ 18:00', '서울특별시 강남구', '2025-06-17', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('UI 디자이너 신입/경력자 모집', 11, '1년 이상', '대졸 이상', '정규직', 12,
        'AWS 클라우드 인프라 관리', 'HTML5, CSS3, Javascript 능숙자', 4436, '09:00 ~ 18:00', '서울특별시 노원구', '2025-06-04', NULL);


INSERT INTO employment_tb (title, user_id, exp, edu, shift, job_id, duty, qualification, sal, working_time, location,
                           end_date, img_url)
VALUES ('데이터 분석가 팀원 채용합니다', 11, '1년 이상', '초대졸 이상', '정규직', 14,
        'Python을 이용한 데이터 분석 및 리포트 작성', '정보보안 자격증 보유자 우대', 4124, '09:00 ~ 18:00', '서울특별시 강서구', '2025-05-31', NULL);
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
insert into scrap_tb(user_id, employment_id, resume_id)
values (5, null, 3);

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
    (1, 'java'),
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
values
    (1, 'java'),
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