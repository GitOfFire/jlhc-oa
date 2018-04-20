package com.jlhc.web.listener;

import com.jlhc.common.exception.ApplicationStartupVaildException;
import com.jlhc.oa.dao.RoleMapper;
import com.jlhc.oa.service.FunctionService;
import com.jlhc.oa.service.RoleService;
import com.jlhc.oa.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent>{
    @Autowired
    RoleService roleService;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("<-----------开始执行数据库默认数据自检------------->");
        ApplicationContext applicationContext = event.getApplicationContext();
        RoleService roleService = applicationContext.getAutowireCapableBeanFactory().getBean(RoleService.class);
        FunctionService functionService = applicationContext.getAutowireCapableBeanFactory().getBean(FunctionService.class);
        Boolean hasDefRoleData = roleService.hasDefRoleData();
        if (!hasDefRoleData){
            try {
                throw  new ApplicationStartupVaildException();
            } catch (ApplicationStartupVaildException e) {
                e.printStackTrace();
            }
        }

    }
}
