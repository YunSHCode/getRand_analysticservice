package com.example.getrand_analysticservice.client;

import com.example.getrand_analysticservice.dto.DefaultPastOYResponseDTO;
import com.example.getrand_analysticservice.dto.RealTimeTrendResponseDTO;
import com.example.getrand_analysticservice.dto.RelatedQueriesResponseDTO;
import com.example.getrand_analysticservice.dto.RelatedTopicsResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "datacollectionservice")
public interface DataCollectionServiceClient {
    @GetMapping("/datacollectionservice/defaultPastOY/findall")
    public List<DefaultPastOYResponseDTO> getAllDefaultPastOY();

    @GetMapping("/datacollectionservice/defaultPastOY/update")
    public List<DefaultPastOYResponseDTO> updateDefaultPastOY();

    @GetMapping("/datacollectionservice/realTimeTrend/findall")
    public List<RealTimeTrendResponseDTO> getAllRealTimeTrend();

    @GetMapping("/datacollectionservice/realTimeTrend/update")
    public List<RealTimeTrendResponseDTO> updateRealTimeTrend();

    @GetMapping("/datacollectionservice/relatedTopics/findall")
    public List<RelatedTopicsResponseDTO> getAllRelatedTopics();

    @GetMapping("/datacollectionservice/relatedTopics/update")
    public List<RelatedTopicsResponseDTO> updateRelatedTopics();

    @GetMapping("/datacollectionservice/relatedQueries/findall")
    public List<RelatedQueriesResponseDTO> getAllRelatedQueries();

    @GetMapping("/datacollectionservice/relatedQueries/update")
    public List<RelatedQueriesResponseDTO> updateRelatedQueries();
}
