package com.jlhc.oa.service.impl;

import com.jlhc.oa.dao.*;
import com.jlhc.oa.dto.function.FuncRightRelation;
import com.jlhc.oa.dto.function.FuncRoleRelation;
import com.jlhc.oa.dto.function.Function;
import com.jlhc.oa.dto.function.Right;
import com.jlhc.oa.dto.function.example.FuncRightRelationExample;
import com.jlhc.oa.dto.function.example.FuncRoleRelationExample;
import com.jlhc.oa.dto.function.example.FunctionExample;
import com.jlhc.oa.dto.function.example.RightExample;
import com.jlhc.oa.dto.right.RightAndFuncId;
import com.jlhc.oa.dto.user.User;
import com.jlhc.oa.service.RightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author renzhong
 * @version 1.0
 * @Description
 * @Date: Created in 16:52 2018/1/4 0004
 */
@Service
public class RightServieImpl extends BaseServiceImpl implements RightService{

    /**注入UserDao*/
    @Autowired
    UserMapper userMapper;

    /**注入roleUserRelationDao*/
    @Autowired
    RoleUserRelationMapper roleUserRelationMapper;

    /**注入funcRoleRelationDao*/
    @Autowired
    FuncRoleRelationMapper funcRoleRelationMapper;

    /**注入funcRightRelationDao*/
    @Autowired
    FuncRightRelationMapper funcRightRelationMapper;

    /**注入rightMapper*/
    @Autowired
    RightMapper rightMapper;

    @Autowired
    FunctionMapper functionMapper;
    /**
     * 查询权限的整体集合
     *
     * @return
     */
    @Override
    public List<Right> getAllRights() {
        return new ArrayList<Right>();
    }

    /**
     * 根据登录用户的Id得到所有的权限
     *
     * @return
     */
    @Override
    public List<String> getRigthsByUserId(@NotNull Integer userId) {
        //数据校验
        if (null == userId){
            return null;
        }
        //验证用户是否存在
        if (this.hasUse(userId)){
            return  null;
        };

        //首先根据用户角色关系表查出所属角色Id
        List<Integer> roleIds = roleUserRelationMapper.selectRoleIdsByUserId(userId);
        //再根据所有角色,查询出来功能Id
        List<Integer> funcIds = new ArrayList<>();
        for (Integer roleId :roleIds ) {
            List<Integer> findedFuncIds = funcRoleRelationMapper.selectFuncIdsByRoleId(roleId);
            funcIds.addAll(findedFuncIds);
        }
        //logger.info("查询到的所有功能ID",funcIds.toString());
        //再根据所有功能查询到所有权限
        List<Integer> rightIds = new ArrayList<>();
        for (Integer funcId :funcIds ) {
            List<Integer> findedRightIds = funcRightRelationMapper.selectRightIdsByFuncId(funcId);
            rightIds.addAll(findedRightIds);
        }
        //logger.info("查询到所有的权限Id",rightIds.toString());
        List<String> rightDatas = new ArrayList<>();
        for (Integer rightId :rightIds ) {
            Right right = rightMapper.selectByPrimaryKey(rightId);
            rightDatas.add(right.getRightData());
        }
        //返回权限集合
        return rightDatas;
    }

    /**
     * 接口用于超级系统管理员进行数据维护,可能没有页面
     *
     * @param rightAndFuncId
     * @return
     */
    @Override
    public Integer createRight2Function(RightAndFuncId rightAndFuncId) {
        Integer resultNum = 0;
        if (null == rightAndFuncId){
            return resultNum;
        }
        Integer funcId = rightAndFuncId.getFuncId();

        Right right = rightAndFuncId.getRight();
        if (null == right){
            return resultNum;
        }
        //首先插入一条权限数据
        resultNum += this.createSelectiveNotExistRightData(right);
        //查一下插入right的数据ID是多少
        RightExample rightExample = new RightExample();
        rightExample.createCriteria()
                .andRightDataEqualTo(right.getRightData());
        List<Right> rightsInserted = rightMapper.selectByExample(rightExample);
        Right rightBinded = rightsInserted.get(0);
        Integer rightBindedId = rightBinded.getRightId();
        //然后权限与function绑定
        Integer resultNumInsertRelation = this.createFuncRightRelation(rightBindedId,funcId);
        if (resultNumInsertRelation != 1) {
            return resultNumInsertRelation;
        }
        resultNum += resultNumInsertRelation;
        return resultNum;
    }

    /**
     * 通过权限名模糊查询权限集合
     *
     * @param rightName
     * @return
     */
    @Override
    public List<Right> queryRightBySlurName(String rightName) {
        if (null == rightName){
            return null;
        }
        RightExample rightExample = new RightExample();
        rightExample.clear();
        rightExample.createCriteria()
                .andRightNameLike(rightName);
        List<Right> rights = rightMapper.selectByExample(rightExample);
        return rights;
    }

