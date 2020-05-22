create table programas(
	idPrograma serial primary key,
	nomePrograma varchar(50) not null,
	dataAcesso varchar (10) not null,
	horaAcesso	varchar (10) not null
);

create table internet(
	idInternet serial primary key,
	nomePagina varchar(100) not null,
	enderecoPagina varchar (1000) not null,
	dataAcesso varchar (10) not null,
	horaAcesso	varchar (10) not null
);

create table relatorio(
	idRelatorio serial primary key,
	dataRelatorio varchar (10) not null,
	horaRelatorio varchar (10) not null,
	idPrograma int,
	idInternet int,
	constraint fk_programas foreign key (idPrograma) references programas(idPrograma),
	constraint fk_internet foreign key (idInternet) references internet(idInternet)
);

create table login(
	idLogin serial primary key,
	nome varchar(100) not null,
	usuario varchar(100) not null unique,
	dataNascimento date,
	idrelatorio int
); 
alter table login add senha varchar(80)
create table pai (
   idpai serial primary key,
   idlogin int unique,
	cpf varchar(14) not null unique,
   observacao varchar(100),
   situacao varchar(1),
   constraint fk_pai_login foreign key (idlogin) references login
);
alter table pai add situacao varchar(1)
create table filho (
   idFilho serial primary key,
   idlogin int unique,
   descricao varchar(100),
   situacao varchar(1),
   constraint fk_filho_login foreign key (idlogin) references login
);
select * from login;
insert into login values(2, 'teste','teste1', '02/11/2019',4, 'teste');
select * from filho;
insert into pai values(1, 1,'47272579805','teste','A');
insert into programas values(1, 'Steam', '02/11/2019', '14:34');
insert into programas values(2, 'PowerPoint', '02/11/2019', '16:30');
select * from internet;
insert into relatorio values(1, '04/11/2019', '07:56');
Select * from relatorio where idProgramas = 1 order by idRelatorio;
insert into relatorio (datarelatorio, horarelatorio, idprogramas, idinternet) values ('15/11/2019','14:21',1,1);
drop table login;
Select p.*, c.idpai, c.cpf, c.observacao, c.situacao from pai c, login p where c.idlogin = p.idlogin order by idlogin

/* ----- Criacao de Views ----- */
create or replace view usuario as
     select l.idlogin, l.nome, l.cpf, l.login, l.senha, 
            p.idpai as id,'pai' as tipo
     from login l, pai p
     where l.idlogin = p.idlogin and 
           l.situacao = 'A' and
           l.permitelogin = 'S'
     union
     select p.idpessoa, p.nome, p.cpf, p.login, p.senha,
            f.idfornecedor as id,'Fornecedor' as tipo
     from pessoa p, fornecedor f
     where f.idpessoa = p.idpessoa and 
           f.situacao = 'A' and
           f.permitelogin = 'S'
