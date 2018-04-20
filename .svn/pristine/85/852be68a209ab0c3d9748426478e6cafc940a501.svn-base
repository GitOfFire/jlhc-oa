package com.jlhc.sell.service.impl;

import com.jlhc.base_com.dao.CompanyMapper;
import com.jlhc.base_com.dto.Company;
import com.jlhc.common.exception.NotVertifyException;
import com.jlhc.common.exception.NullDataForInsertException;
import com.jlhc.common.exception.NullEntityInDatabaseException;
import com.jlhc.common.utils.RandomStringUtil;
import com.jlhc.oa.dao.UserMapper;
import com.jlhc.oa.dto.user.User;
import com.jlhc.oa.service.UserService;
import com.jlhc.sell.dao.FlowMapper;
import com.jlhc.sell.dao.TaskMapper;
import com.jlhc.sell.dao.TaskUserRelationMapper;
import com.jlhc.sell.dto.*;
import com.jlhc.sell.dto.example.FlowExample;
import com.jlhc.sell.dto.example.TaskExample;
import com.jlhc.sell.dto.example.TaskUserRelationExample;
import com.jlhc.sell.service.TaskService;
import com.jlhc.solr.dto.CompanySolr;
import com.jlhc.solr.service.CompanySolrService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskMapper taskMapper;
    @Autowired
    CompanyMapper companyMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    CompanySolrService companySolrService;
    @Autowired
    FlowMapper flowMapper;
    @Autowired
    TaskUserRelationMapper taskUserRelationMapper;
    @Autowired
    UserService userService;
    public static TaskUserRelationExample taskUserRelationExample = new TaskUserRelationExample();

    public static FlowExample flowExample = new FlowExample();

    public static TaskExample taskExample = new TaskExample();


    /**
     * 展示任务详情
     *
     * @param taskId
     * @return
     */
    @Override
    public TaskFullInfo queryTaskById(String taskId) {
        if (null == taskId){
            return null;
        }
        return taskMapper.selectTaskInfoAboutComCusNoteByPrimaryKey(taskId);
    }

    /**
     * 创建单一任务
     *
     * @param task
     * @return
     */
    @Override
    public Integer createTask(Task task) throws NullEntityInDatabaseException {
        //非空校验
        if (null ==  task){
            return 0;
        }
        //如果有输入的相关客户,校验客户是否存在在数据库
        String comId = task.getComId();
        if (null != comId){
            Company company = companyMapper.selectByPrimaryKey(comId);
            if (null == company){
                throw new NullEntityInDatabaseException();
            }
        }
        //校验相关用户是否存在
        Integer userId = task.getTaskCreatedUser();//创建用户
        Integer holdUserId = task.getHoldUserId();//负责人用户
        if (null != userId){
            User user = userMapper.selectByPrimaryKey(userId);
            if (null == user){
                throw new NullEntityInDatabaseException();
            }

        }
        if (null != holdUserId){
            User holdUser = userMapper.selectByPrimaryKey(holdUserId);
            if (null == holdUser){
                throw new NullEntityInDatabaseException();
            }
        }
        String taskId = RandomStringUtil.getRandomCode(32,7);
        task.setTaskId(taskId);
        return taskMapper.insertSelective(task);
    }

    /**
     * 任务修改
     *
     * @param task
     * @return
     */
    @Override
    public Integer reworkTask(TaskForPut task, HttpSession httpSession) throws NullEntityInDatabaseException {
        Integer resultNum = 0;
        //非空校验
        if (null == task||null == task.getTaskId()){
            return 0;
        }
        //数据存在性校验
        Task taskInDataBase = taskMapper.selectByPrimaryKey(task.getTaskId());
        if (null == taskInDataBase){
            throw new NullEntityInDatabaseException();
        }
        //校验公司(客户)是否存在
        String comId = task.getComId();
        if (null != comId){
            Company company = companyMapper.selectByPrimaryKey(comId);
            if (null == company){
                return -31;
            }
        }
        //校验客户经理是否存在
        Integer holdUserId = task.getHoldUserId();
        if (null != holdUserId){
            User user = userMapper.selectByPrimaryKey(holdUserId);
            if (null == user){
                return -32;
            }
        }
        Task taskForInsert = new Task();
        taskForInsert.setHoldUserId(task.getHoldUserId());
        taskForInsert.setComId(task.getComId());
        User user =(User) httpSession.getAttribute("user");
        taskForInsert.setTaskCreatedUser(user.getUserId());
        taskForInsert.setTaskCreatedTime(new Date());
        taskForInsert.setTaskContent(task.getTaskContent());
        taskForInsert.setTaskId(task.getTaskId());
        taskForInsert.setTaskState(task.getTaskState());
        //修改
        resultNum += taskMapper.updateByPrimaryKeySelective(taskForInsert);
        return resultNum;
    }


    /**
     * 将一个任务置于垃圾桶
     *
     * @param taskId
     * @return
     */
    @Override
    public Integer dropTask(String taskId,User user) {
        Task task = new Task();
        task.setTaskId(taskId);
        task.setTaskState(2);//2已放弃
        int resultNum = taskMapper.updateByPrimaryKeySelective(task);
        //日志
        Flow flow = new Flow();
        flow.setTaskId(task.getTaskId());
        flow.setFlowUserId(user.getUserId());
        flow.setFlowTime(new Date());
        flow.setFlowId(RandomStringUtil.getRandomCode(32,7));
        flow.setFlowType(2);
        flow.setFlowDescription(user.getUserTruename()+"将任务<"+task.getTaskContent()+">删除了");
        resultNum += flowMapper.insertSelective(flow);
        return resultNum;
    }

    /**
     * 根据联系人的姓名或电话号码查询任务
     *
     * @param nameOrMobile
     * @return
     */
    @Override
    public List<Task> queryTasksByNameOrMobile(String nameOrMobile) throws SolrServerException {
        if (null == nameOrMobile){
            return null;
        }
        //调用solr查comId
        List<CompanySolr> companySolrs = companySolrService.querySolrCompanyByNameOrMobile(nameOrMobile);
        if (null == companySolrs || 0 >= companySolrs.size()){
            return null;
        }
        //获取其中的comIds
        List<Task> tasks = new ArrayList<>();
        for (CompanySolr companySolr :companySolrs ) {
            taskExample.clear();
            taskExample.createCriteria()
                    .andComIdEqualTo(companySolr.getComId());
            tasks.addAll(taskMapper.selectByExample(taskExample));
            //根据comId查task
        }
        return tasks;
    }

    /**
     * 根据公司名称查询任务
     *
     * @param comName
     * @return
     */
    @Override
    public List<Task> queryTasksByComName(String comName) throws SolrServerException {
        if (null == comName){
            return null;
        }
        //调用solr查寻comId
        List<CompanySolr> companySolrs = companySolrService.querySolrCompanyByName(comName);
        //获取其中的comIds
        List<Task> tasks = new ArrayList<>();
        //根据comId查task
        for (CompanySolr companySolr :companySolrs ) {
            taskExample.clear();
            taskExample.createCriteria()
                    .andComIdEqualTo(companySolr.getComId());
            tasks.addAll(taskMapper.selectByExample(taskExample));

        }
        return tasks;
    }

    /**
     * 根据创建人查询任务
     *
     * @param createUserId
     * @return
     */
    @Override
    public List<Task> queryTasksByCreateUserId(Integer createUserId) {
        if (null == createUserId){
            return null;
        }
        taskExample.clear();
        taskExample.createCriteria()
                .andTaskCreatedUserEqualTo(createUserId);
        List<Task> tasks = taskMapper.selectByExample(taskExample);
        return tasks;
    }

    /**
     * 根据销售经理查询任务
     *
     * @param holdUserId
     * @return
     */
    @Override
    public List<Task> queryTasksByHoldUserId(Integer holdUserId) {
        if(null == holdUserId){
            return null;
        }
        taskExample.clear();
        taskExample.createCriteria()
                .andHoldUserIdEqualTo(holdUserId);
        List<Task> tasks = taskMapper.selectByExample(taskExample);
        return tasks;
    }

    /**
     * 批量添加task信息
     *
     * @param tasks
     * @param flows
     * @return
     */
    @Override
    public Integer createTasks(List<Task> tasks, List<Flow> flows) throws NullDataForInsertException, NullEntityInDatabaseException {
        if (null == tasks || 0 == tasks.size()){
            throw new NullDataForInsertException();
        }
        for (Task task :tasks ) {
            //如果有输入的相关客户,校验客户是否存在在数据库
            String comId = task.getComId();
            if (null != comId){
                Company company = companyMapper.selectByPrimaryKey(comId);
                if (null == company){
                    throw new NullEntityInDatabaseException();
                }
            }
            //校验相关用户是否存在
            Integer userId = task.getTaskCreatedUser();
            if (null != userId){
                User user = userMapper.selectByPrimaryKey(userId);
                if (null == user){
                    throw new NullEntityInDatabaseException();
                }
            }
        }
        Integer resultNum = taskMapper.insertTasks(tasks);
        for (Flow f : flows) {
            //校验相关用户是否存在
            Integer flowUserId = f.getFlowUserId();
            if (null != flowUserId){
                User user = userMapper.selectByPrimaryKey(flowUserId);
                if (null == user){
                    throw new NullEntityInDatabaseException();
                }
            }
            //校验相关任务是否存在
            String taskId = f.getTaskId();
            if (null == taskId){
                throw new NullDataForInsertException();
            }else{
                Task task = taskMapper.selectByPrimaryKey(taskId);
                if (null == task){
                    throw new NullEntityInDatabaseException();
                }
            }
        }
        resultNum += flowMapper.insertFlows(flows);
        return resultNum;
    }

    /**
     * 查询用户操作过的任务(根据流程记录表来查)
     *
     * @param flowUserId
     * @return
     */
    @Override
    public List<Task> queryTasksByFlowUserId(Integer flowUserId) {
        flowExample.clear();
        flowExample.createCriteria()
                .andFlowUserIdEqualTo(flowUserId);
        List<Flow> flows = flowMapper.selectByExample(flowExample);
        List<String> taskIds = new ArrayList<>();
        for (Flow f : flows) {
            String taskId = f.getTaskId();
            taskIds.add(taskId);
        }
        //去重
        ArrayList<String> taskIdsWithOutTheSame = new ArrayList<>(new HashSet<String>(taskIds));
        List<Task> tasks = new ArrayList<>();
        for (String taskId:taskIdsWithOutTheSame ) {
            Task task = taskMapper.selectByPrimaryKey(taskId);
            if(null != task){
                tasks.add(task);
            }
        }
        return tasks;
    }

    /**
     * 批量修改任务的负责人
     *
     * @param tasks
     * @return
     */
    @Override
    public Integer reworkTaskToChangeHoldUserId(List<TaskForUpdateHoldUser> tasks, User user) throws NullEntityInDatabaseException {
        if (null == tasks||0 == tasks.size()){
            throw new NullPointerException();
        }
        //去重
        List<TaskForUpdateHoldUser> tasksWithoutTheSame = new ArrayList<TaskForUpdateHoldUser>(new HashSet<TaskForUpdateHoldUser>(tasks));
        Integer resultNum = 0;
        for (TaskForUpdateHoldUser taskForUpdateHoldUser: tasksWithoutTheSame) {
            Task taskInDataSource = taskMapper.selectByPrimaryKey(taskForUpdateHoldUser.getTaskId());
            if(null == taskInDataSource){
                throw new NullEntityInDatabaseException();
            }
            Task task = new Task();
            task.setTaskId(taskForUpdateHoldUser.getTaskId());
            User holdUser = userMapper.selectByPrimaryKey(taskForUpdateHoldUser.getHoldUserId());
            if (null == holdUser){
                throw new NullEntityInDatabaseException();
            }
            task.setHoldUserId(taskForUpdateHoldUser.getHoldUserId());
            resultNum += taskMapper.updateByPrimaryKeySelective(task);
            Flow flow = new Flow();
            flow.setTaskId(task.getTaskId());
            flow.setFlowUserId(user.getUserId());
            flow.setFlowTime(new Date());
            flow.setFlowId(RandomStringUtil.getRandomCode(32,7));
            flow.setFlowType(2);
            flow.setFlowDescription(user.getUserTruename()+"将任务给了"+holdUser.getUserTruename());
            resultNum += flowMapper.insertSelective(flow);
        }
        return resultNum;
    }

    /**
     * 给某个员工添加共享任务
     *
     *
     * @param userId
     * @param taskId
     * @return
     */
    @Override
    public Integer createRelationTaskToUser(Integer userId, String taskId,Integer operUserId) throws NullEntityInDatabaseException {
        //空校验抛出的错误和自动抛出的错误是一致的
        //校验两个实体是否存在
        User user = userMapper.selectByPrimaryKey(userId);
        if (null == user){
            throw new NullEntityInDatabaseException();
        }
        Task task = taskMapper.selectByPrimaryKey(taskId);
        if (null == task){
            throw new NullEntityInDatabaseException();
        }
        TaskUserRelation taskUserRelation = new TaskUserRelation();
        taskUserRelation.setTaskUserId(RandomStringUtil.getRandomCode(32,7));
        taskUserRelation.setUserId(userId);
        taskUserRelation.setTaskId(taskId);
        Integer resultNum = taskUserRelationMapper.insertSelective(taskUserRelation);
        //日志处理
        Flow flow = new Flow();
        flow.setFlowId(RandomStringUtil.getRandomCode(32,7));
        flow.setFlowDescription("任务<"+task.getTaskContent()+">被共享给了"+user.getUserTruename());
        flow.setFlowType(4);
        flow.setFlowTime(new Date());
        flow.setFlowUserId(operUserId);
        flow.setTaskId(taskId);

        resultNum += flowMapper.insertSelective(flow);
        return resultNum;
    }

    /**
     * 删除共享关系
     *
     * @param taskId
     * @param userId
     * @param operUserId
     * @return
     * @throws NullEntityInDatabaseException
     */
    @Override
    public Integer dropTaskUserRelation(String taskId,Integer userId,Integer operUserId) throws NullEntityInDatabaseException {
        taskUserRelationExample.clear();
        taskUserRelationExample.createCriteria()
                .andTaskIdEqualTo(taskId)
                .andUserIdEqualTo(userId);
        List<TaskUserRelation> taskUserRelations = taskUserRelationMapper.selectByExample(taskUserRelationExample);
        if (null == taskUserRelations||0 >= taskUserRelations.size()){
            throw new NullEntityInDatabaseException();
        }
        Task task = taskMapper.selectByPrimaryKey(taskId);
        if (null == task){
            throw new NullEntityInDatabaseException();
        }
        if (!task.getHoldUserId().equals(operUserId)){
            throw new NotVertifyException();//没有通行证明
        }
        User user = userMapper.selectByPrimaryKey(userId);
        if (null == user){
            throw new NullEntityInDatabaseException();
        }
        Integer resultNum = 0;
        for (TaskUserRelation relation : taskUserRelations) {
            resultNum = taskUserRelationMapper.deleteByPrimaryKey(relation.getTaskUserId());
        }
        //日志
        Flow flow = new Flow();
        flow.setFlowId(RandomStringUtil.getRandomCode(32,7));
        flow.setFlowDescription("任务<"+task.getTaskContent()+">的共享人"+user.getUserTruename()+"被移除");
        flow.setFlowUserId(operUserId);
        flow.setFlowTime(new Date());
        flow.setTaskId(taskId);
        flow.setFlowType(4);//共享人发生变化
        resultNum += flowMapper.insertSelective(flow);

        return resultNum;
    }

    /**
     * 查询共享给某个用户的所有任务
     *
     * @param userId
     * @return
     */
    @Override
    public List<Task> queryTasksByUserIdInTaskUserRelation(Integer userId) {
        List<Task> tasks = taskMapper.selectSharedTaskAtUserId(userId);
        return tasks;
    }

    /**
     * 查询任务公海的任务
     *
     * @return
     */
    @Override
    public List<Task> queryTasksInTaskSea() {
        taskExample.clear();
        taskExample.createCriteria()
                .andTaskStateEqualTo(0);//0,未领取的任务
        List<Task> tasks = taskMapper.selectByExample(taskExample);
        return tasks;
    }

    /**
     * 查询所有废弃的任务
     *
     * @return
     */
    @Override
    public List<Task> queryTasksInTrash() {
        taskExample.clear();
        taskExample.createCriteria()
                .andTaskStateEqualTo(2);//已经放弃
        List<Task> tasks = taskMapper.selectByExample(taskExample);
        return tasks;
    }

    /**
     * 查询某一个公司的相关任务
     *
     * @param comId
     * @return
     */
    @Override
    public List<Task> queryTasksByComId(String comId){
        taskExample.clear();
        taskExample.createCriteria()
                .andComIdEqualTo(comId);
        List<Task> tasks = taskMapper.selectByExample(taskExample);
        return tasks;
    }

    /**
     * 查询自己所在部门以及以下部门所有员工的任务
     *
     * @param orgId
     * @return
     */
    @Override
    public List<Task> queryTasksUnderTheLeadership(Integer orgId) {
        //首先查询部门下面所有的员工
        List<User> users = userService.queryAllUsersByOrgId(orgId);
        //查询每一个员工所属的任务
        List<Task> tasks = new ArrayList<>();
        for (User user : users) {
            taskExample.clear();
            taskExample.createCriteria()
                    .andHoldUserIdEqualTo(user.getUserId());
            List<Task> tasksByHoldUser = taskMapper.selectByExample(taskExample);
            tasks.addAll(tasksByHoldUser);
        }
        return tasks;
    }

    /**
     * 根据公司的信息模糊查询任务
     *
     * @param words
     * @return
     */
    @Override
    public List<Task> queryTasksByFuzzySearchAll(String words) throws SolrServerException {
        //先查公司
        List<CompanySolr> companySolrs = companySolrService.querySolrCompanyByFuzzySearchAll(words);
        //再查任务信息
        List<String> comIds = new ArrayList<>();
        for (CompanySolr com :companySolrs ) {
            comIds.add(com.getComId());
        }
        if (0 <= comIds.size()){
            return null;
        }
        List<Task> tasks = taskMapper.selectTasksByManyComIds(comIds);
        return tasks;
    }
}
