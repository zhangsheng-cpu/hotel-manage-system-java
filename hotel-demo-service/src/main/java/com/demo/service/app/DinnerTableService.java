package com.demo.service.app;

import com.demo.entity.DinnerTableEntity;

import java.util.List;

public interface DinnerTableService {

    List<DinnerTableEntity> getAll(String tableStatus, String tableName);

    DinnerTableEntity findById(int id);

    void update(DinnerTableEntity dinnerTable);
}
