package com.jlhc.web.controller;

import com.jlhc.common.utils.MD5Utils;
import com.jlhc.common.utils.ResultUtil;
import com.jlhc.common.dto.Msg;
import com.jlhc.oa.dto.user.User;
import com.jlhc.oa.dto.user.UsernameAndPasswdToken;
import com.jlhc.oa.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class LoginController extends BaseController{

    @Autowired
    UserService userService;

    /**
     * 登录方法
     */
    @ResponseBody
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public Msg login(@RequestBody @Valid UsernameAndPasswdToken user, BindingResult bindingResult){
        //目前还没做rememberMe
        String username = user.getUsername();
        String password = String.valueOf(user.getPassword());
        //MD5加密
        try {
            password = MD5Utils.EncoderByMd5(password);
            //logger.info(password);
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
            return ResultUtil.error(e);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
            return ResultUtil.error(e);
        }
        //logger.info("登录密码为:"+password);
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);

        Subject subject = SecurityUtils.getSubject();
        try {
            logger.info("-------start登录动作-------");
            subject.login(usernamePasswordToken);   //完成登录,登陆动作就进入自定义的realm了，收不到参数的问题。
            PrincipalCollection principals = subject.getPrincipals();
            User userInfo2Session = (User)principals.getPrimaryPrincipal();
            //清除需隐藏的密码信息
            userInfo2Session.setUserPasswd("");
            SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            userInfo2Session.setLoginTime(dateFormate.format(new Date()));//设置登录时间
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("user", userInfo2Session);
            session.setTimeout(1800000);//30分钟
            //session.setMaxInactiveInterval(30);
            //数据展示结构微调

            return ResultUtil.success("登录成功",userInfo2Session);
        } catch(Exception e) {
            logger.error("登录认证过程报错"+e.getMessage());
            return ResultUtil.error(e);
        }
    }

    /**
     * 登出方法
     * @param session
     * @return
     */
    @RequestMapping(value = "logout",method = RequestMethod.POST)
    @ResponseBody
    public Msg logOut(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
//      session.removeAttribute("user");//subject.logout意味着session已经销毁
        return ResultUtil.success("已经登出");
    }
}
