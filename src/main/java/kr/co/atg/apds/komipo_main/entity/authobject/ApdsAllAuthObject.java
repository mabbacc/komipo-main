package kr.co.atg.apds.komipo_main.entity.authobject;

import kr.co.atg.apds.komipo_main.entity.tobject.*;
import lombok.Data;

import java.util.List;

@Data
public class ApdsAllAuthObject {
    List<T_User> allUserList;
    List<T_Role> allRoleList;
    List<T_Role_Function> allRoleFunctionList;
    List<T_Function> allFunctionList;
    List<T_Function_Api> allApiList;
}
