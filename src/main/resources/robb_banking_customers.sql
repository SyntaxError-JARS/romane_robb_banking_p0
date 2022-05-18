-- Romane Robb's account_info App Database.

-- Step #1 is to create a schema.

drop schema if exists romane_robb_p0;

create schema romane_robb_p0;

-- Step #2 is to create the table. Make sure the table is in the right schema!

drop table if exists customer_info;

create table customer_info (
	"first_name" varchar(20) not null,
	"last_name" varchar(20) not null,
	"email" varchar(40) primary key,
	"password" varchar(10) not null,
	"date_of_birth" varchar(20) not null
);

drop table if exists account_info;

create table account_info (
	id serial primary key,
	"account_number" varchar(10),
	"account_balance" int not null,
	"account_type" varchar(10) not null,
	"user_email" varchar(100) not null,
	"memo" varchar(100) not null
);

-- Linking Tables in SQL
alter table account_info
add constraint fk_user_email
foreign key(user_email) references customer_info(email);

-- Step #3 is to insert data into your table. This part is incredibly tedious without something like Mockaroo :-).

insert into customer_info values ('Megan', 'Lewis', 'meganlewis@gmail.com', '783254@', '1/26/1987');
insert into customer_info values ('Romane', 'Robb', 'robb@mail.com', 'password', '1/27/1901');
insert into customer_info values ('Jennifer', 'Connelly', 'jenniferc@yahoo.com', 'n89thtg!2', '6/15/2001');
-- insert into customer_info values ('Richard', 'Hendrix', 'richhendr45@aol.com', '&%yty5tg!2', '12/2/1975');
-- insert into customer_info values ('Judas', 'Priest', 'jpriest@gmail.com', 'thaou%$#12', '7/21/2006');
insert into customer_info values ('Seyma', 'Kiris', 'SeymaKiris@goldman.com', 'eastcoastk', '6/1/1986');
insert into customer_info values ('Tony', 'Montana', 'tonymontana@morganstanley.com', 'five%567!', '9/15/1948');
insert into customer_info values ('Pablo', 'Escobar', 'unknownpe@yahoo.com', 'vival%colu', '12/1/1949');
insert into customer_info values ('KellyAnn', 'Rothstein', 'BeverlyHills20@hotmail.com', 'pe435%*!', '4/1/1938');
insert into customer_info values ('Monica', 'Revenor', 'kellyAnn@jpmorganchase.com', '016@%567!', '3/5/2009');
insert into customer_info values ('Jessica', 'Taylor', 'mr@smithbarney.com', '754$@#789', '11/11/1941');


insert into account_info values (default, '983456287', 8936, 'Savings', 'meganlewis@gmail.com', 'Data to be entered by the customer');
insert into account_info values (default, '515236491', 29738, 'Checking', 'robb@mail.com', 'Data to be entered by the customer');
insert into account_info values (default, '783452873', 7834, 'Savings', 'jenniferc@yahoo.com', 'Data to be entered by the customer');
-- insert into account_info values (default, '673849208', 904, 'Checking', 'richhendr45@aol.com', 'Data to be entered by the customer');
-- insert into account_info values (default, '538451827', 67238, 'Checking', 'jpriest@gmail.com', 'Data to be entered by the customer');
insert into account_info values (default, '673493762', 673, 'Checking', 'SeymaKiris@goldman.com', 'Data to be entered by the customer');
insert into account_info values (default, '189237510', 47382, 'Savings', 'tonymontana@morganstanley.com', 'Data to be entered by the customer');
insert into account_info values (default, '961824920', 109, 'Checking', 'unknownpe@yahoo.com', 'Data to be entered by the customer');
insert into account_info values (default, '278349182', 8923, 'Savings', 'BeverlyHills20@hotmail.com', 'Data to be entered by the customer');
insert into account_info values (default, '761923761', 78453, 'Checking', 'kellyAnn@jpmorganchase.com', 'Data to be entered by the customer');
insert into account_info values (default, '518236491', 26738, 'Savings', 'mr@smithbarney.com', 'Data to be entered by the customer');


-- Step #4 is to run a test to make sure the data you inserted above is actually in your table.

select * from customer_info;
select * from account_info;

-- Step #5 is to go crazy and test for whatever is on your mind :-).

select * from account_info where debit_card = true;

select * from account_info where account_balance = 2500;

select * from account_info where account_balance >50;

select * from account_info where account_balance != 1500;

select count (*) from account_info;

select count (*) from account_info where age >'24';

select * from account_info where transaction_amount >'150';

-- altering table by adding columns.

alter table account_info
add "checking_account" varchar(25);

alter table account_info
add "savings_account" varchar(25);

alter table account_info
add "security_question" varchar(50);

alter table account_info
add "pin_code" varchar(4);

-- updating exiting table.

-- update account_info

set checking_account = '726734781', savings_account = '826739467', security_question = 'Where did you live when you were 12?', pin_code = '5624'
where id = 1;

update account_info
set checking_account = '875315896', savings_account = '487526853', security_question = 'Who was your favorite teacher in high school?', pin_code = '4857'
where id = 2;

update account_info
set checking_account = '257886579', savings_account = '789254783', security_question = 'What is your pet name?', pin_code = '1895'
where id = 3;

update account_info
set checking_account = '157863486', savings_account = '287536815', security_question = 'What is your favorite film?', pin_code = '7854'
where id = 4;

update account_info
set checking_account = '853752189', savings_account = '489637518', security_question = 'What is your favorite ice cream flavor?', pin_code = '6875'
where id = 5;

update account_info
set checking_account = '486578924', savings_account = '675418972', security_question = 'At what age did you learn to ride a bike?', pin_code = '2357'
where id = 6;

update account_info
set checking_account = '487058924', savings_account = '675418872', security_question = 'At what age did you learn to Spanish?', pin_code = '2087'
where id = 7;

update account_info
set checking_account = '487058924', savings_account = '675418872', security_question = 'At what age did you learn to Spanish?', pin_code = '2087'
where id = 7;

-- Delete a row.

DELETE FROM account_info WHERE id = 1;

DELETE FROM customer_info WHERE id = 4;