package kr.co.atg.apds.komipo_main.fend.mapper.db1.auth;

import kr.co.atg.apds.komipo_main.entity.tobject.T_Function;
import kr.co.atg.apds.komipo_main.entity.tobject.T_Role_Function;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TRoleFunctionMapper {
    /* upsert by role id and function id */
    Integer upsertRoleFunction(
            @Param("roleFunctionIdList") List<T_Role_Function> roleFunctionIdList);

    /* delete for role function update */
    Integer deleteRoleFunctionForUpdate(
            @Param("tFunctionList") List<T_Function> tFunctionList,
            @Param("tRoleId") Integer tRoleId);
}
