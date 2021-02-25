insert into users (email, username, id, password) values ('ivan@gmail.com', 'Ivan', 1000,'$2a$10$1.twDMYM8n3FTL2aEsQN7uPf59jAW6u6B.V2v1bFtBEDqmXre.UpK');
insert into users (email, username, id, password) values ('Vova@gmail.com', 'Vova', 1001,'$2a$10$YPrHZfCSI8gU7WJvnV3cHON0BqhaRJ6YrjUBnRFhczUQca6T25ssm');
insert into users (email, username, id, password) values ('petro@ukr.net', 'Petro', 1002,'$2a$10$V5Q.KNWFoiQ6kkmz9wGXD.cjnRRIfLPy4XGGoCviRznKq4exCiW/.');

insert into addresses (id,city, country, house_number, street) values (1000,'Lviv','UA','12-b','Soborna');
insert into addresses (id,city, country, house_number, street) values (1001,'Kyiv','UA','233/2','Teligy');
insert into addresses (id,city, country, house_number, street) values (1002,'Poltava','UA','122','Pravdy');

insert into hotels (id,addressid, name) values (1000, 1000, 'Nadia');
insert into hotels (id,addressid, name) values (1001, 1001, 'Dnipro');
insert into hotels (id,addressid, name) values (1002, 1002, 'Sloboda');

insert into rooms (id, hotel_id, name, sleeps) values (1000,1000,'Lux',1);
insert into rooms (id, hotel_id, name, sleeps) values (1001,1000,'Lux',2);
insert into rooms (id, hotel_id, name, sleeps) values (1002,1000,'Lux',3);
insert into rooms (id, hotel_id, name, sleeps) values (1003,1001,'DeLux',2);
insert into rooms (id, hotel_id, name, sleeps) values (1004,1001,'Lux',2);
insert into rooms (id, hotel_id, name, sleeps) values (1005,1000,'Single',1);

insert into orders (id, check_in, check_out, client_id,hotel_id, state) values (1000, '2021-08-16','2021-08-17',1000,1000, 'NEW');
insert into orders (id, check_in, check_out, client_id,hotel_id, state) values (1001, '2021-08-19','2021-08-22',1000,1000, 'NEW');
insert into orders (id, check_in, check_out, client_id,hotel_id, state) values (1002, '2021-08-16','2021-09-28',1002,1000, 'DONE');
insert into orders (id, check_in, check_out, client_id,hotel_id, state) values (1003, '2021-09-01','2021-09-02',1002,1001, 'CANCELED');

insert into order_room (fk_order, fk_room) values (1000, 1005);
insert into order_room (fk_order, fk_room) values (1001, 1005);
insert into order_room (fk_order, fk_room) values (1000, 1002);
insert into order_room (fk_order, fk_room) values (1003, 1004);
insert into order_room (fk_order, fk_room) values (1002, 1000);
