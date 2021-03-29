package com.geek45.commons;

import com.geek45.commons.bean.BeanUtil;
import com.geek45.commons.dto.User;
import com.geek45.commons.http.HttpClientUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Map;

@SpringBootTest
class CommonsApplicationTests {
	private static final Logger log = LoggerFactory.getLogger(CommonsApplicationTests.class);

	@Test
	void contextLoads() {
	}

	@Test
	void testHttpClient() throws IOException {
		String url = "https://www.baidu.com";

		Map<String, Object> result = HttpClientUtil.doGet(url);
		String resultStr = result.get("result").toString();
		log.info(resultStr);
	}


}
