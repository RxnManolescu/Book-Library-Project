create table library(
transactionId int primary key,
employeeId int,
employeeName varchar(25),
bookId int,
bookType varchar(25),
issueDate date,
returnDate date);

insert into library(transactionId, employeeId, employeeName, bookId, bookType, issueDate, returnDate)
values(102, 2, "Martin", 111, "Technology", "2022-12-02", "2022-12-09"),
(103, 3, "Sarah", 222, "Management", "2022-12-05", "2022-12-09"),
(104, 1, "Bob", 333, "Technology", "2022-11-29", "2022-12-09"),
(105, 2, "Martin", 222, "Data analytics", "2022-11-30", NULL);

drop table library;

select * from library;

