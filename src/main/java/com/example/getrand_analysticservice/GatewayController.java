package com.example.getrand_analysticservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analysticservice")
public class GatewayController {
    public String test() {
        return "test";
    }
}
