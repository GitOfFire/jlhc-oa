package com.jlhc.oa.dto.role;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RoleGroupWithoutState implements Serializable {
    @Max(999999999)
    private Integer groupId;
    @NotNull
    @Length(min = 1, max = 64)
    private String groupName;
    //Max只有九位
    @NotNull
    @Max(999999999)
    private Integer orgId;
}
