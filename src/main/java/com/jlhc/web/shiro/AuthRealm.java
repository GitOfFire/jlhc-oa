package com.jlhc.web.shiro;

import com.jlhc.common.exception.FailVertifyException;
import com.jlhc.common.exception.NotVertifyException;
import com.jlhc.oa.dto.function.Right;
import com.jlhc.oa.dto.role.Role;
import com.jlhc.oa.dto.user.User;
import com.jlhc.oa.service.RightService;
import com.jlhc.oa.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author renzhong
 * @version 1.0
 * @Description
 * @Date: Created in 16:15 2018/1/4 0004
 */
public class AuthRealm extends AuthorizingRealm {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;

    @Autowired
    private RightService rightService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("-----------start授权动作----------");
        User user = (User)principalCollection.fromRealm(this.getClass().getName()).iterator().next();//获取session中user

        List<String> permissions = new ArrayList<>();
        //这种方法只有超级系统管理员可能还再用,超级系统管理员会将权限分级放入,RoleRight关系表只为开发用超级系统管理员存在
        Set<Role> roles = user.getRoles();
        if (roles.size() > 0){
            for (Role role : roles) {
                Set<Right> rights = role.getRights();
                if (rights.size() > 0){
                    for (Right right : rights){
                        permissions.add(right.getRightData());
                        //logger.info(right.getRightData());
                    }
                }
            }
        }
        if (null != user.getPromissions() && 0 < user.getPromissions().size()){
            //普通用户还要通过这种方法获得
            permissions.addAll(user.getPromissions());
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);
        return info;
    }


    /**
     * 此方法的主要作用时查询数据库，根据用户输入的用户名信息，找到对应的所有的用户信息，其中涉及到关于信息输入格式校验的问题
     *
     * @param authenticationToken
     * @return 登陆信息
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("-------start认证过程------");
        //获取用户输入的token
        UsernamePasswordToken utoken = (UsernamePasswordToken) authenticationToken;
        String username = utoken.getUsername();
        //这里的认证过程超级简单，只是通过用户名找到了用户，把认证后得到的信息存入就好了
        //info生成后,密码的验证工作在CredentialsMatcher中实现
        User user;
        if ("admin".equals(username)){
            //如果是管理员,赋予所有的权限
            user = userService.getAllRolesAndRights();
            if (null == user){
                throw new UnknownAccountException();
            }

            //状态不对,登录验证通不过
            if (0 == user.getUserState()){
                throw new NotVertifyException();
            }

            if (2 == user.getUserState()){
                throw new FailVertifyException();
            }
            /*System.out.println("---查看得到的信息对不对---");
            System.out.println("superManager:"+user);*/

        }
        //如果不是管理员,正常查询
        else{
            //user =  userService.queryUserWithRolesAndRightByName(username);
            user = userService.queryUserByUsername(username);
            //账户不存在,或者该账号异常--有超过两个以上不同的用户名
            if (null == user){
                throw new UnknownAccountException();
            }

            //状态不对,登录验证通不过
            if (0 == user.getUserState()){
                throw new NotVertifyException();
            }

            if (2 == user.getUserState()){
                throw new FailVertifyException();
            }
            List<String> rightDatas = rightService.queryRightDatasByUserId(user.getUserId());//移动到验证后
            List newList = new ArrayList(new HashSet(rightDatas));
            user.setPromissions(newList);

        }

        //Info的第二个参数是查询数据库得到的密码
        try{
            AuthenticationInfo authenticationInfo
                = new SimpleAuthenticationInfo(user,user.getUserPasswd(),this.getClass().getName());//放入shiro,调用CredentialsMatcher检验代码，校验方法执行实在认证代码执行后

            return authenticationInfo;
        }catch(Throwable t){
            t.printStackTrace();
            throw new AuthenticationException();
        }

    }


}
