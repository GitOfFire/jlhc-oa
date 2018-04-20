package com.jlhc.oa.service.impl;

import com.jlhc.oa.dao.OrganizationMapper;
import com.jlhc.oa.dao.RoleGroupMapper;
import com.jlhc.oa.dao.RoleMapper;
import com.jlhc.oa.dto.role.Role;
import com.jlhc.oa.dto.role.RoleGroupWithoutState;
import com.jlhc.oa.dto.role.example.RoleExample;
import com.jlhc.oa.dto.role.RoleGroup;
import com.jlhc.oa.dto.role.example.RoleGroupExample;
import com.jlhc.oa.dto.user.Organization;
import com.jlhc.oa.dto.user.User;
import com.jlhc.oa.service.RoleGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author renzhong
 * @version 1.0
 * @Description
 * @Date: Created in 10:43 2018/1/5 0005
 */
@Service
@Transactional
public class RoleGroupServiceImpl extends BaseServiceImpl implements RoleGroupService {

    /**RoleGroupDao*/
    @Autowired
    RoleGroupMapper roleGroupMapper;
    /**OrganizationMapper*/
    @Autowired
    OrganizationMapper organizationMapper;
    /**RoleMapper*/
    @Autowired
    RoleMapper roleMapper;

    public static RoleGroupExample roleGroupExample = new RoleGroupExample();



    /**
     * 查询角色据根据组名称
     *
     * @param roleGroupName 角色组名称
     * @return
     */
    public List<RoleGroup> findRoleGroupsByRoleGroupNameAndOrgId(Integer groupId,String roleGroupName,Integer orgId){
        if (null == roleGroupName|| "".equals(roleGroupName)){
            return null;
        }

        //根据roleGroup建立example

        RoleGroupExample roleGroupExampleWithRole = new RoleGroupExample();

        //一个参数,名称
        roleGroupExampleWithRole.createCriteria()
            .andGroupNameEqualTo(roleGroupName)
            .andOrgIdEqualTo(orgId)
            .andGroupIdNotEqualTo(groupId);
        List<RoleGroup> roleGroupsByRoleGroupNameAndOrgId = roleGroupMapper.selectByExample(roleGroupExampleWithRole);
        return roleGroupsByRoleGroupNameAndOrgId;
    }

    /**
     * 查询角色据根据组名称
     *
     * @param roleGroupName 角色组名称
     * @return
     */
    public List<RoleGroup> findRoleGroupsByRoleGroupNameAndOrgId(String roleGroupName,Integer orgId){
        if (null == roleGroupName|| "".equals(roleGroupName)){
            return null;
        }

        //根据roleGroup建立example

        RoleGroupExample roleGroupExampleWithRole = new RoleGroupExample();

        //一个参数,名称
        roleGroupExampleWithRole.createCriteria()
                .andGroupNameEqualTo(roleGroupName)
                .andOrgIdEqualTo(orgId);
        List<RoleGroup> roleGroupsByRoleGroupNameAndOrgId = roleGroupMapper.selectByExample(roleGroupExampleWithRole);
        return roleGroupsByRoleGroupNameAndOrgId;
    }

    /**
     * 创建角色组,不允许同名,在插入数据为orgId对应数据不存在的时候,直接返回创建失败
     *
     * @param roleGroupWithoutState
     * @return
     */
    public Integer createRoleGroupWithoutTheSameName(RoleGroupWithoutState roleGroupWithoutState){
        //添加对null的判断
        if (null == roleGroupWithoutState){
            return 0;
        }
        //多写一个根据用户名查询组对象的接口
        List<RoleGroup> roleGroups = this.findRoleGroupsByRoleGroupNameAndOrgId(roleGroupWithoutState.getGroupName(),roleGroupWithoutState.getOrgId());
        //RoleGroupExample roleGroupExampleWithOrgId = new RoleGroupExample();
        Organization organization = organizationMapper.selectByPrimaryKey(roleGroupWithoutState.getOrgId());
        //System.out.println(organization);
        //这里不能用null判断.只能用size来判断
        if (roleGroups.size() > 0){
            //有结果,说明有重名,这里返回-2,说明有重名
            return -2;
        }else if ( null == organization){
            //无该组织机构,设置组织机构为0
            //roleGroup.setOrgId(0);
            return -3;
        }
        RoleGroup roleGroup = new RoleGroup();
        roleGroup.setGroupName(roleGroupWithoutState.getGroupName());
        roleGroup.setGroupDefState(0);
        roleGroup.setOrgId(roleGroupWithoutState.getOrgId());
        int resultNum = roleGroupMapper.insertSelective(roleGroup);
        return resultNum;
    }

