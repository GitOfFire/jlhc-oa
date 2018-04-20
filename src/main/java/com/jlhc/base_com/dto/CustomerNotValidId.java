package com.jlhc.base_com.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CustomerNotValidId  implements Serializable {

    private String cusId;
    @Length(max = 255 , min = 0)
    private String cusName;
    @Min(0)
    @Max(9)
    private Integer cusGenderType;
    @Length(min = 18,max = 18)//去掉对身份证的输入校验
    private String cusIdCard;
    @Length(max = 255)
    private String cusRemarks;
    //@Length(min =36 ,max = 36)//先出现客户,后出现公司,创建客户的时候不能校验所属公司
    private String comId;
    @Pattern(regexp = "^1[0-9]{10}$", message = "请输入正确的手机号")
    private String callMain;
    @Length(max = 255)
    private String callSecondary;

    private static final long serialVersionUID = 1L;
}
