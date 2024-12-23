package com.example.getrand_analysticservice.service;

import com.example.getrand_analysticservice.dao.CommonTrendDAO;
import com.example.getrand_analysticservice.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommonTrendServiceImpl implements CommonTrendService {
    private final CommonTrendDAO commonTrendDAO;

    @Override
    public List<DefaultPastDTO> findOy() {
        List<DefaultPastOY> list = commonTrendDAO.findOy();
        return list.stream()
                .map(entity -> new DefaultPastDTO(
                        entity.getId(),
                        entity.getDate(),
                        entity.getValue(),
                        entity.getCreateDate(),
                        entity.getUpdateDate()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<RealTimeTrendDTO> findRtt() {
        List<RealTimeTrendEntity> list = commonTrendDAO.findRtt();
        return list.stream()
                .map(entity -> new RealTimeTrendDTO(
                        entity.getId(),
                        entity.getQuery(),
                        entity.getSearchVolume(),
                        entity.getIncreasePercentage(),
                        entity.getCreateDate(),
                        entity.getUpdateDate()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<RelatedTopicsDTO> findRt() {
        List<RelatedTopics> list = commonTrendDAO.findRt();
        return list.stream()
                .map(entity -> new RelatedTopicsDTO(
                        entity.getId(),
                        entity.getTitle(),
                        entity.getType(),
                        entity.getValue(),
                        entity.getExtractedValue(),
                        entity.getCreateDate(),
                        entity.getUpdateDate()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<RelatedQueriesDTO> findRq() {
        List<RelatedQueries> list = commonTrendDAO.findRq();
        return list.stream()
                .map(entity -> new RelatedQueriesDTO(
                        entity.getId(),
                        entity.getQuery(),
                        entity.getValue(),
                        entity.getExtractedValue(),
                        entity.getCreateDate(),
                        entity.getUpdateDate()
                ))
                .collect(Collectors.toList());
    }
}
