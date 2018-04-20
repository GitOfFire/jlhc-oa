package com.jlhc.oa.dto.role;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RoleUserRelation implements Serializable {
    @Max(999999999)
    private Integer roleUserId;
    @NotNull
    @Max(999999999)
    private Integer roleId;
    @NotNull
    @Max(999999999)
    private Integer userId;

    private static final long serialVersionUID = 1L;

    private List<Integer> roleIds;

    private List<Integer> UserIds;


}