
-- Exemplo de Cupcakes
-- https://www.cupcakeito.com.br/produtos


-- Primerio - Apagar os dados de todas as tabelas
 
delete from cupcake.tb_itens_venda;
delete from cupcake.tb_venda;
delete from cupcake.tb_cliente;
delete from cupcake.tb_cupcake;
delete from cupcake.tb_sabor;

-- Segundo - Ajustando as Sequences para iniciar com o valor 1

ALTER SEQUENCE cupcake.seq_sabor_id RESTART WITH 1;
ALTER SEQUENCE cupcake.seq_cupcake_id RESTART WITH 1;
ALTER SEQUENCE cupcake.seq_cliente_id RESTART WITH 1;
ALTER SEQUENCE cupcake.seq_venda_id RESTART WITH 1;
ALTER SEQUENCE cupcake.seq_itens_venda_id RESTART WITH 1;


---------------------------------------------------------------------------
----------------         Inserts na tabela Sabor
---------------------------------------------------------------------------

INSERT INTO cupcake.tb_sabor(id_sabor, descricao, status_ativo, data_inclusao)
VALUES ( nextval('cupcake.seq_sabor_id'), 'Brigadeiro', true ,  now() );

INSERT INTO cupcake.tb_sabor(id_sabor, descricao, status_ativo, data_inclusao)
VALUES ( nextval('cupcake.seq_sabor_id'), 'Baunilha', true ,  now() );

INSERT INTO cupcake.tb_sabor(id_sabor, descricao, status_ativo, data_inclusao)
VALUES ( nextval('cupcake.seq_sabor_id'), 'Chococake', true ,  now() );

INSERT INTO cupcake.tb_sabor(id_sabor, descricao, status_ativo, data_inclusao)
VALUES ( nextval('cupcake.seq_sabor_id'), 'Chocoblanc', true ,  now() );

INSERT INTO cupcake.tb_sabor(id_sabor, descricao, status_ativo, data_inclusao)
VALUES ( nextval('cupcake.seq_sabor_id'), 'Churros', true ,  now() );

INSERT INTO cupcake.tb_sabor(id_sabor, descricao, status_ativo, data_inclusao)
VALUES ( nextval('cupcake.seq_sabor_id'), 'Prestígio', true ,  now() );

INSERT INTO cupcake.tb_sabor(id_sabor, descricao, status_ativo, data_inclusao)
VALUES ( nextval('cupcake.seq_sabor_id'), 'Lemon Aid', true ,  now() );

INSERT INTO cupcake.tb_sabor(id_sabor, descricao, status_ativo, data_inclusao)
VALUES ( nextval('cupcake.seq_sabor_id'), 'Banana Nutz', true ,  now() );

INSERT INTO cupcake.tb_sabor(id_sabor, descricao, status_ativo, data_inclusao)
VALUES ( nextval('cupcake.seq_sabor_id'), 'Chocolate Vegano', true ,  now() );

INSERT INTO cupcake.tb_sabor(id_sabor, descricao, status_ativo, data_inclusao)
VALUES ( nextval('cupcake.seq_sabor_id'), 'Chocolate Diet', true ,  now() );

INSERT INTO cupcake.tb_sabor(id_sabor, descricao, status_ativo, data_inclusao)
VALUES ( nextval('cupcake.seq_sabor_id'), 'Cerveja Preta', true ,  now() );

INSERT INTO cupcake.tb_sabor(id_sabor, descricao, status_ativo, data_inclusao)
VALUES ( nextval('cupcake.seq_sabor_id'), 'Bicho-de-pé', true ,  now() );


---------------------------------------------------------------------------
----------------         Inserts na tabela Cupcake
---------------------------------------------------------------------------

INSERT INTO cupcake.tb_cupcake(id_cupcake, id_sabor, descricao, quantidade_estoque, preco, status_ativo, data_inclusao)
VALUES (nextval('cupcake.seq_cupcake_id'), (select id_sabor from cupcake.tb_sabor where descricao = 'Brigadeiro'  ), 
'Bolo de chocolate com cobertura de brigadeiro de verdade, feito com Leite Moça!', 1000, 10, true, now() );

INSERT INTO cupcake.tb_cupcake(id_cupcake, id_sabor, descricao, quantidade_estoque, preco, status_ativo, data_inclusao)
VALUES (nextval('cupcake.seq_cupcake_id'), (select id_sabor from cupcake.tb_sabor where descricao = 'Baunilha'  ), 
'Bolinho de baunilha e cacau e cobertura de cream cheese frosting Philadelphia.', 950, 13, true, now() );

