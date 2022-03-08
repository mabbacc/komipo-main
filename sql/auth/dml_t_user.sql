

INSERT INTO public.t_user (id, user_id, user_pw, user_nm, email, phone_number, user_status, authorities, scope, description, crt_dtm, crt_uid, upd_dtm, upd_uid) 
VALUES 
(1, 'administrator', '{bcrypt}$2a$10$.422r/ie1N7qQ65yiQHpreLcSoHd8RQyK50c31fZ7x37Zs6gcH58u', '슈퍼관리자', 'apds@atg.co.kr', '031-555-1111', '2003', 'ROLE_ADMIN', 'Read,Write,Trust', '최고 관리자', '2022-03-02 08:03:58.244333', null, null, null),
(2, 'atguser', '{bcrypt}$2a$10$.422r/ie1N7qQ65yiQHpreLcSoHd8RQyK50c31fZ7x37Zs6gcH58u', '일반관리자', 'apds@atg.co.kr', '031-555-1111', '2003', 'ROLE_USER', 'Read,Write', '일반 관리자', '2022-03-02 08:03:58.244333', null, null, null),
(3, 'komipo_user1', '{bcrypt}$2a$10$.422r/ie1N7qQ65yiQHpreLcSoHd8RQyK50c31fZ7x37Zs6gcH58u', '일반관리자', 'apds@atg.co.kr', '031-555-1111', '2003', 'ROLE_USER', 'Read,Write', '일반 관리자', '2022-03-02 18:32:35.000000', null, null, null);

--
-- const defaultValues = {
--  loginEmail: "komipo_user1",
--  password: "atge0810!"
-- }
