package com.jlhc.oa.dao;

import com.jlhc.oa.dto.user.Organization;
import com.jlhc.oa.dto.user.example.OrganizationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationMapper {
    int countByExample(OrganizationExample example);

    int deleteByExample(OrganizationExample example);

    int deleteByPrimaryKey(Integer orgId);

    int insert(Organization record);

    int insertSelective(Organization record);

    List<Organization> selectByExample(OrganizationExample example);

    Organization selectByPrimaryKey(Integer orgId);

    int updateByExampleSelective();

    int updateByExample(@Param("record") Organization record, @Param("example") OrganizationExample example);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);

    /**
     * 根据用户id查询当前用户的组织,
     *
     * @param userId
     */
    List<Organization> selectOrganizationByUserId(@Param("userId") Integer userId);

    /**
     * 根据子id查父亲id
     *
     * @param orgChildId
     * @return 父Id
     */
    Integer selectOrgParentIdByOrgChildId(@Param("orgChildId")Integer orgChildId);

    /**
     * 查询子节点
     *
     * @param orgId
     * @return 子节点
     */
    List<Organization> selectChildsByOrgId(Integer orgId);

    /**
     * 查询父节点
     *
     * @param orgIdToUp
     * @return
     */
    List<Integer> selectChildIdsByOrgId(Integer orgIdToUp);
}