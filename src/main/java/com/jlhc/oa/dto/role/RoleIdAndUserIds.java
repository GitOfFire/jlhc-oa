package com.jlhc.oa.dto.role;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class RoleIdAndUserIds {
    @NotNull
    @Max(999999999)
    Integer roleId;
    @Size(min = 1)
    List<Integer> userIds;
}
