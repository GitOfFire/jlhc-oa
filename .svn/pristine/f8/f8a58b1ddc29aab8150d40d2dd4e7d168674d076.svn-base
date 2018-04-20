package com.jlhc.oa.dao;

import com.jlhc.oa.dto.role.RoleUserRelation;
import com.jlhc.oa.dto.role.example.RoleUserRelationExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleUserRelationMapper {
    int countByExample(RoleUserRelationExample example);

    int deleteByExample(RoleUserRelationExample example);

    int deleteByPrimaryKey(Integer roleUserId);

    int insert(RoleUserRelation record);

    int insertSelective(RoleUserRelation record);

    List<RoleUserRelation> selectByExample(RoleUserRelationExample example);

    RoleUserRelation selectByPrimaryKey(Integer roleUserId);

    int updateByExampleSelective(@Param("record") RoleUserRelation record, @Param("example") RoleUserRelationExample example);

    int updateByExample(@Param("record") RoleUserRelation record, @Param("example") RoleUserRelationExample example);

    int updateByPrimaryKeySelective(RoleUserRelation record);

    int updateByPrimaryKey(RoleUserRelation record);

    List<Integer> selectRoleIdsByUserId(Integer userId);
}