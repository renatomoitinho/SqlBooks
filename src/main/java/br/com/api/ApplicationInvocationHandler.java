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

import br.com.repository.BaseRepository;
import br.com.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

/**
 * @author cin_redias
 * @since 17/07/15
 */

@Component
public class ApplicationInvocationHandler implements InvocationHandler {

    @Autowired
    BaseRepository repository;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String query = repository.getQuery(method.getName());
        RowMapper rowMapper = getMapper(method.getDeclaringClass());
        return repository.getTemplate().query(query, rowMapper, args);
    }

    public RowMapper getMapper(Class clazz) throws InstantiationException, IllegalAccessException {
        Class<RowMapper> rowClass = (Class<RowMapper>) ((ParameterizedType) clazz.getGenericInterfaces()[0]).getActualTypeArguments()[1];
        return rowClass.newInstance();
    }
}
