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

import br.com.repository.CompanyRepository;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @author cin_redias
 * @since 17/07/15
 */
public class ProxyFactory {

    public static class MyInvocationHandler implements InvocationHandler {

        public Object invoke(Object proxy, Method method, Object[] args)
                throws Throwable {
            //do something "dynamic"
            System.out.println( method );
            return null;
        }
    }
    public static void main(String[] args) {

        InvocationHandler handler = new MyInvocationHandler();
        CompanyRepository repository = (CompanyRepository) Proxy.newProxyInstance(
                CompanyRepository.class.getClassLoader(),
                new Class[]{CompanyRepository.class},
                handler);

       List list = repository.getCompanyByName("hello");

        System.out.println( list );
    }
}
