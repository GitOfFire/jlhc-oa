package com.jlhc.web.controller;

import com.jlhc.base_com.dto.Customer;
import com.jlhc.base_com.dto.CustomerNotValidId;
import com.jlhc.common.utils.ResultUtil;
import com.jlhc.common.dto.Msg;
import com.jlhc.base_com.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController extends BaseController{

    @Autowired
    CustomerService customerService;

    /**
     * 添加一个用户
     *
     * @param customerNotValidId
     * @param bindingResult
     * @return
     */
    @ApiOperation(value = "传入参数中cusId不必传,添加用户仅仅使用IDcard去重,IdCard为null或者为空不进行重复校验")
    @PostMapping(value = "postCustomer")
    @RequiresPermissions(value = "customer:postCustomer")
    public Msg postCustomer(@RequestBody @Valid CustomerNotValidId customerNotValidId,BindingResult bindingResult){
        try{
            Integer resultNum = customerService.createCustomer(customerNotValidId);
            if (0 == resultNum){
                return ResultUtil.operationFailed("用户添加失败");
            }else if ( -2 == resultNum){
                return ResultUtil.operationFailed("数据库中存在相同身份证号的用户");
            }else if (-3 == resultNum){
                return ResultUtil.addSuccess("关联的企业信息数据不存在,仅添加了单独用户在数据库中");
            }
            return ResultUtil.addSuccess(resultNum);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 根据IdCard查询一个客户
     *
     * @param cusIdCard
     * @return
     */
    @GetMapping(value = "getCustomersByIdCard/{cusIdCard}")
    @RequiresPermissions(value = "customer:getCustomersByIdCard")
    public Msg getCustomersByIdCard(@Length(min = 18,max = 18,message = "身份证号为18位") String cusIdCard){
        try{
            Customer customer = customerService.queryCustomersByIdCard(cusIdCard);
            if (null == customer){
                return ResultUtil.selectFailed();
            }
            return  ResultUtil.selectSuccess(customer);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 修改某一个用户的数据
     *
     * @param customer
     * @return
     */
    @PutMapping(value = "putCustomer")
    @RequiresPermissions(value = "customer:putCustomer")
    public Msg putCustomer(@RequestBody @Valid Customer customer,BindingResult bindingResult){
        try{
            Integer resultNum = customerService.reworkCustomer(customer);
            if (-2 == resultNum){
                return ResultUtil.operationFailed("身份证号重复了");
            }else if (-3 == resultNum){
                return ResultUtil.operationFailed("被修改的数据不存在");
            }else if (0 == resultNum){
                return ResultUtil.operationFailed("未修改任何数据");
            }
            return ResultUtil.updateSuccess(resultNum);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 删除客户信息
     *
     * @param cusId
     * @return
     */
    @DeleteMapping(value = "deleteCustomer/{cusId}")
    @RequiresPermissions(value = "customer:deleteCustomer")
    public Msg deleteCustomer(@PathVariable @Length(min =32 ,max = 32,message = "主键为32位全球唯一码") String cusId){
        try{
            Integer resultNum = customerService.dropCustomerById(cusId);
            if( 0 == resultNum){
                return ResultUtil.operationFailed();
            }else if (-3 == resultNum){
                return ResultUtil.operationFailed("数据库中,company不存在,无法同步solr库中的company数据!!");
            }
            return ResultUtil.deleteSuccess(resultNum);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 根据公司主键查询一个公司相关的所有客户
     *
     * @param comId
     * @return
     */
    @GetMapping(value = "getCustomersByCompanyId/{comId}")
    @RequiresPermissions(value = "customer:getCustomersByCompanyId")
    public Msg getCustomersByCompanyId(@PathVariable @Length(min =32 ,max = 32,message = "主键为32位全球唯一码") String comId){
        try{
            List<Customer> customers = customerService.queryCustomersByComId(comId);
            if (0 == customers.size()||null == customers){
                return ResultUtil.selectFailed();
            }
            return ResultUtil.selectSuccess(customers);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 查询公司的法人代表
     *
     * @param comId 公司ID
     * @return
     */
    @GetMapping(value = "getRepresentativeOfCom/{comId}")
    @RequiresPermissions(value = "customer:getRepresentativeOfCom")
    public Msg getRepresentativeOfCom(@PathVariable @Length(min =32 ,max = 32,message = "主键为32位全球唯一码") String comId){
        try{
            String cusName = customerService.queryRepresentativeOfCom(comId);
            if (null == cusName){
                return ResultUtil.selectFailed();
            }
            return ResultUtil.selectSuccess(cusName);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 根据姓名模糊查询所有的联系人
     *
     * @param cusName
     * @return
     */
    @GetMapping(value = "getCustomersByName")
    @RequiresPermissions(value = "customer:getCustomersByName")
    public Msg getCustomersByName(@RequestParam(value = "cusName") @NotNull @Size(max = 255) String cusName){
        try{
            List<Customer> customers = customerService.queryCustomersByName(cusName);
            if (0 >= customers.size()){
                return ResultUtil.selectFailed();
            }
            return ResultUtil.selectSuccess(customers);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }
}
