package kr.co.atg.apds.komipo_main.fend.mapper.db1.auth;

import kr.co.atg.apds.komipo_main.entity.tobject.T_Function;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TFunctionMapper {
        Integer upsertFunction(
                        @Param("functionList") List<T_Function> functionList);

        Integer deleteFunction(
                        @Param("id") Integer id);

        /* Select T_Function DO by ID(pk) */
        @Select("SELECT * " +
                        "FROM t_function " +
                        "WHERE id = #{id}")
        T_Function selectTableObjectFunctionById(
                        @Param("id") Integer id);

        /* Select PrimaryKey by Function Name */
        @Select("SELECT id " +
                        "FROM t_function " +
                        "WHERE name = #{functionName}")
        Integer selectIdByName(
                        @Param("functionName") String functionName);
}
