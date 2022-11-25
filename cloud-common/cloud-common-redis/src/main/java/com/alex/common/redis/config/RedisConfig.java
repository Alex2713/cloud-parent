package com.alex.common.redis.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * redis 配置
 * @author Alex
 */
@Slf4j
@Component
@EnableCaching
public class RedisConfig {

    /**
     * 缓存生存时间
     */
    private final Duration timeToLive = Duration.ofDays(1);

    /**
     * 缓存管理
     * @param connectionFactory 连接工厂
     * @return RedisCacheManager
     */
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        // redis缓存配置
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(this.timeToLive)
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()))
                .disableCachingNullValues();
        // 缓存配置map
        Map<String,RedisCacheConfiguration> cacheConfigurationMap = new HashMap<>(5);
        // 自定义缓存名，后面使用的@Cacheable的CacheName
        cacheConfigurationMap.put("users",config);
        cacheConfigurationMap.put("default",config);
        // 根据redis缓存配置和reid连接工厂生成redis缓存管理器
        RedisCacheManager redisCacheManager = RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(config)
                .transactionAware()
                .withInitialCacheConfigurations(cacheConfigurationMap)
                .build();
        log.debug("自定义RedisCacheManager加载完成");
        return redisCacheManager;
    }

    /**
     * redisTemplate模板提供给其他类对redis数据库进行操作
     * @param redisConnectionFactory 工厂
     * @return redisTemplate
     */
    @Bean(name = "redisTemplate")
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(keySerializer());
        redisTemplate.setHashKeySerializer(keySerializer());
        redisTemplate.setValueSerializer(valueSerializer());
        redisTemplate.setHashValueSerializer(valueSerializer());
        log.debug("自定义RedisTemplate加载完成");
        return redisTemplate;
    }

    /**
     * 缓存键自动生成器
     * @return KeyGenerator
     */
    @Bean
    public KeyGenerator myKeyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

    /**
     * redis键序列化使用StringRedisSerializer
     * @return RedisSerializer
     */
    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }

    /**
     * redis值序列化使用json序列化器
     * @return RedisSerializer
     */
    private RedisSerializer<Object> valueSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }
}
