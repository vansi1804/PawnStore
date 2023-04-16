-- drop database pawn_store
create database pawn_store;
use pawn_store;
-- drop table account;
create table if not exists account(
	username varchar(20) primary key not null,
	password nvarchar(1000) not null,
	fullname nvarchar(50) not null,
	delete_flag bit default 0
);
-- delete from account where username != 'admin';
-- drop table customer;
create table if not exists customer(
	id varchar(12) primary key not null,
	fullname nvarchar(1000) not null,
	gender nvarchar(10) not null,
	phone_number varchar(12) not null,
	address nvarchar(1000) not null,
	delete_flag bit default 0
);
-- delete from customer
;
-- drop table product_type; 
create table if not exists type_of_product(
	id varchar(12) primary key not null,
	name nvarchar(1000) not null,
	delete_flag bit default 0
);
-- delete from product_type
;
-- drop table product;
create table if not exists product(
	id varchar(12) primary key not null,
	type_id varchar(12),
	name nvarchar(1000) not null,
	info nvarchar(1000) not null,
	status nvarchar(1000) not null,
	foreign key(type_id) references type_of_product(id) on update cascade
);
-- delete from product;
-- drop table pawn_coupon;
create table if not exists pawn_coupon(
	id varchar(12) primary key not null,
	customer_id varchar(12),
	product_id varchar(12),
	amount int default 1,
	price int default 0,
	interest_rate float(2) default 0,
	pawn_date nvarchar(10) not null,
    the_next_interest_payment_date nvarchar(10),
	redemption_or_liquidation_date nvarchar(10),
	liquidation_price bigint default 0,
	status nvarchar(1000) not null,
    foreign key(customer_id) references customer(id) on update cascade,
	foreign key(product_id) references product(id) on update cascade
);
-- delete from pawn_coupon
;
-- drop table interest_payment;
create table if not exists interest_payment(
	pawn_coupon_id varchar(12),
	times int,
	primary key (pawn_coupon_id,times),
	payment_date nvarchar(10) not null,
	money_paid int default 0,
	new_debt int default 0,
	note nvarchar(200),
	foreign key(pawn_coupon_id) references pawn_coupon(id) on update cascade
);
-- delete from interest_payment
;
-- drop table activity_history;
create table if not exists activity_history(
	time nvarchar(20) primary key  not null,
	username varchar(100),
	activity nvarchar(1000) not null,
	object_name nvarchar(1000),
	info nvarchar(1000),
    foreign key(username) references account(username) on update cascade
);
-- delete from activity_history;

-- drop trigger trggupdateproductstatuswheninsertpawn_coupon
CREATE TRIGGER trggupdateproductstatuswheninsertpawn_coupon
AFTER INSERT ON pawn_coupon
FOR EACH ROW
	UPDATE product 
    SET status = N'Chưa chuộc' 
    WHERE id = New.product_id;

-- drop trigger trggupdateproductstatuswhenupdatepawn_coupon
CREATE TRIGGER trggupdateproductstatuswhenupdatepawn_coupon
AFTER UPDATE ON pawn_coupon
FOR EACH ROW
	UPDATE product 
    SET status = NEW.status  
    WHERE id = NEW.product_id AND NEW.status != N'Trễ';
    

insert into account 
values ('admin',N'21232f297a57a5a743894a0e4a801fc3','Admin',0);


