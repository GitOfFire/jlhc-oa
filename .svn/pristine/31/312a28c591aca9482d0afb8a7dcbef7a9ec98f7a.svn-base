package com.example;

import com.jlhc.ApplicationJlhc;
import com.jlhc.web.controller.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = ApplicationJlhc.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration // 由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
		UserController userController = new UserController();
		//List<User> allUser = userController.getAllUser();
		//System.out.println(allUser);

	}

}
