create table if not exists product	(
            id	bigserial	primary key,
            product_identifier	varchar not	null,
            nome	varchar(100)	not	null,
            descricao	varchar not	null,
            preco	float not	null,
            category_id	bigint REFERENCES category(id)
);