INSERT INTO MEMBER (ID, USERNAME, NICKNAME, EMAIL, PASSWORD, NAME, PHONE_NUMBER, BIRTH_DATE, ROLES, CREATE_DATE, MODIFY_DATE) VALUES (default, 'user1', 'user1', 'user1@example.com', '$2a$10$RipmJ2.1x5wRiO5Dx4a2SeAZRfWDiKCQEezI9TzAiP5yAfiH5jIhK', 'User1NAME', '1234567890', '1990-01-01', 'ROLE_USER', NOW(),NOW());
INSERT INTO MEMBER (ID, USERNAME, NICKNAME, EMAIL, PASSWORD, NAME, PHONE_NUMBER, BIRTH_DATE, ROLES, CREATE_DATE, MODIFY_DATE) VALUES (default, 'user2', 'user2', 'user2@example.com', '$2a$10$RipmJ2.1x5wRiO5Dx4a2SeAZRfWDiKCQEezI9TzAiP5yAfiH5jIhK', 'User2NAME', '1234567890', '1990-01-01', 'ROLE_USER', NOW(),NOW());
INSERT INTO MEMBER (ID, USERNAME, NICKNAME, EMAIL, PASSWORD, NAME, PHONE_NUMBER, BIRTH_DATE, ROLES, CREATE_DATE, MODIFY_DATE) VALUES (default, 'admin', 'admin', 'admin@example.com', '$2a$10$RipmJ2.1x5wRiO5Dx4a2SeAZRfWDiKCQEezI9TzAiP5yAfiH5jIhK', 'ADMINNAME', '1234567890', '1990-01-01', 'ROLE_USER, ROLE_ADMIN', NOW(),NOW());

INSERT INTO ADDRESS (ID, MEMBER_ID, PRIMARY_ADDRESS, SECONDARY_ADDRESS, ZIPCODE) VALUES (default, 1, '서울시 송파구',  '문정',  '1111');
INSERT INTO ADDRESS (ID, MEMBER_ID, PRIMARY_ADDRESS, SECONDARY_ADDRESS, ZIPCODE) VALUES (default, 2, '서울시 마포구',  '문정3',  '1112');
INSERT INTO ADDRESS (ID, MEMBER_ID, PRIMARY_ADDRESS, SECONDARY_ADDRESS, ZIPCODE) VALUES (default, 2, '서울시 마포구',  '문정2',  '1113');

INSERT INTO MAIN_CATEGORY (ID, CATEGORY_NAME) VALUES (default, '하드베이트');
INSERT INTO MAIN_CATEGORY (ID, CATEGORY_NAME) VALUES (default, '소프트베이트');
INSERT INTO MAIN_CATEGORY (ID, CATEGORY_NAME) VALUES (default, '메탈지그&스푼');
INSERT INTO MAIN_CATEGORY (ID, CATEGORY_NAME) VALUES (default, '스커트베이트');
INSERT INTO MAIN_CATEGORY (ID, CATEGORY_NAME) VALUES (default, '각종장비');

-- 하드베이트 하위
INSERT INTO SUB_CATEGORY (ID, MAIN_CATEGORY_ID,SUBCATEGORY_NAME) VALUES (default, 1, '플로팅미노우'); --1
INSERT INTO SUB_CATEGORY (ID, MAIN_CATEGORY_ID,SUBCATEGORY_NAME) VALUES (default, 1, '메탈지그');
INSERT INTO SUB_CATEGORY (ID, MAIN_CATEGORY_ID,SUBCATEGORY_NAME) VALUES (default, 1, '타이라바');
INSERT INTO SUB_CATEGORY (ID, MAIN_CATEGORY_ID,SUBCATEGORY_NAME) VALUES (default, 1, '에기');
-- 소프트베이트 하위
INSERT INTO SUB_CATEGORY (ID, MAIN_CATEGORY_ID,SUBCATEGORY_NAME) VALUES (default, 2, '새드'); --5
INSERT INTO SUB_CATEGORY (ID, MAIN_CATEGORY_ID,SUBCATEGORY_NAME) VALUES (default, 2, '테일');
INSERT INTO SUB_CATEGORY (ID, MAIN_CATEGORY_ID,SUBCATEGORY_NAME) VALUES (default, 2, '호그');

