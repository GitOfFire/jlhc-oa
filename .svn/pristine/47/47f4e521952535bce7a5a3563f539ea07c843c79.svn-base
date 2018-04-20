package com.jlhc.oa.service.impl;

import com.jlhc.oa.dao.ModuleMapper;
import com.jlhc.oa.dto.function.Module;
import com.jlhc.oa.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    ModuleMapper moduleMapper;
    @Override
    public Integer createModule(Module module) {
        Integer resultNum = 0;
        Integer resutlNum = moduleMapper.insertSelectiveWhereNotExistTheName(module);
        return resultNum;
    }
}
