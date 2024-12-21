package com.example.getrand_analysticservice.service;

import com.example.getrand_analysticservice.dto.*;
import com.example.getrand_analysticservice.dto.DefaultPastOYDTO;
import com.example.getrand_analysticservice.dto.RelatedQueriesDTO;
import com.example.getrand_analysticservice.dto.RelatedTopicsDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

    @Override
    public void pastOneYear(String query) {
        try {
            String jsonResponse = apiService.defaultTrend("google_trends", query, "today 12-m", apiKey);
            JsonNode rootNode = mapper.readTree(jsonResponse);
            JsonNode timelineData = rootNode.path("interest_over_time").path("timeline_data");
            System.out.println(timelineData);

            List<DefaultPastOYDTO> defaultTrending = new ArrayList<>();

            if (timelineData != null && timelineData.isArray()) { // timelineData가 배열인지 확인
                for (JsonNode node : timelineData) {
                    // defaultTrendMonthsDTO 객체 생성
                    DefaultPastOYDTO dto = new DefaultPastOYDTO();
                    dto.setDate(node.get("date").asText());
                    JsonNode values = node.path("values").get(0);
                    if(values != null) {
                        dto.setValue(values.get("value").asText());
                    }
                    defaultTrending.add(dto);
                }
            }

        } catch (JsonProcessingException e) { // readTree 예외 처리
            throw new RuntimeException(e);
        }
    }

    @Override
    public void fetchRelatedQueries(String query) {
        try {
            String jsonResponse = apiService.getRelatedQueries("KR", "google_trends","RELATED_QUERIES" , query,apiKey);
            JsonNode rootNode = mapper.readTree(jsonResponse);
            JsonNode topQueriesNode = rootNode.path("related_queries").path("top");
            List<RelatedQueriesDTO> topQueries = new ArrayList<>();
            // 'top' 노드가 비어있지 않다면 처리
            if (topQueriesNode != null && topQueriesNode.isArray()) {
                int limit = Math.min(topQueriesNode.size(), 10); // 최대 10개까지만 처리
                for (int i = 0; i < limit; i++) {
                    JsonNode node = topQueriesNode.get(i);
                    RelatedQueriesDTO entity = new RelatedQueriesDTO();

                    // 필드 값 매핑
                    entity.setQuery(node.get("query").asText());
                    entity.setValue(node.get("value").asText());
                    entity.setExtractedValue(node.get("extracted_value").asInt());

                    // 엔티티 출력 및 리스트 추가
                    System.out.println(entity);
                    topQueries.add(entity);
                }
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void fetchRelatedTopics(String query) {
        try {
            String jsonResponse = apiService.getRelatedTopics("KR", "google_trends","RELATED_TOPICS" , query, apiKey);
            JsonNode rootNode = mapper.readTree(jsonResponse);
            JsonNode topTopicsNode = rootNode.path("related_topics").path("top");
            List<RelatedTopicsDTO> topTopics = new ArrayList<>();
            // 'top' 노드가 비어있지 않다면 처리
            if (topTopicsNode != null && topTopicsNode.isArray()) {
                int limit = Math.min(topTopicsNode.size(), 10);
                for (int i = 0; i < limit; i++) {
                    JsonNode node = topTopicsNode.get(i);
                    RelatedTopicsDTO entity = new RelatedTopicsDTO();
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
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
