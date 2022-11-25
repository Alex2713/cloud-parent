package com.alex.entity;

import com.alex.common.mysql.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Alex-2713
 */
@Data
@TableName("table_info")
public class TableInfoEntity extends BaseEntity {
    /**
     * 名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 类型
     */
    @TableField(value = "type")
    private Integer type;
}
