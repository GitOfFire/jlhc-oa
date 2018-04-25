package com.jlhc.web.controller;

import com.jlhc.common.utils.ResultUtil;
import com.jlhc.common.dto.Msg;
import com.jlhc.oa.dto.user.AdminUser;
import com.jlhc.oa.dto.user.ChangePasswdInfo;
import com.jlhc.oa.dto.user.User;
import com.jlhc.oa.dto.user.UserBase;
import com.jlhc.oa.service.OrganizationService;
import com.jlhc.oa.service.UserService;
import com.jlhc.sell.dto.Task;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 用户管理模块
 *
 * @author renzhong
 * @version 1.0
 * @Description
 * @Date: Created in 18:10 2018/1/4 0004
 */
@RequestMapping("/user")
@RestController
@Api( value = "用户模块", description = "这个接口集合用于用户模块界面")
public class UserController extends BaseController{

    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;

    @Autowired
    private OrganizationService organizationService;



    /**
     * 找到单一用户通过ID,该接口用于异步查询单一用户信息时
     *
     * @param userId
     * @return 单一用户信息
     */
    @ApiOperation(value = "找到单一用户通过ID",notes = "用户:查询",nickname = "用户查询")
    @RequestMapping(value = "getUserById/{userId}",method= RequestMethod.GET)
    @RequiresPermissions("user:getUserById")
    public Msg findUserById(@PathVariable @Max(value = 999999999)Integer userId){
        try {
            User user = userService.getUserById(userId);
            if (null == user){
                return ResultUtil.selectFailed();
            }else {
                return ResultUtil.selectSuccess(user);
            }
        }catch (Exception e){
            logger.error("通过用户ID查询用户报错",e);
            return ResultUtil.error(e);
        }
    }


