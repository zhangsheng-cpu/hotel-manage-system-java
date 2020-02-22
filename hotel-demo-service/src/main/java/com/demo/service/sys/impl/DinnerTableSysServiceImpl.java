package com.demo.service.sys.impl;

import com.demo.dao.sys.DinnerTableSysDao;
import com.demo.entity.DinnerTableEntity;
import com.demo.service.sys.DinnerTableSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DinnerTableSysServiceImpl implements DinnerTableSysService {


	@Autowired
	private DinnerTableSysDao dinnerTableDao;

	@Override
	public List<DinnerTableEntity> find(String keyword, String tableStatus, String disabled, int pageNum, int pageSize) {
		return dinnerTableDao.find(keyword, tableStatus, disabled, pageNum, pageSize);
	}

	@Override
	public DinnerTableEntity findById(int id) {
		return dinnerTableDao.findById(id);
	}

	@Override
	public void update(DinnerTableEntity dinnerTable) {
		dinnerTableDao.update(dinnerTable);
	}

	@Override
	public DinnerTableEntity findByTableName(String tableName) {
		return dinnerTableDao.findByTableName(tableName);
	}

	@Override
	public void insert(String tableName) {
		dinnerTableDao.insert(tableName);
	}
}
