package com.jlhc.base_com.service.impl;

import com.jlhc.base_com.dao.CompanyMapper;
import com.jlhc.base_com.dao.CustomerMapper;
import com.jlhc.base_com.dto.Company;
import com.jlhc.base_com.dto.Customer;
import com.jlhc.base_com.dto.CustomerNotValidId;
import com.jlhc.base_com.dto.example.CompanyExample;
import com.jlhc.base_com.dto.example.CustomerExample;
import com.jlhc.base_com.service.CustomerService;
import com.jlhc.common.utils.RandomStringUtil;
import com.jlhc.oa.service.impl.BaseServiceImpl;
import com.jlhc.solr.service.CompanySolrService;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl extends BaseServiceImpl implements CustomerService {

    @Autowired
    CustomerMapper customerMapper;
    
    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    CompanySolrService companySolrService;

//    private static CustomerExample customerExample = new CustomerExample();
//
//    private static CompanyExample companyExample = new CompanyExample();
    /**
     * 对相同的人进行过滤,然后创建客户
     *
     * @param customerNotValidId
     * @return
     */
    @Override
    public Integer createCustomer(@NotNull CustomerNotValidId customerNotValidId) throws IOException, SolrServerException {
        Integer resultNum = 0;
        //什么情况下代表同一个人，
        //身份证号相同代表同一个人,如果身份证号是null或者""的时候,就不用对身份证重复校验了
        if (!"".equalsIgnoreCase(customerNotValidId.getCusIdCard())&&(null != customerNotValidId.getCusIdCard())) {
            Customer customerByIdCard = this.queryCustomersByIdCard(customerNotValidId.getCusIdCard());
            if (null != customerByIdCard) {
                return -2;
            }
        }
        Customer customer = new Customer();
        //UUID加入
        String cusId = RandomStringUtil.getRandomCode(32,7);
        customer.setCusId(cusId);
        customer.setCallMain(customerNotValidId.getCallMain());
        customer.setCallSecondary(customerNotValidId.getCallSecondary());
        customer.setComId(customerNotValidId.getComId());
        customer.setCusGenderType(customerNotValidId.getCusGenderType());
        customer.setCusIdCard(customerNotValidId.getCusIdCard());
        customer.setCusName(customerNotValidId.getCusName());
        customer.setCusRemarks(customerNotValidId.getCusRemarks());
        //插入
        resultNum += customerMapper.insertSelective(customer);
        Company company = companyMapper.selectByPrimaryKey(customerNotValidId.getComId());
        if (null == company){
            return -3;
        }
        companySolrService.createOrReworkSolrCompany(company);
        return resultNum;
    }

    @Override
    public boolean hasTheSameIdCard(Customer customer1, Customer customer2) {
        String cusIdCard1 = customer1.getCusIdCard();
        String cusIdCard2 = customer2.getCusIdCard();
        int i = cusIdCard1.compareTo(cusIdCard2);
        return i == 0 ?true:false;
    }

    @Override
    public Customer queryCustomersByIdCard(String cusIdCard) {
//        if (null == cusIdCard || "".equalsIgnoreCase(cusIdCard)){
//            return null;
//        }
        CustomerExample customerExample = new CustomerExample();
        customerExample.clear();
        customerExample.createCriteria()
            .andCusIdCardEqualTo(cusIdCard);
        List<Customer> customers = customerMapper.selectByExample(customerExample);
        if (0 == customers.size()){
            return null;
        }
        for (int i = 1 ; i > customers.size(); i++) {
            customerMapper.deleteByPrimaryKey(customers.get(1).getCusId());
        }
        return  customers.get(0);
    }

    /**
     * 修改用户数据
     *
     * @param customer
     * @return 0,未修改任何数据或者传入数据完全不合格
     *         -2,非Null和""的重复的身份证号
     *         -3,不存在的用户数据,无法修改
     */
    @Override
    public Integer reworkCustomer(Customer customer) throws IOException, SolrServerException {
        Integer resultNum = 0;
        if (null == customer||"".equalsIgnoreCase(customer.getCusId())||null == customer.getCusId()){
            return resultNum;
        }
        //首先校验是否存在
        Customer customerByKey = customerMapper.selectByPrimaryKey(customer.getCusId());
        if (null == customerByKey){
            return -3;
        }
        //验证身份证号是不是改重了,如果修改的身份证号是null或者""的时候,就不用对身份证校验了
        if (!"".equalsIgnoreCase(customer.getCusIdCard())&&(null != customer.getCusIdCard())){
            CustomerExample customerExample = new CustomerExample();
            customerExample.clear();
            customerExample.createCriteria()
                    .andCusIdCardEqualTo(customer.getCusIdCard());
            List<Customer> customers = customerMapper.selectByExample(customerExample);
            if (1 < customers.size()){
                return -2;
            }
        }
        //执行修改动作
        resultNum += customerMapper.updateByPrimaryKeySelective(customer);
        Company company = companyMapper.selectByPrimaryKey(customer.getComId());
        if (null == company){
            return -3;
        }
        companySolrService.createOrReworkSolrCompany(company);
        return resultNum;
    }

    /**
     * 删除联系人
     *
     * @param cusId
     * @return -4,有其他数据不能删除
     */
    @Override
    public Integer dropCustomerById(@NotNull String cusId) throws IOException, SolrServerException {
        Integer resultNum = 0;
        //判断用户是否为某一企业法人,不判断了,法人只存名字
//        companyExample.clear();
//        companyExample.createCriteria()
//                .andCusNameEqualTo(cusId);
//        List<Company> companies = companyMapper.selectByExample(companyExample);
//        if (0 < companies.size()){
//            return -4;
//        }
        Customer customer = customerMapper.selectByPrimaryKey(cusId);
        if (null == customer){
            return 1;//说明已经删除了
        }
        Company company = companyMapper.selectByPrimaryKey(customer.getComId());
        if (null == company){
            return -3;
        }
        resultNum += customerMapper.deleteByPrimaryKey(cusId);
        
        companySolrService.createOrReworkSolrCompany(company);
        return resultNum;
    }

    /**
     * 根据公司ID查询所有相关客户
     *
     * @param comId
     * @return 该公司关联的所有客户
     */
    @Override
    public List<Customer> queryCustomersByComId(String comId) {
        if (null == comId||"".equalsIgnoreCase(comId)){
            return null;
        }
        CustomerExample customerExample = new CustomerExample();
        customerExample.clear();
        customerExample.createCriteria()
                .andComIdEqualTo(comId);
        List<Customer> customers = customerMapper.selectByExample(customerExample);
        logger.info(customers.toString());
        return customers;
    }

    /**
     * 查询一个公司的法定代表
     *
     * @param comId
     * @return
     */
    @Override
    public String queryRepresentativeOfCom(String comId) {
        if (null == comId||"".equalsIgnoreCase(comId)){
            return null;
        }
        //根据公司查出法定代表人ID
        Company company = companyMapper.selectByPrimaryKey(comId);
        if (null == company||"".equalsIgnoreCase(company.getCusName())||null == company.getCusName()){
            return null;
        }

        //根据该company查询该法定代表的详细信息

        return company.getCusName();
    }

    /**
     * 根据姓名模糊查询法人
     *
     * @param cusName
     * @return
     */
    @Override
    public List<Customer> queryCustomersByName(String cusName) {
        CustomerExample customerExample = new CustomerExample();
        if(null == cusName||"".equalsIgnoreCase(cusName)){
            customerExample.clear();
            customerExample.createCriteria()
                    .andCusNameLike("");

        }
        customerExample.clear();
        cusName = "%" + cusName + "%";
        customerExample.createCriteria()
                .andCusNameLike(cusName);
        List<Customer> customers = customerMapper.selectByExample(customerExample);
        return customers;
    }


}
