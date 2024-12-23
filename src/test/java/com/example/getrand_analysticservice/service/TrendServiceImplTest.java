package com.example.getrand_analysticservice.service;

import com.example.getrand_analysticservice.dto.DefaultPastOYResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TrendServiceImplTest {

    @Autowired
    private TrendService trendServiceImpl;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void pastOneYear() {
        String query = "test";

        // When (첫 번째 호출: 캐시에 없음, 실제 호출 발생)
        List<DefaultPastOYResponseDTO> firstResponse = trendServiceImpl.pastOneYear(query);
        assertThat(firstResponse).isNotNull();

        // Then (캐시 동작 확인)
        Object cachedResponse = cacheManager.getCache("pastOneYearCache").get(query).get();
        assertThat(cachedResponse).isInstanceOf(List.class); // 캐시된 데이터 타입 확인
        assertThat(cachedResponse).isEqualTo(firstResponse); // 캐시된 데이터와 첫 번째 응답 비교

        // When (두 번째 호출: 캐시에 저장된 데이터 반환)
        List<DefaultPastOYResponseDTO> secondResponse = trendServiceImpl.pastOneYear(query);

        // Then (캐시가 제대로 반환되었는지 확인)
        assertThat(secondResponse).isEqualTo(firstResponse);

        // RedisTemplate로 직접 데이터 확인
        String redisKey = "pastOneYearCache::" + query;
        Object redisData = redisTemplate.opsForValue().get(redisKey);
        assertThat(redisData).isInstanceOf(List.class); // Redis 데이터 타입 확인
        assertThat(redisData).isEqualTo(firstResponse); // Redis에 저장된 데이터와 첫 번째 응답 비교
    }
}