    /**
     * 添加用户,入参,用户基本数据,用户组织id
     *
     * @param user
     * @return
     */
    @ApiOperation(value = "添加用户",notes = "入参,用户基本数据,用户组织id",nickname = "用户添加")
    @RequestMapping(value = "postUser",method = RequestMethod.POST)
    @RequiresPermissions("user:postUser")
    public Msg postUser(@RequestBody @Valid User user, BindingResult bindingResult){
        //logger.info(session.getAttribute("user").toString());
        try {
            Integer resultNum = userService.createUser(user);
            if (-2 == resultNum){
                return ResultUtil.operationFailed("重复的用户名");
            }else if (-3 == resultNum){
                return ResultUtil.operationFailed("未知的组织机构");
            }else if (0 == resultNum){
                return ResultUtil.operationFailed();
            }else if(-5 ==resultNum){
                return ResultUtil.operationFailed("输入的组织机构实体数据不属于具体的公司组织树,属于脏数据");
            }
            return ResultUtil.addSuccess(resultNum);
        }catch (Exception e){
            logger.error("添加用户失败",e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 根据组织机构查询用户,只查询本组织机构下的用户
     *
     * @param orgId
     * @return
     */
    @ApiOperation(value = "根据组织机构查询用户集合",notes = "用户:查询",nickname = "根据组织机构查询用户")
    @RequestMapping(value = "getUsersByOrgId/{orgId}",method = RequestMethod.GET)
    @RequiresPermissions("user:getUsersByOrgId")
    public Msg getUsersByOrgId(@PathVariable @Max(999999999) Integer orgId){
        try {
            List<User> users = userService.queryUsersByOrgId(orgId);
            if (0 == users.size()||null == users){
                return ResultUtil.selectFailed();
            }
            return ResultUtil.selectSuccess(users);
        }catch (Exception e){
            logger.error("根据组织机构查询用户出错",e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 根据组织机构查询所有用户
     *
     * @param orgId
     * @return
     */
    @RequestMapping(value = "getAllUsersByOrgId/{orgId}",method = RequestMethod.GET)
    @RequiresPermissions("user:getAllUsersByOrgId")
    public Msg getAllUsersByOrgId(@PathVariable @NotNull @Max(999999999) Integer orgId){
        try {
            List<User> users = userService.queryAllUsersByOrgId(orgId);
            if (null == users||0 == users.size()){
                return ResultUtil.selectFailed();
            }
            return ResultUtil.selectSuccess(users);
        }catch (Exception e){
            logger.error("根据组织机构查询所有用户出错",e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 根据用户真名模糊查询用户
     *
     * @param userTrueName
     * @return
     */
    @ApiOperation(value = "根据用户真名模糊查询用户",notes = "用户:查询",nickname = "根据用户真名模糊查询用户")
    @RequestMapping(value = "getUsersNearUserTrueName",method = RequestMethod.GET)
    @RequiresPermissions("user:getUsersNearUserTrueName")
    public Msg getUsersNearUserTrueName(@RequestParam @NotNull String userTrueName){
        //logger.info(userTrueName);
        try {
            List<User> users = userService.queryUsersNearUserTrueName(userTrueName);
            if (0 == users.size()||null == users){
                return ResultUtil.selectFailed();
            }
            return ResultUtil.selectSuccess(users);

        }catch (Exception e){
            logger.error("根据用户真名模糊查询用户出错",e);
            return ResultUtil.error(e);
        }
    }
//备选添加根据真实姓名模糊查询所有用户
//    public Msg getAllUsersNearUserTrueName(@RequestParam @NotNull String userTrueName){
//        try {
//            List<User> users = userService.queryAllUsersNearUserTrueName(userTrueName);
//            if (0 == users.size()||null == users){
//                return ResultUtil.selectFailed();
//            }
//            return ResultUtil.selectSuccess(users);
//
//        }catch (Exception e){
//            logger.error("根据用户真名模糊查询用户出错",e);
//            return ResultUtil.error(e);
//        }
//    }

    /**
     * 修改用户基础信息以及用户所属组织机构
     *
     * @param user 封装了要修改的用户信息以及用户所要变更的组织机构信息
     * @return 返回修改结果
     */
    @ApiOperation(value = "修改用户基础信息以及用户所属组织机构",notes = "用户:修改",nickname = "修改用户基础信息以及用户所属组织机构")
    @RequestMapping(value = "putUserAndOrg",method = RequestMethod.PUT)
    @RequiresPermissions("user:putUserAndOrg")
    public Msg putUserAndOrg(@RequestBody @Valid User user,BindingResult bindingResult){
        try {
            Integer resultNum = userService.reworkBaseUserAndOrg(user);
            if (resultNum == -2){
                return ResultUtil.operationFailed("用户名重复");
            }else if (resultNum == 0){
                return ResultUtil.operationFailed();
            }else if (resultNum == -3){
                return ResultUtil.operationFailed("不存在的组织机构");
            }else if(-5 ==resultNum){
                return ResultUtil.operationFailed("输入的组织机构实体数据不属于具体的公司组织树,属于脏数据");
            }
            return ResultUtil.updateSuccess(resultNum);
        }catch (Exception e){
            logger.error("修改用户基础信息以及用户所属组织机构出现错误",e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 修改用户基本信息
     *
     * @param userBase
     * @return
     */
    @ApiOperation(value = "修改用户基本信息",notes = "用户:修改",nickname = "修改用户基本信息接口")
    @RequestMapping(value = "putBaseUser",method = RequestMethod.PUT)
    @RequiresPermissions("user:putBaseUser")
    public Msg putBaseUser(@RequestBody @Valid UserBase userBase, BindingResult BindingResult){
        try{
            Integer resultNum = userService.reworkBaseUser(userBase);
            if (resultNum == -2){
                return ResultUtil.operationFailed("用户名重复");
            }else if (resultNum == 0){
                return ResultUtil.operationFailed();
            }
            return ResultUtil.updateSuccess(resultNum);
        }catch (Exception e){
            logger.error("修改用户基本信息",e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 修改用户与组织机构关系
     *
     * @param userId
     * @param orgId
     * @return
     */
    @PutMapping(value = "putOrgIdOfUser")
    @RequiresPermissions(value = "user:putOrgIdOfUser")
    public Msg putOrgIdOfUser(@RequestParam @Max(999999999) @NotNull Integer userId,@RequestParam @NotNull @Max(999999999) Integer orgId){
        try{
            Integer resultNum = userService.reworkOrgIdOfUser(userId,orgId);
            if (0 == resultNum){
                return ResultUtil.operationFailed("未修改任何数据");
            }else if(-3 == resultNum){
                return ResultUtil.operationFailed("输入的用户或者组织机构实体数据不存在");
            }else if(-5 ==resultNum){
                return ResultUtil.operationFailed("输入的组织机构实体数据不属于具体的公司组织树,属于脏数据");
            }
            return ResultUtil.updateSuccess(resultNum);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 员工离职
     *
     * @param userId 离职员工的Id
     * @return
     */
    @ApiOperation(value = "员工离职")
    @RequestMapping(value = "deleteUser/{userId}",method = RequestMethod.DELETE)
    @RequiresPermissions("user:deleteUser")
    public Msg dimissionUser(@PathVariable @Max(999999999) Integer userId){
        try {
            Integer resultNum = userService.dropUser(userId);
            if (resultNum == -3){
                return ResultUtil.operationFailed("该用户不存在");
            }
            return ResultUtil.deleteSuccess(resultNum);
        }catch (Exception e){
            logger.error("员工离职接口出错",e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 根据角色查询用户
     *
     * @param roleId 角色Id
     * @return
     */
    @RequestMapping(value = "getUsersByRoleId/{roleId}",method = RequestMethod.GET)
    @RequiresPermissions("user:getUsersByRoleId")
    public Msg getUsersByRoleId(@PathVariable @Max(999999999) @NotNull Integer roleId){
        try{
            List<User> users = userService.queryUsersByRoleId(roleId);
            if (0 < users.size()|| null != users){
                return ResultUtil.selectSuccess(users);
            }
            return ResultUtil.selectFailed();
        }catch(Exception e){
            logger.error("根据角色查询用户",e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 修改密码
     *
     * @param changePasswdInfo
     * @return
     */
    @PutMapping(value = "putPasswords")
    @RequiresPermissions("user:putPasswords")
    public Msg putPasswords(@RequestBody @Valid ChangePasswdInfo changePasswdInfo,BindingResult bindingResult){
        try{
            Integer resultNum = userService.reworkPasswords(changePasswdInfo.getUserId(),changePasswdInfo.getOldPasswords(),changePasswdInfo.getNewPasswords());
            if (-3 == resultNum){
                return ResultUtil.operationFailed("要修改的用户不存在");
            }
            return ResultUtil.updateSuccess(resultNum);
        }catch(Exception e){
            return errorResultOperation(e);
        }
    }

    /**
     * 添加某一公司的系统管理员
     *
     * @param adminUser
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "postAdminUser")
    @RequiresPermissions("user:foradmin:postAdminUser")
    public Msg postAdminUser(@RequestBody @Valid AdminUser adminUser, BindingResult bindingResult){
        try{
            User user = new User();
            Integer orgId = adminUser.getOrgId();
            boolean isCompanyOrg = organizationService.isCompanyOrg(orgId);
            if(isCompanyOrg){
               user.setOrgId(orgId);
            }else {
                return ResultUtil.operationFailed("该组织机构不是公司");
            }
            user.setUserUsername(adminUser.getUserUsername());
            user.setUserPasswd(adminUser.getUserPasswd());
            user.setUserMobile(adminUser.getUserMobile());
            user.setUserPronounce(adminUser.getUserPronounce());
            user.setUserTruename(adminUser.getUserTruename());
            user.setUserEmail(adminUser.getUserEmail());
            user.setUserState(1);
            Integer resultNum = userService.createAdminUser(user);
            if (-2 == resultNum){
                return ResultUtil.operationFailed("同一组织机构下不允许创建相同登录名用户!!");
            }
            return ResultUtil.addSuccess(resultNum);
        }catch(Exception e){
            return errorResultOperation(e);
        }
    }

    /**
     * 查询某公司的系统管理员
     *
     * @param companyOrgId
     * @return
     */
    @GetMapping("getTheAdminUserOfOrg/{companyOrgId}")
    @RequiresPermissions("user:foradmin:getTheAdminUserOfOrg")
    public Msg getTheAdminUserOfOrg(@PathVariable @Max(999999999) @Min(1) Integer companyOrgId){
        try{
            List<User> adminUsers = userService.queryAdminUserOfOrg(companyOrgId);
            if (null == adminUsers||0 == adminUsers.size()){
                return ResultUtil.selectFailed();
            }
            return ResultUtil.selectSuccess(adminUsers);
        }catch(Exception e){
            return errorResultOperation(e);
        }
    }

    /**
     * 查询某一任务的共享用户
     *
     * @param taskId
     * @return
     */
    @GetMapping("getUsersByTaskIdInTaskUserRelation/{taskId}")
    @RequiresPermissions("user:getUsersByTaskIdInTaskUserRelation")
    public Msg getUsersByTaskIdInTaskUserRelation(@PathVariable @NotNull @Size(min = 32,max = 32,message = "代理主键为32位全球唯一码")String taskId){
        try{
            List<User> users = userService.queryUsersByTaskIdInTaskUserRelation(taskId);
            if (null == users || 0 >= users.size()){
                return ResultUtil.selectFailed();
            }
            return ResultUtil.selectSuccess(users);
        }catch(Exception e){
            return errorResultOperation(e);
        }
    }

    /**
     * 获得登录用户所在部门的所有员工
     *
     * @param httpSession
     * @return
     */
    @GetMapping("getUsersOfOrg")
    @RequiresPermissions("user:getUsersOfOrg")
    public Msg getUsersOfOrg(HttpSession httpSession){
        try{
            User user = (User)httpSession.getAttribute("user");
            List<User> users = userService.getUsersOfOrg(user.getOrgId());
            if (null == users || 0 >= users.size()){
                return ResultUtil.selectFailed();
            }
            return ResultUtil.selectSuccess(users);
        }catch(Exception e){
            return errorResultOperation(e);
        }
    }

    /**
     * 重置密码
     *
     * @param userId
     * @return
     */
    @PutMapping("putUserPasswdToDef/{userId}")
    @RequiresPermissions("user:putUserPasswdToDef")
    public Msg putUserPasswdToDef(@PathVariable @Max(999999999) Integer userId){
        try{
            Integer resultNum  = userService.reworkUserPasswdToDef(userId);
            if (0 >= resultNum){
                return ResultUtil.operationFailed();
            }
            return ResultUtil.updateSuccess(resultNum);
        }catch(Exception e){
            return errorResultOperation(e);
        }
    }
}
