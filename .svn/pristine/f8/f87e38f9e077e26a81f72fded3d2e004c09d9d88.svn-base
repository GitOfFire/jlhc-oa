package com.jlhc.base_com.service;

import com.jlhc.base_com.dto.Company;
import com.jlhc.base_com.dto.CompanyForPut;
import com.jlhc.common.exception.NullEntityInDatabaseException;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

public interface CompanyService {
    Integer createCompany(Company company) throws IOException, SolrServerException;

    Integer reworkCompany(CompanyForPut company) throws IOException, SolrServerException, NullEntityInDatabaseException;

    Integer dropCompany(String comId) throws IOException, SolrServerException;

    Company queryCompanyByComId(String comId);
}
