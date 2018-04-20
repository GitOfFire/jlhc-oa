package com.jlhc.oa.dto.role;

import javax.validation.Valid;
import javax.validation.constraints.Max;

/**
 * @author renzhong
 * @version 1.0
 * @Description
 * @Date: Created in 13:45 2018/1/22 0022
 */
public class RoleAndRoleGroupId {

    @Valid
    private Role role;

    @Max(999999999)
    private Integer roleGroupId;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getRoleGroupId() {
        return roleGroupId;
    }

    public void setRoleGroupId(Integer roleGroupId) {
        this.roleGroupId = roleGroupId;
    }

    @Override
    public String toString() {
        return "RoleAndRoleGroupId{" +
                "role=" + role +
                ", roleGroupId=" + roleGroupId +
                '}';
    }
}
