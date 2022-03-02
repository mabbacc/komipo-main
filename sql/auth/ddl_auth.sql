create table t_user
(
    id           integer                             not null
        primary key
        constraint id
            unique,
    user_id      varchar(255)                        not null
        unique,
    user_pw      varchar(255)                        not null,
    user_nm      varchar(255)                        not null,
    email        varchar(63),
    phone_number varchar(63),
    user_status  varchar(15),
    authorities  varchar(63),
    scope        varchar(63),
    description  varchar(2047),
    crt_dtm      timestamp default CURRENT_TIMESTAMP not null,
    crt_uid      integer,
    upd_dtm      timestamp,
    upd_uid      integer
);

alter table t_user
    owner to k1pledev;

create table t_function
(
    id          integer not null
        constraint t_function_pk
            primary key,
    name        varchar(63)
        constraint unique_name
            unique,
    description varchar(2047),
    crt_dtm     timestamp default CURRENT_TIMESTAMP,
    crt_uid     integer,
    upd_dtm     timestamp,
    upd_uid     integer
);

alter table t_function
    owner to k1pledev;

create table t_function_api
(
    id           integer                             not null
        constraint t_function_api_pk
            primary key,
    t_functionid integer                             not null
        constraint api_function_id
            references t_function
            on update cascade on delete cascade,
    uri          varchar(2047),
    method       varchar(2047),
    description  varchar(2047),
    crt_dtm      timestamp default CURRENT_TIMESTAMP not null,
    crt_uid      integer,
    upd_dtm      timestamp,
    upd_uid      integer
);

alter table t_function_api
    owner to k1pledev;

create table t_role
(
    id          integer                             not null
        primary key,
    authority   varchar(63)                         not null,
    description varchar(2047),
    crt_dtm     timestamp default CURRENT_TIMESTAMP not null,
    crt_uid     integer,
    upd_dtm     timestamp,
    upd_uid     integer
);

alter table t_role
    owner to k1pledev;

create table t_role_api
(
    id          integer                             not null
        primary key,
    t_roleid    integer                             not null,
    uri         varchar(2047),
    description varchar(2047),
    crt_dtm     timestamp default CURRENT_TIMESTAMP not null,
    crt_uid     integer,
    upd_dtm     timestamp,
    upd_uid     integer
);

alter table t_role_api
    owner to k1pledev;

create table t_role_function
(
    id           integer                             not null
        primary key,
    t_roleid     integer                             not null
        constraint role_id
            references t_role,
    t_functionid integer                             not null
        constraint function_id
            references t_function,
    crt_dtm      timestamp default CURRENT_TIMESTAMP not null,
    crt_uid      integer,
    upd_dtm      timestamp,
    upd_uid      integer
);

alter table t_role_function
    owner to k1pledev;

