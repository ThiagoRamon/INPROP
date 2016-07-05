
--insert into apoio(id, id_recompensa_fk, id_perfil_fk, status, data_cadastro)
--values(null, 7, 40,0, sysdate());




--select * from projeto p inner join recompensa r on r.id_projeto_fk = p.id
--  where p.id =1;
--update apoio set status = 1 where id <> 1 and id <> 2;


select  sum(r.valor) as total  from recompensa  r inner join apoio a   on  a.id_recompensa_fk = r.id  and a.status=0 ;



select  p.id as id_projeto, p.foto, p.apresentacao, p.titulo, p.data_inicial, p.data_final, p.status,
       cat.id as id_categoria,  cat.nome as categoria, p.valor_total,
       per.id as id_perfil, u.id   as id_usuario,  u.nome as usuario,
       e.id   as id_endereco,
       cp.id  as id_cep,
       bar.id as id_bairro,
       cid.id as id_cidade, cid.nome as cidade, est.uf,
       ps.sigla , sum(r.valor) as total
  from projeto p inner join proposta  pro         on pro.id          = p.id_proposta_fk
                 inner join categoria cat         on cat.id          = pro.id_categoria_fk
                 inner join perfil    per         on per.id          =  pro.id_perfil_fk
                 inner join usuario   u           on u.id            = per.id_usuario_fk
                 inner join endereco  e           on e.id_perfil_fk  = per.id
                 inner join cep       cp          on cp.id           = e.id_cep_fk
                 inner join bairro    bar         on bar.id          = cp.id_bairro_fk
                 inner join cidade    cid         on cid.id          = bar.id_cidade_fk
                inner join estado     est         on est.id          = cid.id_estado_fk
                 inner join pais      ps          on ps.id           = est.id_pais_fk
                 inner join recompensa r          on r.id_projeto_fk = p.id
                 inner join apoio      ap          on ap.id_recompensa_fk = r.id and ap.status=0;




--select p.id, p.foto, p.descricao,p.url_video,p.apresentacao,
--       p.titulo, p.data_inicial, p.data_final, p.status
--from projeto p inner join proposta pro         on pro.id         = p.id_proposta_fk
--                 inner join perfil   per         on per.id         =  pro.id_perfil_fk













--select * from apoio;

--select * from recompensa;

select r.valor as v
  from recompensa r inner join apoio ap  on ap.id_recompensa_fk = r.id_recompensa
 group by r.valor;

select p.id_projeto, t.valMult as totalArrecadado
  from projeto p inner join (select id_projeto_fk, count(ap.id_perfil_fk)*r.valor as valMult
                               from recompensa r inner join apoio ap  on ap.id_recompensa_fk = r.id_recompensa
                              ) as t on t.id_projeto_fk = p.id_projeto
where group by p.id_projeto



select  from (select count(ap.id_perfil_fk)*r.valor as valMult
  from recompensa r inner join apoio ap  on ap.id_recompensa_fk = r.id_recompensa
 where id_projeto_fk =  3
 group by r.valor) as t ;


select r.valor,count(ap.id_perfil_fk)as quantApoio, (count(ap.id_perfil_fk)*r.valor)as valMult
  from recompensa r inner join apoio ap  on ap.id_recompensa_fk = r.id_recompensa
 where id_projeto_fk =  2
 group by r.valor;


select (r.valor*count(ap.id_perfil_fk))
  from recompensa r inner join apoio ap  on ap.id_recompensa_fk = r.id_recompensa
 where id_projeto_fk =  1
 group by r.valor;

select p.id_projeto, p.foto_projeto, p.descricao,
       p.titulo, p.data_inicial, p.data_final, p.status,
       per.nome, c.nome_cidade, e.nome_estado
  from projeto p inner join proposta pro         on id_proposta    = id_proposta_fk
                 inner join perfil   per         on id_perfil      =  pro.id_perfil_fk
                 inner join cidade     c         on id_cidade      = per.id_cidade_fk
                 inner join estado     e         on id_estado      = c.id_estado_fk;
























--novo
--A ativo I inativo
create table pais(
id           int primary key auto_increment,
nome         varchar(50)not null unique,
sigla        varchar(5) not null unique,
status       enum('A','I'),
data_cadastro date not null
);

create table estado(
id           int primary key auto_increment,
nome         varchar(50)not null unique,
uf           varchar(5)not null unique,
data_cadastro date not null,
status       enum('A','I'),
id_pais_fk   int not null,
foreign key (id_pais_fk)references pais(id) ON DELETE CASCADE
);
'

