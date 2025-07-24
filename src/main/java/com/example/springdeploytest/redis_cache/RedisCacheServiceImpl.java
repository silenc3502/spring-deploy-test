package com.example.springdeploytest.redis_cache;

import lombok.RequiredArgsConstructor;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisCacheServiceImpl implements RedisCacheService {

    final private StringRedisTemplate redisTemplate;

    @Override
    public <K, V> void setKeyAndValue(K key, V value) {
        setKeyAndValue(key, value, Duration.ofMinutes(720));
    }

    @Override
    public <K, V> void setKeyAndValue(K key, V value, Duration timeToLive) {
        String keyAsString = String.valueOf(key);
        String valueAsString = String.valueOf(value);

        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(keyAsString, valueAsString, timeToLive);
    }

    @Override
    public <T> T getValueByKey(String key, Class<T> clazz) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String value = valueOperations.get(key);
        if (value == null) {
            return null;
        }

        if (clazz == String.class) {
            return clazz.cast(value);
        }

        if (clazz == Long.class) {
            return clazz.cast(Long.valueOf(value));
        }

        throw new IllegalArgumentException("지원하지 않는 타입입니다: " + clazz);
    }

    @Override
    public void deleteByKey(String userToken) {
        redisTemplate.delete(userToken);
    }
}
