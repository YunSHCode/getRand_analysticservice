package com.example.getrand_analysticservice.dao;

import com.example.getrand_analysticservice.dto.DefaultPastOY;
import com.example.getrand_analysticservice.dto.RealTimeTrendEntity;
import com.example.getrand_analysticservice.dto.RelatedQueries;
import com.example.getrand_analysticservice.dto.RelatedTopics;

import java.util.List;

public interface CommonTrendDAO {
    List<RealTimeTrendEntity> findRtt();

    List<RelatedTopics> findRt();

    List<RelatedQueries> findRq();

    List<DefaultPastOY> findOy();
}
