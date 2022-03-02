package kr.co.atg.apds.komipo_main.security.model;

import kr.co.atg.apds.komipo_main.entity.tobject.T_Function;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.ObjectUtils;

import java.util.List;

public class UserContext {
    private String user_name;
    private final List<GrantedAuthority> authorities;
    private final String scope;

    private Long idx;
    private String user_id;
    private String phone_number;
    private String user_status;

    //
    private List<T_Function> functions;

    private UserContext(String user_name,
            List<GrantedAuthority> authorities,
            String scope,
            Long idx,
            String user_id,
            String phone_number,
            String user_status) {
        this.user_name = user_name;
        this.authorities = authorities;
        this.scope = scope;

        this.idx = idx;
        this.user_id = user_id;
        this.phone_number = phone_number;
        this.user_status = user_status;
    }

    public static UserContext create(String user_name, List<GrantedAuthority> authorities, String scope, Long idx,
            String user_id, String phone_number, String user_status) {
        if (ObjectUtils.isEmpty(user_name))
            throw new IllegalArgumentException("Username is blank: " + user_name);
        return new UserContext(user_name, authorities, scope, idx, user_id, phone_number, user_status);
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String username) {
        this.user_name = username;
    }

    public List<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public String getScope() {
        return scope;
    }

    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public List<T_Function> getFunctions() {
        return functions;
    }

    public void setFunctions(List<T_Function> functions) {
        this.functions = functions;
    }

}
