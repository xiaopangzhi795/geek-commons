package com.geek45.commons;

import com.geek45.commons.http.HttpClientUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
@SpringBootConfiguration
public class CommonsApplicationTests {
	private static final Logger log = LoggerFactory.getLogger(CommonsApplicationTests.class);

	@Test
	public void contextLoads() {
	}

	@Test
	public void testHttpClient() throws IOException {
		String url = "https://www.baidu.com";

		Map<String, Object> result = HttpClientUtil.doGet(url);
		String resultStr = result.get("result").toString();
		log.info(resultStr);
	}


}