-- 메탈지그&스푼 하위
INSERT INTO SUB_CATEGORY (ID, MAIN_CATEGORY_ID,SUBCATEGORY_NAME) VALUES (default, 3, '메탈지그'); --8
INSERT INTO SUB_CATEGORY (ID, MAIN_CATEGORY_ID,SUBCATEGORY_NAME) VALUES (default, 3, '스푼');

-- 스커트베이트 하위
INSERT INTO SUB_CATEGORY (ID, MAIN_CATEGORY_ID,SUBCATEGORY_NAME) VALUES (default, 4, '스피너베이트'); --10
INSERT INTO SUB_CATEGORY (ID, MAIN_CATEGORY_ID,SUBCATEGORY_NAME) VALUES (default, 4, '스피너');

-- 각종장비 하위
INSERT INTO SUB_CATEGORY (ID, MAIN_CATEGORY_ID,SUBCATEGORY_NAME) VALUES (default, 5, '낚시대'); --10
INSERT INTO SUB_CATEGORY (ID, MAIN_CATEGORY_ID,SUBCATEGORY_NAME) VALUES (default, 5, '악세사리');


INSERT INTO PRODUCT (ID, PRODUCT_NAME, PRODUCT_PRICE,  MAIN_CATEGORY_ID, SUB_CATEGORY_ID, CREATE_DATE, MODIFY_DATE) VALUES (default,'사파이어14A',9000,1,1,NOW(),NOW());
INSERT INTO PRODUCT (ID, PRODUCT_NAME, PRODUCT_PRICE, MAIN_CATEGORY_ID, SUB_CATEGORY_ID, CREATE_DATE, MODIFY_DATE)VALUES (default, '사파이어142', 10000,  1, 1, NOW(), NOW());
INSERT INTO PRODUCT (ID, PRODUCT_NAME, PRODUCT_PRICE, MAIN_CATEGORY_ID, SUB_CATEGORY_ID, CREATE_DATE, MODIFY_DATE)VALUES (default, '사파이어91', 8000,  1, 1, NOW(), NOW());
INSERT INTO PRODUCT (ID, PRODUCT_NAME, PRODUCT_PRICE,  MAIN_CATEGORY_ID, SUB_CATEGORY_ID, CREATE_DATE, MODIFY_DATE)VALUES (default, '사파이어40C', 8000, 1, 1, NOW(), NOW());
INSERT INTO PRODUCT (ID, PRODUCT_NAME, PRODUCT_PRICE,MAIN_CATEGORY_ID, SUB_CATEGORY_ID, CREATE_DATE, MODIFY_DATE)VALUES (default, '사파이어103', 12000, 1, 1, NOW(), NOW());
INSERT INTO PRODUCT (ID, PRODUCT_NAME, PRODUCT_PRICE, MAIN_CATEGORY_ID, SUB_CATEGORY_ID, CREATE_DATE, MODIFY_DATE)VALUES (default, '헬라R85새드', 6000,  2, 5, NOW(), NOW()); --6
INSERT INTO PRODUCT (ID, PRODUCT_NAME, PRODUCT_PRICE, MAIN_CATEGORY_ID, SUB_CATEGORY_ID, CREATE_DATE, MODIFY_DATE)VALUES (default, '사라리플새드4인치', 6000, 2, 5, NOW(), NOW());
INSERT INTO PRODUCT (ID, PRODUCT_NAME, PRODUCT_PRICE,MAIN_CATEGORY_ID, SUB_CATEGORY_ID, CREATE_DATE, MODIFY_DATE)VALUES (default, 'T어쌔신5인치', 4000, 2, 5, NOW(), NOW());
INSERT INTO PRODUCT (ID, PRODUCT_NAME, PRODUCT_PRICE,  MAIN_CATEGORY_ID, SUB_CATEGORY_ID, CREATE_DATE, MODIFY_DATE)VALUES (default, '5인치 T-LINE(LT)', 6000,  2, 6, NOW(), NOW());
INSERT INTO PRODUCT (ID, PRODUCT_NAME, PRODUCT_PRICE, MAIN_CATEGORY_ID, SUB_CATEGORY_ID, CREATE_DATE, MODIFY_DATE)VALUES (default, '5인치 T-LINE(TD)', 6000, 2, 6, NOW(), NOW());
INSERT INTO PRODUCT (ID, PRODUCT_NAME, PRODUCT_PRICE,  MAIN_CATEGORY_ID, SUB_CATEGORY_ID, CREATE_DATE, MODIFY_DATE)VALUES (default, '울트라R38C테일', 6000, 2, 6, NOW(), NOW());
INSERT INTO PRODUCT (ID, PRODUCT_NAME, PRODUCT_PRICE,  MAIN_CATEGORY_ID, SUB_CATEGORY_ID, CREATE_DATE, MODIFY_DATE)VALUES (default, 'TOP LT 메탈', 8000, 3, 8, NOW(), NOW()); --12
INSERT INTO PRODUCT (ID, PRODUCT_NAME, PRODUCT_PRICE,MAIN_CATEGORY_ID, SUB_CATEGORY_ID, CREATE_DATE, MODIFY_DATE)VALUES (default, '대구전용 메탈', 12000, 3, 8, NOW(), NOW());
INSERT INTO PRODUCT (ID, PRODUCT_NAME, PRODUCT_PRICE, MAIN_CATEGORY_ID, SUB_CATEGORY_ID, CREATE_DATE, MODIFY_DATE)VALUES (default, 'TPIAA 털스푼', 5000,  3, 9, NOW(), NOW());

