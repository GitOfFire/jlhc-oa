package com.jlhc.oa.service.impl;

import com.jlhc.ApplicationJlhc;
import com.jlhc.common.exception.NullEntityInDatabaseException;
import com.jlhc.oa.dao.*;
import com.jlhc.oa.dto.role.Role;
import com.jlhc.oa.dto.role.RoleGroup;
import com.jlhc.oa.dto.role.example.RoleExample;
import com.jlhc.oa.dto.role.example.RoleGroupExample;
import com.jlhc.oa.dto.user.*;
import com.jlhc.oa.dto.user.example.OrganizationExample;
import com.jlhc.oa.dto.user.example.UserOrganizationRelationExample;
import com.jlhc.oa.service.OrganizationService;
import com.jlhc.oa.service.RoleGroupService;
import org.apache.ibatis.jdbc.Null;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author renzhong
 * @version 1.0
 * @Description
 * @Date: Created in 16:11 2018/1/9 0009
 */
@Service
public class OrganizationServiceImpl implements OrganizationService{
    private final static Logger logger = LoggerFactory.getLogger(ApplicationJlhc.class);

    /**声明用户组织关系映射UserOrgMapper*/
    @Autowired
    UserOrganizationRelationMapper userOrganizationRelationMapper;

    /**声明OrgMapper*/
    @Autowired
    OrganizationMapper organizationMapper;

    /**声明UserMapper*/
    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleGroupMapper roleGroupMapper;

    @Autowired
    RoleGroupService roleGroupService;

    @Autowired
    RoleMapper roleMapper;

//    RoleGroupExample roleGroupExample = new RoleGroupExample();
//
//    OrganizationExample organizationExample = new OrganizationExample();
//
//    RoleExample roleExample = new RoleExample();
    /**
     * 判断下是不是root节点或者企业节点
     *
     * @param domainOrgId
     * @return true : 录入节点为root或者公司节点; false : 录入节点为普通节点
     */
    public boolean isRootOrCompany(Integer domainOrgId){
        boolean isRoot = 0 == domainOrgId;
        Integer rootId = this.showOrgParentIdByOrgChildId(domainOrgId);
        boolean isCompany;
        if (null == rootId){
            isCompany = false;
        }else {
            isCompany = (0 == rootId);
        }
        boolean b = isRoot || isCompany;
        //   boolean b = (0 == domainOrgId )||(0 == showOrgParentIdByOrgChildId(domainOrgId));
        //logger.info(Boolean.toString(b));
        return b;
    }

    /**
     * 单查父id
     *
     * @param domainOrgId
     * @return 父节点id
     */
    public Integer showOrgParentIdByOrgChildId(Integer domainOrgId){

        return organizationMapper.selectOrgParentIdByOrgChildId(domainOrgId);

    }

    //提供一个根据子节点查询父节点的接口,如果根节点是企业节点,也就是查到root根节点0以下的那一级

    /**
     * 所属企业节点查询
     *
     * @param domainId 被删除的组织节点
     * @return 被删除的组织所属的企业,或者是根节点
     */
    public Integer getChildOfRoot(Integer domainId){
        if (isRootOrCompany(domainId)){
            return domainId;
        }else {
            //查其父节点
            return this.getChildOfRoot(organizationMapper.selectOrgParentIdByOrgChildId(domainId));
        }
    }

    /**
     * 删除组织节点及其子节点
     *
     * @param orgId
     * @return
     */
    @Override
    public Integer dropOrgWithChild(Integer orgId) throws NullEntityInDatabaseException {
        //判定节点是否是公司节点或者根节点
        if(this.isRootOrCompany(orgId)){
            return -2;
        }
        //找父节点参数
        Integer parentId= organizationMapper.selectOrgParentIdByOrgChildId(orgId);
        int rescursionResultNum = 0;
        //递归处理节点关系
        return this.recursionDealWithTheDrop(rescursionResultNum,parentId,orgId);
    }


