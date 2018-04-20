package com.jlhc.sell.service;

import com.jlhc.sell.dto.Flow;

import java.util.List;

public interface FlowService {
    //Integer createFlow(Flow flow);
    List<Flow> queryFlowsByTaskId(String taskId);
}
