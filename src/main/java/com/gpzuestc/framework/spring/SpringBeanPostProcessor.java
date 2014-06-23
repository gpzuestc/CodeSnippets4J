package com.gpzuestc.framework.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author gpzuestc
 * @date: 2014-6-23
 * @description:  
 * 
 */
@Component
public class SpringBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		System.out.println("--springBeanPostProcessor: post process before initialization, [beanName=" + beanName + ", bean=" + bean + "]");  
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		System.out.println("--springBeanPostProcessor: post process after initialization, [beanName=" + beanName + ", bean=" + bean + "]");  
		return bean;
	}

}