create table cidade(
id           int primary key auto_increment,
nome         varchar(50)not null unique,
data_cadastro date not null,
id_estado_fk        int not null,
foreign key (id_estado_fk)references estado(id)  ON DELETE CASCADE
);

create table bairro(
    id           int primary key auto_increment,
    nome         varchar(50)not null unique,
    data_cadastro date not null,
    id_cidade_fk        int not null,
    foreign key (id_cidade_fk)references cidade(id)  ON DELETE CASCADE
);

create table cep(
    id           int primary key auto_increment,
    numero         varchar(50)not null unique,
    tipo           varchar(50),
    logradouro         varchar(100),
    data_cadastro    date not null,
    id_bairro_fk        int not null,
    foreign key (id_bairro_fk)references bairro(id) ON DELETE CASCADE
);

create table endereco(
    id                 int primary key auto_increment,
    numero             varchar(50),
    complemento        varchar(50),
    referencia         varchar(255),
    status             enum('A','I','E','T'),
    data_cadastro      date not null,
    id_cep_fk          int not null,
    foreign key (id_cep_fk)references cep(id) ON DELETE CASCADE,
    id_perfil_fk          int not null,
    foreign key (id_perfil_fk)references perfil(id) ON DELETE CASCADE
);




create table categoria(
id           int primary key auto_increment,
nome                   varchar(50)not null unique,
status                enum('A','I','E','T')not null,
data_cadastro         date not null
);


--tipo usuario= ADM(administrador) , BAS(BASico), EMP(EMPRESA)
--status a(ativo) , i (inativo)
-- genero masculino feminino
create table usuario(
    id                    int primary key auto_increment,
    nome                  varchar(35)not null,
    sobre_nome            varchar(50)not null,
    genero                enum('M','F')not null,
    email                 varchar(80)not null unique,
    senha                 varchar(50)not null ,
    status                enum('A','I','B','E')not null,
    tipo                  enum('ADM','BAS','EMP')not null,
    data_nascimento       date not null,
    data_cadastro         date not null,
    data_confirmacao      date not null
);





create table perfil(
    id                   int primary key auto_increment,
    foto                 varchar(200)not null,
    biografia            text,
    status               enum('A','I','B','E'),
    data_cadastro          date not null,
    id_usuario_fk        int not null unique,
    foreign key (id_usuario_fk)references usuario(id)on delete cascade,
  );


create table contato(
    id                   int primary key auto_increment,
    tel                  varchar(20)not null,
    cel                  varchar(20)not null,
    fax                  varchar(20)not null,
    tel_comercial        varchar(20)not null,
    facebook             varchar(250)not null,
    twitter              varchar(250)not null,
    canal_youtube        varchar(200)not null,
    obs                 varchar(250)not null,
    status              enum('A','B','T','I','E'),
    data_cadastro        date not null,
    id_perfil_fk        int not null unique,
    foreign key (id_perfil_fk)references perfil(id)on delete cascade
  );


create table perfil(
    id                   int primary key auto_increment,
    tel                  varchar(20)not null,
    cel                  varchar(20)not null,
    tel_comercial        varchar(20)not null,
 fax                  varchar(20)not null,
    facebook             varchar(250)not null,
    twitter        varchar(250)not null,
    canal_youtube        varchar(200)not null,
    obs                 varchar(250)not null,
    data_cadastro        date not null,
    id_perfil_fk        int not null unique,
    foreign key (id_perfil_fk)references perfil(id)on delete cascade,
  );



create table perfil_endereco(
    id                   int primary key auto_increment,
    id_perfil_fk        int not null unique,
    foreign key (id_perfil_fk)references perfil(id)on delete cascade,
     id_endereco_fk        int not null unique,
    foreign key (id_endereco_fk)references endereco(id)on delete cascade,

)

/*
descricao : é a descricao sobre o projeto
links     : sao os links para sites que a equipe do inprop´pode encontrar mais
            informaçoes sobre a usuario e seu projeto
valor     : é uma base de a quanto voce precisa para realizar o seu projeto
status    :
            A = proposta em andamento ou aguardando aprovação ou só lida
            N = proposta não lida
            R = proposta reprovada

A=aprovado
R=reprovado
L=lido
N=nao lido
*/
--0 proposta nao lida;
--1 proposta lida e aguardando resposta
--2 proposta aceita  pronta para ser cadastrado o projeto
--3 proposta aceita  com projeto em andamento
--4 proposta aceita  com projeto terminado bem sucedido
--5 proposta aceita  com projeto terminado mal sucedido
--6 proposta  recusada

