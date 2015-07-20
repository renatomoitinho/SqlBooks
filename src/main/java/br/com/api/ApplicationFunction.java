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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.BiFunction;

/**
 * @author cin_redias
 * @since 20/07/15
 */
@Component
public class ApplicationFunction {

    @Autowired
    BaseRepository repository;

    public void mapper(String methodName, String query, BiFunction function) {

    }

    public Object query(String query,RowMapper mapper,Object... params){
        return repository.getTemplate().query(query,mapper,params);
    }

}
