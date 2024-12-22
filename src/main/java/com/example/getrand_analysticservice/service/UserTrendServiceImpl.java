package com.example.getrand_analysticservice.service;

import com.example.getrand_analysticservice.dao.UserTrendDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserTrendServiceImpl implements UserTrendService {
    private final UserTrendDAO userTrendDAO;




}
