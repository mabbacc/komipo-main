package kr.co.atg.apds.komipo_main.fend.memorybean;

import kr.co.atg.apds.komipo_main.entity.authobject.ApdsAllAuthObject;
import kr.co.atg.apds.komipo_main.entity.tobject.T_Function;
import kr.co.atg.apds.komipo_main.entity.tobject.T_Function_Api;
import kr.co.atg.apds.komipo_main.entity.tobject.T_Role;
import kr.co.atg.apds.komipo_main.entity.tobject.T_Role_Function;
import kr.co.atg.apds.komipo_main.fend.mapper.db1.auth.BManagementInformationMapper;
import kr.co.atg.apds.komipo_main.fend.mapper.db1.auth.TFunctionApiMapper;
import kr.co.atg.apds.komipo_main.fend.mapper.db1.auth.TFunctionMapper;
import kr.co.atg.apds.komipo_main.fend.mapper.db1.auth.TRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;

@Slf4j
@Component
public class UserAuthorityObjectComponent {
    /* Mapper Beans */
    BManagementInformationMapper bManagementInformationMapper;
    TRoleMapper tRoleMapper;
    TFunctionMapper tFunctionMapper;
    TFunctionApiMapper tFunctionApiMapper;

    @Autowired
    public UserAuthorityObjectComponent(
            TRoleMapper tRoleMapper,
            TFunctionMapper tFunctionMapper,
            TFunctionApiMapper tFunctionApiMapper,
            BManagementInformationMapper bManagementInformationMapper) {
        this.tRoleMapper = tRoleMapper;
        this.tFunctionMapper = tFunctionMapper;
        this.tFunctionApiMapper = tFunctionApiMapper;
        this.bManagementInformationMapper = bManagementInformationMapper;
    }

    /*************************************************************************************
     * Private Instances *
     **************************************************************************************/
    private ApdsAllAuthObject apdsAllAuthObject; // All Authority Information Instance

    private Map<String, List<T_Function_Api>> authorityApiMappingMap; // Auth <-> Api PK map (for Interceptor)
    private Map<Integer, List<T_Function>> userFunctionMappingMap; // For JWT Token Generation

    private Map<Integer, List<T_Function>> roleFunctionDataObjectMap; // for front
    private Map<Integer, List<T_Function_Api>> functionApiDataObjectMap; // for front

    private Map<Integer, List<Integer>> roleFunctionMap;
    private Map<Integer, List<Integer>> functionApiMap;

    /*************************************************************************************
     * Private Instances *
     **************************************************************************************/

    @PostConstruct // Executing this method when the application will be run
    @Transactional
    /**
     * @description get all authority information and set instance
     */
    public synchronized void makeAllAuthObjectMapInstance() {
        this.apdsAllAuthObject = bManagementInformationMapper.selectAllAuthObjects();
        if (this.apdsAllAuthObject == null) {
            log.info("Authority Information does not Exist");
            return;
        }
        log.info("Authority Information Fetched");

        /* Make Mapping Map Instance */
        /* 1. Role <-> Function Map */
        _makeRoleFunctionMappingMap();

        /* 2. Function <-> API Map */
        _makeFunctionApiMappingMap();

        /* 3. Make "Authority String : Api PrimaryKey" Mapping */
        _makeAuthorityApiMappingMap();

        /* 4. Make User : Function List Map */
        _makeUserFunctionMappingMap();
    }

    /* Return authority : API Mapping Map */
    public Map<String, List<T_Function_Api>> getAuthorityApiMappingMap() {
        return authorityApiMappingMap;
    }

    /* Return Authority Data Object */
    public ApdsAllAuthObject getApdsAllAuthObject() {
        return apdsAllAuthObject;
    }

    /* Return Authority and T_FunctionList Map */
    public Map<Integer, List<T_Function>> getRoleFunctionDataObjectMap() {
        return roleFunctionDataObjectMap;
    }

    /* Return FunctionName and T_Function_Api List Map */
    public Map<Integer, List<T_Function_Api>> getFunctionApiDataObjectMap() {
        return functionApiDataObjectMap;
    }

    /* Returns Function List for Each Users */
    public List<T_Function> getUserFunctionList(Integer id) {
        if (this.userFunctionMappingMap == null
                || this.userFunctionMappingMap.isEmpty())
            return null;
        return userFunctionMappingMap.get(id);
    }

    /*************************************************************************************
     *
     * Private Methods *
     *
     ************************************************************************************/

