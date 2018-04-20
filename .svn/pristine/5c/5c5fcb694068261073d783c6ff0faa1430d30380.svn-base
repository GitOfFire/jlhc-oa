package com.jlhc.oa.dto.function;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class FuncRoleRelation implements Serializable {

    @Max(999999999)
    private Integer funcRoleId;
    @NotNull
    @Max(999999999)
    private Integer funcId;
    @NotNull
    @Max(999999999)
    private Integer roleId;

    private static final long serialVersionUID = 1L;

    public Integer getFuncRoleId() {
        return funcRoleId;
    }

    public void setFuncRoleId(Integer funcRoleId) {
        this.funcRoleId = funcRoleId;
    }

    public Integer getFuncId() {
        return funcId;
    }

    public void setFuncId(Integer funcId) {
        this.funcId = funcId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }


}