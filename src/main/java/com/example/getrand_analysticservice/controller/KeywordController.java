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
    public String callKeywordAPI(@RequestParam("keyword") String keyword) {
        trendService.pastOneYear(keyword);
        trendService.fetchRelatedQueries(keyword);
        trendService.fetchRelatedQueries(keyword);

        return "OK";
    }
}
