package com.example.getrand_analysticservice.service;

public interface TrendService {
    void pastOneYear(String query);
    void fetchRelatedQueries(String query);
    void fetchRelatedTopics(String query);
}
