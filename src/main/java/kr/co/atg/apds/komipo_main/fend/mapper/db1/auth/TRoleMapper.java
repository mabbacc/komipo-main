package kr.co.atg.apds.komipo_main.fend.mapper.db1.auth;

import kr.co.atg.apds.komipo_main.entity.tobject.T_Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TRoleMapper {
        Integer upsertRole(
                        @Param("roleList") List<T_Role> roleList);

        Integer deleteRole(
                        @Param("id") Integer id);

        /* Select Authority String */
        @Select("SELECT authority " +
                        "FROM t_role " +
                        "WHERE id = #{id}")
        String selectAuthorityStringById(
                        @Param("id") Integer id);

        /* Select Id by Authority String */
        @Select("SELECT id " +
                        "FROM t_role " +
                        "WHERE authority = #{authority}")
        Integer selectIdByAuthority(
                        @Param("authority") String authority);
}
