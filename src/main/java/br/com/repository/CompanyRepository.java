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

package br.com.repository;

import br.com.api.annotations.*;
import br.com.model.Company;
import br.com.repository.wrapper.CompanyMapper;

import java.util.List;

/**
 * @author cin_redias
 * @since 16/07/15
 */

@Queries("company")
public interface CompanyRepository extends Repository<Company, CompanyMapper> {

    @Query("select * from company where name =:name")
    List<Company> getCompanyByName(@Param("name") String name);

    @Query("select * from company where id =:id")
    Company getById(@Param("id") Integer id);

    @Update("update company set name=:name,age=:age,address=:address,salary=:salary where id=:id")
    void update(Company company);

    @Delete("delete from company where id=:id")
    void delete(Integer id);

    @Insert("insert into company where id=:id")
    void save(Integer id);

    @Query("select count(*) from company")
    Long count();

    @BatchUpdate("update company set name=:name,age=:age,address=:address,salary=:salary where id=:id")
    void batchUpdate(List<Company> companies);

}
