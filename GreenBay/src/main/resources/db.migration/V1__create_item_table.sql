create table items
(
    item_id     bigint not null auto_increment,
    name        varchar(255),
    description varchar(255),
    photoUrl    varchar(255),
    startingPrice decimal,
    purchasePrice decimal,
    user        bigint,
    primary key (item_id)
);