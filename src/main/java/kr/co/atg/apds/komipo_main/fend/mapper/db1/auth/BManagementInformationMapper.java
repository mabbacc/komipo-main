package kr.co.atg.apds.komipo_main.fend.mapper.db1.auth;

import kr.co.atg.apds.komipo_main.entity.authobject.ApdsAllAuthObject;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BManagementInformationMapper {
    /* Get All Authority tables (t_role, t_role_function, t_function, t_function_api */
    ApdsAllAuthObject selectAllAuthObjects();
}
