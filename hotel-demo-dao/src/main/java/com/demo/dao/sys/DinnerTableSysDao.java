package com.demo.dao.sys;

import com.demo.entity.DinnerTableEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface DinnerTableSysDao {

    List<DinnerTableEntity> find(@Param("one") String keyword,@Param("two") String tableStatus,@Param("three") String disabled, @Param("pageNum")int pageNum, @Param("pageSize")int pageSize);

    DinnerTableEntity findById(int id);

    void update(DinnerTableEntity dinnerTable);

    DinnerTableEntity findByTableName(String tableName);

    void insert(@Param("tableName") String tableName);
}