create table proposta(
id           int primary key auto_increment,
descricao             text not null,
recompensas           text not null,
links                 text not null,
valor_de              int  not null,
valor_ate             int  not null,
data_cadastro         date not null,
status                int not null,
id_categoria_fk       int not null,
foreign key (id_categoria_fk)references categoria(id)on delete cascade,
id_perfil_fk       int not null,
foreign key (id_perfil_fk)references perfil(id)on delete cascade
);


create table pesquisa(
id           int primary key auto_increment,
titulo                varchar(100),
descricao             varchar(200),
data_cadastro         date not null,
);

create table resposta_pesquisa(
id           int primary key auto_increment,
resposta                varchar(255),
data_cadastro         date not null,
id_pesquisa_fk       int,
foreign key (id_pesquisa_fk)references pesquisa(id)on delete cascade

);


--1 projeto em andamento
--2 projeto finalizado e concluido sucesso
--3 projeto finalizado e nao concluido concluido
--4 projeto bloqueado

create table projeto(
id                 int primary key auto_increment,
foto               varchar(50)not null,
url_video          text not null,
titulo             varchar(255)not null unique,
apresentacao       varchar(255) not null,
descricao          text not null,
data_inicial       date not null,
data_final         date not null,
data_cadastro      date not null,
status              int not null,
valor_total        int(12)not null,
id_proposta_fk     int not null unique,
foreign key (id_proposta_fk)references proposta(id)on delete cascade
);

--status
--1 recompensa ativa para serem apoiadas
--2 recompensa que atingiu susa qunatidade total
--3 recompensa inativa para serem apoiadas


create table recompensa(
id                    int primary key auto_increment,
valor                 int not null,
descricao             varchar(250)not null,
quantidade            int not null,
status                int not null,
data_cadastro         date not null,
id_projeto_fk         int not null,
foreign key (id_projeto_fk)references projeto(id)on delete cascade
);

create table foto(
id                    int primary key auto_increment,
nome                  varchar(250) not null,
formato               varchar(5)not null,
tamanho               varchar(5)not null,
data_cadastro         date not null,
id_projeto_fk        int not null,
foreign key (id_projeto_fk)references projeto(id)on delete cascade
);


--0 = pago
--1 = cancelado


create table apoio(
id                      int primary key auto_increment,
id_recompensa_fk        int not null,
foreign key (id_recompensa_fk)references recompensa(id)on delete cascade,
id_perfil_fk           int not null,
foreign key (id_perfil_fk)references perfil(id)on delete cascade,
status                  int not null,
data_cadastro           date not null
);

create table site(
id            int primary key auto_increment,
link_site             varchar(255) not null,
data_cadastro         date not null,
id_perfil_fk          int not null,
foreign key (id_perfil_fk)references perfil(id)on delete cascade
);

create table video(
id               int primary key auto_increment,
url              varchar(255) not null unique,
data_cadastro         date not null,
id_projeto_fk           int not null,
foreign key (id_projeto_fk)references jprojeto(id)on delete cascade
);



 create table comentario(
            id   int primary key auto_increment,
            texto           text,
            data_cadastro         date not null,
            status           enum('A','I','E'),
            id_projeto_fk        int not null,
            foreign key (id_projeto_fk)references projeto(id)on delete cascade,
            id_perfil_fk        int not null,
            foreign key (id_perfil_fk)references perfil(id)on delete cascade
  );










drop  database inprop_db;
create database inprop_db

create table categoria(
id_           int primary key auto_increment,
nome         varchar(50)not null unique
);

create table pais(
id           int primary key auto_increment,
nome         varchar(50)not null unique
sigla        varchar(5)not null unique

);


create table estado(
id           int primary key auto_increment,
nome         varchar(50)not null unique
uf           varchar(50)not null unique
id_pais_fk   int not null,
foreign key (id_pais_fk)references pais(id)
);


create table cidade(
id           int primary key auto_increment,
nome         varchar(50)not null unique,
id_estado_fk        int not null,
foreign key (id_estado_fk)references estado(id)
);

create table bairro(
    id           int primary key auto_increment,
    nome         varchar(50)not null unique,
    id_cidade_fk        int not null,
    foreign key (id_cidade_fk)references cidade(id)
);

create table cep(
    id           int primary key auto_increment,
    numero         varchar(50)not null unique,
    id_bairro_fk        int not null,
    foreign key (id_bairro_fk)references bairro(id)
);

create table endereco(
    id                 int primary key auto_increment,
    logradouro         varchar(100)not null unique,
    numero             varchar(50)not null unique,
    complemento        varchar(50)not null unique,
    referencia         varchar(255)not null unique,
    id_cep_fk          int not null,
    foreign key (id_cep_fk)references cep(id)
);




