package com.example.getrand_analysticservice.controller;

import com.example.getrand_analysticservice.service.TrendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/keyword")
@RequiredArgsConstructor
public class KeywordController {
    private final TrendService trendService;

    @GetMapping("/keyword/api")
    @ResponseBody
    public String callKeywordAPI(@RequestParam("keyword") String query) {
        trendService.pastOneYear(query);
        trendService.fetchRelatedQueries(query);
        trendService.fetchRelatedQueries(query);
        return "OK";
    }
}
