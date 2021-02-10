insert into users (email, name, id) values ('ivan@gmail.com', 'Ivan', 1000);
insert into users (email, name, id) values ('Vova@gmail.com', 'Vova', 1001);
insert into users (email, name, id) values ('petro@ukr.net', 'Petro', 1002);

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