INSERT INTO IMAGE (ID,PRODUCT_ID,PATH,IMAGE_NAME,FULL_NAME,CREATED_DATE, DTYPE) VALUES (DEFAULT,1,'https://www.topcasting.co.kr/shop/data/tntshop1/img_big/1/9_gs_img_dae.jpg','사파이어14A','사파이어14A',now(),'MAIN');
INSERT INTO IMAGE (ID,PRODUCT_ID,PATH,IMAGE_NAME,FULL_NAME,CREATED_DATE,DTYPE) VALUES (DEFAULT,1,'https://www.topcasting.co.kr/shop/data/tntshop1/img_body/1/9_bed3_tcs14abbf3bcbcc6e4c0ccc1f6.jpg','d_사파이어14A','사파이어14A',now(),'DETAILED');

INSERT INTO IMAGE (ID,PRODUCT_ID,PATH,IMAGE_NAME,FULL_NAME,CREATED_DATE,DTYPE) VALUES (DEFAULT,2,'https://www.topcasting.co.kr/shop/data/tntshop1/img_big/1/10_gs_img_jung.jpg','사파이어142','사파이어142',now(),'MAIN');
INSERT INTO IMAGE (ID,PRODUCT_ID,PATH,IMAGE_NAME,FULL_NAME,CREATED_DATE,DTYPE) VALUES (DEFAULT,2,'https://www.topcasting.co.kr/shop/data/tntshop1/img_body/1/9_bed3_tcs14abbf3bcbcc6e4c0ccc1f6.jpg','사파이어142','사파이어142',now(),'DETAILED');

INSERT INTO IMAGE (ID,PRODUCT_ID,PATH,IMAGE_NAME,FULL_NAME,CREATED_DATE,DTYPE) VALUES (DEFAULT,3,'https://www.topcasting.co.kr/shop/data/tntshop1/img_big/1/15_gs_img_dae.jpg','사파이어91','사파이어91',now(),'MAIN');
INSERT INTO IMAGE (ID,PRODUCT_ID,PATH,IMAGE_NAME,FULL_NAME,CREATED_DATE,DTYPE) VALUES (DEFAULT,3,'https://www.topcasting.co.kr/shop/data/tntshop1/img_body/1/9_bed3_tcs14abbf3bcbcc6e4c0ccc1f6.jpg','사파이어91','사파이어91',now(),'DETAILED');

