package com.jlhc.base_com.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Company implements Serializable {

    private String comId;
    @NotEmpty
    @Length(max = 255)
    private String comUnicode;
    @NotEmpty
    @Length(max = 255)
    private String comName;
    @Max(4)
    @Min(1)
    @NotNull
    private Integer comType;
    @NotEmpty
    @Length(max = 255)
    private String comAddress;
    @NotEmpty
    @Length(max = 255)
    private String cusName;
    @NotNull
    @Max(20)
    private Long comCapital;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Past(message="必须是一个过去的日期")//这里是否还要添加其他属性值
    private Date comFoundTime;
    @NotNull
    @Max(20)
    private Long comBusinessTerm;
    @NotEmpty
    @Length(max = 255)
    private String comBussinessScope;

    @Length(max = 255)
    private String comDescription;

    @NotNull
    @Max(9)
    @Min(0)
    private Integer comStateType;//1,正常运营;0,已注销

    private static final long serialVersionUID = 1L;

}