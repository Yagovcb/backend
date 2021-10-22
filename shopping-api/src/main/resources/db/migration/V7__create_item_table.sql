create table if not exists item	(
           shop_id	            bigserial	  not null ,
           product_identifier	varchar(100)  not null,
           price	            decimal       not null,
           foreign key (shop_id) references shop(id)
);