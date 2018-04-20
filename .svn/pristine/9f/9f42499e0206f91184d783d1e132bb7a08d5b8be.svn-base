package com.jlhc.oa.service.impl;

import com.jlhc.common.exception.NotOnlyException;
import com.jlhc.common.exception.NullEntityInDatabaseException;
import com.jlhc.common.utils.MD5Utils;
import com.jlhc.oa.dao.*;
import com.jlhc.oa.dto.role.Role;
import com.jlhc.oa.dto.role.RoleUserRelation;
import com.jlhc.oa.dto.role.example.RoleUserRelationExample;
import com.jlhc.oa.dto.user.*;
import com.jlhc.oa.dto.user.example.UserExample;
import com.jlhc.oa.service.OrganizationService;
import com.jlhc.oa.service.RightService;
import com.jlhc.oa.service.UserService;
import com.jlhc.sell.dto.Task;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author renzhong
 * @version 1.0
 * @Description
 * @Date: Created in 16:07 2018/1/4 0004
 */
@Transactional
@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private UserOrganizationRelationMapper userOrganizationRelationMapper;

    @Autowired
    private RoleUserRelationMapper roleUserRelationMapper;

    @Autowired
    private RightService rightService;

    @Autowired
    private OrganizationService organizationService;

    private UserExample userExample = new UserExample();
    /**
     * 查询用户的所有权限信息,用于开发时给系统管理员使用
     *
     * @param username 通过id查询
     * @return 用户的所有的权限信息
     */
    @Override
    public User queryUserWithRolesAndRightByName(String username) {
        if (null == username){
            return null;
        }
        userExample.clear();
        userExample.createCriteria()
            .andUserUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);

        //用户数超过一个说明有问题
        if (0 == users.size()||1 < users.size()){
        throw new NotOnlyException();
        }
        User user = users.get(0);
        //通过用户名获取用户的角色信息
        //通过用户id获取角色Id信息
        user.setRoles(roleMapper.selectByUserId(user.getUserId()));
        List<String> promissions = rightService.getRigthsByUserId(user.getUserId());
        user.setPromissions(promissions);
        //User user = userMapper.findByUserName(username);

        return user;
    }

    /**
     * 通常用于superManager系统管理员
     *
     * @return
     */
    @Override
    public User getAllRolesAndRights() {
        User user = userMapper.getUserWithAllRolesAndRights();
        return user;
    }

    /**
     * 通过用户ID查找单一用户,只能查到正常在职状态的用户
     *
     * @param userId
     * @return
     */
    @Override
    public User getUserById(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        return user;
    }

    /**
     * 创建用户,组织机构被指定
     *
     * @param createdUser
     * @return
     */
    @Override
    public Integer createUser(User createdUser) throws UnsupportedEncodingException, NoSuchAlgorithmException, NullEntityInDatabaseException {
        Integer resultNum = 0;
        //将用户密码md5加密
        createdUser.setUserPasswd(MD5Utils.EncoderByMd5(createdUser.getUserPasswd()));
        //createdUser.setOrgId(orgId);
        //判断用户名是否存在
        //查询同名用户
        List<User> userListWithSameName = queryUsersByUserName(createdUser.getUserUsername());
        //父组织机构
        Organization organization = organizationMapper.selectByPrimaryKey(createdUser.getOrgId());
        if (0 == userListWithSameName.size() && null != organization){
            //没有重名,组织存在
            //完善orgId和companyOrgId的逻辑关系
            boolean companyOrgIdHasFound = false;
            List<Organization> organizations = organizationService.queryParentOrgByOrgId(createdUser.getOrgId());
            for (Organization org :organizations ) {
                if (0 == org.getOrgParentId()){
                    companyOrgIdHasFound = true;
                    createdUser.setCompanyOrgId(org.getOrgId());
                }
            }
            if (!companyOrgIdHasFound){
                return -5;
            }
            //插入用户数据
            resultNum += userMapper.insert(createdUser);
            //查询用户id
//            List<User> users = this.queryUsersByUserName(createdUser.getUserUsername());
//            Integer createdId = 0;
//            if (users.size() == 1){
//                createdId = users.get(0).getUserId();
//                //建立关系
//                UserOrganizationRelation userOrganizationRelation = new UserOrganizationRelation();
//                userOrganizationRelation.setUserId(createdId);
//                userOrganizationRelation.setOrgId(orgId);
//                resultNum += userOrganizationRelationMapper.insert(userOrganizationRelation);
//            }else {
//                //未知原因重名,数据库本身脏数据,极少数情况
//                resultNum = -2;
//            }
        }else if (0 == userListWithSameName.size() && null == organization){

            //不重名,组织机构不存在
            resultNum = -3;
        }else if (0 != userListWithSameName.size()){
            //重名,组织机构不存在
            resultNum = -2;
        }
        return resultNum;
    }

    /**
     * 根据组织机构查询用户
     *
     * @param orgId
     * @return
     */
    @Override
    public List<User> queryUsersByOrgId(Integer orgId) {
        ArrayList<User> users = new ArrayList<>();
        Organization organization = organizationMapper.selectByPrimaryKey(orgId);

        if (null != organization){
//            UserOrganizationRelationExample userOrganizationRelationExample = new UserOrganizationRelationExample();
//            userOrganizationRelationExample.createCriteria().andOrgIdEqualTo(orgId);
            //组织机构存在,根据组织机构OrgId,查询对应的用户
            userExample.clear();
            userExample.createCriteria()
                    .andOrgIdEqualTo(orgId);
            users.addAll( userMapper.selectByExample(userExample));
            for (User user :users){
                user.setUserPasswd(null);
            }
//            for (UserOrganizationRelation userOrganizationRelation : userOrganizationRelations){
//                User user = userMapper.selectByPrimaryKey(userOrganizationRelation.getUserId());
//                if (1 == user.getUserState()){
//                    users.add(user);
//                }
//            }
        }
        return users;
    }

    /**
     * 查询所有的用户,包括组织机构下子孙的
     *
     * @param orgId
     * @return
     */
    @Override
    public List<User> queryAllUsersByOrgId(Integer orgId){
        List<User> users = new ArrayList<>();
        Organization organization = organizationMapper.selectByPrimaryKey(orgId);

        if (null != organization){
//            UserOrganizationRelationExample userOrganizationRelationExample = new UserOrganizationRelationExample();
//            userOrganizationRelationExample.createCriteria().andOrgIdEqualTo(orgId);
            //组织机构存在,根据组织机构OrgId,查询对应的用户
            List<Integer> allChildrenOrgId = organizationService.queryAllChildrenOrgId(orgId);
            if (null == allChildrenOrgId||0 == allChildrenOrgId.size()) {
                return null;
            }
            for (Integer childId :allChildrenOrgId ) {
                userExample.clear();
                userExample.createCriteria()
                        .andOrgIdEqualTo(childId);
                users.addAll(userMapper.selectByExample(userExample));
            }
            for (User user :users){
                user.setUserPasswd(null);
            }
//            for (UserOrganizationRelation userOrganizationRelation : userOrganizationRelations){
//                User user = userMapper.selectByPrimaryKey(userOrganizationRelation.getUserId());
//                if (1 == user.getUserState()){
//                    users.add(user);
//                }
//            }
        }
        return users;
    }

    @Override
    public Integer createAdminUser(User createdUser) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        Integer resultNum = 0;
        //将用户密码md5加密
        createdUser.setUserPasswd(MD5Utils.EncoderByMd5(createdUser.getUserPasswd()));
        //createdUser.setOrgId(orgId);
        //判断用户名是否存在
        //查询同名用户
        List<User> userListWithSameName = queryUsersByUserName(createdUser.getUserUsername());
        if (0 != userListWithSameName.size()){
            //重名
            resultNum = -2;
        }
        //插入用户数据
        resultNum += userMapper.insertSelective(createdUser);
        //插入成功
        if (1 == resultNum){
            //添加关系
            RoleUserRelation roleUserRelation = new RoleUserRelation();
            Integer lastInsertId = userMapper.selectLastInsertId();
            roleUserRelation.setUserId(lastInsertId);
            roleUserRelation.setRoleId(2);//系统自检,系统管理员角色的roleId为2
            resultNum += roleUserRelationMapper.insertSelective(roleUserRelation);
        }
        return resultNum;
    }

    /**
     * 特定接口,查询某个公司的系统管理员
     *
     * @param companyOrgId
     * @return
     */
    @Override
    public List<User> queryAdminUserOfOrg(Integer companyOrgId) {
        if (0 == companyOrgId||null == companyOrgId){
            return null;
        }
        List<User> adminUsers = userMapper.selectByComOrgAndRole(companyOrgId, 2);//2是默认数据..系统管理员角色roleId
        return adminUsers;
    }

    /**
     * 根据任务查询共享人
     *
     * @param taskId
     * @return
     */
    @Override
    public List<User> queryUsersByTaskIdInTaskUserRelation(String taskId) {
        List<User> users = userMapper.selectShareUsersByTaskId(taskId);
        return users;
    }

    /**
     * 查询组织机构(包括下属的组织机构)所有的员工
     *
     * @param orgId
     * @return
     */
    @Override
    public List<User> getUsersOfOrg(Integer orgId) throws NullEntityInDatabaseException {
        //验证一下组织机构的存在性
        Organization organization = organizationMapper.selectByPrimaryKey(orgId);
        if (null == organization){
            throw new NullEntityInDatabaseException();
        }
        //首先查一下所有的组织机构
        List<Integer> orgIds = organizationService.queryAllChildrenOrgId(orgId);
        if (null == orgIds||0 >= orgIds.size()){
            return null;
        }
        List<User> users = userMapper.selectUsersInOrgs(orgIds);
        return users;
    }


    /**
     * 根据用户真实姓名模糊查询用户信息
     *
     * @param userTrueName
     * @return
     */
    @Override
    public List<User> queryUsersNearUserTrueName(String userTrueName) {
        if (null == userTrueName){
            return null;
        }
        UserExample userExample = new UserExample();
        userTrueName = "%" + userTrueName + "%";
        userExample.createCriteria()
                .andUserTruenameLike(userTrueName);
                //.andUserStateEqualTo(1);
        List<User> users = userMapper.selectByExample(userExample);
        for (User user : users) {
            user.setUserPasswd(null);
        }
        return users;
    }

    /**
     * 修改用户基本情况
     *
     * @param userBase
     * @return
     */
    @Override
    public Integer reworkBaseUser(UserBase userBase) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Integer resultNum = 0;
        if (null == userBase){
            return 0;
        }
