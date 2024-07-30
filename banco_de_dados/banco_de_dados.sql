

-- Nome do Banco de Dados: 
-- cupcake_db

-- Configuração do Banco de dados
-- Arquivo: application.properties
-- Diretório: src/main/resources

-- Cria o esquema cupcake
CREATE SCHEMA cupcake;


-- Criação da sequence para a tabela: tb_sabor
create sequence cupcake.seq_sabor_id;

-- Criação da tabela: tb_sabor
CREATE TABLE cupcake.tb_sabor(
    id_sabor bigint NOT NULL,
	descricao character varying(200) NOT NULL,
	status_ativo boolean NOT NULL DEFAULT true,
    data_inclusao timestamp NOT NULL DEFAULT now(),
    data_ultima_alteracao timestamp,

    CONSTRAINT pk_sabor PRIMARY KEY (id_sabor)

);

COMMENT ON TABLE cupcake.tb_sabor    IS 'Tabela utilizada para inserir/ cadastrar os sabores dos cupcakes. Exemplo de Sabores: chocolate, nozes, baunilha, coco, laranja, maçã, cenoura, limão, maracujá ...';



-- Criação da sequence para a tabela: tb_cupcake
create sequence cupcake.seq_cupcake_id;

-- Criação da tabela: tb_cupcake
CREATE TABLE cupcake.tb_cupcake(
    id_cupcake bigint NOT NULL,
	id_sabor bigint NOT NULL,
	descricao character varying(200) NOT NULL,
	quantidade_estoque integer,
	preco numeric,
	status_ativo boolean NOT NULL DEFAULT true,
    data_inclusao timestamp NOT NULL DEFAULT now(),
    data_ultima_alteracao timestamp,

    CONSTRAINT pk_cupcake PRIMARY KEY (id_cupcake),
	CONSTRAINT fk_cupcake_sabor FOREIGN KEY (id_sabor)
        REFERENCES cupcake.tb_sabor (id_sabor)

);

COMMENT ON TABLE cupcake.tb_cupcake    IS 'Tabela utilizada para inserir/ cadastrar os cupcakes.';
    


-- Criação da sequence para a tabela: tb_cliente
create sequence cupcake.seq_cliente_id;

-- Criação da tabela: tb_cliente
CREATE TABLE cupcake.tb_cliente(
    id_cliente bigint NOT NULL,
    nome character varying(200) NOT NULL,
    email character varying(200) NOT NULL,
    cpf character varying(20) NOT NULL,
    data_nascimento timestamp,
    celular character varying(20) NOT NULL,
	status_ativo boolean NOT NULL DEFAULT true,
    data_inclusao timestamp NOT NULL DEFAULT now(),
    data_ultima_alteracao timestamp,
    CONSTRAINT pk_cliente PRIMARY KEY (id_cliente)

);

COMMENT ON TABLE cupcake.tb_cliente    IS 'Tabela utilizada para inserir/ cadastrar os clientes.';
    


-- Criação da sequence para a tabela: tb_venda
create sequence cupcake.seq_venda_id;

-- Criação da tabela: tb_venda
CREATE TABLE cupcake.tb_venda(
    id_venda bigint NOT NULL,
	id_cliente bigint NOT NULL,	
	valor_total_venda numeric,
	status_ativo boolean NOT NULL DEFAULT true,
    data_inclusao timestamp NOT NULL DEFAULT now(),
    data_ultima_alteracao timestamp,

    CONSTRAINT pk_venda PRIMARY KEY (id_venda),
	CONSTRAINT fk_venda_cliente FOREIGN KEY (id_cliente)
        REFERENCES cupcake.tb_cliente (id_cliente)

);

COMMENT ON TABLE cupcake.tb_venda    IS 'Tabela utilizada para cadastrar / manter as vendas.';



-- Criação da sequence para a tabela: tb_itens_venda
create sequence cupcake.seq_itens_venda_id;

-- Criação da tabela: tb_itens_venda
CREATE TABLE cupcake.tb_itens_venda(
    id_itens_venda bigint NOT NULL,
	id_venda bigint NOT NULL,	
	id_cupcake bigint NOT NULL,
	quantidade integer,
	valor numeric,
	valor_total numeric,
	status_ativo boolean NOT NULL DEFAULT true,
    data_inclusao timestamp NOT NULL DEFAULT now(),
    data_ultima_alteracao timestamp,

    CONSTRAINT pk_itens_venda PRIMARY KEY (id_itens_venda),
	CONSTRAINT fk_itens_venda_venda FOREIGN KEY (id_venda)
        REFERENCES cupcake.tb_venda (id_venda),
    CONSTRAINT fk_itens_venda_cupcake FOREIGN KEY (id_cupcake)
        REFERENCES cupcake.tb_cupcake (id_cupcake)

);

COMMENT ON TABLE cupcake.tb_itens_venda    IS 'Tabela utilizada para inserir/ cadastrar os itens da venda. Em uma venda o cliente pode comprar vários itens/ cupcakes de uma vez.';



