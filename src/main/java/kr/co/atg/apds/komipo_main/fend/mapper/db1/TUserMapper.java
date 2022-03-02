package kr.co.atg.apds.komipo_main.fend.mapper.db1;

import kr.co.atg.apds.komipo_main.common.constant.Constants;
import kr.co.atg.apds.komipo_main.entity.tobject.T_User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TUserMapper {

        /* Get All User Information */
        @Select("SELECT * " +
                        "FROM " + Constants.AUTH_USER_TABLE_NAME)
        List<T_User> selectAllUsers();

        /* Select One User Information */
        @Select("SELECT TOP 1 * " +
                        "FROM " + Constants.AUTH_USER_TABLE_NAME + " " +
                        "WHERE user_id = #{user_id}")
        T_User selectOneUser(
                        @Param("user_id") String user_id);

        /* Upsert User */
        Integer upsertUser(
                        @Param("tUserList") List<T_User> tUserList);

        Integer deleteUser(
                        @Param("id") Integer id);

        /* Get Primary Key by User Id */
        @Select("SELECT TOP 1 id " +
                        "FROM " + Constants.AUTH_USER_TABLE_NAME + " " +
                        "WHERE user_id = #{user_id}")
        Integer getPkByUserId(
                        @Param("user_id") String user_id);
}
