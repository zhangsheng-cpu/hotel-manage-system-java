package com.demo.service.sys;

import com.demo.entity.DinnerTableEntity;

import java.util.List;


public interface DinnerTableSysService {

	List<DinnerTableEntity> find(String keyword, String tableStatus, String disabled, int pageNum, int pageSize);

	DinnerTableEntity findById(int id);

	void update(DinnerTableEntity dinnerTable);

	DinnerTableEntity findByTableName(String tableName);

	void insert(String tableName);

}
