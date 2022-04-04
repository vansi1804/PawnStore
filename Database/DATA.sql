--go
--use master 
--if exists (select name from sys.databases where name = 'QLCD')
drop database PawnShop
go
create database PawnShop
go 
use PawnShop

go
drop table Account
GO
CREATE TABLE Account
(
	_userName VARCHAR(20) PRIMARY KEY,
	_passWord VARCHAR(20),
	_userFullName NVARCHAR(30)
)
go
drop table Customer
GO
CREATE TABLE Customer
(
	_id varchar(10) PRIMARY KEY,
	_name NVARCHAR(50),
	_phoneNumber VARCHAR(11) CHECK (DATALENGTH(_phoneNumber) = 10 or DATALENGTH(_phoneNumber) = 11) ,
	_address NVARCHAR(100),	
	_identityNumber VARCHAR(12) UNIQUE check (DATALENGTH(_identityNumber) = 9 or DATALENGTH(_identityNumber) = 12),
)
GO
drop table TypeOfProduct
GO
CREATE TABLE TypeOfProduct
(
	_id varchar(10) PRIMARY KEY,
	_name NVARCHAR(50),
)
go
drop table Product
GO
CREATE TABLE Product
(
	_id varchar(10) PRIMARY KEY,
	_name NVARCHAR(50),
	_typeOfProductID varchar(10),
	foreign key (_typeOfProductID) references TypeOfProduct(_id) on update cascade 
)
GO
drop table PawnCoupon
GO
CREATE TABLE PawnCoupon
(
	_id varchar(10) PRIMARY KEY,
	_setupDate DATETIME,
	_money FLOAT,
	_status bit,
	_customerID varchar(10) foreign key (_customerID) references Customer(_id) on update cascade,
	_userName VARCHAR(20) foreign key (_userName) references Account(_userName) on update cascade,
)
GO
drop table PawnCouponDetail
GO
CREATE TABLE PawnCouponDetail
(
	_productID varchar(10),
	_pawnCouponID varchar(10),
	_amount int,
	_money money,
	_interesrPaymentDate DATETIME,
	PRIMARY KEY (_productID,_pawnCouponID),
	foreign key (_productID) references Product(_id) on update cascade,
	foreign key (_pawnCouponID) references PawnCoupon(_id) on update cascade,
)
GO 
drop table BillOfPayment
CREATE TABLE BillOfPayment
(
	_id varchar(10) PRIMARY KEY,
	_setupDate DATETIME,
	_pawnTicketID varchar(10),
	_interest FLOAT,
	_total MONEY,
	foreign key (_pawnTicketID) references PawnCoupon(_id) on update cascade 
)
GO
SET DATEFORMAT DMY