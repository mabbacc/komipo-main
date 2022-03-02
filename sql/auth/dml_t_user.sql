

insert into t_user (id, user_id, user_pw, user_nm, email, phone_number, user_status, scope, authorities, description )
values
       ( 1, 'administrator', '{bcrypt}$2a$10$.422r/ie1N7qQ65yiQHpreLcSoHd8RQyK50c31fZ7x37Zs6gcH58u', '슈퍼관리자', 'apds@atg.co.kr', '031-555-1111', 2003, 'Read,Write,Trust', 'ROLE_ADMIN', '최고 관리자'),
       ( 2, 'atguser', '{bcrypt}$2a$10$.422r/ie1N7qQ65yiQHpreLcSoHd8RQyK50c31fZ7x37Zs6gcH58u', '일반관리자', 'apds@atg.co.kr', '031-555-1111', 2003, 'Read,Write', 'ROLE_USER', '일반 관리자');
