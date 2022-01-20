create table if not exists shop_report	(
                       id	    bigserial	primary key,
                       count	integer	    not	null,
                       total    decimal     not	null,
                       media	decimal     not	null
);