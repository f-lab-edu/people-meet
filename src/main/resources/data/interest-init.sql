INSERT INTO interest_category(interest_category_id, create_time, update_time, name) VALUES (1, now(), now(), 'IT');
INSERT INTO interest_category(interest_category_id, create_time, update_time, name) VALUES (2, now(), now(), '문화');
INSERT INTO interest_category(interest_category_id, create_time, update_time, name) VALUES (3, now(), now(), '기타');


INSERT INTO interest(interest_id, create_time, update_time, name, interest_category_id) values (1, now(), now(), '개발', 1);

INSERT INTO interest(interest_id, create_time, update_time, name, interest_category_id) values (2, now(), now(), '영화', 2);
INSERT INTO interest(interest_id, create_time, update_time, name, interest_category_id) values (3, now(), now(), '연극', 3);