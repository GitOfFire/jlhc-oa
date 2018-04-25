package com.jlhc.sell.service.impl;

import com.jlhc.sell.dao.FlowMapper;
import com.jlhc.sell.dto.Flow;
import com.jlhc.sell.dto.example.FlowExample;
import com.jlhc.sell.service.FlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FlowServiceImpl implements FlowService {
    @Autowired
    FlowMapper flowMapper;
//    public static FlowExample flowExample = new FlowExample();
    /**
     * 查询任务的所有流转记录
     *
     * @param taskId
     * @return
     */
    @Override
    public List<Flow> queryFlowsByTaskId(String taskId) {
        if (null == taskId||"".equals(taskId)){
            throw new NullPointerException();
        }
        FlowExample flowExample = new FlowExample();
        flowExample.clear();
        flowExample.createCriteria()
                .andTaskIdEqualTo(taskId);
        List<Flow> flows = flowMapper.selectByExample(flowExample);
        return flows;
    }
}