INSERT INTO IMAGE (ID,PRODUCT_ID,PATH,IMAGE_NAME,FULL_NAME,CREATED_DATE,DTYPE) VALUES (DEFAULT,4,'https://www.topcasting.co.kr/shop/data/tntshop1/img_big/1/19_gs_img_dae.jpg','사파이어40C','사파이어40C',now(),'MAIN');
INSERT INTO IMAGE (ID,PRODUCT_ID,PATH,IMAGE_NAME,FULL_NAME,CREATED_DATE,DTYPE) VALUES (DEFAULT,4,'https://www.topcasting.co.kr/shop/data/tntshop1/img_body/1/9_bed3_tcs14abbf3bcbcc6e4c0ccc1f6.jpg','사파이어40C','사파이어40C',now(),'DETAILED');

INSERT INTO IMAGE (ID,PRODUCT_ID,PATH,IMAGE_NAME,FULL_NAME,CREATED_DATE,DTYPE) VALUES (DEFAULT,5,'https://www.topcasting.co.kr/shop/data/tntshop1/img_big/2/135_gs_img_jung.jpg','사파이어103','사파이어103',now(),'MAIN');
INSERT INTO IMAGE (ID,PRODUCT_ID,PATH,IMAGE_NAME,FULL_NAME,CREATED_DATE,DTYPE) VALUES (DEFAULT,5,'https://www.topcasting.co.kr/shop/data/tntshop1/img_body/1/9_bed3_tcs14abbf3bcbcc6e4c0ccc1f6.jpg','사파이어103','사파이어103',now(),'DETAILED');

INSERT INTO IMAGE (ID,PRODUCT_ID,PATH,IMAGE_NAME,FULL_NAME,CREATED_DATE,DTYPE) VALUES (DEFAULT,6,'https://www.topcasting.co.kr/shop/data/tntshop1/img_big/1/42_gs_img_dae.jpg','헬라R85새드','헬라R85새드',now(),'MAIN'); --6
INSERT INTO IMAGE (ID,PRODUCT_ID,PATH,IMAGE_NAME,FULL_NAME,CREATED_DATE,DTYPE) VALUES (DEFAULT,6,'https://www.topcasting.co.kr/shop/data/tntshop1/img_big/1/42_img_add11.jpg','헬라R85새드','헬라R85새드',now(),'DETAILED');

INSERT INTO IMAGE (ID,PRODUCT_ID,PATH,IMAGE_NAME,FULL_NAME,CREATED_DATE,DTYPE) VALUES (DEFAULT,7,'https://www.topcasting.co.kr/shop/data/tntshop1/img_big/1/46_gs_img_dae.jpg','사라리플새드4인치','사라리플새드4인치',now(),'MAIN'); --7
INSERT INTO IMAGE (ID,PRODUCT_ID,PATH,IMAGE_NAME,FULL_NAME,CREATED_DATE,DTYPE) VALUES (DEFAULT,7,'https://www.topcasting.co.kr/shop/data/tntshop1/img_big/1/46_img_add11.jpg','사라리플새드4인치','사라리플새드4인치',now(),'DETAILED');

INSERT INTO IMAGE (ID,PRODUCT_ID,PATH,IMAGE_NAME,FULL_NAME,CREATED_DATE,DTYPE) VALUES (DEFAULT,8,'https://www.topcasting.co.kr/shop/data/tntshop1/img_big/1/47_gs_img_dae.jpg','T어쌔신5인치','T어쌔신5인치',now(),'MAIN'); --8
INSERT INTO IMAGE (ID,PRODUCT_ID,PATH,IMAGE_NAME,FULL_NAME,CREATED_DATE,DTYPE) VALUES (DEFAULT,8,'https://www.topcasting.co.kr/shop/data/tntshop1/img_big/1/47_img_add11.jpg','T어쌔신5인치','T어쌔신5인치',now(),'DETAILED');