//        List<User> users = new ArrayList<>();
//        String userName = userBase.getUserUsername();
//        Integer userId = userBase.getUserId();
//        if (null != userName || "".equals(userName)){
//            users = queryUserByUserNameWithoutTheUserId(userName,userId);
//            //去除本id用户
//        }
//        if (0 == users.size()){
//            //没有重名
//            User user = new User();
//            user.setUserId(userBase.getUserId());
//            user.setUserState(userBase.getUserState());
//            user.setUserEmail(userBase.getUserEmail());
//            user.setUserTruename(userBase.getUserTruename());
//            user.setUserMobile(userBase.getUserMobile());
//            user.setUserPronounce(userBase.getUserPronounce());
//            user.setUserUsername(userBase.getUserUsername());
//            resultNum += userMapper.updateByPrimaryKeySelective(user);
//        }else {
//            //有重名,指除了这个用户,有其他用户还有用这个名字
//            resultNum = -2;
//        }
        User user = new User();
        user.setUserId(userBase.getUserId());
        user.setUserState(userBase.getUserState());
        user.setUserEmail(userBase.getUserEmail());
        user.setUserTruename(userBase.getUserTruename());
        user.setUserMobile(userBase.getUserMobile());
        user.setUserPronounce(userBase.getUserPronounce());
        user.setUserUsername(null);
        resultNum += userMapper.updateByPrimaryKeySelective(user);
        return resultNum;
    }

    /**
     * 修改用户基本情况以及所属组织机构
     *
     * @param user
     * @return
     */
    @Override
    public Integer reworkBaseUserAndOrg(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException, NullEntityInDatabaseException {
        Integer resultNum = 0;
        Integer userId = user.getUserId();
        Integer orgId = user.getOrgId();
        //将用户密码md5加密
        user.setUserPasswd(MD5Utils.EncoderByMd5(user.getUserPasswd()));
        //组织机构
        Organization organization = organizationMapper.selectByPrimaryKey(orgId);
        //用户名重名校验
        if (null == user){
            return 0;
        }
        List<User> users = new ArrayList<>();
        String userName = user.getUserUsername();
        if (null != userName || "".equals(userName)){
            users = queryUserByUserNameWithoutTheUserId(userName,userId);
        }
        if (0 == users.size()){
            //没有重名
            resultNum += userMapper.updateByPrimaryKeySelective(user);
        }else {
            //有重名
            return -2;
        }
        //组织机构存在性校验
        if (null == organization){
            return -3;
        }
        //具体的修改数据动作
        if (null == userId){
            return resultNum;
        }else if (null == orgId){
            return resultNum;
        }
        //完善orgId和companyOrgId的逻辑关系
        boolean companyOrgIdHasFound = false;
        List<Organization> organizations = organizationService.queryParentOrgByOrgId(orgId);
        for (Organization org :organizations ) {
            if (0 == org.getOrgParentId()){
                companyOrgIdHasFound = true;
                user.setCompanyOrgId(org.getOrgId());
            }
        }
        if (!companyOrgIdHasFound){
            return -5;
        }
        resultNum += userMapper.updateByPrimaryKeySelective(user);
//        UserOrganizationRelationExample userOrganizationRelationExample = new UserOrganizationRelationExample();
//        userOrganizationRelationExample.createCriteria()
//                .andUserIdEqualTo(userId);
//        UserOrganizationRelation userOrganizationRelation = new UserOrganizationRelation();
//        userOrganizationRelation.setOrgId(orgId);
//        resultNum += userOrganizationRelationMapper.updateByExampleSelective(userOrganizationRelation, userOrganizationRelationExample);
        return resultNum;
    }

    /**
     * 员工离职
     *
     * @param userId 离职员工ID
     * @return
     */
    @Override
    public Integer dropUser(Integer userId) {
        Integer resultNum = 0;
        if (null == userId){
            return resultNum;
        }
        //校验员工存在
        User user = userMapper.selectByPrimaryKey(userId);
        if (null == user){
            return -3;
        }
        User dropedUser = new User();
        dropedUser.setUserState(2);//2为离职状态
        dropedUser.setUserId(userId);
        resultNum += userMapper.updateByPrimaryKeySelective(dropedUser);
        return resultNum;
    }

    /**
     * 根据角色查询用户
     *
     * @param roleId
     * @return
     */
    @Override
    public List<User> queryUsersByRoleId(Integer roleId) {
        if (null == roleId){
            return null;
        }
        //角色存在性校验
        Role role = roleMapper.selectByPrimaryKey(roleId);
        if (null == role){
            return null;
        }
        RoleUserRelationExample roleUserRelationExample = new RoleUserRelationExample();
        roleUserRelationExample.createCriteria()
                .andRoleIdEqualTo(roleId);
        List<RoleUserRelation> roleUserRelations = roleUserRelationMapper.selectByExample(roleUserRelationExample);
        ArrayList<User> users = new ArrayList<>();
        for ( RoleUserRelation roleUserRelation:roleUserRelations) {
            User user = userMapper.selectByPrimaryKey(roleUserRelation.getUserId());
            //只查询在职用户,将筛选工作交给前台
//            if (1 == user.getUserState()){
                users.add(user);
//            }
        }
        return users;
    }

    /**
     * 修改用户的密码,专用接口
     *
     * @param userId
     * @param oldPasswords
     * @param newPasswords
     * @return
     */
    @Override
    public Integer reworkPasswords(Integer userId, String oldPasswords,String newPasswords) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Integer resultNum = 0;
        //验证存在性
        User beFindUser = userMapper.selectByPrimaryKey(userId);
        if (beFindUser == null){
            return -3;
        }
        //验证密码是否正确
        String oldMD5Passwords = MD5Utils.EncoderByMd5(oldPasswords);
        boolean equals = beFindUser.getUserPasswd().equals(oldMD5Passwords);
//        logger.info("数据库中的加密密码:"+beFindUser.getUserPasswd());
//        logger.info("输入的求验证的密码:"+oldMD5Passwords);
        if (!equals){
            throw new IncorrectCredentialsException();
        }


        //执行修改
        User user = new User();
        user.setUserId(userId);
        String newMD5Passwords = MD5Utils.EncoderByMd5(newPasswords);
        user.setUserPasswd(newMD5Passwords);
        resultNum += userMapper.updateByPrimaryKeySelective(user);
        return resultNum;
    }

    /**
     * 修改用户所属的组织机构
     *
     * @param userId
     * @param orgId
     * @return
     */
    @Override
    public Integer reworkOrgIdOfUser(Integer userId, Integer orgId) throws NullEntityInDatabaseException {
        Integer resultNum = 0;
        //校验
        if (null == userId || null == orgId){
            return 0;
        }

        //存在性校验
        User user = userMapper.selectByPrimaryKey(userId);
        Organization organization = organizationMapper.selectByPrimaryKey(orgId);
        if (null == user || null == organization){
            return -3;
        }
        //重复性校验
        if (user.getOrgId() == orgId){
            return resultNum;
        }
        //修改
        User userUpdata = new User();
        //完善orgId和companyOrgId的逻辑关系
        boolean companyOrgIdHasFound = false;
        List<Organization> organizations = organizationService.queryParentOrgByOrgId(orgId);
        for (Organization org :organizations ) {
            if (0 == org.getOrgParentId()){
                companyOrgIdHasFound = true;
                userUpdata.setCompanyOrgId(org.getOrgId());
            }
        }
        if (!companyOrgIdHasFound){
            return -5;
        }
        userUpdata.setUserId(userId);
        userUpdata.setOrgId(orgId);
        //logger.info(userUpdata.toString());
        resultNum += userMapper.updateByPrimaryKeySelective(userUpdata);
        return resultNum;
    }

    /**
     * 根据账户查询用户
     *
     * @param username
     * @return
     */
    @Override
    public User queryUserByUsername(String username) {
        if (null == username){
            return null;
        }
        userExample.clear();
        userExample.createCriteria()
                .andUserUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        if (1 < users.size() || 0 >= users.size()){
            return null;
        }
        return users.get(0);
    }

    /**
     * 根据用户名精确查询用户
     *
     * @param username 查询条件
     * @return 相同名字的用户集合
     */
    private List<User> queryUsersByUserName(String username) {
        userExample.clear();
        userExample.createCriteria()
                .andUserUsernameEqualTo(username);
                //.andUserStateEqualTo(1);
        return userMapper.selectByExample(userExample);
    }

    /**
     * 查询用户组,排除自己创建的特定userID,校验同名
     *
     * @param username
     * @param userId
     * @return
     */
    private List<User> queryUserByUserNameWithoutTheUserId(String username,Integer userId) {
        userExample.clear();
        userExample.createCriteria()
                .andUserUsernameEqualTo(username)
                .andUserIdNotEqualTo(userId);
        return userMapper.selectByExample(userExample);
    }
}
