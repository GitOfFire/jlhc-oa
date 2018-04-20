package com.jlhc.oa.service;

import com.jlhc.common.exception.ApplicationStartupVaildException;
import com.jlhc.common.exception.NullEntityInDatabaseException;
import com.jlhc.oa.dto.function.*;
import com.jlhc.oa.dto.role.RoleIdAndFuncIds;
import com.jlhc.oa.dto.role.RoleIdAndModuleId;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public interface FunctionService {

    Map<String,HashSet<Function>> queryFunctionByRoleId(Integer roleId) throws NullEntityInDatabaseException;

    Integer reworkRoleFuncRelations(RoleIdAndFuncIds roleIdAndFuncIds);

    Integer dropRoleFunctionAllRelationOfModule(RoleIdAndModuleId roleIdAndModuleId);

    Integer createFunction(Function function);

    List<Function> queryFunctionBySulrFuncName(String sulrName);

    List<FuncRoleRelation> queryFuncRoleRelationsOfRole(Integer roleId);

    Integer dropRoleFunctionRelationsOfRole(Integer roleId);

    Integer createRoleFuncRelations(Integer roleId, List<Integer> createdFuncIds);

    Integer dropRoleFuncRelations(Integer roleId, List<Integer> dropedFuncIds);


}
