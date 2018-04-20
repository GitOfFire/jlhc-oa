package com.jlhc.sell.dao;

import com.jlhc.sell.dto.Flow;
import com.jlhc.sell.dto.example.FlowExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FlowMapper {
    int countByExample(FlowExample example);

    int deleteByExample(FlowExample example);

    int deleteByPrimaryKey(String flowId);

    int insert(Flow record);

    int insertSelective(Flow record);

    List<Flow> selectByExample(FlowExample example);

    Flow selectByPrimaryKey(String flowId);

    int updateByExampleSelective(@Param("record") Flow record, @Param("example") FlowExample example);

    int updateByExample(@Param("record") Flow record, @Param("example") FlowExample example);

    int updateByPrimaryKeySelective(Flow record);

    int updateByPrimaryKey(Flow record);

    int insertFlows(List<Flow> flows);
}