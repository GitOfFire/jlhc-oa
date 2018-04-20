package com.jlhc.web.controller;

import com.jlhc.common.utils.ResultUtil;
import com.jlhc.common.dto.Msg;
import com.jlhc.oa.dto.user.Organization;
import com.jlhc.oa.dto.user.OrganizationWithChildTree;
import com.jlhc.oa.dto.user.User;
import com.jlhc.oa.service.OrganizationService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author renzhong
 * @version 1.0
 * @Description
 * @Date: Created in 15:59 2018/1/9 0009
 */
@RequestMapping("/organization")
@RestController
public class OrganizationController extends BaseController{

    @Autowired
    OrganizationService organizationService;
    /**
     * 添加组织,数据校验,封装数据传递
     *
     * @param organization
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "addOrganization",method = RequestMethod.POST)
    @RequiresPermissions("organization:add")
    public Msg addOrganization(@RequestBody @Valid Organization organization, BindingResult bindingResult){
        //首先处理校验异常,已经由springAOP--vaildAspect实现
        /*if (bindingResult.hasErrors()){
            Map<String, String> vaildErrorMap = super.getVaildErrorMap(bindingResult);
            return ResultUtil.vaildError(vaildErrorMap);
        }*/
        //然后调用service进行插入处理
        try {
            System.out.println(organization);
            Integer resultNum = organizationService.createOrganization(organization);
            if (-2 == resultNum){
                return ResultUtil.operationFailed("组织机构名称相同");
            } else if (0 == resultNum){
                return ResultUtil.operationFailed();
            }
            return ResultUtil.addSuccess(resultNum);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(e);
        }
    }

    /**
     * 根据父节点查询组织
     *
     * @param orgParentId 父节点ID
     * @return
     */
    @RequestMapping(value = "findOrganizationsByParent/{orgParentId}",method = RequestMethod.GET)
    @RequiresPermissions("organization:query")
    public Msg findOrganizationsByParent(@PathVariable @NotNull @Max(value = 999999999,message = "最大不能超过9位")Integer orgParentId){
        try {
            List<Organization> orgByParent = organizationService.findOrgByParent(orgParentId);
            return ResultUtil.selectSuccess(orgByParent);
        }catch (Exception e){
            logger.error("根据父节点查询组织",e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 修改组织机构名称&&修改组织节点名称
     *
     * @param organization 主要封装信息是id name
     * @return
     */
    @ApiOperation(value = "8736f8adf41a4fa79508857ef2462434",notes = "机构:修改",nickname = "机构修改")
    @RequestMapping(value = "updateParentOrNameOfOrganization",method = RequestMethod.PUT)
    @RequiresPermissions("organization:update")
    public Msg updateParentOrNameOfOrganization(@RequestBody @Valid Organization organization,BindingResult bindingResult){
        try {
            Integer resultNum = organizationService.updateNameOfOrganization(organization);
            if (-2 == resultNum){
                return ResultUtil.operationFailed("组织机构名称相同");
            } else if (0 == resultNum){
                return ResultUtil.operationFailed();
            }
            return ResultUtil.updateSuccess(resultNum);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(e);
        }
    }
    /********组织机构删除*********/
    /**
     * 删除组织机构节点,该方法必须提示,需要将其子节点删除,会将用户归于公司总节点下
     *
     * @param orgId
     * @return
     */
    @ApiOperation(value = "ba49c4dc6e4a479a84212d739d5d96ea",notes = "机构:删除",nickname = "机构删除")
    @RequestMapping(value = "deleteOrgById/{orgId}",method = RequestMethod.DELETE)
    @RequiresPermissions("organization:delete")
    public Msg deleteOrgById(@PathVariable @NotNull @Max(value = 999999999,message = "最大不能超过9位") Integer orgId){
        //调用service方法
        try {
            Integer resultNum = organizationService.dropOrgWithChild(orgId);
            if (resultNum == -2){
                return ResultUtil.operationFailed("根节点或公司节点不允许直接删除");
            }else if (resultNum == 0){
                return ResultUtil.operationFailed();
            }
            return ResultUtil.deleteSuccess(resultNum);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 递归查询组织机构的所有下属组织机构Id
     *
     * @param orgId
     * @return
     */
    @RequestMapping(value = "getAllOrganizationIdsByParent/{orgId}",method = RequestMethod.GET)
    @RequiresPermissions("organization:getAllOrganizationIdsByParent")
    public Msg getAllChildrenOrgIdByOrgId(@PathVariable @NotNull @Max(value = 999999999,message = "最大不能超过9位")Integer orgId){
        try{
            List<Integer> allChildrenOrgId = organizationService.queryAllChildrenOrgId(orgId);
            if (null == allChildrenOrgId|| 0 == allChildrenOrgId.size()){
                return ResultUtil.selectFailed();
            }
            return ResultUtil.selectSuccess(allChildrenOrgId);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 根据orgId查询父节点(面包屑)
     *
     * @param orgId
     * @return
     */
    @RequestMapping(value = "getParentOrgByOrgId/{orgId}",method = RequestMethod.GET)
    @RequiresPermissions("organization:getParentOrgByOrgId")
    public Msg getParentOrgByOrgId(@PathVariable @NotNull @Max(value = 999999999,message = "最大不能超过9位")Integer orgId){
        try{
            List<Organization> parentsAndOrg = organizationService.queryParentOrgByOrgId(orgId);
            if (0 >= parentsAndOrg.size()){
                return ResultUtil.selectFailed();
            }
            return ResultUtil.selectSuccess(parentsAndOrg);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 根据orgId获取该组织机构的详细信息
     *
     * @param orgId
     * @return
     */
    @GetMapping(value = "getBaseOrgInfo/{orgId}")
    @RequiresPermissions("organization:getBaseOrgInfo")
    public Msg getBaseOrgInfo(@PathVariable @NotNull @Max(value = 999999999,message = "最大不能超过9位")Integer orgId){
        try{
            Organization organization = organizationService.queryBaseOrgInfo(orgId);
            if (null == organization){
                return ResultUtil.selectFailed();
            }
            return ResultUtil.selectSuccess(organization);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return ResultUtil.error(e);
        }
    }

    /**
     * 添加company
     *
     * @param orgNameAsCompany
     * @return
     */
    @PostMapping(value = "postCompanyOrg")
    @RequiresPermissions("organization:foradmin:postCompanyOrg")
    public Msg postCompanyOrg(@RequestParam("orgNameAsCompany")
                                  @Length(min = 1,max = 64)
                                  @NotEmpty String orgNameAsCompany){
        try{
            Organization organization = new Organization();
            organization.setOrgName(orgNameAsCompany);
            organization.setOrgParentId(0);//肯定父节点是0
            Integer resultNum = organizationService.createOrganization(organization);
            if (-2 == resultNum){
                return ResultUtil.operationFailed("公司名称重复!!");
            }
            return ResultUtil.addSuccess(resultNum);
        }catch(Exception e){
            return errorResultOperation(e);
        }
    }

    /**
     * 分页查询根节点组织(使用系统的公司),分页参数废弃不用
     *
     * @Param
     * @param offSet 偏移页数(显示几页)
     * @param limit 第几页(开始显示)
     * @return
     */
    @PostMapping(value = "getCompanyOrgsWithPageInfo")
    @RequiresPermissions("organization:foradmin:getCompanyOrgsWithPageInfo")
    public Msg getCompanyOrgsWithPageInfo(@RequestParam("orgNameAsCom")
                                                      String orgNameAsCom,
                                          @RequestParam("offSet") Integer offSet,
                                          @RequestParam("limit") Integer limit){
        try{
            List<Organization> companyOrgs = organizationService.queryCompanyOrgsWithPageInfo(orgNameAsCom,offSet,limit);
            if (0 >= companyOrgs.size()){
                return ResultUtil.selectFailed();
            }
            return ResultUtil.selectSuccess(companyOrgs);
        }catch(Exception e){
            return errorResultOperation(e);
        }
    }

    /**
     * 获取用户所在org节点的分支树
     *
     * @return
     */
    @GetMapping(value = "getTheChildrenTreeOfTheOrg")
    @RequiresPermissions("organization:getTheChildrenTreeOfTheOrg")
    public Msg getTheChildrenTreeOfTheOrg(HttpSession httpSession){
        try{
            User user = (User)httpSession.getAttribute("user");
            Integer orgId = user.getOrgId();
            Organization organizationWithChildTree
                = organizationService.queryTheChildrenTreeOfTheOrg(orgId);
            return ResultUtil.selectSuccess(organizationWithChildTree);
        }catch(Exception e){
            return errorResultOperation(e);
        }        
    }

    /**
     * 获取用户下属的所有org节点
     *
     * @param httpSession
     * @return
     */
    @RequiresPermissions(value = "organization:getTheChildrenOfTheOrg")
    @GetMapping(value = "getTheChildrenOfTheOrg")
    public Msg getTheChildrenOfTheOrg(HttpSession httpSession){
        try{
            User user = (User)httpSession.getAttribute("user");
            Integer orgId = user.getOrgId();
            List<Organization> organizations = organizationService.queryTheChildrenOfTheOrg(orgId);
            if (null == organizations||0==organizations.size()){
                return ResultUtil.selectFailed();
            }
            return ResultUtil.selectSuccess(organizations);
        }catch(Exception e){
            return errorResultOperation(e);
        }
    }
}