INSERT INTO cupcake.tb_cupcake(id_cupcake, id_sabor, descricao, quantidade_estoque, preco, status_ativo, data_inclusao)
VALUES (nextval('cupcake.seq_cupcake_id'), (select id_sabor from cupcake.tb_sabor where descricao = 'Chococake'  ), 
'Bolo de chocolate com recheio e cobertura de ganache de chocolate meio amargo.', 900, 11, true, now() );

INSERT INTO cupcake.tb_cupcake(id_cupcake, id_sabor, descricao, quantidade_estoque, preco, status_ativo, data_inclusao)
VALUES (nextval('cupcake.seq_cupcake_id'), (select id_sabor from cupcake.tb_sabor where descricao = 'Chocoblanc'  ), 
'Bolo de baunilha com recheio de geleia caseira de frutas vermelhas e cobertura de ganache de chocolate branco.', 950, 12, true, now() );

INSERT INTO cupcake.tb_cupcake(id_cupcake, id_sabor, descricao, quantidade_estoque, preco, status_ativo, data_inclusao)
VALUES (nextval('cupcake.seq_cupcake_id'), (select id_sabor from cupcake.tb_sabor where descricao = 'Churros'  ), 
'Bolinho de baunilha e canela com cobertura e recheio de doce de leite, açúcar e canela. É igual e nem é frito!', 1000, 13, true, now() );

INSERT INTO cupcake.tb_cupcake(id_cupcake, id_sabor, descricao, quantidade_estoque, preco, status_ativo, data_inclusao)
VALUES (nextval('cupcake.seq_cupcake_id'), (select id_sabor from cupcake.tb_sabor where descricao = 'Prestígio'  ), 
'Bolinho de chocolate com recheio de coco e cobertura de ganache de chocolate meio amargo e coco.', 900, 13, true, now() );

INSERT INTO cupcake.tb_cupcake(id_cupcake, id_sabor, descricao, quantidade_estoque, preco, status_ativo, data_inclusao)
VALUES (nextval('cupcake.seq_cupcake_id'), (select id_sabor from cupcake.tb_sabor where descricao = 'Lemon Aid'  ), 
'Bolo de baunilha com limão com recheio de mousse de limão e cobertura de lemon buttercream e raspas de limão.', 950, 10, true, now() );

INSERT INTO cupcake.tb_cupcake(id_cupcake, id_sabor, descricao, quantidade_estoque, preco, status_ativo, data_inclusao)
VALUES (nextval('cupcake.seq_cupcake_id'), (select id_sabor from cupcake.tb_sabor where descricao = 'Banana Nutz'  ), 
'Bolo fofinho de banana com nozes e cobertura e recheio de doce de leite com castanha de caju. O tradicional!', 900, 12, true, now() );

INSERT INTO cupcake.tb_cupcake(id_cupcake, id_sabor, descricao, quantidade_estoque, preco, status_ativo, data_inclusao)
VALUES (nextval('cupcake.seq_cupcake_id'), (select id_sabor from cupcake.tb_sabor where descricao = 'Chocolate Vegano'  ), 
'Bolo de chocolate vegano com recheio e cobertura de ganache vegana de chocolate. Muito recomendado!', 1000, 18, true, now() );

INSERT INTO cupcake.tb_cupcake(id_cupcake, id_sabor, descricao, quantidade_estoque, preco, status_ativo, data_inclusao)
VALUES (nextval('cupcake.seq_cupcake_id'), (select id_sabor from cupcake.tb_sabor where descricao = 'Chocolate Diet'  ), 
'Bolo de chocolate com recheio e cobertura de ganache de chocolate ao leite, tudo sem açúcar e seguro para diabéticos.', 850, 18, true, now() );

INSERT INTO cupcake.tb_cupcake(id_cupcake, id_sabor, descricao, quantidade_estoque, preco, status_ativo, data_inclusao)
VALUES (nextval('cupcake.seq_cupcake_id'), (select id_sabor from cupcake.tb_sabor where descricao = 'Cerveja Preta'  ), 
'Bolo de chocolate meio amargo e cerveja Porter com cobertura e recheio de chocolate meio amargo e Bailey.', 1000, 15, true, now() );

