package com.example.getrand_analysticservice.service;

import com.example.getrand_analysticservice.dto.DefaultPastOYResponseDTO;
import com.example.getrand_analysticservice.dto.RelatedQueriesResponseDTO;
import com.example.getrand_analysticservice.dto.RelatedTopicsResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrendServiceImpl implements TrendService {
    private final TrendAPIService apiService;
    private final ObjectMapper mapper;
    @Value("${serp.api-key}")
    private String apiKey;

//    @Cacheable(value = "pastOneYearCache", key = "#query", unless = "#result == null || #result.isEmpty()")
    @Override
    public List<DefaultPastOYResponseDTO> pastOneYear(String query) {
        try {
            System.out.println("api호출후 실행: pastOneYear");
            String jsonResponse = apiService.defaultTrend("google_trends", query, "today 12-m", apiKey);
            JsonNode rootNode = mapper.readTree(jsonResponse);
            JsonNode timelineData = rootNode.path("interest_over_time").path("timeline_data");
            System.out.println(timelineData);

            List<DefaultPastOYResponseDTO> defaultTrending = new ArrayList<>();

            if (timelineData != null && timelineData.isArray()) { // timelineData가 배열인지 확인
                for (JsonNode node : timelineData) {
                    // defaultTrendMonthsDTO 객체 생성
                    DefaultPastOYResponseDTO dto = new DefaultPastOYResponseDTO();
                    dto.setDate(node.get("date").asText());
                    JsonNode values = node.path("values").get(0);
                    if(values != null) {
                        dto.setValue(values.get("value").asText());
                    }
                    defaultTrending.add(dto);
                }
            }
            return defaultTrending;
        } catch (JsonProcessingException e) { // readTree 예외 처리
            throw new RuntimeException(e);
        }
    }

//    @Cacheable(value = "relatedQueriesCache", key = "#query", unless = "#result == null || #result.isEmpty()")
    @Override
    public List<RelatedQueriesResponseDTO> fetchRelatedQueries(String query) {
        try {
            System.out.println("api호출후 실행: fetchRelatedQueries");
            String jsonResponse = apiService.getRelatedQueries("KR", "google_trends","RELATED_QUERIES" , query,apiKey);
            JsonNode rootNode = mapper.readTree(jsonResponse);
            JsonNode topQueriesNode = rootNode.path("related_queries").path("top");
            List<RelatedQueriesResponseDTO> topQueries = new ArrayList<>();
            // 'top' 노드가 비어있지 않다면 처리
            if (topQueriesNode != null && topQueriesNode.isArray()) {
                int limit = Math.min(topQueriesNode.size(), 10); // 최대 10개까지만 처리
                for (int i = 0; i < limit; i++) {
                    JsonNode node = topQueriesNode.get(i);
                    RelatedQueriesResponseDTO entity = new RelatedQueriesResponseDTO();

                    // 필드 값 매핑
                    entity.setQuery(node.get("query").asText());
                    entity.setValue(node.get("value").asText());
                    entity.setExtractedValue(node.get("extracted_value").asInt());

                    // 엔티티 출력 및 리스트 추가
                    System.out.println(entity);
                    topQueries.add(entity);
                }
            }

            return topQueries;

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

//    @Cacheable(value = "relatedTopicsCache", key = "#query", unless = "#result == null || #result.isEmpty()")
    @Override
    public List<RelatedTopicsResponseDTO> fetchRelatedTopics(String query) {
        try {
            System.out.println("api호출후 실행: fetchRelatedTopics");
            String jsonResponse = apiService.getRelatedTopics("KR", "google_trends","RELATED_TOPICS" , query, apiKey);
            JsonNode rootNode = mapper.readTree(jsonResponse);
            JsonNode topTopicsNode = rootNode.path("related_topics").path("top");
            List<RelatedTopicsResponseDTO> topTopics = new ArrayList<>();
            // 'top' 노드가 비어있지 않다면 처리
            if (topTopicsNode != null && topTopicsNode.isArray()) {
                int limit = Math.min(topTopicsNode.size(), 10);
                for (int i = 0; i < limit; i++) {
                    JsonNode node = topTopicsNode.get(i);
                    RelatedTopicsResponseDTO entity = new RelatedTopicsResponseDTO();
                    JsonNode topicNode = node.get("topic");
                    if (topicNode != null) {
                        entity.setTitle(topicNode.get("title").asText());
                        entity.setType(topicNode.get("type").asText());
                    }

                    entity.setValue(node.get("value").asText());
                    entity.setExtractedValue(node.get("extracted_value").asInt());

                    System.out.println(entity);
                    topTopics.add(entity);
                }
            }

            return topTopics;

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
