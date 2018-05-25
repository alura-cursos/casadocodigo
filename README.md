Testado com JRE8 e JRE9, Apache Tomcat 7,8,9

1) Para compilar e empacotar na linha de comando use:

	mvn clean package

2a) Projeto sobe com o profile "dev" configurado através da classe ServletSpring no método onStartup(..)

	servletContext.setInitParameter("spring.profiles.active", "dev");

2b) Para não usar o profile "dev" basta comentar o InitParameter, mas ai é preciso adicionar no Tomcat 
o profile" dentro das "Run Configurations..."

	 "-Dspring.profiles.active=dev"

3) Ao rodar pelo Eclipse e Tomcat acesse 

	http://localhost:8080/casadocodigo

4) Se preferir gerar o banco, seguem os comandos SQL para o banco MySQL:

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