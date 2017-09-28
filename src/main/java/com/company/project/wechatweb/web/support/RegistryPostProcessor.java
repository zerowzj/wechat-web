package com.company.project.wechatweb.web.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wangzhj on 2017/5/31.
 */
//@Configuration
public class RegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistryPostProcessor.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        LOGGER.info("Invoke Metho postProcessBeanFactory");
        BeanDefinition definition = beanFactory.getBeanDefinition("dataSource");
        MutablePropertyValues mpv = definition.getPropertyValues();
        Object obj = mpv.get("username");
        LOGGER.info(obj.toString());
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        LOGGER.info("Invoke Metho postProcessBeanDefinitionRegistry");

        BeanDefinition definition = registry.getBeanDefinition("dataSource");
        MutablePropertyValues mpv = definition.getPropertyValues();
        Object obj = mpv.get("username");
        LOGGER.info(obj.toString());
//        registerBean(registry, "shanhyA", ShanhyA.class);
//        registerBean(registry, "shanhyB", ShanhyB.class);
        String[] beanNames = registry.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            LOGGER.info("===> bean name = {}", beanName);
        }
    }

    private void registerBean(BeanDefinitionRegistry registry, String beanName, Class<?> beanClass) {

//        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition();
//        builder.getRawBeanDefinition().setB
//
//        BeanWrapper beanWrapper = new BeanWrapperImpl(new Object());
//
//
//        BeanDefinition beanDefinition = new GenericBeanDefinition();
//        AnnotatedGenericBeanDefinition abd = new AnnotatedGenericBeanDefinition(beanClass);
//
//        ScopeMetadata scopeMetadata = scopeMetadataResolver.resolveScopeMetadata(abd);
//        abd.setScope(scopeMetadata.getScopeName());
//        // 可以自动生成name
//        String beanName = (name != null ? name : this.beanNameGenerator.generateBeanName(abd, registry));
//
//        AnnotationConfigUtils.processCommonDefinitionAnnotations(abd);
//
//        BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(abd, beanName);
//        BeanDefinitionReaderUtils.registerBeanDefinition(definitionHolder, registry);
    }
}
