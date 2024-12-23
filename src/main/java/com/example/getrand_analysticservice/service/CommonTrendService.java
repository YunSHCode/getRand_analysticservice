package com.example.getrand_analysticservice.service;

import com.example.getrand_analysticservice.dto.*;

import java.util.List;

public interface CommonTrendService {
    List<RealTimeTrendDTO> findRtt();

    List<RelatedTopicsDTO> findRt();

    List<RelatedQueriesDTO> findRq();

    List<DefaultPastDTO> findOy();
}