    /**
     * 递归进行节点删除操作,组织机构关联用户去了公司节点,组织机构默认呢角色组去了上级
     *
     * @param domainOrgId
     * @return 1:操作成功;其他:操作失败
     */
    @Override
    public Integer recursionDealWithTheDrop(int resultNum,Integer domainParentId,Integer domainOrgId)throws NullEntityInDatabaseException {
        //判断是否是本节点,弹出递归
        if(domainParentId == domainOrgId){
            return resultNum;
        }else{
            //下属节点
            List<Organization> organizationChildren = organizationMapper.selectChildsByOrgId(domainOrgId);
            //logger.info(organizationChildren.toString());
            //下属用户
            List<User> users = userMapper.selectByOrg(domainOrgId);
            //是否有下属节点
            boolean hasOrgChild = (0 != organizationChildren.size() && null != organizationChildren);
            //是否有下属用户
            boolean hasUsers = (0 != users.size() && null != users);

            if (!hasOrgChild){

                //这时弹出的一种可能,无下属节点,处理一下下属用户,向上跳一层
                //处理用户
                if (hasUsers){
                    resultNum = resultNum + this.leaveOrg(users);
                }
                //在执行删除节点操作前,首先执行一下参数向上变换的过程
                Integer domainOrgIdToUp = this.organizationMapper.selectOrgParentIdByOrgChildId(domainOrgId);
                /*if (null == domainOrgId){
                    return this.recursionDealWithTheDrop(resultNum,domainParentId,domainParentId);
                }*/
                //没有节点,执行删除节点操作,此种条件下递归发生参数变化
                //同时执行一下角色组的操作
                resultNum += operationRoleGroup(domainOrgId,domainOrgIdToUp);
                resultNum = resultNum + organizationMapper.deleteByPrimaryKey(domainOrgId);
                //入参发生变化,这时跳出递归的关键,第二个入参为父节点ID,注意!!!此处用domaimOrgId算得,dominaOrgId为递归中的变量
                return this.recursionDealWithTheDrop(resultNum,domainParentId,domainOrgIdToUp);
            }else {
                //拥有下属节点,处理本节点用户,节点再向下跳一级
                Organization firstChild = organizationChildren.get(0);
                //处理用户
                if (hasUsers){
                    resultNum = resultNum + this.leaveOrg(users);
                }
                //向下跳一级,入参发生变化
                return this.recursionDealWithTheDrop(resultNum,domainParentId,firstChild.getOrgId());
            }
        }
    }


