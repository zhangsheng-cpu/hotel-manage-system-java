package com.demo.service.app.impl;

import com.demo.dao.app.DinnerTableDao;
import com.demo.entity.DinnerTableEntity;
import com.demo.service.app.DinnerTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 过往云烟~
 */
@Service
public class DinnnerTableServiceImpl implements DinnerTableService {

    @Autowired
    private DinnerTableDao dao;

    @Override
    public List<DinnerTableEntity> getAll(String tableStatus, String tableName) {
        return dao.getAll(tableStatus, tableName);
    }

    @Override
    public DinnerTableEntity findById(int id) {
        return dao.findById(id);
    }

    @Override
    public void update(DinnerTableEntity dinnerTable) {
        dao.update(dinnerTable);
    }
}
