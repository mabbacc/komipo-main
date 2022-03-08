create table if not exists t_function
(
    id          serial
        primary key,
    name        varchar(63)
        constraint unique_name
            unique,
    description varchar(2047),
    
    crt_dtm     timestamp with time zone default now() not null,
    crt_uid     integer,
    upd_dtm     timestamp with time zone,
    upd_uid     integer
);

alter table t_function
    owner to k1pledev;

create table if not exists t_function_api
(
    id           serial
        primary key,
    t_functionid integer                                not null
        references t_function
            on update cascade on delete cascade,
    uri          varchar(2047),
    method       varchar(2047),
    description  varchar(2047),
    
    crt_dtm      timestamp with time zone default now() not null,
    crt_uid      integer,
    upd_dtm      timestamp with time zone,
    upd_uid      integer
);

comment on table t_function_api is '기능에 따른 API 목록';

alter table t_function_api
    owner to k1pledev;

create table if not exists t_role
(
    id          serial
        primary key,
    authority   varchar(63)                            not null,
    description varchar(2047),
    
    crt_dtm     timestamp with time zone default now() not null,
    crt_uid     integer,
    upd_dtm     timestamp with time zone,
    upd_uid     integer
);

alter table t_role
    owner to k1pledev;

create table if not exists t_role_function
(
    id           serial
        primary key,
    t_roleid     integer                                not null
        references t_role
            on update cascade on delete cascade,
    t_functionid varchar(255)                           not null,
    description  varchar(2047),
    
    crt_dtm      timestamp with time zone default now() not null,
    crt_uid      integer,
    upd_dtm      timestamp with time zone,
    upd_uid      integer
);

comment on table t_role_function is '기능 기반 권한 설정';

alter table t_role_function
    owner to k1pledev;

create table if not exists t_user
(
    id           serial
        primary key,
    user_id      varchar(255)                           not null
        constraint unique_userid
            unique,
    user_pw      varchar(255)                           not null,
    user_nm      varchar(255)                           not null,
    email        varchar(63),
    phone_number varchar(63),
    user_status  varchar(15),
    authorities  varchar(63),
    scope        varchar(63),
    description  varchar(2047),
    crt_dtm      timestamp with time zone default now() not null,
    crt_uid      integer,
    upd_dtm      timestamp with time zone,
    upd_uid      integer
);

alter table t_user
    owner to k1pledev;

