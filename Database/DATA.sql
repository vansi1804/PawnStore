

create database PawnStore
go
use PawnStore

drop table Account
go
create table Account(
	_username varchar(10) primary key,
	_password nvarchar(30),
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
	_pawnDate Date,
	_customerID varchar(12) foreign key references Customer(_id) on update cascade,
	_productID varchar(10) foreign key references Product(_id) on update cascade,
	_amount int,
	_price float,
	_interestRate float,
	_redeemingDate Date default null,
	_status nvarchar(50),
	_username varchar(10) foreign key references Account(_username) on update cascade
)

select * from PawnCoupon where _status = N'Chưa chuộc';

go
drop table InterestPayment
go
create table InterestPayment(
	_pawCouponID varchar(10) foreign key references PawnCoupon(_id) on update cascade,
	_times int,
	primary key (_pawCouponID,_times),
	_paymentDate Date,
	_money float,
	_debt float,
	_note nvarchar(200)
)
