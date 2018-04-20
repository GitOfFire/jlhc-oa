package com.jlhc.web.controller;


import com.jlhc.common.dto.Msg;
import com.jlhc.common.utils.ResultUtil;
import com.jlhc.sell.dto.Flow;
import com.jlhc.sell.service.FlowService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("flow")
public class SellFlowController extends BaseController {
    @Autowired
    FlowService flowService;

    /**
     * 根据任务查询任务流转记录
     *
     * @param taskId
     * @return
     */
    @GetMapping("getFlowsByTaskId/{taskId}")
    @RequiresPermissions("flow:getFlowsByTaskId")
    public Msg getFlowsByTaskId(@PathVariable @Size(min = 32,max = 32,message = "代理主键为32位全球唯一码")  String taskId){
        try{
            List<Flow> flows = flowService.queryFlowsByTaskId(taskId);
            if (null == flows ||0 == flows.size()){
                return ResultUtil.selectFailed();
            }
            return ResultUtil.selectSuccess(flows);
        }catch(Exception e){
            return errorResultOperation(e);
        }
    };
}
