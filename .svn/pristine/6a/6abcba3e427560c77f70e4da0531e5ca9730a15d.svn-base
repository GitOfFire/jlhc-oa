package com.jlhc.oa.dto.function;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RoleIdAndFuncIdWithSelected implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Max(999999999)
    Integer roleId;
    //@NotNull
    @Size(min=1,message = "有要改的再发请求,没有别发")
    List<FuncIdWithSelected> funcIdsWithSelected;
}