    /**
     * @description Make "Role ID : Function ID List" Mapping
     */
    private void _makeRoleFunctionMappingMap() {
        if (this.apdsAllAuthObject.getAllRoleFunctionList() != null) {
            // Iterate All Role Function Mapping Table
            this.roleFunctionMap = new HashMap<>();
            // String Auth : T_Function List
            this.roleFunctionDataObjectMap = new HashMap<>();

            for (T_Role_Function roleFuncObj : this.apdsAllAuthObject.getAllRoleFunctionList()) {
                /*
                 * 이미 map 에 role id 가 키로서 존재하는 경우
                 * FunctionId를 담는 List 가 존재하기 때문에 add 수행
                 */
                if (!this.roleFunctionMap.containsKey(roleFuncObj.getT_roleid())) {
                    this.roleFunctionMap.put(roleFuncObj.getT_roleid(), new ArrayList<>());
                }
                this.roleFunctionMap.get(roleFuncObj.getT_roleid()).add(roleFuncObj.getT_functionid());

                /* Make "String Auth : T_Function List" */
                // get Key value Authority
                // String authority =
                // tRoleMapper.selectAuthorityStringById(roleFuncObj.getT_roleid());
                // if(!this.roleFunctionDataObjectMap.containsKey(authority)){
                // this.roleFunctionDataObjectMap.put(authority, new ArrayList<>());
                // }
                // T_Function functionItem =
                // tFunctionMapper.selectTableObjectFunctionById(roleFuncObj.getT_functionid());
                // this.roleFunctionDataObjectMap.get(authority).add(functionItem);
                if (!this.roleFunctionDataObjectMap.containsKey(roleFuncObj.getT_roleid())) {
                    this.roleFunctionDataObjectMap.put(roleFuncObj.getT_roleid(), new ArrayList<>());
                }
                T_Function functionItem = tFunctionMapper.selectTableObjectFunctionById(roleFuncObj.getT_functionid());
                this.roleFunctionDataObjectMap.get(roleFuncObj.getT_roleid()).add(functionItem);

            }
            log.info("roleFunctionMap(Key: Role ID, Value: Function Id List) Creation Success");
        }
    }

    /**
     * @description Make "Function ID : API ID List" Mapping
     */
    private void _makeFunctionApiMappingMap() {
        if (this.apdsAllAuthObject.getAllApiList() != null) {
            // Iterate All API Table
            this.functionApiMap = new HashMap<>();
            for (T_Function_Api apiFuncObj : this.apdsAllAuthObject.getAllApiList()) {
                /*
                 * 이미 map 에 functionID 가 키로서 존재하는 경우
                 * API id 를 담는 List가 존재하기 때문에 add 수행
                 */
                if (!this.functionApiMap.containsKey(apiFuncObj.getT_functionid())) {
                    this.functionApiMap.put(apiFuncObj.getT_functionid(), new ArrayList<>());
                }
                this.functionApiMap.get(apiFuncObj.getT_functionid()).add(apiFuncObj.getId());
            }
            log.info("functionApiMap(Key: Function Id, Value: API Id List) Creation Success");
        }

        if (this.apdsAllAuthObject.getAllFunctionList() != null) {
            this.functionApiDataObjectMap = new HashMap<>();
            for (T_Function tFunction : this.apdsAllAuthObject.getAllFunctionList()) {
                if (!this.functionApiDataObjectMap.containsKey(tFunction.getId())) {
                    this.functionApiDataObjectMap.put(tFunction.getId(), new ArrayList<>());
                }
                this.functionApiDataObjectMap.get(tFunction.getId()).addAll(
                        (tFunctionApiMapper.selectApiByFunctionId(tFunction.getId())));
            }
        }

    }

    /**
     * @description Make "Authority String : Api PrimaryKey" Mapping
     */
    private void _makeAuthorityApiMappingMap() {
        if (apdsAllAuthObject == null)
            return;
        this.authorityApiMappingMap = new HashMap<>();

        /* iterate all each roles */
        for (T_Role in : this.apdsAllAuthObject.getAllRoleList()) {
            /* Step 1. Authority Role Primary Key */
            List<Integer> apiKeyList = new ArrayList<>();

            // Get Function Key List (role_id, Function Key List)
            if (roleFunctionMap.get(in.getId()) != null
                    && !roleFunctionMap.get(in.getId()).isEmpty()) {
                for (Integer functionKey : roleFunctionMap.get(in.getId())) // Integer 1, 2, ... (PK)
                {
                    if (functionApiMap.get(functionKey) != null
                            && !functionApiMap.get(functionKey).isEmpty()) {
                        // Add Map (ROLE and API)
                        apiKeyList.addAll(functionApiMap.get(functionKey));
                    }
                }
            }

            // Key: "ROLE_ADMIN", Value: role->function->API List
            if (apiKeyList.isEmpty())
                this.authorityApiMappingMap.put(in.getAuthority(), new ArrayList<>());
            else
                this.authorityApiMappingMap.put(in.getAuthority(), tFunctionApiMapper.selectApiByAuthority(apiKeyList));
        }
    }

    /***
     * @description Make User <-> Function Mapping
     */
    private void _makeUserFunctionMappingMap() {
        if (apdsAllAuthObject == null)
            return;
        this.userFunctionMappingMap = new HashMap<>();

        for (var user : this.apdsAllAuthObject.getAllUserList()) {
            Integer id = tRoleMapper.selectIdByAuthority(user.getAuthorities()); // ROLE_ADMIN, ROLE_USER, ...

            if (!this.userFunctionMappingMap.containsKey(user.getId())) {
                if (roleFunctionDataObjectMap.get(id) != null && !roleFunctionDataObjectMap.get(id).isEmpty())
                    this.userFunctionMappingMap.put(user.getId(), roleFunctionDataObjectMap.get(id));
                else
                    this.userFunctionMappingMap.put(user.getId(), null);
            }
        }
    }
}
