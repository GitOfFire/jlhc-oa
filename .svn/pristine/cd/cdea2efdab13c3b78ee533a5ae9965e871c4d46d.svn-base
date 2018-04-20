package com.jlhc.sell.dto;

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

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TaskForPut implements Serializable {
    @NotNull
    @Size(min = 32,max = 32,message = "代理主键为32位全球唯一码")
    private String taskId;
    @Size(min = 32,max = 32,message = "代理主键为32位全球唯一码")
    private String comId;
    @Size(max = 255)
    private String taskContent;
    /*@Max(999999999)
    private Integer taskCreatedUser;
    @CreatedDate
    private Date taskCreatedTime;*/
    @Max(999999999)
    private Integer holdUserId;
    @Max(9)
    @NotNull
    private Integer taskState;
    private static final long serialVersionUID = 1L;
}
