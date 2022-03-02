package kr.co.atg.apds.komipo_main.security.mapper;

import kr.co.atg.apds.komipo_main.security.model.T_admin;
import kr.co.atg.apds.komipo_main.security.model.T_user;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuthMapper {

    /* Admin */
    // (A011) 관리자 정보 가져오기(id 값 기준)
    T_admin selectAdminById(@Param("id") Integer id);

    // (A012) 관리자 정보 가져오기(admin_id 값 기준)
    T_admin selectAdminByAdminId(@Param("admin_id") String admin_id);

    /* Admin */
    // (A021) 사용자 정보 가져오기(id 값 기준)
    T_user selectUserById(@Param("id") Integer id);

    // (A022) 사용자 정보 가져오기(user_id 값 기준)
    T_user selectUserByUserId(@Param("user_id") String user_id);

}
