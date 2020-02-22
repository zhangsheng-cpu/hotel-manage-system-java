package com.demo.dao.app;

import com.demo.entity.DinnerTableEntity;

import java.util.List;

/**
 * @author 过往云烟~
 */
public interface DinnerTableDao {

    //通过 餐桌名或餐桌状态 查询未被删除的餐桌
    List<DinnerTableEntity> getAll(String tableStatus, String tableName);

    //查询 tb_dinner_table 表
    DinnerTableEntity findById(int id);

    //修改 tb_dinner_table 表
    void update(DinnerTableEntity dinnerTableEntity);
}
