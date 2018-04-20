package com.jlhc.oa.dto.user;

import com.jlhc.oa.dto.role.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AdminUser implements Serializable {

//    @Max(value = 999999999)
//    private Integer userId;
    @Length(min = 1,max = 64)
    @NotEmpty(message = "账号不能为空")
    private String userUsername;
    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, message = "密码不能小于6位")
    private String userPasswd;
    @Email(message = "请输入正确的邮箱")
    private String userEmail;
    @NotEmpty(message = "真实姓名不能为空")
    @Length(max = 64, message = "真实姓名字符串长度小于64")
    private String userTruename;
    @Pattern(regexp = "^1[0-9]{10}$", message = "请输入正确的手机号")
    private String userMobile;
//    @Max(value = 2, message = "账号没有这种状态")
//    @Min(value = 0, message = "账号没有这种状态")
//    private Integer userState;
    @Length(max = 64, message = "基本描述长度小于64")
    private String userPronounce;
    @NotNull
    @Max(999999999)
    private Integer orgId;

//    private String loginTime;
//
//    //!!!!目前缺少数据范围,可以在这里设置
//    private List<String> promissions;
//    /**
//     * 用户角色集合
//     */
//    private Set<Role> roles = new HashSet<>();
//
//    /**
//     * 用户对应的组织机构集合
//     */
//    private Set<Organization> organizations = new HashSet<>();//用户与组织机构多对多,从而灵活配给用户的数据范围

    private static final long serialVersionUID = 1L;
}
