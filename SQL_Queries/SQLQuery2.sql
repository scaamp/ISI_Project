create table crypto (
id bigint,
cryptoName varchar(255),
usdPrice bigint,
eurPrice bigint,
circulatingSupply bigint,
primary key (id))

insert into crypto (id, cryptoName, usdPrice, eurPrice, circulatingSupply) VALUES (1, 'BTC', 24000, 23200, 19269400)

delete from crypto;
delete from marketCap;

select * from crypto;
select * from marketCap;

