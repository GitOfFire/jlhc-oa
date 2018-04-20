package com.jlhc.oa.service;

import com.jlhc.common.exception.NullEntityInDatabaseException;
import com.jlhc.oa.dto.user.Organization;
import com.jlhc.oa.dto.user.OrganizationWithChildTree;

import java.util.List;

/**
 * @author renzhong
 * @version 1.0
 * @Description
 * @Date: Created in 16:06 2018/1/9 0009
 */
public interface OrganizationService {
    public Integer createOrganization(Organization organization);
    public List<Organization> findOrgByNameAndOrgParentId(String orgName,Integer orgParentId);
    public List<Organization> findOrgByParent(Integer orgParentId);
    public Integer updateNameOfOrganization(Organization organization);
    public Integer getChildOfRoot(Integer domainId);
    public Integer dropOrgWithChild(Integer orgId) throws NullEntityInDatabaseException;
    public Integer recursionDealWithTheDrop(int resultNum,Integer domainParentId,Integer domainOrgId) throws NullEntityInDatabaseException;
    public List<Integer> queryAllChildrenOrgId(Integer orgId);
    List<Organization> queryParentOrgByOrgId(Integer orgId) throws NullEntityInDatabaseException;
    Organization queryBaseOrgInfo(Integer orgId);
    List<Organization> queryCompanyOrgsWithPageInfo(String orgNameAsCom,Integer offSet, Integer limit);
    boolean isCompanyOrg(Integer orgId);
    Organization queryTheChildrenTreeOfTheOrg(Integer orgId);
    List<Organization> queryTheChildrenOfTheOrg(Integer orgId);
}
