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

import br.com.api.Book;
import br.com.model.Company;
import br.com.repository.wrapper.CompanyMapper;

import java.util.List;

/**
 * @author cin_redias
 * @since 16/07/15
 */

@Book("company")
public interface CompanyRepository extends Repository<Company,CompanyMapper> {
    List<Company> getCompanyByName(String name);
}
