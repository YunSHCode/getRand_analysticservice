package com.example.getrand_analysticservice.controller;


import com.example.getrand_analysticservice.dto.*;
import com.example.getrand_analysticservice.service.CommonTrendService;
import com.example.getrand_analysticservice.service.UserTrendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data")
@RequiredArgsConstructor
public class DataResponseController {
    private final CommonTrendService commonTrendService;
    private final UserTrendService userTrendService;

    @GetMapping("/pastOy")
    public List<DefaultPastDTO> getPastOy(){
        List<DefaultPastDTO> list = commonTrendService.findOy();
        return list;
    }

    @GetMapping("/realTimeTrend")
    public List<RealTimeTrendDTO> getRealTimeTrend() {
        List<RealTimeTrendDTO> list = commonTrendService.findRtt();
        return list;
    }

    @GetMapping("/relatedQuery")
    public List<RelatedQueriesDTO> getRelatedQuery() {
        List<RelatedQueriesDTO> list = commonTrendService.findRq();
        return list;
    }

    @GetMapping("/relatedTopic")
    public List<RelatedTopicsDTO> getRelatedTopic() {
        System.out.println("요청들어옴");
        List<RelatedTopicsDTO> list = commonTrendService.findRt();
        return list;
    }

}
