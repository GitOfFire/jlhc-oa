package com.jlhc.sell.dto;

import com.jlhc.base_com.dto.Company;
import com.jlhc.base_com.dto.Customer;
import com.jlhc.oa.dto.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Task implements Serializable {
    @NotNull
    @Size(min = 32,max = 32,message = "代理主键为32位全球唯一码")
    private String taskId;
    @Size(min = 32,max = 32,message = "代理主键为32位全球唯一码")
    private String comId;
    @Size(max = 255)
    private String taskContent;
    @Max(999999999)
    private Integer taskCreatedUser;
    @CreatedDate
    private Date taskCreatedTime;
    @Max(999999999)
    private Integer holdUserId;
    @Max(9)
    @NotNull
    private Integer taskState;

    //负责人具体情况
    private User user;
    //公司相关联系人具体情况
    private List<Customer> customers;
    //公司具体情况
    private Company company;
    //相关工作记录具体情况
    private List<Note> notes;

    private static final long serialVersionUID = 1L;


}