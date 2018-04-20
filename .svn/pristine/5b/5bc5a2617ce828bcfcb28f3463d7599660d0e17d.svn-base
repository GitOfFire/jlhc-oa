package com.jlhc.oa.dto.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ChangePasswdInfo {
    @NotNull
    @Max(999999999)
    Integer userId;
    @NotEmpty(message = "密码不能为空") @Length(min = 6, message = "密码不能小于6位")
    String oldPasswords;
    @NotEmpty(message = "密码不能为空") @Length(min = 6, message = "密码不能小于6位")
    String newPasswords;
}
