create database BookLibrary;

use BookLibrary;

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

insert into book
values(111, "The Founders", "Data Analytics", "Jimmy Soni", "Description for 111 book", 5),
(222, "Steve Jobs ","Technology", "Walter Isaacson", "Description for 222 book", 4),
(333, "Elon Musk", "Management", "Ashlee Vans", "Description of 333 book", 2);

select * from book;

insert into employee
values(1, "Bob", "password1", 0),
(2, "Martin", "password2", 0),
(3, "Sarah", "password3", 0);

select * from employee;

insert into library(transactionId, employeeId, employeeName, bookType, issueDate, expectedReturnDate, lateFee)
values(102, 2, "Martin", "Technology", "2022-12-02", "2022-12-09", 0.0),
(103, 3, "Sarah", "Management", "2022-12-05", "2022-12-12", 00),
(104, 1, "Bob", "Technology", "2022-11-29", "2022-12-06", 0.0),
(105, 2, "Martin", "Data analytics", "2022-11-30", "2022-12-07", 0.0);