show tables;
create table benefit
(
    id          bigint not null auto_increment,
    description varchar(255),
    name        varchar(255),
    type        varchar(255),
    product_id  bigint,
    primary key (id)
) engine = InnoDB;

create table benefit_requirement
(
    benefit_id     bigint not null,
    requirement_id bigint not null,
    primary key (benefit_id, requirement_id)
) engine = InnoDB;

create table product
(
    id          bigint not null auto_increment,
    description varchar(255),
    name        varchar(255),
    type        varchar(255),
    primary key (id)
) engine = InnoDB;

create table requirement
(
    id              bigint not null auto_increment,
    description     varchar(255),
    inquiry_content varchar(255),
    type            varchar(255),
    primary key (id)
) engine = InnoDB;

create table user
(
    id            bigint not null auto_increment,
    annual_income integer,
    birth         datetime(6),
    job           varchar(255),
    name          varchar(255),
    primary key (id)
) engine = InnoDB;

create table user_requirement
(
    id             bigint not null auto_increment,
    is_satisfied   bit,
    norm_date      date,
    requirement_id bigint,
    user_id        bigint,
    primary key (id)
) engine = InnoDB;

alter table benefit
    add constraint FK8a9nc8kw3adr38tirolk9pn7j
        foreign key (product_id) references product (id);

alter table benefit_requirement
    add constraint FK9ffqkrgx9jwbcocra4b2qfmc7
        foreign key (requirement_id) references requirement (id);

alter table benefit_requirement
    add constraint FK431gt6k0mlhv9s05uv4ylalsb
        foreign key (benefit_id) references benefit (id);

alter table user_requirement
    add constraint FKsoi775u2oa19srmt6b8jpwanl
        foreign key (requirement_id) references requirement (id);

alter table user_requirement
    add constraint FKfmqqhrtko4jfstg12jp8kbtq2
        foreign key (user_id) references user (id);
