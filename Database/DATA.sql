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
	_username varchar(100) primary key,
	_password nvarchar(Max),
	_fullname nvarchar(50),
	_deleteflag bit
)
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
drop table TypeOfProduct
go 
create table TypeOfProduct(
	_id varchar(12) primary key,
	_name nvarchar(MAX),
	_deleteflag bit
)

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
drop table PawnCoupon
go
create table PawnCoupon(
	_id varchar(12) primary key,
	_customerID varchar(12) foreign key references Customer(_id) on update cascade,
	_productID varchar(12) foreign key references Product(_id) on update cascade,
	_amount int,
	_price int,
	_interestRate float,
	_pawnDate varchar(20),
	_redeemOrLiquidationDate varchar(20),
	_liquidationPrice int,
	_status nvarchar(max)
)
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
drop table ActivityHistory
go
create table ActivityHistory(
	_time varchar(20) primary key,
	_username varchar(100) foreign key references Account(_username) on update cascade,
	_activity nvarchar(Max),
	_objectname nvarchar(Max),
	_infor nvarchar(Max)
)

select * from Account
delete from Account
insert into Account values ('admin','admin','Admin',0)

select * from Customer where _gender = N'Nữ' and _deleteflag = 0
delete from Customer

select * from TypeOfProduct
delete from TypeOfProduct

select * from Product
delete from Product

select * from PawnCoupon
delete from PawnCoupon
Update PawnCoupon 
Set _customerID = 'HÐ0000000001',
_productID = '215528540',
_amount = 1,
_price = 10000000,
_interestRate = 0.3,
_pawnDate = '10/08/2022',
_redeemOrLiquidationDate = '',
_liquidationPrice = 13000000 _status = ? Where _id = ?



select * from InterestPayment
delete from InterestPayment

select * from ActivityHistory
delete from ActivityHistory


CREATE TRIGGER trgg_PawnCoupon ON PawnCoupon
AFTER INSERT, UPDATE
AS
BEGIN
	UPDATE Product SET Product._status = PawnCoupon._status
	from Product inner join PawnCoupon on Product._id = PawnCoupon._productID
END

CREATE TRIGGER trgg_Product ON Product
AFTER UPDATE
AS
BEGIN
	UPDATE Product SET _status = N'Chưa chuộc'
	WHERE _status = N'Trễ'
END

390


Select PawnCoupon._id
, Customer._id, Customer._fullname, Customer._gender, Customer._phonenumber, Customer._address
, Customer._deleteflag
, Product._id, TypeOfProduct._id, TypeOfProduct._name, TypeOfProduct._deleteflag
, Product._name, Product._information, Product._status
, PawnCoupon._amount, PawnCoupon._price, PawnCoupon._interestRate
, PawnCoupon._pawnDate, PawnCoupon._redeemOrLiquidationDate, PawnCoupon._liquidationPrice
, PawnCoupon._status
 From PawnCoupon Inner Join Customer On PawnCoupon._customerID = Customer._id
 Inner Join Product On PawnCoupon._productID = Product._id
 Inner Join TypeOfProduct On Product._typeID = TypeOfProduct._id
 Where Convert(datetime,PawnCoupon._pawnDate,105)
 Between Convert(datetime,'01/08/2022',105) And Convert(datetime,'31/08/2022',105)
 Or Convert(datetime,PawnCoupon._redeemOrLiquidationDate,105) 
 Between Convert(datetime,'01/08/2022',105) And Convert(datetime,'31/08/2022',105)