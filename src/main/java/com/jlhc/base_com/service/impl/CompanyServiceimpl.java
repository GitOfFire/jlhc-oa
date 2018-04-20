package com.jlhc.base_com.service.impl;

import com.jlhc.base_com.dao.CompanyMapper;
import com.jlhc.base_com.dao.CustomerMapper;
import com.jlhc.base_com.dto.Company;
import com.jlhc.base_com.dto.CompanyForPut;
import com.jlhc.base_com.dto.Customer;
import com.jlhc.base_com.dto.example.CompanyExample;
import com.jlhc.base_com.dto.example.CustomerExample;
import com.jlhc.base_com.service.CompanyService;
import com.jlhc.common.exception.NullEntityInDatabaseException;
import com.jlhc.common.utils.RandomStringUtil;
import com.jlhc.oa.service.impl.BaseServiceImpl;
import com.jlhc.solr.dto.CompanySolr;
import com.jlhc.solr.service.CompanySolrService;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class CompanyServiceimpl extends BaseServiceImpl implements CompanyService {

    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    CompanySolrService companySolrService;

    private static CompanyExample companyExample = new CompanyExample();

    private static CustomerExample customerExample = new CustomerExample();

    /**
     * 新添加一个公司信息同时同步到solr中
     *
     * @param company
     * @return
     */
    @Override
    public Integer createCompany(Company company) throws IOException, SolrServerException {
        Integer resultNum = 0;
        //首先进行存在性验证
        //验证社会统一信息编码
        companyExample.clear();
        companyExample.createCriteria()
                .andComUnicodeEqualTo(company.getComUnicode());
        List<Company> companies = companyMapper.selectByExample(companyExample);
        //验证公司名称
        companyExample.clear();
        companyExample.createCriteria()
                .andComNameEqualTo(company.getComName());
        companies.addAll(companyMapper.selectByExample(companyExample));
        if (0 < companies.size() || null == companies){
            return -2;
        }
        //不验证名字的存在性了,不再customer中也没问题
//        customerExample.clear();
//        customerExample.createCriteria()
//                .andCusNameEqualTo(company.getCusName());
//        List<Customer> customers = customerMapper.selectByExample(customerExample);
//        if (null == customers||0 == customers.size()){
//            return -3;
//        }
        String UUID = RandomStringUtil.getRandomCode(32, 7);
        company.setComId(UUID);
        resultNum += companyMapper.insert(company);

        //同步solr
        CompanySolr companySolr = new CompanySolr();
        companySolr.setComId(UUID);
//        companySolr.setComBussinessScope(company.getComBussinessScope());
//        companySolr.setComDescription(company.getComDescription());
        companySolr.setComName(company.getComName());
        companySolr.setComUnicode(company.getComUnicode());
        companySolr.setCusName(company.getCusName());
        //查一下其他相关人物,不用查,查也没有...
//        customerExample.clear();
//        customerExample.createCriteria()
//                .andComIdEqualTo(UUID);
//        customerMapper.selectByExample(customerExample);
        //companySolr.setOtherCusNames("");
        UpdateResponse rsp = companySolrService.createOrReworkSolrCompany(company);
        int status = rsp.getStatus();
        //logger.info(rsp.toString()+":"+status);

        return resultNum;
    }

    /**
     * 修改一个公司的基本信息,包括修改法人
     *
     * @param company
     * @return
     */
    @Override
    public Integer reworkCompany(CompanyForPut company) throws IOException, SolrServerException, NullEntityInDatabaseException {
        Integer resultNum = 0;
        //存在性判断,公司存在,用户姓名不必要必须存在
        Company findComByComId = companyMapper.selectByPrimaryKey(company.getComId());
        if(null == findComByComId){
            throw new NullEntityInDatabaseException();
        }
//        customerExample.clear();
//        customerExample.createCriteria()
//                .andCusNameEqualTo(company.getCusName());
//        List<Customer> customers = customerMapper.selectByExample(customerExample);
//        if (null == customers||0 == customers.size()){
//            return -3;
//        }
        //重复判断,关键信息不能与其他重复
        //验证社会统一信息编码
        companyExample.clear();
        companyExample.createCriteria()
                .andComUnicodeEqualTo(company.getComUnicode())
                .andComIdNotEqualTo(company.getComId());
        List<Company> companies = companyMapper.selectByExample(companyExample);
        //验证公司名称
        companyExample.clear();
        companyExample.createCriteria()
                .andComNameEqualTo(company.getComName())
                .andComIdNotEqualTo(company.getComId());
        companies.addAll(companyMapper.selectByExample(companyExample));
        if (0 < companies.size() || null == companies){
            return -2;
        }
        //执行修改操作
        Company finalCompany = new Company();
        finalCompany.setComId(company.getComId());
        finalCompany.setComAddress(company.getComAddress());
        finalCompany.setComBusinessTerm(company.getComBusinessTerm());
        finalCompany.setComBussinessScope(company.getComBussinessScope());
        finalCompany.setComCapital(company.getComCapital());
        finalCompany.setComDescription(company.getComDescription());
        finalCompany.setComFoundTime(company.getComFoundTime());
        finalCompany.setComName(company.getComName());
        finalCompany.setCusName(company.getCusName());
        finalCompany.setComUnicode(company.getComUnicode());
        finalCompany.setComType(company.getComType());
        resultNum += companyMapper.updateByPrimaryKeySelective(finalCompany);

        companySolrService.createOrReworkSolrCompany(companyMapper.selectByPrimaryKey(finalCompany.getComId()));//之所以要再查一遍,是因为不知道修改了哪些东西
        return resultNum;
    }

    /**
     * 删除,修改状态
     *
     * @param comId
     * @return
     */
    @Override
    public Integer dropCompany(String comId) throws IOException, SolrServerException {
        Integer resultNum = 0;
        if (null == comId||"".equalsIgnoreCase(comId)){
            return 0;
        }
        //校验公司是否存在
        Company company = companyMapper.selectByPrimaryKey(comId);
        if (null == company){
            return -3;
        }
        //执行删除动作
        Company beDeletedCom  = new Company();
        beDeletedCom.setComId(comId);
        beDeletedCom.setComStateType(0);
        resultNum += companyMapper.updateByPrimaryKeySelective(beDeletedCom);

        //执行solr删除
        companySolrService.dropCompanyByComId(comId);
        return resultNum;
    }

    /**
     * 根据主键查询公司详细信息
     *
     * @param comId
     * @return
     */
    @Override
    public Company queryCompanyByComId(String comId) {
        Company company = companyMapper.selectByPrimaryKey(comId);
        return company;
    }
}
