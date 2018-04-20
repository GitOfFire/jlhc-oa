package com.jlhc.oa.dao;

import com.jlhc.oa.dto.function.FuncRightRelation;
import com.jlhc.oa.dto.function.example.FuncRightRelationExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncRightRelationMapper {
    int countByExample(FuncRightRelationExample example);

    int deleteByExample(FuncRightRelationExample example);

    int deleteByPrimaryKey(Integer funcRightId);

    int insert(FuncRightRelation record);

    int insertSelective(FuncRightRelation record);

    List<FuncRightRelation> selectByExample(FuncRightRelationExample example);

    FuncRightRelation selectByPrimaryKey(Integer funcRightId);

    int updateByExampleSelective(@Param("record") FuncRightRelation record, @Param("example") FuncRightRelationExample example);

    int updateByExample(@Param("record") FuncRightRelation record, @Param("example") FuncRightRelationExample example);

    int updateByPrimaryKeySelective(FuncRightRelation record);

    int updateByPrimaryKey(FuncRightRelation record);

    List<Integer> selectRightIdsByFuncId(Integer funcId);
}