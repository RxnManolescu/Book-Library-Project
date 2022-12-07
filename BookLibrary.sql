create database BookLibrary;

-- employee
create table employee(
employeeId int primary key,
employeeName varchar(25),
password varchar(25),
bookQuantity int);

-- book
create table book(
bookId int primary key, 
bookName varchar(40),
bookType varchar(25),
bookAuthor varchar(30),
bookDescription varchar(300),
numberOfCopies int);

-- library
create table library(
transactionId int primary key,
employeeId int,
employeeName varchar(25),
bookType varchar(25),
issueDate date,
expectedReturnDate date,
returnDate date, -- set as today -> default(current_date())
lateFee double);

drop table employee;

insert into library(transactionId, employeeId, employeeName, bookType, issueDate, expectedReturnDate, lateFee)
values(101, 1, "Bob", "Data Analytics", '2022-12-01', '2022-12-08', 0.0);

select * from library;