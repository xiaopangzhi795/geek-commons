package com.geek45.commons;

import com.geek45.commons.bean.BeanUtil;
import com.geek45.commons.dto.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommonsApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void copyTest(){
		User src = new User();
		src.setGender("1");
		src.setName("tes");
		User des = null;

	}

}
