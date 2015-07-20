/* Copyright (c) - UOL Inc,
 * Todos os direitos reservados
 *
 * Este arquivo e uma propriedade confidencial do Universo Online Inc.
 * Nenhuma parte do mesmo pode ser copiada, reproduzida, impressa ou
 * transmitida por qualquer meio sem autorizacao expressa e por escrito
 * de um representante legal do Universo Online Inc.
 *
 * All rights reserved
 *
 * This file is a confidential property of Universo Online Inc.
 * No part of this file may be reproduced or copied in any form or by
 * any means without written permission from an authorized person from
 * Universo Online Inc.
 */

package br.com.api;

import br.com.api.annotations.Queries;
import br.com.repository.CompanyRepository;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;

/**
 * @author cin_redias
 * @since 17/07/15
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {

    @Autowired
    ProxyFactory proxyFactory;

    ApplicationScannerComponentProvider scanner;

    private static ApplicationContext context;

    @PostConstruct
    public void initializer() throws ClassNotFoundException {
        scanner = new ApplicationScannerComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Queries.class));

        for (BeanDefinition bd : scanner.findCandidateComponents("")) {
            verifyMethod(Class.forName(bd.getBeanClassName()));
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ConfigurableApplicationContext configContext = (ConfigurableApplicationContext) applicationContext;
        configContext.getBeanFactory().registerSingleton("companyRepository", proxyFactory.getInstance(CompanyRepository.class));
        context = configContext;
    }

    public void verifyMethod(Class clazz){
        for(Method method: clazz.getDeclaredMethods()){
           // System.out.println( method.getName() );
        }
    }

}
