package kr.co.atg.apds.komipo_main.fend.mapper.db1;

import kr.co.atg.apds.komipo_main.entity.tobject.T_Function_Api;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TFunctionApiMapper {
        /*
         * Get All Api related with function
         */
        @Select("SELECT * " +
                        "FROM t_function_api " +
                        "WHERE t_functionid = #{functionId}")
        List<T_Function_Api> selectApiByFunctionId(
                        @Param("functionId") Integer functionId);

        /* Select All t_function_api by PKs */
        List<T_Function_Api> selectApiByAuthority(
                        @Param("apiKeyList") List<Integer> apiKeyList);

        /* Select All FunctionApi */
        List<T_Function_Api> selectAllFunctionApis();

        /* Upsert API to t_function_api */
        Integer upsertFunctionApi(
                        @Param("functionApiList") List<T_Function_Api> functionApiList);

        /* Delete Function Api Row */
        Integer deleteFunctionApi(
                        @Param("tFunctionApiList") List<T_Function_Api> tFunctionApiList);
}