    /**
     * 处理组织机构的节点角色组
     *
     * @param domainOrgId 处理的主节点,默认角色组删除,默认角色编入上级默认角色组,其他角色组编入上级组织机构节点中
     * @param domainOrgIdToUp 输入上级角色组,这是最终角色
     * @return
     */
    public Integer operationRoleGroup(Integer domainOrgId,Integer domainOrgIdToUp) throws NullEntityInDatabaseException {
        Integer resultNum = 0;
        //首先这两个组织机构都是必须存在的
        //如果组织机构不存在,抛错
        Organization domainOrgToUp = organizationMapper.selectByPrimaryKey(domainOrgIdToUp);
        if (null == domainOrgToUp){
            throw new NullEntityInDatabaseException();
        }
        Organization domainOrg = organizationMapper.selectByPrimaryKey(domainOrgId);
        if (null == domainOrgToUp){
            throw new NullEntityInDatabaseException();
        }
        //将角色组所属角色的角色组关系换到上级默认角色组
        RoleGroupExample roleGroupExample = new RoleGroupExample();
        roleGroupExample.clear();
        roleGroupExample.createCriteria()
                .andOrgIdEqualTo(domainOrgIdToUp)
                .andGroupDefStateEqualTo(1);
        List<RoleGroup> roleGroups = roleGroupMapper.selectByExample(roleGroupExample);
        RoleGroup roleGroup = new RoleGroup();
        if (0 == roleGroups.size()){
            //上级默认角色组不存在时,要将帮助创建上级默认角色组
            roleGroup.setGroupDefState(1);
            //获取组织机构名称
            Organization organization = organizationMapper.selectByPrimaryKey(domainOrgIdToUp);
            roleGroup.setGroupName(organization.getOrgName()+"的默认角色组");
            roleGroup.setOrgId(domainOrgIdToUp);
            //执行插入动作
            resultNum += roleGroupMapper.insertSelective(roleGroup);
            //插入结果反馈
            roleGroupExample.clear();
            roleGroupExample.createCriteria()
                    .andGroupDefStateEqualTo(1)
                    .andOrgIdEqualTo(domainOrgIdToUp);
            roleGroup = roleGroupMapper.selectByExample(roleGroupExample).get(0);
        }
        if (1 == roleGroups.size()){
            //正常情况
            roleGroup =  roleGroups.get(0);
        }
        if (roleGroups.size()>1){
            //默认角色组过多时将角色组其他部分删除,将其中一个变为默认角色组
            roleGroup = roleGroups.get(0);
            roleGroup.setGroupDefState(1);
            //获取组织机构名称
            Organization organization = organizationMapper.selectByPrimaryKey(domainOrgIdToUp);
            roleGroup.setGroupName(organization.getOrgName()+"的默认角色组");
            //执行更行动作
            resultNum += roleGroupMapper.updateByPrimaryKeySelective(roleGroup);
            //其余奇怪的默认分组部分删除
            for (RoleGroup group :roleGroups ) {
                resultNum += roleGroupMapper.deleteByPrimaryKey(group.getGroupId());
            }
        }

        //角色关系转移
        //根据组织机构获取所有默认角色组
        roleGroupExample.clear();
        roleGroupExample.createCriteria()
                .andOrgIdEqualTo(domainOrgId)
                .andGroupDefStateEqualTo(1);
        List<RoleGroup> allDefRoleGroupsAtDomain = roleGroupMapper.selectByExample(roleGroupExample);

        //获取默认角色组所有角色
        List<Role> allDefRolesAtDomain =  new ArrayList<>();
        RoleExample roleExample = new RoleExample();
        for (RoleGroup roleGroupAtDomain :allDefRoleGroupsAtDomain) {
            roleExample.clear();
            roleExample.createCriteria()
                    .andRoleGroupIdEqualTo(roleGroupAtDomain.getGroupId());
            List<Role> roles = roleMapper.selectByExample(roleExample);
            allDefRolesAtDomain.addAll(roles);
        }
        //把这些角色的角色组所属修改为上级默认角色组
        for (Role role : allDefRolesAtDomain) {
            role.setRoleGroupId(roleGroup.getGroupId());
            resultNum += roleMapper.updateByPrimaryKey(role);
        }
        //查询其他角色组
        roleGroupExample.clear();
        roleGroupExample.createCriteria()
                .andGroupDefStateEqualTo(0)
                .andOrgIdEqualTo(domainOrgId);
        //将其他角色组放到上一层
        RoleGroup record = new RoleGroup();
        record.setOrgId(domainOrgIdToUp);
        resultNum += roleGroupMapper.updateByExampleSelective(record,roleGroupExample);
        //将默认角色组删除
        roleGroupExample.clear();
        roleGroupExample.createCriteria()
                .andOrgIdEqualTo(domainOrgId)
                .andGroupDefStateEqualTo(1);

        resultNum += roleGroupMapper.deleteByExample(roleGroupExample);
        return resultNum;
    }
    //组织被删除时,用户与组织的关系处理
    //添加用户组织到,最高级公司组织
    /**
     * 用户脱离组织,重归公司分配
     *
     * @param users 需要脱离组织的员工
     * @return 操作数据库成功的数据条数
     */
    public Integer leaveOrg(List<User> users){
        Integer resultNum = 0;
        if (0 == users.size()||null == users){
            return resultNum;
        }
        for (User user:users) {
            //通过用户找组织id
            UserOrganizationRelationExample userOrganizationRelationExample = new UserOrganizationRelationExample();
            userOrganizationRelationExample.createCriteria()
                    .andUserIdEqualTo(user.getUserId());
            List<UserOrganizationRelation> userOrganizationRelations = userOrganizationRelationMapper.selectByExample(userOrganizationRelationExample);
            for (UserOrganizationRelation userOrganizationRelation: userOrganizationRelations) {
                //查询该组织所属公司
                Integer companyOrgId = this.getChildOfRoot(userOrganizationRelation.getOrgId());
                //修改用户组织机构为用户所在的公司
                resultNum = resultNum + userMapper.leaveOrg(user.getUserId(),companyOrgId);

            }
        }
        return resultNum;
    }


    /**
     * 查询同一个机构下的用户名相同的org
     *
     * @param orgName
     * @param orgParentId
     * @return
     */
    public List<Organization> findOrgByNameAndOrgParentId(String orgName,Integer orgParentId){
        OrganizationExample organizationExample = new OrganizationExample();
        organizationExample.createCriteria().andOrgNameEqualTo(orgName).andOrgParentIdEqualTo(orgParentId);
        List<Organization> organizations = organizationMapper.selectByExample(organizationExample);
        return organizations;
    }

