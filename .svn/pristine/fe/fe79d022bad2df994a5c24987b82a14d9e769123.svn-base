package com.jlhc.oa.service;

import com.jlhc.oa.dto.role.RoleGroup;
import com.jlhc.oa.dto.role.RoleGroupWithoutState;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface RoleGroupService {

    List<RoleGroup> findRoleGroupsByRoleGroupNameAndOrgId(Integer groupId ,String roleGroupName,Integer orgId);

    Integer createRoleGroupWithoutTheSameName(RoleGroupWithoutState roleGroupWithoutState);

    List<RoleGroup> findRoleGroups(HttpSession httpSession);

    Integer updateRoleGroupWithoutTheSameName(RoleGroup roleGroup);

    Integer dropRoleGroupById(Integer roleGroupId);

    List<RoleGroup> findRoleGroupsByRoleGroupNameAndOrgId(String roleGroupName,Integer orgId);
}
