package com.jlhc.sell.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class NoteForPut implements Serializable {
    private String noteId;

    private String noteContent;

    //private Date noteCreatedTime;

    private String taskId;

    //private Integer userId;

    private static final long serialVersionUID = 1L;
}
