package com.jlhc.sell.service;

import com.jlhc.common.exception.NullDataForInsertException;
import com.jlhc.common.exception.NullEntityInDatabaseException;
import com.jlhc.oa.dto.user.User;
import com.jlhc.sell.dto.*;
import org.apache.solr.client.solrj.SolrServerException;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface TaskService {
    public TaskFullInfo queryTaskById(String taskId);

    Integer createTask(Task task) throws NullEntityInDatabaseException;

    Integer reworkTask(TaskForPut task, HttpSession httpSession) throws NullEntityInDatabaseException;

    Integer dropTask(String taskId,User user);

    List<Task> queryTasksByNameOrMobile(String nameOrMobile) throws SolrServerException;

    List<Task> queryTasksByComName(String comName) throws SolrServerException;

    List<Task> queryTasksByCreateUserId(Integer createUserId);

    List<Task> queryTasksByHoldUserId(Integer holdUserId);

    Integer createTasks(List<Task> tasks, List<Flow> flows) throws NullDataForInsertException, NullEntityInDatabaseException;

    List<Task> queryTasksByFlowUserId(Integer flowUserId);

    Integer reworkTaskToChangeHoldUserId(List<TaskForUpdateHoldUser> taskIdAndHoldUserIdMap, User user) throws NullEntityInDatabaseException;

    Integer createRelationTaskToUser(Integer userId, String taskId,Integer operUserId) throws NullEntityInDatabaseException;

    Integer dropTaskUserRelation(String taskId,Integer userId,Integer operUserId) throws NullEntityInDatabaseException;

    List<Task> queryTasksByUserIdInTaskUserRelation(Integer userId);

    List<Task> queryTasksInTaskSea();

    List<Task> queryTasksInTrash();

    List<Task> queryTasksByComId(String comId);

    List<Task> queryTasksUnderTheLeadership(Integer orgId);

    List<Task> queryTasksByFuzzySearchAll(String words) throws SolrServerException;
}
