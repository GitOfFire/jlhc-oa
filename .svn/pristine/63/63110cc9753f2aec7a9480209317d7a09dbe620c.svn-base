package com.jlhc.oa.service;

import com.jlhc.oa.dto.function.Right;
import com.jlhc.oa.dto.right.RightAndFuncId;
import java.util.List;

public interface RightService {

    public List<Right> getAllRights();

    public List<String> getRigthsByUserId(Integer userId);

    Integer createRight2Function(RightAndFuncId rightAndFuncId);

    List<Right> queryRightBySlurName(String rightName);

    List<Right> queryRightsBySlurData(String slurData);

    List<Right> queryRightsByFuncId(Integer funcId);

    Integer dropRightAndFuncRightRelation(Integer rightId);

    Integer createFuncRightRelation(Integer rightId, Integer funcId);

    List<String> queryRightDatasByUserId(Integer userId);
}
