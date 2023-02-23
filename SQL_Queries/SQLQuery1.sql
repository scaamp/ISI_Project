create table wallets (
id bigint,
cryptoName varchar(255),
amount int,
primary key (id),
foreign key (id) references dbo.users(id));

drop table wallets;
drop table users;

select * from dbo.users
select * from dbo.wallets;

delete from dbo.users
delete from dbo.wallets;

create table crypto (
id bigint,
cryptoName varchar(255),
usdPrice bigint,
eurPrice bigint,
circulatingSupply bigint,
primary key (id))

delete from crypto;
delete from marketCap;

select * from marketCap;

drop table dbo.users;
drop table dbo.wallets;

delete from dbo.users;
delete from dbo.wallets

select * from dbo.users;
select * from dbo.wallets;

insert into dbo.wallets (wallet_id, amount, crypto_name, user_id) values (304, 25, 'BTC', 703);