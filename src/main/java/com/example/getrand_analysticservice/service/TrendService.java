package com.example.getrand_analysticservice.service;

import com.example.getrand_analysticservice.dto.DefaultPastOYResponseDTO;
import com.example.getrand_analysticservice.dto.RelatedQueriesResponseDTO;
import com.example.getrand_analysticservice.dto.RelatedTopicsResponseDTO;

import java.util.List;

public interface TrendService {
    List<DefaultPastOYResponseDTO> pastOneYear(String query);
    List<RelatedQueriesResponseDTO> fetchRelatedQueries(String query);
    List<RelatedTopicsResponseDTO> fetchRelatedTopics(String query);
}
