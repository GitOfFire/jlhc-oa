package com.jlhc.sell.dao;

import com.jlhc.sell.dto.TaskUserRelation;
import com.jlhc.sell.dto.example.TaskUserRelationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskUserRelationMapper {
    int countByExample(TaskUserRelationExample example);

    int deleteByExample(TaskUserRelationExample example);

    int deleteByPrimaryKey(String taskUserId);

    int insert(TaskUserRelation record);

    int insertSelective(TaskUserRelation record);

    List<TaskUserRelation> selectByExample(TaskUserRelationExample example);

    TaskUserRelation selectByPrimaryKey(String taskUserId);

    int updateByExampleSelective(@Param("record") TaskUserRelation record, @Param("example") TaskUserRelationExample example);

    int updateByExample(@Param("record") TaskUserRelation record, @Param("example") TaskUserRelationExample example);

    int updateByPrimaryKeySelective(TaskUserRelation record);

    int updateByPrimaryKey(TaskUserRelation record);
}