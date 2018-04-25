package com.jlhc.oa.service.impl;

import com.jlhc.common.exception.ApplicationStartupVaildException;
import com.jlhc.common.exception.NullEntityInDatabaseException;
import com.jlhc.oa.dao.FuncRoleRelationMapper;
import com.jlhc.oa.dao.FunctionMapper;
import com.jlhc.oa.dao.ModuleMapper;
import com.jlhc.oa.dao.RoleMapper;
import com.jlhc.oa.dto.function.*;
import com.jlhc.oa.dto.function.example.FuncRoleRelationExample;
import com.jlhc.oa.dto.function.example.FunctionExample;
import com.jlhc.oa.dto.function.example.ModuleExample;
import com.jlhc.oa.dto.role.Role;
import com.jlhc.oa.dto.role.example.RoleExample;
import com.jlhc.oa.dto.role.RoleIdAndFuncIds;
import com.jlhc.oa.dto.role.RoleIdAndModuleId;
import com.jlhc.oa.service.FunctionService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Size;
import java.util.*;

@Service
public class FunctionServiceImpl extends BaseServiceImpl implements FunctionService {

    /**功能DAO*/
    @Autowired
    FunctionMapper functionMapper;
    /**角色DAO*/
    @Autowired
    RoleMapper roleMapper;
    /**模块DAO*/
    @Autowired
    ModuleMapper moduleMapper;
    /**功能角色关系DAO*/
    @Autowired
    FuncRoleRelationMapper funcRoleRelationMapper;

//    private ModuleExample moduleExample = new ModuleExample();
//
//    private FunctionExample functionExample = new FunctionExample();
//
//    private RoleExample roleExample = new RoleExample();
//
//    private FuncRoleRelationExample funcRoleRelationExample = new FuncRoleRelationExample();
    @Override
    public Map<String,HashSet<Function>> queryFunctionByRoleId(Integer roleId) throws NullEntityInDatabaseException {
        //数据校验
        if (null == roleId){
            return null;
        }
        Role role = roleMapper.selectByPrimaryKey(roleId);
        if (null == role){
            return null;
        }

        //开始查询功能
        //查询角色所有的function
        //List<Function> functionsOfRole = functionMapper.selectByRoleId(roleId);
        FunctionExample functionExample = new FunctionExample();
        functionExample.clear();
        //所有的func集合
        List<Function> allFunctions = functionMapper.selectByExample(functionExample);
        if (0 >= allFunctions.size() || null == allFunctions){
            return null;
        }
        //Func集合中取出ALl模块集合拿出来
        HashSet<Integer> allModuleIds = new HashSet<>();
        for (Function function : allFunctions) {
            Integer moduleId = function.getModuleId();
            allModuleIds.add(moduleId);
        }




        //查到角色拥有的func
        List<Function> functionsOfRole = functionMapper.selectByRoleId(roleId);
        //比对,设置拥有的角色
        for (Function function : allFunctions) {
            function.setIsSelect(false);
            for (Function functionOfRole : functionsOfRole) {
                Boolean isSelected = functionOfRole.equalsById(function);

                if (isSelected){

                    function.setIsSelect(isSelected);
                    break;
                }
            }
        }

        //根据不同的模块传回前端去
        Map<String,HashSet<Function>> map =
                new HashMap<>();
        for (Function function : allFunctions) {
            Integer moduleId = function.getModuleId();
            Module module = moduleMapper.selectByPrimaryKey(moduleId);
            if (null == module){
                throw new NullEntityInDatabaseException();
            }
            String moduleName = module.getModuleName();
            if(!map.containsKey(moduleName)){
                HashSet<Function> theseFunctionsOfModule = new HashSet<Function>();
                theseFunctionsOfModule.add(function);
                map.put(moduleName,theseFunctionsOfModule);
            }
            HashSet<Function> theseFunctionsOfModule = map.get(moduleName);
            theseFunctionsOfModule.add(function);
            map.put(moduleName,theseFunctionsOfModule);
        }
        return map;
    }