    /**
     * 根据模糊的Data数据查询right的详细信息
     *
     * @param slurData
     * @return
     */
    @Override
    public List<Right> queryRightsBySlurData(String slurData) {
        if (null == slurData){
            return null;
        }
        RightExample rightExample = new RightExample();
        rightExample.clear();
        rightExample.createCriteria()
                .andRightDataLike(slurData);
        List<Right> rights = rightMapper.selectByExample(rightExample);
        return rights;
    }

    /**
     * 根据funcName查询基本信息属于模糊查询
     *
     * @param funcId
     * @return
     */
    @Override
    public List<Right> queryRightsByFuncId(Integer funcId) {
        //根据需要查询function的存在性
        Function function = functionMapper.selectByPrimaryKey(funcId);
        if (null == function){
            //function不存在,也就没有查询关系的必要
            return null;
        }
        List<Right> rights = rightMapper.selectByFuncId(funcId);
        return rights;
    }

    /**
     * 删除right和它全部的关系
     *
     * @param rightId
     * @return
     */
    @Override
    public Integer dropRightAndFuncRightRelation(Integer rightId) {
        Integer resultNum = 0;
        if (null == rightId){
            return resultNum;
        }
        //删除关系
        FuncRightRelationExample funcRightRelationExample = new FuncRightRelationExample();
        funcRightRelationExample.clear();
        funcRightRelationExample.createCriteria()
                .andRightIdEqualTo(rightId);
        List<FuncRightRelation> funcRightRelations = funcRightRelationMapper.selectByExample(funcRightRelationExample);
        if (null != funcRightRelations || 0 < funcRightRelations.size()){
            for (FuncRightRelation f :funcRightRelations ) {
                Integer funcRightId = f.getFuncRightId();
                resultNum += funcRightRelationMapper.deleteByPrimaryKey(funcRightId);
            }
        }
        Right right = rightMapper.selectByPrimaryKey(rightId);
        if (null != right || null != right.getRightId()){
            resultNum += rightMapper.deleteByPrimaryKey(right.getRightId());
        }
        return resultNum;
    }

    /**
     * 建立func..right关系,建立之前必须校验数据存在性
     *
     * @param rightId
     * @param funcId
     * @return
     */
    public Integer createFuncRightRelation(Integer rightId,Integer funcId){

        if (null == rightId||null == funcId){
            //入参有问题
            return 0;
        }
        Right right = rightMapper.selectByPrimaryKey(rightId);
        if (null == right){
            //right数据不存在
            return -3;
        }
        RightExample rightExample = new RightExample();
        rightExample.clear();
        rightExample.createCriteria()
                .andRightDataEqualTo(right.getRightData());
        List<Right> rights = rightMapper.selectByExample(rightExample);

        if (1 != rights.size()){
            //数据库表中的right数据是有问题的,是rightData重复
            return -2;
        }
        Function func = functionMapper.selectByPrimaryKey(funcId);
        if (null == func){
            //数据不存在,不能添加
            return -3;
        }
        //审查一下组合后的的插入数据是否存在
        FuncRightRelationExample funcRightRelationExample = new FuncRightRelationExample();
        funcRightRelationExample.clear();
        funcRightRelationExample.createCriteria()
                .andFuncIdEqualTo(funcId)
                .andRightIdEqualTo(rightId);
        int relationCounts = funcRightRelationMapper.countByExample(funcRightRelationExample);
        if (relationCounts > 0){
            return -2;
        }
        FuncRightRelation funcRightRelation = new FuncRightRelation();
        funcRightRelation.setRightId(rightId);
        funcRightRelation.setFuncId(funcId);
        return funcRightRelationMapper.insert(funcRightRelation);

    }

    /**
     * 根据用户Id查询其权限数据
     *
     * @param userId
     * @return
     */
    @Override
    public List<String> queryRightDatasByUserId(Integer userId) {
        List<String> permissions = rightMapper.selectRightDatasByUserId(userId);
        return permissions;
    }

    /**
     * 插入一条权限数据,初定于超级管理员用
     *
     * @param right
     * @return
     */
    public Integer createSelectiveNotExistRightData(Right right){
        return rightMapper.insertSelectiveNotExistRightData(right);
    }
    /**
     * 用于判断某UserID用户是否存在
     *
     * @param userId
     * @return
     */
    private Boolean hasUse(Integer userId){

        //根据userId查询用户
        User user = userMapper.selectByPrimaryKey(userId);
        if (null == user){
            return false;
        }
        return true;
    }
}