    /**
     * 通过父亲节点查询其子节点
     *
     * @param orgParentId
     * @return
     */
    @Override
    public List<Organization> findOrgByParent(Integer orgParentId) {
        if (null == orgParentId){
            return null;
        }
        OrganizationExample organizationExample = new OrganizationExample();
        organizationExample.createCriteria()
            .andOrgParentIdEqualTo(orgParentId);
        List<Organization> organizations = organizationMapper.selectByExample(organizationExample);
        
        if (0 < organizations.size()){
            for (Organization org : organizations) {
                List<Organization> orgChildren = organizationMapper.selectChildsByOrgId(org.getOrgId());
                if (0 < orgChildren.size()){
                    org.setHasChild(true);
                }
            }
            return organizations;
        }
        return null;
    }

    /**
     * 修改组织机构名称,修改父节点
     *
     * @param organization 组织机构数据
     * @return
     */
    @Override
    public Integer updateNameOfOrganization(Organization organization) {
        //查一下数据库,去重
        //添加对null的判断
        if (null == organization){
            return 0;
        }
        //父组织校验
        Organization organizationParent = organizationMapper.selectByPrimaryKey(organization.getOrgParentId());
        if (null == organizationParent){
            //存入默认父ID
            organization.setOrgParentId(0);
        }
        //名称重复校验
        List<Organization> orgsByName
                = this.findOrgByNameAndOrgParentId(organization.getOrgName(),organization.getOrgParentId());
        if (orgsByName.size() > 0){
            //有重复
            return -2;
        }
        //执行修改sql
        int resultNum = organizationMapper.updateByPrimaryKeySelective(organization);
        return resultNum;
    }

    /**
     * 录入数据,父ID判定在先,名称重复判定在后
     *
     * @param organization
     * @return
     */
    @Override
    public Integer createOrganization(Organization organization) {
        //判定空
        if (null == organization){
            return 0;
        }
        //条件查询该父Id是否存在
        Organization organizationParent = organizationMapper.selectByPrimaryKey(organization.getOrgParentId());
        if (null == organizationParent){
            //存入默认父ID
            organization.setOrgParentId(0);
        }
        //条件判定名称是否重复,同一组织机构下不能同名
        List<Organization> orgsByName
                = this.findOrgByNameAndOrgParentId(organization.getOrgName(),organization.getOrgParentId());
        if (orgsByName.size() > 0){
            //有重复
            return -2;
        }

        //存入当前时间
        organization.setOrgCreatedtime(new Date());

        //执行创建动作
        int resultNum = organizationMapper.insert(organization);//这里不插入空,哪怕报错
        //插入组织机构默认的角色组
        OrganizationExample organizationExample = new OrganizationExample();
        organizationExample.clear();
        organizationExample.createCriteria()
                .andOrgNameEqualTo(organization.getOrgName())
                .andOrgParentIdEqualTo(organization.getOrgParentId());
        List<Organization> organizations = organizationMapper.selectByExample(organizationExample);
        if (organizations.size()>2){
            return -2;
        }else if (organizations.size() == 1){
            //组织机构创建成功,插入默认角色组
            RoleGroup roleGroup = new RoleGroup();
            roleGroup.setOrgId(organizations.get(0).getOrgId());
            roleGroup.setGroupName(organization.getOrgName()+"的默认分组");
            roleGroup.setGroupDefState(1);
            resultNum += roleGroupMapper.insert(roleGroup);
        }
        return resultNum;
    }

    /**
     * 查询组织机构节点的所有徒子徒孙
     *
     * @param orgId
     * @return
     */
    @Override
    public List<Integer> queryAllChildrenOrgId(Integer orgId){
        List<Integer> allChildrenOrgIds = new ArrayList<>();
        if (null == orgId){
            return null;
        }
        //查一下父亲节点
        Integer parentId = organizationMapper.selectOrgParentIdByOrgChildId(orgId);
        //递归查一下所有子节点
        allChildrenOrgIds = recursionGetAllChildrenOrgId(parentId,orgId,allChildrenOrgIds);
        return allChildrenOrgIds;
    }

