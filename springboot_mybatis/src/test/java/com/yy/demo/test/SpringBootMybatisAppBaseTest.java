package com.yy.demo.test;

import com.yy.demo.config.postprocess.Chinese;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class SpringBootMybatisAppBaseTest {
	
	@Test
	public void test1() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        Chinese chinese = (Chinese) context.getBean("chinese");
        chinese.getName();
		System.out.println("test");
	}

}
