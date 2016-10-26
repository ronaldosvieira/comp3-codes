create table comodo (
	id serial primary key,
	descricao varchar(255) not null,
	tipo varchar(255) not null
);

create table sala (
	comodo_id int not null references comodo (id)
);

create table quarto (
	comodo_id int not null references comodo (id)
);

create table cozinha (
	comodo_id int not null references comodo (id)
);

create table comodo_composto (
	comodo_id int not null references comodo (id)
);

create table comodo_composto_comodo (
	comodo_composto_id int references comodo_composto (comodo_id),
	comodo_id int references comodo (id),
	primary key (comodo_composto_id, comodo_id)
);

create table mobilia (
	id serial primary key,
	descricao varchar(255) not null,
	custo numeric not null,
	tempo_entrega int not null
);

create table comodo_mobilia (
	comodo_id int not null references comodo (id),
	mobilia_id int not null references mobilia (id),
	primary key (comodo_id, mobilia_id)
);

create table contrato (
	id serial primary key,
	comissao numeric not null
);

create table ambiente (
	id serial primary key,
	contrato_id int not null references contrato (id),
	num_paredes int not null,
	num_portas int not null,
	metragem numeric not null
);

create table item_venda (
	id serial primary key,
	mobilia_id int not null references mobilia (id),
	ambiente_id int not null references ambiente (id),
	quantidade int not null
);
