
INSERT INTO region(region_id, create_time, update_time, level, name, parents_region_id) values (1, now(), now(), 1, '서울', null);
INSERT INTO region(region_id, create_time, update_time, level, name, parents_region_id) values (2, now(), now(), 1, '경기', null);
INSERT INTO region(region_id, create_time, update_time, level, name, parents_region_id) values (3, now(), now(), 1, '인천', null);

INSERT INTO region(region_id, create_time, update_time, level, name, parents_region_id) values (4, now(), now(), 2, '안양', 2);
INSERT INTO region(region_id, create_time, update_time, level, name, parents_region_id) values (5, now(), now(), 2, '강남', 1);
INSERT INTO region(region_id, create_time, update_time, level, name, parents_region_id) values (6, now(), now(), 2, '방배', 1);
INSERT INTO region(region_id, create_time, update_time, level, name, parents_region_id) values (7, now(), now(), 2, '부평', 3);
INSERT INTO region(region_id, create_time, update_time, level, name, parents_region_id) values (8, now(), now(), 2, '수원', 2);