    /**
     * 根据orgId查找其父节点,爷爷节点,曾祖父节点....
     *
     * @param orgId
     * @return
     */
    @Override
    public List<Organization> queryParentOrgByOrgId(Integer orgId) throws NullEntityInDatabaseException {
        List<Organization> parentsAndOrg = new ArrayList<>();
        Organization organization = organizationMapper.selectByPrimaryKey(orgId);
        if (null == organization){
            throw new NullEntityInDatabaseException();
        }
        parentsAndOrg.add(organization);
        while (0==0){
            if (0 == organization.getOrgParentId()||0 == organization.getOrgId()){
                break;
            }
            organization = organizationMapper.selectByPrimaryKey(organization.getOrgParentId());
            parentsAndOrg.add(organization);
        }
        //倒叙排列
        Collections.reverse(parentsAndOrg);
        return parentsAndOrg;
    }

    /**
     * 根据orgId查找orgInfo
     *
     * @param orgId
     * @return
     */
    @Override
    public Organization queryBaseOrgInfo(Integer orgId) {
        if (null == orgId){
            return null;
        }
        Organization organization = organizationMapper.selectByPrimaryKey(orgId);
        return organization;
    }

    /**
     * 根据公司名称,分页查询根组织机构下的公司,这些组织机构就是公司的概念,分页暂缓使用
     *
     * @param offSet 第几条开始
     * @param limit 显示几条
     * @return
     */
    @Override
    public List<Organization> queryCompanyOrgsWithPageInfo(String orgNameAsCom, Integer offSet, Integer limit) {
        OrganizationExample organizationExample = new OrganizationExample();
        organizationExample.clear();
        organizationExample.createCriteria()
                .andOrgNameLike("%"+orgNameAsCom+"%")
                .andOrgParentIdEqualTo(0)
                ;
        //organizationExample.setOffset(offSet);
        //organizationExample.setLimit(limit);
        List<Organization> organizations = organizationMapper.selectByExample(organizationExample);
        return organizations;
    }

    /**
     * 判断是否是公司
     *
     * @param orgId
     * @return
     */
    @Override
    public boolean isCompanyOrg(Integer orgId) {
        //非空
        if (null == orgId){
            return false;
        }
        Organization organization = organizationMapper.selectByPrimaryKey(orgId);
        if (null == organization){
            return false;
        }
        if (null != organization.getOrgParentId()&&0 == organization.getOrgParentId()){
            return true;
        }
        return false;
    }

    /**
     * 根据节点递归查询树形组织机构
     *
     * @param orgId
     * @return
     */
    @Override
    public Organization queryTheChildrenTreeOfTheOrg(Integer orgId) {

        if (null == orgId){
            return null;
        }
        Organization organization = organizationMapper.selectByPrimaryKey(orgId);
        if (null == organization){
            return null;
        }
        List<Organization> organizations;
        if (0 == orgId){
            OrganizationExample organizationExample = new OrganizationExample();
            //处理总根节点
            organizationExample.clear();
            organizations = organizationMapper.selectByExample(organizationExample);
        }else{
            //首先找到所有的子节点集合
            organizations = this.recursionGetAllChildrenOrg(organization.getOrgParentId(), organization, new ArrayList<Organization>());
        }
        //将集合转换成tree
        Organization resultOrg = this.changeListToTree(organization.getOrgId(),organizations);
        if (null == resultOrg.getChildren()||0 == resultOrg.getChildren().size()){
            return organization;
        }
        return resultOrg;
    }

    @Override
    public List<Organization> queryTheChildrenOfTheOrg(Integer orgId) {
        if (null == orgId){
            return null;
        }
        Organization organization = organizationMapper.selectByPrimaryKey(orgId);
        if (null == organization){
            return null;
        }
        List<Organization> organizations;
        if (0 == orgId){
            OrganizationExample organizationExample = new OrganizationExample();
            //处理总根节点
            organizationExample.clear();
            organizations = organizationMapper.selectByExample(organizationExample);
        }else{
            //首先找到所有的子节点集合
            organizations = this.recursionGetAllChildrenOrg(organization.getOrgParentId(), organization, new ArrayList<Organization>());
        }
        return organizations;
    }

    /**
     * 集合状的tree变成树状的
     * @param theRootNode
     * @return
     */
    private Organization changeListToTree(Integer theRootNode,List<Organization> orgList){
        Organization resultOrg = new Organization();
        for (Organization org : orgList) {
            if (theRootNode == org.getOrgId()){
                resultOrg = org;
            }
            for (Organization organization : orgList) {
                if (organization.getOrgParentId() == org.getOrgId()){
                    if (null == org.getChildren()){
                        org.setChildren(new ArrayList<Organization>());
                    }
                    org.getChildren().add(organization);
                }
            }
        }
        return resultOrg;
    }



