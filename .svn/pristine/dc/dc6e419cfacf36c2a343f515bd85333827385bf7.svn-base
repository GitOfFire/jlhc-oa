package com.jlhc.oa.dto.function;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
public class FuncIdWithSelected implements Serializable {

    private static final long serialVersionUID = 1L;
    @NotNull
    @Max(999999999)
    Integer funcId;
    //@NotNull(message = "我得知道你是要添加功能(true)还是删除功能(false),你写个null,不行!!")
    //默认值为false
    boolean isSelected;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuncIdWithSelected that = (FuncIdWithSelected) o;
        return Objects.equals(funcId, that.funcId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(funcId);
    }
}