    /**
     * 修改某个角色单一模块的functions
     *
     * @param roleIdAndFuncIds
     * @return 修改数据库的条数,其中-3表示数据不存在,-4表示数据来自两个不同的模块
     */
    @Override
    public Integer reworkRoleFuncRelations(RoleIdAndFuncIds roleIdAndFuncIds) {
        //数据校验,看看数据库中是否有这几条数据
        Integer roleId = roleIdAndFuncIds.getRoleId();
        Role role = roleMapper.selectByPrimaryKey(roleId);

        //返回无此数据提示
        if (role == null){
            return -3;
        }
        List<Integer> funcIds = roleIdAndFuncIds.getFuncIds();


        Boolean isEquals = false;
        //校验func存在性
        FunctionExample functionExample = new FunctionExample();
        functionExample.clear();
        List<Function> allFunctions = functionMapper.selectByExample(functionExample);
        List<Integer> allFunctionIds = this.getFuncIds(allFunctions);
        isEquals = allFunctionIds.containsAll(funcIds);
        //返回无此数据提示
        if (!isEquals){
            return -3;
        }
        //根据FuncId集合获取其相应的moduleId集合,如果发现属于两个模块,我们不予受理,返回-4
        List<Integer> moduleIds = functionMapper.getFunctionModuleIdsByByIdListParams(funcIds);
        if(1 < new HashSet<Integer>(moduleIds).size()){
            return -4;
        }
        //System.out.println("模块ID呀!!!!!:"+moduleIds.get(0));
        //插入数据库,给roleFunctionRelation建立关系就可以
        //查询某一模块的所有功能ID
        functionExample.clear();
        functionExample.createCriteria()
                .andModuleIdEqualTo(moduleIds.get(0));
        //传来module的所有func
        List<Function> functionsOftheModule = functionMapper.selectByExample(functionExample);//有问题
        //logger.info("传来模块的function"+functionsOftheModule);
        List<Integer> functionsIdsOftheModule = getFuncIds(functionsOftheModule);

        //查询某一角色的所有功能ID
        List<Function> functionsOftheRole = functionMapper.selectByRoleId(roleId);
        List<Integer> functionsIdsOftheRole = getFuncIds(functionsOftheRole);
        //取交集
        functionsIdsOftheModule.retainAll(functionsIdsOftheRole);
        //logger.info("取交集的结果:"+functionsIdsOftheModule);
        //换个新声明
        List<Integer> functionsIdsOftheRoleAndtheModule = functionsIdsOftheModule;
        //logger.info("前端传过来的某一角色某一模块的功能集合functionsIdsOftheRoleAndtheModule:"+functionsIdsOftheRoleAndtheModule.toString());
        //来一次copy
        List<Integer> noChangeRelationFuncIds = new ArrayList<>();
        noChangeRelationFuncIds.addAll(functionsIdsOftheRoleAndtheModule);

        //求出不需要改变关系的功能id
        noChangeRelationFuncIds.retainAll(funcIds);
        //logger.info("不需要改变关系的功能id...noChangeRelationFuncIds:"+noChangeRelationFuncIds.toString());
        //再来一次copy
        List<Integer> needDelRelationFuncIds = new ArrayList<>();
        needDelRelationFuncIds.addAll(functionsIdsOftheRoleAndtheModule);
        //求出需要删掉关系的功能Id
        needDelRelationFuncIds.removeAll(noChangeRelationFuncIds);
        //logger.info("需要删掉关系的功能Id...needDelRelationFuncIds"+needDelRelationFuncIds.toString());
        //求出需要添加关系的功能Ids
        List<Integer> needAddRelationFuncIds = new ArrayList<>();
        needAddRelationFuncIds.addAll(funcIds);
        needAddRelationFuncIds.removeAll(noChangeRelationFuncIds);
        //logger.info("需要添加关系的功能Ids...needAddRelationFuncIds"+needAddRelationFuncIds.toString());
        Integer resultNum = 0;
        //调用删除关系方法
        if (!(null == needDelRelationFuncIds || 0 == needDelRelationFuncIds.size())){
            FuncRoleRelationExample funcRoleRelationExample = new FuncRoleRelationExample();
            //批量删除关系
            funcRoleRelationExample.clear();
            funcRoleRelationExample.createCriteria()
                    .andRoleIdEqualTo(roleId)
                    .andFuncIdIn(needDelRelationFuncIds);
            resultNum += funcRoleRelationMapper.deleteByExample(funcRoleRelationExample);
        }

        //调用添加关系方法
        if (!(null == needAddRelationFuncIds || 0 == needAddRelationFuncIds.size())){
            //logger.info("开始添加了:"+needAddRelationFuncIds);
            FuncRoleRelation addFuncRoleRelation = new FuncRoleRelation();
            for (Integer needAddRelationFuncId : needAddRelationFuncIds) {
                addFuncRoleRelation.setRoleId(roleId);
                addFuncRoleRelation.setFuncId(needAddRelationFuncId);
                resultNum += funcRoleRelationMapper.insert(addFuncRoleRelation);
            }
        }

        return resultNum;
    }