INSERT INTO cupcake.tb_cupcake(id_cupcake, id_sabor, descricao, quantidade_estoque, preco, status_ativo, data_inclusao)
VALUES (nextval('cupcake.seq_cupcake_id'), (select id_sabor from cupcake.tb_sabor where descricao = 'Bicho-de-pé'  ), 
'Bolinho de bicho-de-pé e cobertura de bicho-de-pé. Pra quem não é de São Paulo, é um brigadeiro de morango.', 950, 13, true, now() );


---------------------------------------------------------------------------
----------------         Inserts na tabela Cliente
---------------------------------------------------------------------------

INSERT INTO cupcake.tb_cliente(id_cliente, nome, email, cpf, data_nascimento, celular, status_ativo, data_inclusao)
VALUES ( nextval('cupcake.seq_cliente_id') , 'Daniel Dias Almeida', 'daniel.dias.almeida@gmail.com', '09400945094', '1975-10-14', '31956016988', true, now());

INSERT INTO cupcake.tb_cliente(id_cliente, nome, email, cpf, data_nascimento, celular, status_ativo, data_inclusao)
VALUES ( nextval('cupcake.seq_cliente_id') , 'Raissa Goncalves Costa', 'raissa.goncalves.costa@gmail.com', '22310389005', '1981-01-10', '34972472650', true, now());

INSERT INTO cupcake.tb_cliente(id_cliente, nome, email, cpf, data_nascimento, celular, status_ativo, data_inclusao)
VALUES ( nextval('cupcake.seq_cliente_id') , 'Giovanna Melo Martins', 'giovanna.martins@gmail.com', '96799767005', '2000-02-13', '47978372036', true, now());

INSERT INTO cupcake.tb_cliente(id_cliente, nome, email, cpf, data_nascimento, celular, status_ativo, data_inclusao)
VALUES ( nextval('cupcake.seq_cliente_id') , 'Bruno Fernandes Gomes', 'bruno.fernandes.gomes@gmail.com', '35940487050', '1988-04-25', '48930152381', true, now());

INSERT INTO cupcake.tb_cliente(id_cliente, nome, email, cpf, data_nascimento, celular, status_ativo, data_inclusao)
VALUES ( nextval('cupcake.seq_cliente_id') , 'Vitor Gomes Barbosa', 'vitor.gomes.barbosa@gmail.com', '34632408077', '1980-09-30', '11920675857', true, now());


---------------------------------------------------------------------------
----------------         Inserts na tabela Venda e Itens Venda
---------------------------------------------------------------------------

-- Cliente Daniel 09400945094

INSERT INTO cupcake.tb_venda(id_venda, id_cliente, valor_total_venda, status_ativo, data_inclusao)
VALUES ( nextval('cupcake.seq_venda_id') , (select id_cliente from cupcake.tb_cliente where cpf = '09400945094' ) , 3300, true, now() );

INSERT INTO cupcake.tb_itens_venda(id_itens_venda, id_venda, id_cupcake, quantidade, valor, valor_total, status_ativo, data_inclusao)
VALUES ( 
	nextval('cupcake.seq_itens_venda_id') , 
	(select id_venda from cupcake.tb_venda where id_cliente = (select id_cliente from cupcake.tb_cliente where cpf = '09400945094' ) ), 
	(select id_cupcake from cupcake.tb_cupcake where id_sabor = (select id_sabor from cupcake.tb_sabor where descricao = 'Brigadeiro') ), 
	200, 10, 2000, true, now() );
	
INSERT INTO cupcake.tb_itens_venda(id_itens_venda, id_venda, id_cupcake, quantidade, valor, valor_total, status_ativo, data_inclusao)
VALUES ( 
	nextval('cupcake.seq_itens_venda_id') , 
	(select id_venda from cupcake.tb_venda where id_cliente = (select id_cliente from cupcake.tb_cliente where cpf = '09400945094' ) ), 
	(select id_cupcake from cupcake.tb_cupcake where id_sabor = (select id_sabor from cupcake.tb_sabor where descricao = 'Baunilha') ),
	100, 13, 1300, true, now() );	

-- Cliente Raissa 22310389005

INSERT INTO cupcake.tb_venda(id_venda, id_cliente, valor_total_venda, status_ativo, data_inclusao)
VALUES ( nextval('cupcake.seq_venda_id') , (select id_cliente from cupcake.tb_cliente where cpf = '22310389005' ) , 5350, true, now() );

