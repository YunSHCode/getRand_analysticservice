package com.example.getrand_analysticservice.controller;

import com.example.getrand_analysticservice.dto.DefaultPastOYResponseDTO;
import com.example.getrand_analysticservice.dto.RealTimeTrendResponseDTO;
import com.example.getrand_analysticservice.dto.RelatedQueriesResponseDTO;
import com.example.getrand_analysticservice.dto.RelatedTopicsResponseDTO;
import com.example.getrand_analysticservice.service.DataCollectionResponseService;
import com.example.getrand_analysticservice.service.TrendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analysticservice")
@RequiredArgsConstructor
public class GatewayController {
    private final DataCollectionResponseService dataCollectionResponseService;
    private final TrendService trendService;

    @GetMapping("/defaultPastOY/findall")
    @ResponseBody
    public List<DefaultPastOYResponseDTO> getAllDefaultPastOY() {
        return dataCollectionResponseService.getAllDefaultPastOY();
    }

    @GetMapping("/defaultPastOY/update")
    @ResponseBody
    public List<DefaultPastOYResponseDTO> updateDefaultPastOY() {
        return dataCollectionResponseService.updateDefaultPastOY();
    }

    @GetMapping("/defaultPastOY/keyword")
    @ResponseBody
    public List<DefaultPastOYResponseDTO> keywordDefaultPastOY(@RequestParam("keyword") String query) {
        return trendService.pastOneYear(query);
    }

    @GetMapping("/realTimeTrend/findall")
    @ResponseBody
    public List<RealTimeTrendResponseDTO> getAllRealTimeTrend() {
        return dataCollectionResponseService.getAllRealTimeTrend();
    }

    @GetMapping("/realTimeTrend/update")
    @ResponseBody
    public List<RealTimeTrendResponseDTO> updateRealTimeTrend() {
        return dataCollectionResponseService.updateRealTimeTrend();
    }

    @GetMapping("/relatedTopics/findall")
    @ResponseBody
    public List<com.example.getrand_analysticservice.dto.RelatedTopicsResponseDTO> getAllRelatedTopics() {
        return dataCollectionResponseService.getAllRelatedTopics();
    }

    @GetMapping("/relatedTopics/update")
    @ResponseBody
    public List<com.example.getrand_analysticservice.dto.RelatedTopicsResponseDTO> updateRelatedTopics() {
        return dataCollectionResponseService.updateRelatedTopics();
    }

    @GetMapping("/relatedTopics/keyword")
    @ResponseBody
    public List<RelatedTopicsResponseDTO> keywordRelatedTopics(@RequestParam("keyword") String query) {
        return trendService.fetchRelatedTopics(query);
    }

    @GetMapping("/relatedQueries/findall")
    @ResponseBody
    public List<com.example.getrand_analysticservice.dto.RelatedQueriesResponseDTO> getAllRelatedQueries() {
        return dataCollectionResponseService.getAllRelatedQueries();
    }

    @GetMapping("/relatedQueries/update")
    @ResponseBody
    public List<com.example.getrand_analysticservice.dto.RelatedQueriesResponseDTO> updateRelatedQueries() {
        return dataCollectionResponseService.updateRelatedQueries();
    }

    @GetMapping("/relatedQueries/keyword")
    @ResponseBody
    public List<RelatedQueriesResponseDTO> keywordRelatedQueries(@RequestParam("keyword") String query) {
        return trendService.fetchRelatedQueries(query);
    }
}