INSERT INTO IMAGE (ID,PRODUCT_ID,PATH,IMAGE_NAME,FULL_NAME,CREATED_DATE,DTYPE) VALUES (DEFAULT,9,'https://www.topcasting.co.kr/shop/data/tntshop1/img_tiny/1/37_gs_img_so.jpg','5인치 T-LINE(LT)','5인치 T-LINE(LT)',now(),'MAIN');
INSERT INTO IMAGE (ID,PRODUCT_ID,PATH,IMAGE_NAME,FULL_NAME,CREATED_DATE,DTYPE) VALUES (DEFAULT,9,'https://www.topcasting.co.kr/shop/data/tntshop1/img_big/1/37_img_add11.jpg','5인치 T-LINE(LT)','5인치 T-LINE(LT)',now(),'DETAILED');

INSERT INTO IMAGE (ID,PRODUCT_ID,PATH,IMAGE_NAME,FULL_NAME,CREATED_DATE,DTYPE) VALUES (DEFAULT,10,'https://www.topcasting.co.kr/shop/data/tntshop1/img_tiny/1/38_gs_img_so.jpg','5인치 T-LINE(TD)','5인치 T-LINE(LT)',now(),'MAIN');
INSERT INTO IMAGE (ID,PRODUCT_ID,PATH,IMAGE_NAME,FULL_NAME,CREATED_DATE,DTYPE) VALUES (DEFAULT,10,'https://www.topcasting.co.kr/shop/data/tntshop1/img_big/1/38_img_add11.jpg','5인치 T-LINE(TD)','5인치 T-LINE(LT)',now(),'DETAILED');

INSERT INTO IMAGE (ID,PRODUCT_ID,PATH,IMAGE_NAME,FULL_NAME,CREATED_DATE,DTYPE) VALUES (DEFAULT,11,'https://www.topcasting.co.kr/shop/data/tntshop1/img_tiny/1/40_gs_img_so.jpg','울트라R38C테일','울트라R38C테일',now(),'MAIN');
INSERT INTO IMAGE (ID,PRODUCT_ID,PATH,IMAGE_NAME,FULL_NAME,CREATED_DATE,DTYPE) VALUES (DEFAULT,11,'https://www.topcasting.co.kr/shop/data/tntshop1/img_big/1/40_img_add11.jpg','울트라R38C테일','울트라R38C테일',now(),'DETAILED');

INSERT INTO IMAGE (ID,PRODUCT_ID,PATH,IMAGE_NAME,FULL_NAME,CREATED_DATE,DTYPE) VALUES (DEFAULT,12,'https://www.topcasting.co.kr/shop/data/tntshop1/img_big/2/124_gs_img_jung.jpg','TOP LT 메탈','TOP LT 메탈',now(),'MAIN');
INSERT INTO IMAGE (ID,PRODUCT_ID,PATH,IMAGE_NAME,FULL_NAME,CREATED_DATE,DTYPE) VALUES (DEFAULT,12,'https://www.topcasting.co.kr/shop/data/tntshop1/img_body/2/124_c74e_top_lt_b8dec5bb__bbe7c0ccc6aebcf6c1a4.jpg','TOP LT 메탈','TOP LT 메탈',now(),'DETAILED');

INSERT INTO IMAGE (ID,PRODUCT_ID,PATH,IMAGE_NAME,FULL_NAME,CREATED_DATE,DTYPE) VALUES (DEFAULT,13,'https://www.topcasting.co.kr/shop/data/tntshop1/img_big/1/77_gs_img_dae.jpg','대구전용 메탈','대구전용 메탈',now(),'MAIN');
INSERT INTO IMAGE (ID,PRODUCT_ID,PATH,IMAGE_NAME,FULL_NAME,CREATED_DATE,DTYPE) VALUES (DEFAULT,13,'https://www.topcasting.co.kr/shop/data/tntshop1/img_big/1/77_img_add11.jpg','대구전용 메탈','대구전용 메탈',now(),'DETAILED');

