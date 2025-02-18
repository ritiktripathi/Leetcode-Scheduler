package com.scheduler.daily_challange_scheduler.controller;

import com.scheduler.daily_challange_scheduler.entity.DailyProblem;
import com.scheduler.daily_challange_scheduler.model.Response;
import com.scheduler.daily_challange_scheduler.service.LeetCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeetCodeController {

    @Autowired
    private LeetCodeService leetCodeService;

    @GetMapping("/dailyproblem")
    public ResponseEntity<?> getTodayProblem() {

        Response response = leetCodeService.getTodayProblem();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/addtodayproblem")
    public ResponseEntity<?> addProblem() {

        leetCodeService.addDailyProblem();

        return new ResponseEntity<>("Problem added successfully check 1", HttpStatus.OK);
    }

}
