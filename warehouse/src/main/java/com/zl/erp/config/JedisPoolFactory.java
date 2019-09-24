package com.zl.erp.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Description: redis配置
 * @Author: zhutao
 * @Date: 2019/9/18
 */
@Slf4j
@Configuration
public class JedisPoolFactory {
    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxActive;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private long maxWaitMillis;

    @Bean
    public JedisPool generateJedisPoolFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(maxActive);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxWaitMillis(maxWaitMillis);
        //在获取Jedis连接时，自动检验连接是否可用
        poolConfig.setTestOnBorrow(true);
        //在将连接放回池中前，自动检验连接是否有效
        poolConfig.setTestOnReturn(true);
        //自动测试池中的空闲连接是否都是可用连接
        poolConfig.setTestWhileIdle(true);
        //创建连接池
        return new JedisPool(poolConfig, host, port, timeout, password);
    }
}