INSERT INTO IMAGE (ID,PRODUCT_ID,PATH,IMAGE_NAME,FULL_NAME,CREATED_DATE,DTYPE) VALUES (DEFAULT,14,'https://www.topcasting.co.kr/shop/data/tntshop1/img_big/1/1_gs_img_dae.jpg','TPIAA 털스푼','TPIAA 털스푼',now(),'MAIN');
INSERT INTO IMAGE (ID,PRODUCT_ID,PATH,IMAGE_NAME,FULL_NAME,CREATED_DATE,DTYPE) VALUES (DEFAULT,14,'https://www.topcasting.co.kr/shop/data/tntshop1/img_body/1/1_646b_c5d0bdbac7acb1ddbbf6bbf3bcbcc6e4c0ccc1f6.jpg','TPIAA 털스푼','TPIAA 털스푼',now(),'DETAILED');

--itemId 1번의 옵션 5개
insert into options (id,product_id,color_name,stock) values (default,1,'A390',100);
insert into options (id,product_id,color_name,stock) values (default,1,'A027',200);
insert into options (id,product_id,color_name,stock) values (default,1,'A026',300);
insert into options (id,product_id,color_name,stock) values (default,1,'A028',400);
insert into options (id,product_id,color_name,stock) values (default,1,'F1104',500);
--itemId 2번의 옵션
insert into options (id,product_id,color_name,stock) values (default,2,'B390',100); --6
insert into options (id,product_id,color_name,stock) values (default,2,'B655',200);
--itemId 3번의 옵션
insert into options (id,product_id,color_name,stock) values (default,3,'91-001',100); --8
insert into options (id,product_id,color_name,stock) values (default,3,'91-002',200);
--itemId 4번의 옵션
insert into options (id,product_id,color_name,stock) values (default,4,'F1631',100); --10
insert into options (id,product_id,color_name,stock) values (default,4,'F1632',200);
--itemId 5번의 옵션
insert into options (id,product_id,color_name,stock) values (default,5,'103-001',100); --12
insert into options (id,product_id,color_name,stock) values (default,5,'103-002',200);
--itemId 6번의 옵션
insert into options (id,product_id,color_name,stock) values (default,6,'NO-001',100); --14
insert into options (id,product_id,color_name,stock) values (default,6,'NO-002',100);
insert into options (id,product_id,color_name,stock) values (default,6,'NO-003',100);
--product 7번의 옵션
insert into options (id,product_id,color_name,stock) values (default,7,'NO-001',100); --17
insert into options (id,product_id,color_name,stock) values (default,7,'NO-002',100);
insert into options (id,product_id,color_name,stock) values (default,7,'NO-005',100);
--product 8번의 옵션
insert into options (id,product_id,color_name,stock) values (default,8,'NO-001',100); --20
insert into options (id,product_id,color_name,stock) values (default,8,'NO-004',100);
insert into options (id,product_id,color_name,stock) values (default,8,'NO-038',100);

--itemId 9번의 옵션
insert into options (id,product_id,color_name,stock) values (default,9,'WATERMELON SEED',100); --23
insert into options (id,product_id,color_name,stock) values (default,9,'WATERMELON GOLD',200);

--itemId 10번의 옵션
insert into options (id,product_id,color_name,stock) values (default,10,'WATERMELON SEED',100); --25
insert into options (id,product_id,color_name,stock) values (default,10,'WATERMELON CHART',200);

--itemId 11번의 옵션
insert into options (id,product_id,color_name,stock) values (default,11,'001',100); --27
insert into options (id,product_id,color_name,stock) values (default,11,'002',200);

--itemId 12번의 옵션
insert into options (id,product_id,color_name,stock) values (default,12,'NO-001',100); --29
insert into options (id,product_id,color_name,stock) values (default,12,'NO-002',200);

--itemId 13번의 옵션
insert into options (id,product_id,color_name,stock) values (default,13,'NO-001',100); --31
insert into options (id,product_id,color_name,stock) values (default,13,'NO-002',200);

--itemId 14번의 옵션
insert into options (id,product_id,color_name,stock) values (default,14,'금-5g',100); --33
insert into options (id,product_id,color_name,stock) values (default,14,'금-7g',200);
insert into options (id,product_id,color_name,stock) values (default,14,'금-9g',200);
