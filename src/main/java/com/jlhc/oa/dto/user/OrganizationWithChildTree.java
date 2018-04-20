package com.jlhc.oa.dto.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class OrganizationWithChildTree implements Serializable {
//    /**组织机构ID,唯一标识*/
//    @Max(value = 999999999,message = "最大不能超过9位")
    private Integer orgId;

//    /**父组织机构Id*/
//    @Max(value = 999999999,message = "最大不能超过9位")
//    @NotNull
    private Integer orgParentId;

//    /**组织机构名称*/
//    @Length(min = 1,max = 64)
//    @NotEmpty
    private String orgName;

//    /**组织机构创建时间*/
//    @Past
    private Date orgCreatedtime;

//    /**组织机构描述*/
//    @Length(max = 200)
    private String orgDescription;

    private List<Organization> children;

    private static final long serialVersionUID = 1L;
}
