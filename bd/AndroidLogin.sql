create database LoginAndroid
go
use LoginAndroid

CREATE TABLE  Users(
IdUsers int identity(1,1) primary key,
Name varchar(15) not null,
Gmail varchar(100) not null,
Phone varchar(10) not null,
UserL varchar(30) not null,
Password varchar(30) not null
)

SELECT* FROM Users