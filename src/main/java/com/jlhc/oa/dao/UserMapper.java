package com.jlhc.oa.dao;

import com.jlhc.oa.dto.user.User;
import com.jlhc.oa.dto.user.example.UserExample;
import com.jlhc.sell.dto.Task;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserMapper {

    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User findByUserName(String userName);

    /**
     * 专门用于超级系统管理员
     *
     * @return
     */
    User getUserWithAllRolesAndRights();


    List<User> selectByOrg(Integer orgId);

    /**
     * 修改单一用户的组织关系
     *
     * @return 修改成功的条数
     */
    Integer leaveOrg(@Param("userId") Integer userId,@Param("orgId") Integer orgId);

    /**
     * 获取插入数据的ID
     *
     * @return 上次插入数据的id
     */
    Integer selectLastInsertId();

    /**
     * 查询某一组织机构下拥有某个角色的用户
     *
     * @param companyOrgId
     * @param roleId
     * @return
     */
    List<User> selectByComOrgAndRole(@Param("companyOrgId") Integer companyOrgId,@Param("roleId") Integer roleId);

    /**
     * 根据任务Id查询该任务的共享人
     *
     * @param taskId
     * @return
     */
    List<User> selectShareUsersByTaskId(@Param("taskId") String taskId);

    List<User> selectUsersInOrgs(/*@Param("orgIds") */List<Integer> orgIds);
}