    /**
     * 删除某一角色的某一模块的所有功能
     *
     * @param roleIdAndModuleId
     * @return
     */
    @Override
    public Integer dropRoleFunctionAllRelationOfModule(RoleIdAndModuleId roleIdAndModuleId) {
        Integer resultNum = 0;
        //首先进行数据存在性校验
        Integer roleId = roleIdAndModuleId.getRoleId();
        RoleExample roleExample = new RoleExample();
        roleExample.clear();
        roleExample.createCriteria()
                .andRoleIdEqualTo(roleId);
        int roleCount = roleMapper.countByExample(roleExample);
        if (0 >= roleCount){
            return -3;
        }else if (1 < roleCount){
            return -2;
        }
        Integer moduleId = roleIdAndModuleId.getModuleId();
        ModuleExample moduleExample = new ModuleExample();
        moduleExample.clear();
        moduleExample.createCriteria()
                .andModuleIdEqualTo(moduleId);
        int moduleCount = moduleMapper.countByExample(moduleExample);
        if (0 >= moduleCount){
            return -3;
        }else if (1 < moduleCount){
            return -2;
        }
        //根据module查询function
        FunctionExample functionExample = new FunctionExample();
        functionExample.clear();
        functionExample.createCriteria()
                .andModuleIdEqualTo(moduleId);
        List<Function> needDelFunctionsOfModule = functionMapper.selectByExample(functionExample);
        List<Integer> needDelFunctionIdsOfModule = this.getFuncIds(needDelFunctionsOfModule);
        //查到所有要删除的relation
        FuncRoleRelationExample funcRoleRelationExample = new FuncRoleRelationExample();
        funcRoleRelationExample.clear();
        funcRoleRelationExample.createCriteria()
                .andRoleIdEqualTo(roleId)
                .andFuncIdIn(needDelFunctionIdsOfModule);
        List<FuncRoleRelation> needDelfuncRoleRelations = funcRoleRelationMapper.selectByExample(funcRoleRelationExample);
        if (null == needDelfuncRoleRelations || 0 >= needDelfuncRoleRelations.size()){
            return 0;
        }
        //执行进行删除
        List<Integer> relIds = this.getRelIds(needDelfuncRoleRelations);
        for (Integer relId : relIds) {

            resultNum += funcRoleRelationMapper.deleteByPrimaryKey(relId);
        }
        return resultNum;
    }

    /**
     * 超级管理员使用,可能无页面,同一个模块不能创建同一个名称的func
     *
     * @param function
     * @return
     */
    @Override
    public Integer createFunction(Function function) {
        Integer result = 0;
        if (null == function){
            return result;
        }

        result = functionMapper.insertNotExistTheSameName(function);
        return result;
    }

    /**
     * 根据功能名模糊查询功能
     *
     * @param sulrName
     * @return
     */
    @Override
    public List<Function> queryFunctionBySulrFuncName(String sulrName) {
        if (null == sulrName||"".equals(sulrName)){
            return null;
        }
        FunctionExample functionExample = new FunctionExample();
        functionExample.clear();
        functionExample.createCriteria()
                .andFuncNameLike(sulrName);
        List<Function> functions = functionMapper.selectByExample(functionExample);
        return functions;
    }

    /**
     * 查询角色和用户的关系,根据角色Id
     *
     * @param roleId
     * @return
     */
    @Override
    public List<FuncRoleRelation> queryFuncRoleRelationsOfRole(Integer roleId) {
        if (null == roleId){
            return null;
        }
        FuncRoleRelationExample funcRoleRelationExample = new FuncRoleRelationExample();
        funcRoleRelationExample.clear();
        funcRoleRelationExample.createCriteria()
                .andRoleIdEqualTo(roleId);
        List<FuncRoleRelation> relations = funcRoleRelationMapper.selectByExample(funcRoleRelationExample);
        return relations;
    }

    /**
     * 根据角色Id删除----功能与角色的关系
     *
     * @param roleId
     * @return
     */
    @Override
    public Integer dropRoleFunctionRelationsOfRole(Integer roleId) {
        if (null == roleId){
            return 0;
        }
        FuncRoleRelationExample funcRoleRelationExample = new FuncRoleRelationExample();
        funcRoleRelationExample.clear();
        funcRoleRelationExample.createCriteria()
                .andRoleIdEqualTo(roleId);
        Integer resultNum = funcRoleRelationMapper.deleteByExample(funcRoleRelationExample);
        return resultNum;
    }