create table usuario(
id                   int primary key auto_increment,
email                 varchar(70)not null unique,
senha                 varchar(50)not null ,
status                varchar(20)not null,
data_cadastro         date not null,
data_confirmacao      date not null
);






create table perfil(
id                   int primary key auto_increment,
nome                 varchar(70),
foto                 varchar(200)not null,
biografia            text,
genero               varchar(1)not null,
data_nascimento      varchar(30)not null,
id_usuario_fk        int not null unique,
foreign key (id_usuario_fk)references usuario(id),
id_endereco_fk        int,
foreign key (id_endereco_fk)references endereco(id)
);


/*
descricao : é a descricao sobre o projeto
links     : sao os links para sites que a equipe do inprop´pode encontrar mais
            informaçoes sobre a usuario e seu projeto
valor     : é uma base de a quanto voce precisa para realizar o seu projeto
status    :
            A = proposta em andamento ou aguardando aprovação ou só lida
            N = proposta não lida
            R = proposta reprovada
*/
create table proposta(
id_proposta           int primary key auto_increment,
descricao             text not null,
recompensas           text not null,
links                 text not null,
valor                 varchar(30) not null,
data_emissao          date not null,
status                varchar(30)not null,
id_categoria_fk       int not null,
foreign key (id_categoria_fk)references categoria(id_categoria),
id_perfil_fk       int not null,
foreign key (id_perfil_fk)references perfil(id_perfil)
);


create table pesquisa(
id_pesquisa           int primary key auto_increment,
titulo                varchar(100),
descricao             varchar(200),
);
create table resposta_pesquisa(
id_resposta_pesquisa           int primary key auto_increment,
resposta                varchar(255),
id_pesquisa_fk       int,
foreign key (id_pesquisa_fk)references pesquisa(id_pesquisa)

);



create table projeto(
id_projeto         int primary key auto_increment,
foto_projeto        varchar(255)not null,
titulo             varchar(255)not null unique,
descricao          text not null,
data_inicail       date not null,
data_final         date not null,
status             varchar(30)not null,
valor_total        int(12)not null,
id_proposta_fk     int not null unique,
foreign key (id_proposta_fk)references proposta(id_proposta)
);

create table recompensa(
id_recompensa         int primary key auto_increment,
valor                 int not null,
descricao             varchar(250)not null,
status                varchar(4)not null,
id_projeto_fk         int not null,
foreign key (id_projeto_fk)references projeto(id_projeto)
);
create table foto(
id_foto              int primary key auto_increment,
nome                 varchar(250) not null,
status               varchar(5)not null,
data_emissao         varchar(20)not null,
id_projeto_fk        int not null,
foreign key (id_projeto_fk)references projeto(id_projeto)
);



create table apoio(
id_apoio              int primary key auto_increment,
id_recompensa_fk        int not null,
foreign key (id_recompensa_fk)references recompensa(id_recompensa),
id_perfil_fk        int not null,
foreign key (id_perfil_fk)references perfil(id_perfil),
status               varchar(20)not null
);
create table site(
id_site              int primary key auto_increment,
link_site            varchar(255) not null,
id_perfil_fk        int not null,
foreign key (id_perfil_fk)references perfil(id_perfil)
);

create table video(

id_video               int primary key auto_increment,
link_video             varchar(255) not null unique,
data_emissao           varchar(20),
id_projeto_fk           int not null,
foreign key (id_projeto_fk)references jprojeto(id_projeto)
);




    create table comentario(
            id_comentario   int primary key auto_increment,
            texto           text,
            data_emissao     varchar,
            status           varchar,
            id_projeto_fk        int not null,
            foreign key (id_projeto_fk)references projeto(id_projeto),
            id_perfil_fk        int not null,
            foreign key (id_perfil_fk)references perfil(id_perfil)
  );


--1 status conta ativa
--2 status conta ativa

create table conta
(
        id       int primary key auto_increment,
     saldo       double(10,2) not null,
    status       int(2)       not null,
    id_perfil_fk int          not null,
    foreign key (id_perfil_fk)references perfil(id_perfil)
)

--status 1 lancamento efetuado com sucesso
--status 2 lancamento nao foi efetuado
--status 3 lancamento aguardando comfirmacao da empresa
--status 4 lancamento confirmado pela inprop e ok
--status 5 lancamento confirmado pela inprop e nao ok

create table lancamento(
            id   int primary key auto_increment,
         valor     double(10,2),
     descricao     varchar(255),
     data_acao     date,
      status       int,
    id_conta_fk       int,
    foreign key (id_conta_fk) references conta(id)

)









