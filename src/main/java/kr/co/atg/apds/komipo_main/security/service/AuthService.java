package kr.co.atg.apds.komipo_main.security.service;

import kr.co.atg.apds.komipo_main.security.mapper.AuthMapper;
import kr.co.atg.apds.komipo_main.security.model.T_admin;
import kr.co.atg.apds.komipo_main.security.model.T_user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    AuthMapper authMapper;

    public T_admin getAdminById(Integer id) {
        return authMapper.selectAdminById(id);
    }

    public T_admin getAdminByAdminId(String admin_id) {
        return authMapper.selectAdminByAdminId(admin_id);
    }

    public T_user getUserById(Integer id) {
        return authMapper.selectUserById(id);
    }

    public T_user getUserByUserId(String user_id) {
        return authMapper.selectUserByUserId(user_id);
    }

}
