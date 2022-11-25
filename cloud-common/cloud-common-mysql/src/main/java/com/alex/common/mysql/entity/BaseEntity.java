package com.alex.common.mysql.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

/**
 * BasicsEntity：基础Entity实体类
 * @author Alex-2713
 *  注解说明：
 *      TableName
 *          表名注解
 *          value：String-非必须-默认""
 *              表名
 *          resultMap：String-非必须-默认""
 *              xml中resultMap的id
 *          schema：String-非必须-默认""
 *              schema(@since 3.1.1)
 *          keepGlobalPrefix：boolean-非必须-默认false
 *              是否保持使用全局的 tablePrefix 的值(如果设置了全局 tablePrefix 且自行设置了 value 的值)(@since 3.1.1)
 *      TableId
 *          键注解
 *          value：String-非必须-默认""
 *              主键字段名
 *          type：Enum-非必须-默认IdType.NONE
 *              主键类型
 *                  IdType
 *                      AUTO            数据库自增
 *                      INPUT           自行输入
 *                      ID_WORKER       分布式全局唯一ID 长整型类型
 *                      UUID            32位UUID字符串
 *                      NONE            无状态
 *                      ID_WORKER_STR   分布式全局唯一ID 字符串类型
 *      TableField
 *          字段注解(非主键)
 *          value：String-非必须-默认""
 *              字段名
 *          el：String-非必须-默认""
 *              映射为原生 #{ ... } 逻辑,相当于写在 xml 里的 #{ ... } 部分
 *          exist：boolean-非必须-默认true
 *              是否为数据库表字段
 *          condition：String-非必须-默认""
 *              字段 where 实体查询比较条件,有值设置则按设置的值为准,没有则为默认全局的 %s=#{%s}
 *          update：String-非必须-默认""
 *              字段 update set 部分注入, 例如：update="%s+1"：表示更新时会set version=version+1(该属性优先级高于 el 属性)
 *          strategy：Enum-非必须-默认FieldStrategy.DEFAULT
 *              字段验证策略
 *                  FieldStrategy
 *                      IGNORED     忽略判断
 *                      NOT_NULL    非NULL判断
 *                      NOT_EMPTY   非空判断(只对字符串类型字段,其他类型字段依然为非NULL判断)
 *                      DEFAULT     追随全局配置
 *          fill：Enum-非必须-默认FieldFill.DEFAULT
 *              字段自动填充策略
 *                  FieldFill
 *                      DEFAULT         默认不处理
 *                      INSERT          插入时填充字段
 *                      UPDATE          更新时填充字段
 *                      INSERT_UPDATE   插入和更新时填充字段
 *          select：boolean-非必须-默认true
 *              是否进行 select 查询
 *          keepGlobalFormat：boolean-非必须-默认false
 *              是否保持使用全局的 format 进行处理(@since 3.1.1)
 *      Verison
 *          乐观锁注解、标记 @Verison 在字段上
 *      EnumValue
 *          通枚举类注解(注解在枚举字段上)
 *      TableLogic
 *          表字段逻辑处理注解（逻辑删除）
 *          value：String-非必须-默认""
 *              逻辑未删除值
 *          delval：String-非必须-默认""
 *              逻辑删除值
 *      SqlParser
 *          租户注解
 *          filter：boolean-非必须-默认false
 *              true: 表示过滤SQL解析，即不会进入ISqlParser解析链，否则会进解析链并追加例如tenant_id等条件
 *      KeySequence
 *          序列主键策略 oracle
 *          value：String-非必须-默认""
 *              序列名
 *          clazz：Class-非必须-默认Long.class
 *              id的类型, 可以指定String.class，这样返回的Sequence值是字符串"1"
 */
@Data
public class BaseEntity implements Serializable {
    /**
     * 主键
     *  not null
     *  length: 20
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 创建人
     *  not null
     *  length: 20
     */
    @TableField(value = "create_by")
    private Long createBy;

    /**
     * 创建时间
     *  not null
     *  length: 20
     */
    @TableField(value = "create_time")
    private Long createTime;

    /**
     * 最后修改人
     *  length: 20
     */
    @TableField(value = "update_by")
    private Long updateBy;

    /**
     * 最后修改时间
     *  length: 20
     */
    @TableField(value = "update_time")
    private Long updateTime;

    /**
     * 删除标记
     */
    @TableField(value = "del_flag")
    private Integer delFlag;

    /**
     * 数据版本
     *  not null
     *  length: 10
     *  default: 1
     */
    @TableField(value = "version_flag")
    private Integer versionFlag;

    /**
     * 备注
     *  length: 500
     */
    @TableField(value = "remark")
    private String remark;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
