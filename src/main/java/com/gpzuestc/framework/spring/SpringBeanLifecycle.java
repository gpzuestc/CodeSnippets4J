package com.gpzuestc.framework.spring;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author gpzuestc
 * @date: 2014-6-23
 * @description:  
 * 
 */
public class SpringBeanLifecycle implements BeanFactoryAware, BeanNameAware,InitializingBean, DisposableBean{
	
	private String foo;
	
	public SpringBeanLifecycle(){
		System.out.println("--constructor");
	}

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-beans.xml");
		ctx.close();
		
		/**
		--constructor
		--set method
		--beanName aware, [name=springBeanLifecircle]
		--beanFactory aware, [beanFactory=org.springframework.beans.factory.support.DefaultListableBeanFactory@1a0fced4: defining beans [springBeanPostProcessor,org.springframework.context.annotation.internalConfigurationAnnotationProcessor,org.springframework.context.annotation.internalAutowiredAnnotationProcessor,org.springframework.context.annotation.internalRequiredAnnotationProcessor,org.springframework.context.annotation.internalCommonAnnotationProcessor,springBeanLifecircle,org.springframework.context.annotation.ConfigurationClassPostProcessor$ImportAwareBeanPostProcessor#0]; root of factory hierarchy]
		--springBeanPostProcessor: post process before initialization, [beanName=springBeanLifecircle, bean=com.gpzuestc.framework.spring.SpringBeanLifecycle@2e1551b0]
		--postConstruct method of @PostConstruct
		--after properties set of InitializingBean interface
		--init-method of xml conf
		--springBeanPostProcessor: post process after initialization, [beanName=springBeanLifecircle, bean=com.gpzuestc.framework.spring.SpringBeanLifecycle@2e1551b0]
		--preDestroy method of @@PreDestroy
		--destroy-method of DisposableBean interface
		--destroy-method of xml conf
		**/
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("--destroy-method of DisposableBean interface");  
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("--after properties set of InitializingBean interface");  
		
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("--beanName aware, [name=" + name + "]"); 
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("--beanFactory aware, [beanFactory=" + beanFactory.toString() + "]");  
		
	}
	
	public void initMethod(){
		System.out.println("--init-method of xml conf");
	}
	
	public void destroyMethod(){
		System.out.println("--destroy-method of xml conf");
	}
	
	@PostConstruct
	public void postConstructor(){
		System.out.println("--postConstruct method of @PostConstruct");
	}
	
	@PreDestroy
	public void preDestroy(){
		System.out.println("--preDestroy method of @@PreDestroy");
	}

	public String getFoo() {
		return foo;
	}

	public void setFoo(String foo) {
		this.foo = foo;
		System.out.println("--set method");
	}
}
