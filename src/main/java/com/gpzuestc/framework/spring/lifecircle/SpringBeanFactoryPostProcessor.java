package com.gpzuestc.framework.spring.lifecircle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author gpzuestc
 * @date: 2014-6-23
 * @description:  
 * 
 */
@Component
public class SpringBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("<--- begin SpringBeanFactoryPostProcessor");
		String[] names = beanFactory.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println("definition bean name:" + name);
		}
		System.out.println("---> end SpringBeanFactoryPostProcessor");
	}

}

