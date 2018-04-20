package com.jlhc.web.controller;

import com.jlhc.base_com.dto.Company;
import com.jlhc.base_com.dto.CompanyForPut;
import com.jlhc.base_com.service.CompanyService;
import com.jlhc.common.utils.ResultUtil;
import com.jlhc.common.dto.Msg;
import com.jlhc.solr.dto.CompanySolr;
import com.jlhc.solr.service.CompanySolrService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("company")
public class CompanyController extends BaseController{

    /**注入ComService*/
    @Autowired
    CompanyService companyService;

    @Autowired
    CompanySolrService companySolrService;

    /**
     * 新增一个公司数据
     * @param company
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "postCompany")
    @RequiresPermissions("company:postCompany")
    public Msg postCompany(@RequestBody @Valid Company company,BindingResult bindingResult){
        try{
            Integer resultNum = companyService.createCompany(company);
            if (-2 == resultNum){
                return ResultUtil.operationFailed("数据库中,公司已经存在(公司名不能重复;社会统一信用代码不能重复)");
            }
            return ResultUtil.addSuccess(resultNum);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }

//    @GetMapping("getDate/{date}")
//    public String getDate(@PathVariable @DateTimeFormat Date date){
//        logger.info(date.toString());
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        String format = dateFormat.format(date);
//        return format;
//    }

    /**
     * 修改一个公司的基础数据
     *
     * @param company
     * @return
     */
    @PutMapping(value = "putCompany")
    @RequiresPermissions("company:putCompany")
    public Msg putCompany(@RequestBody @Valid CompanyForPut company, BindingResult bindingResult){
        try{
            //logger.info("进来了...");
            Integer resultNum = companyService.reworkCompany(company);
            /*if (-3 == resultNum){
                return ResultUtil.operationFailed("公司/公司法定代表人数据不存在,不能指定");
            }else */if (-2 == resultNum){
                return ResultUtil.operationFailed("数据库中,公司已经存在(企业名称已经被其他公司使用/社会统一信用代码已经被其他公司使用)");
            }
            return ResultUtil.updateSuccess(resultNum);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }


    /**
     * 删除,修改状态
     *
     * @param comId
     * @return
     */
    @DeleteMapping(value = "deleteCompany/{comId}")
    @RequiresPermissions(value = "company:deleteCompany")
    public Msg deleteCompany(@PathVariable @Length(max = 32,min = 32,message = "32位全球唯一码") String comId){
        try{
            Integer resultNum = companyService.dropCompany(comId);
            if (-3 == resultNum){
                return ResultUtil.operationFailed("公司不存在");
            }else if (0 == resultNum){
                return ResultUtil.operationFailed();
            }
            return ResultUtil.deleteSuccess(resultNum);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    };
    //仅仅测试solr使用
    @PostMapping("testCreateSolrCompany")
    @RequiresPermissions(value = "companysolr:testCreateSolrCompany")
    public Msg testCreateSolrCompany(@RequestBody Company company,BindingResult bindingResult){
        UpdateResponse solrCompany = null;
        try {
            solrCompany = companySolrService.createOrReworkSolrCompany(company);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return ResultUtil.operationSuccess(solrCompany);

    }

    //仅仅测试solr使用
    @GetMapping("testQuerySolrCompanyByName/{name}")
    @RequiresPermissions(value = "companysolr:testQuerySolrCompanyByName")
    public Msg testQuerySolrCompanyByName(@PathVariable String name){
        List<CompanySolr> companySolrs = null;
        try {
            companySolrs = companySolrService.querySolrCompanyByName(name);
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return ResultUtil.operationSuccess(companySolrs);
    }
    //仅仅测试solr使用
    @DeleteMapping("testDeleteSolrCompanyById/{comId}")
    @RequiresPermissions(value="companysolr:testDeleteSolrCompanyById")
    public Msg testDeleteSolrCompanyById(@PathVariable String comId){
        try{
            companySolrService.dropCompanyByComId(comId);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
        return ResultUtil.deleteSuccess();
    }

    /**
     * solr的模糊查询
     * 根据社会统一信用代码精确查
     * 根据企业信息名称模糊查
     * 根据法人真实姓名模糊查
     * 根据其他人真实姓名模糊查
     * 根据电话号查
     *
     * @param words
     * @return
     */
    @GetMapping("getCompanysByFuzzySearchAll")
    @RequiresPermissions("companysolr:getCompanysByFuzzySearchAll")
    public Msg getCompanysByFuzzySearchAll(@RequestParam String words){
        try{
            List<CompanySolr> companySolrs = companySolrService.querySolrCompanyByFuzzySearchAll(words);
            if (null == companySolrs|| 0 >= companySolrs.size()){
                return ResultUtil.selectFailed();
            }
            return ResultUtil.selectSuccess(companySolrs);
        }catch(Exception e){
            return errorResultOperation(e);
        }
    };

    /**
     * 根据Id查询公司信息
     *
     * @param comId
     * @return
     */
    @GetMapping("getCompanyByComId/{comId}")
    @RequiresPermissions(value = "company:getCompanyByComId")
    public Msg getCompanyByComId(@PathVariable @Length(max=32,min=32,message = "主键为32位全球唯一码") String comId){
        try{
            Company company = companyService.queryCompanyByComId(comId);
            if (null == company){
                return ResultUtil.selectFailed();
            }
            return ResultUtil.selectSuccess(company);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 查询全部公司信息
     *
     * @return
     */
    @GetMapping("getAllCompanyFromSolr")
    @RequiresPermissions(value = "companysolr:getAllCompanyFromSolr")
    public Msg getAllCompanyFromSolr(){
        try{
            SolrDocumentList results = companySolrService.queryAllCompanyFromSolr();
            return ResultUtil.selectSuccess(results);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }


}
