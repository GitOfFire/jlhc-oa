package com.jlhc.sell.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TaskForUpdateHoldUser implements Serializable {
    @NotNull
    @Size(min = 32,max = 32,message = "代理主键为32位全球唯一码")
    String taskId;
    @NotNull
    @Max(999999999)
    Integer holdUserId;
}
