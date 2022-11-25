package com.alex.common.core.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * redis 通用工具接口
 * @author Alex
 */
public interface RedisService {
    /////////////////// redis transaction start
    /**
     * 开启事务
     */
    void openTransaction();

    /**
     * 提交事务
     */
    void commitTransaction();

    /**
     * 回滚事务
     */
    void rollbackTransaction();
    /////////////////// redis transaction end

    /////////////////// redis base start
    /**
     * 设置缓存失效时间
     * @param key 缓存名
     * @param time 失效时间
     * @return true成功 false失败
     */
    boolean expire(String key, long time);

    /**
     * 获取缓存失效时间
     * @param key 缓存名
     * @return 失效时间 单位：秒 返回0代表为永久有效
     */
    long getExpire(String key);

    /**
     * 判断缓存是否存在
     * @param key 缓存名
     * @return true 存在 false不存在
     */
    boolean exist(String key);

    /**
     * 删除缓存
     * @param key 缓存名 可以传一个或多个值
     */
    void del(String... key);
    /////////////////// redis base end

    /////////////////// redis String start
    /**
     * 获取缓存内容
     * @param key 缓存名
     * @return 缓存结果
     */
    Object get(String key);

    /**
     * 设置缓存内容
     * @param key 缓存名
     * @param value 缓存内容
     * @return true成功 false失败
     */
    boolean set(String key, Object value);

    /**
     * 设置缓存内容
     * @param key 缓存名
     * @param value 缓存内容
     * @param time 失效时间 单位：秒 time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false失败
     */
    boolean set(String key, Object value, long time);

    /**
     * 递增
     * @param key 缓存名
     * @param delta 递增因子
     * @return 递增后的缓存内容
     */
    long incr(String key, long delta);

    /**
     * 递减
     * @param key 缓存名
     * @param delta 递减因子
     * @return 递减后的缓存内容
     */
    long decr(String key, long delta);
    /////////////////// redis String end

    /////////////////// redis hash start
    /**
     * 获取缓存内容
     * @param key 缓存名
     * @param item hash名
     * @return 缓存内容
     */
    Object getHash(String key, String item);

    /**
     * 获取缓存内容
     * @param key 缓存名
     * @return 缓存内容
     */
    Map<Object, Object> getHash(String key);

    /**
     * 设置缓存内容
     * @param key 缓存名
     * @param map 缓存内容
     * @return true成功 false失败
     */
    boolean setHash(String key, Map<String, Object> map);

    /**
     * 设置缓存内容
     * @param key 缓存名
     * @param map 缓存内容
     * @param time 失效时间 单位：秒 time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false失败
     */
    boolean setHash(String key, Map<String, Object> map, long time);

    /**
     * 设置缓存内容
     * @param key 缓存名
     * @param item hash名
     * @param value 缓存内容
     * @return true成功 false失败
     */
    boolean setHash(String key, String item, Object value);

    /**
     * 设置缓存内容
     * @param key 缓存名
     * @param item hash名
     * @param value 缓存内容
     * @param time 失效时间 单位：秒 time要大于0 如果time小于等于0则保留之前的设置
     * @return true成功 false失败
     */
    boolean setHash(String key, String item, Object value, long time);

    /**
     * 删除缓存
     * @param key 缓存名
     * @param item hash名 可以传一个或多个值
     */
    void delHash(String key, Object... item);

    /**
     * 判断缓存是否存在
     * @param key 缓存名
     * @param item hash名
     * @return true 存在 false不存在
     */
    boolean existHash(String key, String item);

    /**
     * 递增
     * @param key 缓存名
     * @param item hash名
     * @param delta 递增因子
     * @return 递增后的缓存内容
     */
    double incrHash(String key, String item, double delta);

    /**
     * 递减
     * @param key 缓存名
     * @param item hash名
     * @param delta 递减因子
     * @return 递减后的缓存内容
     */
    double decrHash(String key, String item, double delta);
    /////////////////// redis hash end

    /////////////////// redis set start
    /**
     * 获取缓存内容
     * @param key 缓存名
     * @return 缓存内容
     */
    Set<Object> getSet(String key);

    /**
     * 从set对象中弹出一个值
     * @param key key
     * @return 值
     */
    Object getSetPop(String key);

    /**
     * 从set对象中弹出一个值
     * @param key key
     * @param size 弹出大小
     * @return 值列表
     */
    List<Object> getSetPop(String key, long size);

    /**
     * 获取缓存内容的长度
     * @param key 缓存名
     * @return 缓存内容的长度
     */
    long getSetSize(String key);

    /**
     * 设置缓存内容
     * @param key 缓存名
     * @param values 缓存内容 可以传一个或多个值
     * @return 成功个数
     */
    long setSet(String key, Object... values);

    /**
     * 设置缓存内容
     * @param key 缓存名
     * @param time 失效时间 单位：秒 time要大于0 如果time小于等于0则保留之前的设置
     * @param values 缓存内容 可以传一个或多个值
     * @return 成功个数
     */
    long setSet(String key, long time, Object... values);

    /**
     * 删除缓存
     * @param key 缓存名
     * @param values 缓存内容 可以传一个或多个值
     * @return 删除的个数
     */
    long delSet(String key, Object... values);

    /**
     * 判断缓存是否存在
     * @param key 缓存名
     * @param value 缓存内容
     * @return true 存在 false不存在
     */
    boolean existSet(String key, Object value);
    /////////////////// redis set end

    /////////////////// redis list start
    /**
     * 获取缓存内容
     * @param key 缓存名
     * @param start 开始位置
     * @param end 结束位置
     * @return 缓存内容
     */
    List<Object> getList(String key, long start, long end);

    /**
     * 获取缓存内容
     * @param key 缓存名
     * @param index 位置索引
     * @return 缓存内容
     */
    Object getList(String key, long index);

    /**
     * 获取缓存内容的长度
     * @param key 缓存名
     * @return 缓存内容的长度
     */
    long getListSize(String key);

    /**
     * 设置缓存内容
     * @param key 缓存名
     * @param value 缓存内容
     * @return true成功 false失败
     */
    boolean setList(String key, Object value);

    /**
     * 设置缓存内容
     * @param key 缓存名
     * @param index 位置索引
     * @param value 缓存内容
     * @return true成功 false失败
     */
    boolean setList(String key, long index, Object value);

    /**
     * 设置缓存内容
     * @param key 缓存名
     * @param value 缓存内容
     * @param time 失效时间 单位：秒 time要大于0 如果time小于等于0则保留之前的设置
     * @return true成功 false失败
     */
    boolean setList(String key, Object value, long time);

    /**
     * 设置缓存内容
     * @param key 缓存名
     * @param value 缓存内容
     * @return true成功 false失败
     */
    boolean setList(String key, List<Object> value);

    /**
     * 设置缓存内容
     * @param key 缓存名
     * @param value 缓存内容
     * @param time 失效时间 单位：秒 time要大于0 如果time小于等于0则保留之前的设置
     * @return true成功 false失败
     */
    boolean setList(String key, List<Object> value, long time);

    /**
     * 删除缓存
     * @param key 缓存名
     * @param count 移除多少个
     * @param value 缓存内容
     * @return 移除的个数
     */
    long delList(String key, long count, Object value);
    /////////////////// redis list end
}
