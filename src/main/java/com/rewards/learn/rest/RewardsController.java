package com.rewards.learn.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.learn.service.RewardsCalculator;


@CrossOrigin
@RestController
public class RewardsController {
    
    @Autowired
    RewardsCalculator calculator;
    
    @GetMapping(path = "/rewards")
    public  Map<Integer, Map<String, Integer>> getRewards() {
        return calculator.getRewards();
    }
    
    
}
