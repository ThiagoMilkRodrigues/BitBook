create database if not exists BitBook_DB;
use BitBook_DB;

create table Transacoes(

ID int not null auto_increment,
Usuario varchar(50),
Tipo varchar(50),
Data_registro datetime,
Moeda varchar(50),
ValorInvestido float,
ValorNaCompra float,
ValorNaVenda float,
DataCompra datetime,
DataVenda datetime,

primary key(ID)
);

/* só para ver se deu tudo certo*/

select * from Transacoes;

create table Usuarios(

ID int not null auto_increment,
Nome varchar(50),
Senha varchar(50),

primary key(ID)
);

insert into Usuarios (Nome, Senha) Values ('teste', '1234');
insert into Usuarios (Nome, Senha) Values ('Thiago Milk', 'milk1967');

select * from Usuarios