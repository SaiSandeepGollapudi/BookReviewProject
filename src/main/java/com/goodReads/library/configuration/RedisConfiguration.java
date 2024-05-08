package com.goodReads.library.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {
        /**
         * Steps to add Caching -> Redis
         * <p>
         * 1. add dependency
         * 2. setup Driver for Redis, comes through dependency
         * a.Lettuce by default
         * b. Jedis
         * <p>
         * 3. Create a connection bean
         * 4. Create a template to access the data.
         */

@Bean
public LettuceConnectionFactory getLettuceConnectionFactory() {// this method is responsible for creating and configuring a LettuceConnectionFactory bean,
// which represents a connection to the Redis server.
RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("redis-14609.c325.us-east-1-4.ec2.redns.redis-cloud.com",14609);
//    RedisStandaloneConfiguration object is used to configure the connection details for a standalone Redis server,
// it has two types of dbs stand alone and clustered and ours is stand alone. stand alone is where one is instance and is supporting all my data
//It sets up the connection details such as the host, port, and password required to connect to the Redis server.
redisStandaloneConfiguration.setPassword("EwHyPUAQoXlAJ3IRKnUwekJUewCUSAEZ");
// we are not passing userName as it's just default. Lettuce, on the other hand, can be considered the driver as it's the client library responsible for communicating with the Redis server.
LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);//LettuceConnectionFactory object uses this configuration to establish a
// connection to the Redis server setting up Driver for Redis, for creating a connection, i.e. how spring should communicate with the app, maintaining a session,
return lettuceConnectionFactory;
}

 @Bean
public RedisTemplate<String, Object> getRedisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {// we are using method injection to inject lettuceConnectionFactory
// helps in passing data from app to redis via packet of redis template. is for getting all the operations you did on the data types
//It requires a connection factory to be set in order to establish a connection to the Redis server. This connection factory is provided as a parameter to the method by Lettuce driver.
//The RedisTemplate simplifies Redis operations by providing methods to perform common operations like reading, writing, and deleting data from Redis.
RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();// String key, Object value
//In this method, we configure the serializer for keys using setKeySerializer(). Serializers are used to convert Java objects into a format that can be stored in Redis.
redisTemplate.setKeySerializer(new StringRedisSerializer());
redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//redisTemplate.setValueSerializer(new StringRedisSerializer());1
redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));//converting Java book object into JSON format before storing them in Redis,Jackson2Json is java to JSON
// Finally, the LettuceConnectionFactory bean obtained from the previous method is set as the connection factory for the RedisTemplate.
redisTemplate.setConnectionFactory(lettuceConnectionFactory);
return redisTemplate;//The configured RedisTemplate instance is returned as a bean from this method, making it available for use elsewhere in your application.
        }
}
