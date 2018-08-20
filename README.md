## Ambiente 

 Aplicação foi testado com JRE8 e JRE9 no Apache Tomcat 7, 8, 9
 
## MySQL

O projeto usa MySQL e deve existir já o banco `casadocodigo` (sem senha). O JPA foi configurado para dropar e gerar as tables automaticamente ao iniciar (na classe `JPAConfiguration`). Caso queira gerar as tabelas manualmente, abaixo desse README tem os comandos SQL.

## Compilação

 Para compilar immporte o projeto no Eclipse (*Import as Maven Projeto*) ou compile na linha de comando usando Maven:

	mvn clean package

## Profile DEV

O projeto sobe automaticamente ativando o profile "dev". Isso foi configurado através da classe ServletSpringMVC no método onStartup(..).

	servletContext.setInitParameter("spring.profiles.active", "dev");

Para não usar o profile "dev" basta comentar o InitParameter, no entanto é preciso um paramentro de inicialização no Tomcat (dentro das "Run Configurations...")

	 "-Dspring.profiles.active=dev"

## URL e Inicialização

Ao rodar no Eclipse pelo  Tomcat acesse:

	http://localhost:8080/casadocodigo
	
Execute a "URL Mágica" para cadastrar produtos e um usuario padrão (Login: admin@casadocodigo.com.br, Senha: 123456)	

## SQL para geração das tables

Se preferir gerar o banco, seguem os comandos SQL para o banco MySQL:

```SQL
drop table if exists Produto;
drop table if exists Produto_precos;
drop table if exists Usuario_Role;
drop table if exists Role;
drop table if exists Usuario;
create table Produto (id integer not null auto_increment, dataLancamento datetime, descricao varchar(255), paginas integer not null, sumarioPath varchar(255), titulo varchar(255), primary key (id));
create table Produto_precos (Produto_id integer not null, tipo integer, valor decimal(19,2));
create table Role (nome varchar(255) not null, primary key (nome));
create table Usuario (email varchar(255) not null, nome varchar(255), senha varchar(255), primary key (email));
create table Usuario_Role (email varchar(255) not null, role_nome varchar(255) not null);
alter table Produto_precos add constraint FK_hl4xdmygc7v2x607r4rbs6x3a foreign key (Produto_id) references Produto (id);
alter table Usuario_Role add constraint FK_5nbp4m2sk65w2mq9rfn680cx2 foreign key (role_nome) references Role (nome);
alter table Usuario_Role add constraint FK_4w45e3buitnd4f3ok8jdlrqkh foreign key (email) references Usuario (email);
```
