package com.jlhc.oa.dto.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.jetbrains.annotations.TestOnly;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 只含有用户基本信息
 * @author renzhong
 * @version 1.0 , 2018-2-11 13:44
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserBase {
    @Max(value = 999999999)
    @NotNull
    private Integer userId;
    //@NotEmpty(message = "账号不能为空")
    //private String userUsername;
    @Email(message = "请输入正确的邮箱")
    private String userEmail;
    @NotEmpty(message = "真实姓名不能为空")
    private String userTruename;
    @Pattern(regexp = "^1[0-9]{10}$", message = "请输入正确的手机号")
    private String userMobile;
    @Max(value = 2, message = "账号没有这种状态")
    @Min(value = 0, message = "账号没有这种状态")
    private Integer userState;
    @Length(max = 64, message = "真实姓名长度小于64")
    private String userPronounce;
    private static final long serialVersionUID = 1L;
}
