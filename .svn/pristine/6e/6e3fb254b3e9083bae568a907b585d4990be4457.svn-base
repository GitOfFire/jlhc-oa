package com.jlhc.oa.dao;

import com.jlhc.oa.dto.function.Right;
import com.jlhc.oa.dto.function.example.RightExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RightMapper {
    int countByExample(RightExample example);

    int deleteByExample(RightExample example);

    int deleteByPrimaryKey(Integer rightId);

    int insert(Right record);

    int insertSelective(Right record);

    List<Right> selectByExample(RightExample example);

    Right selectByPrimaryKey(Integer rightId);

    int updateByExampleSelective(@Param("record") Right record, @Param("example") RightExample example);

    int updateByExample(@Param("record") Right record, @Param("example") RightExample example);

    int updateByPrimaryKeySelective(Right record);

    int updateByPrimaryKey(Right record);

    Integer insertSelectiveNotExistRightData(Right right);

    List<Right> selectByFuncId(Integer funcId);

    List<String> selectRightDatasByUserId(Integer userId);
}