INSERT INTO cupcake.tb_itens_venda(id_itens_venda, id_venda, id_cupcake, quantidade, valor, valor_total, status_ativo, data_inclusao)
VALUES ( 
	nextval('cupcake.seq_itens_venda_id') , 
	(select id_venda from cupcake.tb_venda where id_cliente = (select id_cliente from cupcake.tb_cliente where cpf = '22310389005' ) ), 
	(select id_cupcake from cupcake.tb_cupcake where id_sabor = (select id_sabor from cupcake.tb_sabor where descricao = 'Baunilha') ),
	200, 13, 2600, true, now() );
	
INSERT INTO cupcake.tb_itens_venda(id_itens_venda, id_venda, id_cupcake, quantidade, valor, valor_total, status_ativo, data_inclusao)
VALUES ( 
	nextval('cupcake.seq_itens_venda_id') , 
	(select id_venda from cupcake.tb_venda where id_cliente = (select id_cliente from cupcake.tb_cliente where cpf = '22310389005' ) ), 
	(select id_cupcake from cupcake.tb_cupcake where id_sabor = (select id_sabor from cupcake.tb_sabor where descricao = 'Chococake') ),
	250, 11, 2750, true, now() );
	

-- Cliente Giovanna 96799767005

INSERT INTO cupcake.tb_venda(id_venda, id_cliente, valor_total_venda, status_ativo, data_inclusao)
VALUES ( nextval('cupcake.seq_venda_id') , (select id_cliente from cupcake.tb_cliente where cpf = '96799767005' ) , 6510, true, now() );

INSERT INTO cupcake.tb_itens_venda(id_itens_venda, id_venda, id_cupcake, quantidade, valor, valor_total, status_ativo, data_inclusao)
VALUES ( 
	nextval('cupcake.seq_itens_venda_id') , 
	(select id_venda from cupcake.tb_venda where id_cliente = (select id_cliente from cupcake.tb_cliente where cpf = '96799767005' ) ), 
	(select id_cupcake from cupcake.tb_cupcake where id_sabor = (select id_sabor from cupcake.tb_sabor where descricao = 'Chocoblanc') ),
	210, 12, 2520, true, now() );
	
INSERT INTO cupcake.tb_itens_venda(id_itens_venda, id_venda, id_cupcake, quantidade, valor, valor_total, status_ativo, data_inclusao)
VALUES ( 
	nextval('cupcake.seq_itens_venda_id') , 
	(select id_venda from cupcake.tb_venda where id_cliente = (select id_cliente from cupcake.tb_cliente where cpf = '96799767005' ) ), 
	(select id_cupcake from cupcake.tb_cupcake where id_sabor = (select id_sabor from cupcake.tb_sabor where descricao = 'Churros') ),
	180, 13, 2340, true, now() );
	
INSERT INTO cupcake.tb_itens_venda(id_itens_venda, id_venda, id_cupcake, quantidade, valor, valor_total, status_ativo, data_inclusao)
VALUES ( 
	nextval('cupcake.seq_itens_venda_id') , 
	(select id_venda from cupcake.tb_venda where id_cliente = (select id_cliente from cupcake.tb_cliente where cpf = '96799767005'  ) ), 
	(select id_cupcake from cupcake.tb_cupcake where id_sabor = (select id_sabor from cupcake.tb_sabor where descricao = 'Prestígio') ),
	150, 11, 1650, true, now() );	
	
	
-- Cliente Vitor 34632408077

INSERT INTO cupcake.tb_venda(id_venda, id_cliente, valor_total_venda, status_ativo, data_inclusao)
VALUES ( nextval('cupcake.seq_venda_id') , (select id_cliente from cupcake.tb_cliente where cpf = '34632408077' ) , 2000, true, now() );

INSERT INTO cupcake.tb_itens_venda(id_itens_venda, id_venda, id_cupcake, quantidade, valor, valor_total, status_ativo, data_inclusao)
VALUES ( 
	nextval('cupcake.seq_itens_venda_id') , 
	(select id_venda from cupcake.tb_venda where id_cliente = (select id_cliente from cupcake.tb_cliente where cpf = '34632408077' ) ), 
	(select id_cupcake from cupcake.tb_cupcake where id_sabor = (select id_sabor from cupcake.tb_sabor where descricao = 'Brigadeiro') ), 
	200, 10, 2000, true, now() );



---------------------------------------------------------------------------
----------------         Para Conferir os Inserts
---------------------------------------------------------------------------

/*

select * from cupcake.tb_sabor
select * from cupcake.tb_cupcake
select * from cupcake.tb_cliente
select * from cupcake.tb_venda
select * from cupcake.tb_itens_venda

*/
 
 




		