﻿
use master
go
RESTORE DATABASE PawnStore WITH RECOVERY
go
drop database PawnStore
go
create database PawnStore
go
use PawnStore
go
set dateformat dmy
go
drop table Account
go
create table Account(
	_username varchar(100) primary key not null,
	_password nvarchar(Max),
	_fullname nvarchar(50),
	_deleteflag bit default 0
)
go
delete from Account where _username <> 'admin'

go
drop table Customer
go
create table Customer(
	_id varchar(12) primary key,
	_fullname nvarchar(MAX),
	_gender nvarchar(10),
	_phonenumber varchar(12),
	_address nvarchar(MAX),
	_deleteFlag bit
)
go
delete from Customer

go
drop table TypeOfProduct
go 
create table TypeOfProduct(
	_id varchar(12) primary key,
	_name nvarchar(MAX),
	_deleteflag bit
)
go
delete from TypeOfProduct

go
drop table Product
go
create table Product(
	_id varchar(12) primary key,
	_typeID varchar(12) foreign key references TypeOfProduct(_id) on update cascade,
	_name nvarchar(max),
	_information nvarchar(max),
	_status nvarchar(max),
)
go
delete from Product


go
drop table PawnCoupon
go
create table PawnCoupon(
	_id varchar(12) primary key,
	_customerID varchar(12) foreign key references Customer(_id) on update cascade,
	_productID varchar(12) foreign key references Product(_id) on update cascade,
	_amount int,
	_price int,
	_interestRate float(2),
	_pawnDate varchar(20),
	_redeemOrLiquidationDate varchar(20),
	_liquidationPrice int,
	_status nvarchar(max)
)
go
delete from PawnCoupon

go
drop table InterestPayment
go
create table InterestPayment(
	_pawnCouponID varchar(12) foreign key references PawnCoupon(_id) on update cascade,
	_times int,
	primary key (_pawnCouponID,_times),
	_paymentDate nvarchar(20),
	_money int,
	_debt int,
	_note nvarchar(200)
)
go
delete from InterestPayment

go
drop table ActivityHistory
go
create table ActivityHistory(
	_time varchar(20) primary key,
	_username varchar(100) foreign key references Account(_username) on update cascade,
	_activity nvarchar(Max),
	_objectname nvarchar(Max),
	_infor nvarchar(Max)
)
go
delete from ActivityHistory

GO
Drop Trigger trgg_PawnCoupon
GO
CREATE TRIGGER trgg_PawnCoupon ON PawnCoupon
AFTER INSERT, UPDATE
AS
BEGIN
	UPDATE Product 
	SET _status = (Select Top(1) PawnCoupon._status 
							From PawnCoupon
							Where PawnCoupon._productID = Product._id
							Order By _id DESC)
	from Product
END

GO
CREATE TRIGGER trgg_Product ON Product
AFTER UPDATE
AS
BEGIN
	UPDATE Product SET _status = N'Chưa chuộc'
	WHERE _status = N'Trễ'
END


go
insert into Account values ('admin',N'21232f297a57a5a743894a0e4a801fc3Khi bạn muốn bỏ cuộc, hãy nghĩ đến lý do bắt đầu','Admin',0)

select * from Account
