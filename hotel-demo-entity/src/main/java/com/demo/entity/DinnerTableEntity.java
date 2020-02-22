package com.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author 过往云烟~
 */
@Data
@AllArgsConstructor
public class DinnerTableEntity {
    private int id;
    /**
     * tableName   :      餐桌名称
     * tableStatus :    状态 0：未使用, 1：正在使用
     * beginUseDate :   占用
     * createDate   :   餐桌创建时间
     * disabled    :    1：为删除餐桌 默认：0
     */
    private String tableName;
    private int tableStatus;
    private Date beginUseDate;
    private Date createDate;
    private Date updateDate;
    private int disabled;

    public DinnerTableEntity() {
    }
}