    /**
     * 查询一个集合的所有子节点
     * @param pid
     * @param domainOrg
     * @param allChildrenOrg
     * @return
     */
    private List<Organization> recursionGetAllChildrenOrg(Integer pid,Organization domainOrg,List<Organization> allChildrenOrg){
        if (pid == domainOrg.getOrgId()){
            return allChildrenOrg;
        }else {
            OrganizationExample organizationExample = new OrganizationExample();
            organizationExample.clear();
            organizationExample.createCriteria()
                    .andOrgParentIdEqualTo(domainOrg.getOrgId());
            //所有子节点
            List<Organization> organizations = organizationMapper.selectByExample(organizationExample);
            boolean resultsHasAllChild = allChildrenOrg.containsAll(organizations);
            boolean hasChildOrg = organizations.size()>0;

            if ((!resultsHasAllChild) && hasChildOrg) {
                //集合中还有没遍历完的,而且当前节点还是有子集的
                //获取还没有被遍历的下级节点集合
                List<Organization> forSearchChildren = new ArrayList<>();
                forSearchChildren.addAll(organizations);
                //取交集
                organizations.retainAll(allChildrenOrg);
                //移除交集部分,得到还没遍历完的集合
                forSearchChildren.removeAll(organizations);
                for (Organization child : forSearchChildren) {
                    allChildrenOrg = this.recursionGetAllChildrenOrg(pid, child, allChildrenOrg);
                }
            } else {
                //没有子节点或者子节点都在集合里的就向上跳一层
                if (!allChildrenOrg.contains(domainOrg)) {
                    //没有子节点当然要把自己添加进去
                    allChildrenOrg.add(domainOrg);
                }
                Organization orgToUp = organizationMapper.selectByPrimaryKey(domainOrg.getOrgParentId());
                allChildrenOrg = this.recursionGetAllChildrenOrg(pid, orgToUp, allChildrenOrg);
            }
            return allChildrenOrg;
        }
    }


    /**
     * 递归查询orgId下的所有子节点Id
     *
     * @param orgId
     * @param domainOrgId
     * @param allChildrenOrgIds
     * @return
     */
    private List<Integer> recursionGetAllChildrenOrgId(Integer orgId, Integer domainOrgId, List<Integer> allChildrenOrgIds) {
        //什么时候跳出,orgIdToUp == orgId的时候跳出
        if (orgId == domainOrgId) {
            //ArrayList<Integer> allChildrenOrgIdsWithOutTheSame = new ArrayList<>(new HashSet<>(allChildrenOrgIds));
            //return allChildrenOrgIdsWithOutTheSame;
            return allChildrenOrgIds;
        } else {
            //查询orgId的子节点,放入结果list中
            //List<Organization> organizations = organizationMapper.selectChildsByOrgId(orgIdToUp);
            List<Integer> idChildren = organizationMapper.selectChildIdsByOrgId(domainOrgId);
            boolean resultHasAllChild = allChildrenOrgIds.containsAll(idChildren);
            boolean hasOrgChild = 0 != idChildren.size();


            if ((!resultHasAllChild) && hasOrgChild) {
                //获取还没有被遍历的下级节点集合
                List<Integer> forSearchChildren = new ArrayList<>();
                forSearchChildren.addAll(idChildren);
                //System.out.println(forSearchChildren);
                //取交集
                idChildren.retainAll(allChildrenOrgIds);
                //System.out.println(idChildren);
                //移除交集部分
                //System.out.println(forSearchChildren);
                forSearchChildren.removeAll(idChildren);
                //System.out.println(forSearchChildren);
                for (Integer child : forSearchChildren) {
                    allChildrenOrgIds = this.recursionGetAllChildrenOrgId(orgId, child, allChildrenOrgIds);
                }
            } else {
                if (!allChildrenOrgIds.contains(domainOrgId)) {
                    allChildrenOrgIds.add(domainOrgId);
                }
                Integer orgIdToUp = organizationMapper.selectOrgParentIdByOrgChildId(domainOrgId);
                allChildrenOrgIds = this.recursionGetAllChildrenOrgId(orgId, orgIdToUp, allChildrenOrgIds);
            }
            return allChildrenOrgIds;

        }
    }
}
