/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.commons.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName: SpringLoader
 * @Decription:
 * @Author: qian
 * qian create SpringLoader.java of 2021/7/24 9:36 上午
 */
@Component
public class SpringLoader implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringLoader.applicationContext = applicationContext;
    }

    public static <T> Map<String, T> getBeans(Class<T> clazz) {
        return applicationContext.getBeansOfType(clazz);
    }

    public static <T> T getBeanOfType(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public static boolean containsBean(String beanName) {
        return applicationContext.containsBean(beanName);
    }
}