    /**
     * 添加功能和角色的关系啦
     *
     * @param roleId
     * @param createdFuncIds
     * @return -2存在不合理的重复数据-3有func  role  不存在
     */
    @Override
    public Integer createRoleFuncRelations(@NotNull Integer roleId, @Size(min = 1,message = "起码添加上一个") List<Integer> createdFuncIds) {
        Integer resultNum = 0;
        //校验角色是否存在,校验function是否都存在
        Role role = roleMapper.selectByPrimaryKey(roleId);
        if (null == role){
            return -3;
        }
        for (Integer funcId :createdFuncIds ) {
            Function function = functionMapper.selectByPrimaryKey(funcId);
            if (null == function){
                return -3;
            }
            FuncRoleRelationExample funcRoleRelationExample = new FuncRoleRelationExample();
            funcRoleRelationExample.clear();
            funcRoleRelationExample.createCriteria()
                    .andRoleIdEqualTo(roleId)
                    .andFuncIdEqualTo(funcId);
            //判断下数据库中存在这个数据不
            List<FuncRoleRelation> relations = funcRoleRelationMapper.selectByExample(funcRoleRelationExample);
            if (0 < relations.size()){
                continue;
            }else if ( 1 < relations.size()){
                //return -2;
                //有重复数据,我们执行删除
                //去掉一个不删除的
                relations.remove(0);
                for (FuncRoleRelation fr :relations ) {
                    funcRoleRelationMapper.deleteByPrimaryKey(fr.getFuncRoleId());
                }
            }
            //既然不存在,我们创建
            FuncRoleRelation funcRoleRelation = new FuncRoleRelation();
            funcRoleRelation.setRoleId(roleId);
            funcRoleRelation.setFuncId(funcId);
            resultNum += funcRoleRelationMapper.insert(funcRoleRelation);
        }
        return resultNum;
    }

    /**
     * 删除功能和角色的关系
     *
     * @param roleId
     * @param dropedFuncIds
     * @return
     */
    @Override
    public Integer dropRoleFuncRelations(@NotNull Integer roleId, @Size(min = 1,message = "起码删除一个") List<Integer> dropedFuncIds) {
        Integer resultNum = 0;
        //删除直接执行
        for (Integer funcId:dropedFuncIds ) {
            //查询出所有存在的关系,都删除
            FuncRoleRelationExample funcRoleRelationExample = new FuncRoleRelationExample();
            funcRoleRelationExample.clear();
            funcRoleRelationExample.createCriteria()
                    .andFuncIdEqualTo(funcId)
                    .andRoleIdEqualTo(roleId);
            resultNum += funcRoleRelationMapper.deleteByExample(funcRoleRelationExample);
        }
        return resultNum;
    }




    /**
     * 获取某个数据封装对象集合的全部Id,用于对关系的修改
     *
     * @param functions
     * @return
     */
    @NotNull
    private List<Integer> getFuncIds(List<Function> functions) {
        List<Integer> functionsIds= new ArrayList<>();
        for (Function f:functions
                ) {
            functionsIds.add(f.getFuncId());
        }
        return functionsIds;
    }

    /**
     * 得到功能的id
     *
     * @param modules
     * @return
     */
    @NotNull
    private List<Integer> getModIds(List<Module> modules){
        List<Integer> moduleIds = new ArrayList<>();
        for (Module module :modules ) {
            moduleIds.add(module.getModuleId());
        }
        return moduleIds;
    }

    /**
     * 得到relationId
     *
     * @param funcRoleRelations
     * @return
     */
    @NotNull
    private List<Integer> getRelIds(List<FuncRoleRelation> funcRoleRelations){
        List<Integer> relIds = new ArrayList<>();
        for (FuncRoleRelation funcRoleRelation :funcRoleRelations ) {
            relIds.add(funcRoleRelation.getFuncRoleId());
        }
        return relIds;
    }
    /**
     * 比较两个Integer值是否相等
     *
     * @param num1
     * @param num2
     * @return
     */
    private boolean compareInteger(Integer num1,Integer num2){
        if(null == num1 || null == num2){
            return false;
        }

        return num1.equals(num2);
    }
}
