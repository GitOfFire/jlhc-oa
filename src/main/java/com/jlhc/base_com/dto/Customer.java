package com.jlhc.base_com.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Delegate;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Customer implements Serializable {
    @Length(min =32 ,max = 32,message = "主键为32位全球唯一码")
    private String cusId;
    @Length(max = 255 , min = 0)
    private String cusName;
    @Min(0)
    @Max(9)
    private Integer cusGenderType;

    @Length(max = 18)
    private String cusIdCard;
    @Length(max = 255)
    private String cusRemarks;
    @Length(min =32 ,max = 32,message = "主键为32位全球唯一码")
    private String comId;
    @Pattern(regexp = "^1[0-9]{10}$", message = "请输入正确的手机号")
    private String callMain;
    @Length(max = 255)
    private String callSecondary;

    private static final long serialVersionUID = 1L;

}