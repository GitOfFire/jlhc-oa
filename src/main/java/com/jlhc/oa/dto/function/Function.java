package com.jlhc.oa.dto.function;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Function implements Serializable {

    @Max(999999999)
    private Integer funcId;
    @Length(min = 1,max = 125)
    @NotBlank
    private String funcName;
    @Length(max = 127)
    private String funcDescription;
    @Max(999999999)
    @NotNull
    private Integer moduleId;

    private Module module;

    private Boolean isSelect;

    private static final long serialVersionUID = 1L;

    public boolean equalsById(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
//        if (!super.equals(o)) return false;
        Function function = (Function) o;
        return Objects.equals(funcId, function.funcId);
    }
}
