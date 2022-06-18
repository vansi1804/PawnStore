

create database PawnStore
go
use PawnStore
go
set dateformat dmy
go
drop table Account
go
create table Account(
	_username varchar(1000) primary key,
	_password nvarchar(1000),
	_fullname nvarchar(50)
)
go
drop table Customer
go
create table Customer(
	_id varchar(12) primary key,
	_fullname nvarchar(50),
	_gender nvarchar(10),
	_phonenumber varchar(12),
	_address nvarchar(100)
)
go
drop table TypeOfProduct
go 
create table TypeOfProduct(
	_id varchar(10) primary key,
	_name nvarchar(30)
)

go
drop table Product
go
create table Product(
	_id varchar(10) primary key,
	_name nvarchar(30),
	_information nvarchar(200),
	_status nvarchar(50),
	_typeID varchar(10) foreign key references TypeOfProduct(_id) on update cascade
)

go
drop table PawnCoupon
go
create table PawnCoupon(
	_id varchar(10) primary key,
	_pawnDate nvarchar(20),
	_customerID varchar(12) foreign key references Customer(_id) on update cascade,
	_productID varchar(10) foreign key references Product(_id) on update cascade,
	_amount int,
	_price float,
	_interestRate float,
	_redeemingDate Date default null,
	_status nvarchar(50),
	_username varchar(1000) foreign key references Account(_username) on update cascade
)

go
drop table InterestPayment
go
create table InterestPayment(
	_pawCouponID varchar(10) foreign key references PawnCoupon(_id) on update cascade,
	_times int,
	primary key (_pawCouponID,_times),
	_paymentDate nvarchar(20),
	_paymentUntilDate nvarchar(20),
	_money float,
	_debt float,
	_note nvarchar(200)
)

go
drop table ActivityHistory
go
create table ActivityHistory(
	_activeTime nvarchar(20) primary key,
	_username varchar(1000) foreign key references Account(_username) on update cascade,
	_activity nvarchar(Max)
)

select * from Account
delete from Account
update Account set _username = 'XOgeo1KhuegHhb4yJ/+OJg==', _password = 'XOgeo1KhuegHhb4yJ/+OJg=='
insert into Account values ('XOgeo1KhuegHhb4yJ/+OJg==','XOgeo1KhuegHhb4yJ/+OJg==','Admin')

select * from Customer
delete from Customer

select * from TypeOfProduct
delete from TypeOfProduct

select * from Product
delete from Product
Update Product set _status = null where _status = ''

select * from PawnCoupon
delete from PawnCoupon

select * from InterestPayment
delete from InterestPayment

select * from ActivityHistory
delete from ActivityHistory
insert into ActivityHistory values(CONVERT(VARCHAR, '18-06-2022 09:04:00', 103),'XOgeo1KhuegHhb4yJ/+OJg==',N'Đăng nhập')