    /**
     *  查询RoleGroup对象的集合,根据用户的组织机构查询
     *
     * @return
     */
    @Override
    public List<RoleGroup> findRoleGroups(HttpSession httpSession){//这里用了笨的获取session的方法
        //获取当前用户的组织id
        User loginUser = (User)httpSession.getAttribute("user");
        //根据用户id查询用户所属的组织,是一个集合,已经废弃,用户与组织机构是多对一的关系
        //List<Organization> organizations = organizationMapper.selectOrganizationByUserId(loginUser.getUserId());
        //角色组集合
        //Set<RoleGroup> roleGroups = new HashSet<>();
//        List<RoleGroup> roleGroups = new ArrayList<>();
//        for (Organization organization :organizations) {
//            //当前组织所有的角色组
//            RoleGroupExample roleGroupExample = new RoleGroupExample();
//            roleGroupExample.createCriteria().andOrgIdEqualTo(organization.getOrgId());
//            List<RoleGroup> roleGroupsAtOnlyOneOrg = roleGroupMapper.selectByExample(roleGroupExample);
//            roleGroups.addAll(roleGroupsAtOnlyOneOrg);
//        }
        roleGroupExample.clear();
        roleGroupExample.createCriteria().andOrgIdEqualTo(loginUser.getOrgId());
        List<RoleGroup> roleGroups = roleGroupMapper.selectByExample(roleGroupExample);
        //类型转换
        //List<RoleGroup> resultRoleGroups = new ArrayList<>(roleGroups);
        return roleGroups;
    }

    /**
     * 修改角色组对象,不同的名称,而且存储的组织机构存在
     *
     * @param roleGroup,其中只有groupName,roleGroupId
     * @return 修改结果
     */
    @Override
    public Integer updateRoleGroupWithoutTheSameName(RoleGroup roleGroup) {
        Integer resultNum = 0;
        //查一下数据库,去重
        //添加对null的判断
        if (null == roleGroup){
            return 0;
        }
        //判断是不是默认组,如果是默认组不允许修改
        RoleGroup roleGroupByGroupId = roleGroupMapper.selectByPrimaryKey(roleGroup.getGroupId());
        if (roleGroupByGroupId == null){
            //这个组不存在,不能修改
            return -3;
        }
        if (1 == roleGroupByGroupId.getGroupDefState()){
            //这个组是默认组,不能修改
            return -4;
        }

        //多写一个根据用户名查询组对象的接口,判断是否有重名
        List<RoleGroup> roleGroups = this.findRoleGroupsByRoleGroupNameAndOrgId(roleGroup.getGroupId(),roleGroup.getGroupName(),roleGroup.getOrgId());
        //RoleGroupExample roleGroupExampleWithOrgId = new RoleGroupExample();
        Organization organization = organizationMapper.selectByPrimaryKey(roleGroup.getOrgId());
        //这里不能用null判断.只能用size来判断
        if (null == organization){
            //说明无OrgId的组织存在
            return -3;
        }
        if (roleGroups.size()>0){
            //说明有属于同一个组织机构的统一组名的roleGroup,这里返回-2,说明同一组织机构下出现除了本角色组的重名角色组
            return -2;
        }
        resultNum += roleGroupMapper.updateByPrimaryKeySelective(roleGroup);
        //logger.info(resultNum.toString());
        return resultNum;
    }

    /**
     * 删除角色组
     *
     * @param roleGroupId
     */
    @Override
    public Integer dropRoleGroupById(Integer roleGroupId) {
        Integer resultNum = 0;
        //根据ID获取角色组
        RoleGroup roleGroup = roleGroupMapper.selectByPrimaryKey(roleGroupId);
        //判定是不是默认组,roleGroupDefState:1,默认组,roleGroupDefState:0,不是默认组
        if (1 == roleGroup.getGroupDefState()){
            //默认组不允许删除
            return -4;
        }
        //不是默认组,判定是否有角色,用到角色查询,根据角色组ID查询角色
        List<Role> roles = roleMapper.selectByGroupId(roleGroupId);
        //没有角色,直接删除
        if (0 == roles.size()){
            //直接删除
            resultNum += roleGroupMapper.deleteByPrimaryKey(roleGroupId);
            return resultNum;
        } else{
            //有角色,将其放入默认组中
            //一个组织只有一个默认组
            //查询默认组
            RoleGroupExample roleGroupExample = new RoleGroupExample();
            roleGroupExample.createCriteria().andGroupDefStateEqualTo(1).andOrgIdEqualTo(roleGroup.getOrgId());//这里得到的默认组是一个
            List<RoleGroup> defRoleGroups = roleGroupMapper.selectByExample(roleGroupExample);
            RoleGroup defRoleGroup;
            if (defRoleGroups.size() > 0){
                //查询到的默认组
                defRoleGroup = defRoleGroups.get(0);
                //修改角色的角色组id
                for (Role role : roles) {
                    role.setRoleGroupId(defRoleGroup.getGroupId());
                    RoleExample roleExample = new RoleExample();
                    roleExample.createCriteria().andRoleGroupIdEqualTo(defRoleGroup.getOrgId());
                    resultNum += roleMapper.updateByExampleSelective(role,roleExample);
                }
            }
            //全部放入后,删除角色组
            resultNum += roleGroupMapper.deleteByPrimaryKey(roleGroupId);
            return resultNum;
        }
    }
}
