package com.example.getrand_analysticservice.dao;

import com.example.getrand_analysticservice.dto.DefaultPastOY;
import com.example.getrand_analysticservice.dto.RealTimeTrendEntity;
import com.example.getrand_analysticservice.dto.RelatedQueries;
import com.example.getrand_analysticservice.dto.RelatedTopics;
import com.example.getrand_analysticservice.repository.DefaultPastOYRepository;
import com.example.getrand_analysticservice.repository.RealTimeTrendRepository;
import com.example.getrand_analysticservice.repository.RelatedQueriesRepository;
import com.example.getrand_analysticservice.repository.RelatedTopicsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommonTrendDAOImpl implements CommonTrendDAO {
    private final RealTimeTrendRepository realTimeTrendRepository;
    private final RelatedTopicsRepository relatedTopicsRepository;
    private final RelatedQueriesRepository relatedQueriesRepository;
    private final DefaultPastOYRepository defaultPastOYRepository;

    @Override
    public List<DefaultPastOY> findOy() {
        List<DefaultPastOY> list = defaultPastOYRepository.findAll();
        return list;
    }

    @Override
    public List<RealTimeTrendEntity> findRtt() {
        List<RealTimeTrendEntity> list = realTimeTrendRepository.findAll();
        return list;
    }

    @Override
    public List<RelatedTopics> findRt() {
        List<RelatedTopics> list = relatedTopicsRepository.findAll();
        return list;
    }

    @Override
    public List<RelatedQueries> findRq() {
        List<RelatedQueries> list = relatedQueriesRepository.findAll();
        return list;
    }
}
