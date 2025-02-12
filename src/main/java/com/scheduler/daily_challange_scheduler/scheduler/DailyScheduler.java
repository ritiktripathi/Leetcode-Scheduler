package com.scheduler.daily_challange_scheduler.scheduler;

import com.scheduler.daily_challange_scheduler.LeetCodeDailyProblem;
import com.scheduler.daily_challange_scheduler.service.LeetCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class DailyScheduler {

    private final RestTemplate restTemplate = new RestTemplate();

    @Lazy
    @Autowired
    LeetCodeService leetCodeService;

    @Value("${leetcode.session.token}")
    private String sessionToken;

    @Scheduled(cron = "0 6 * * *")
    public void fetchDailyProblem() {

        // Set headers for the request
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Cookie", "LEETCODE_SESSION=" + sessionToken);

        // Create the request body with the query
        String body = "{\n" +
                "    \"query\": \"\\n    query questionOfToday {\\n  activeDailyCodingChallengeQuestion {\\n    date\\n    link\\n    question {\\n      frontendQuestionId: questionFrontendId\\n      title\\n      titleSlug\\n      difficulty\\n    }\\n  }\\n}\",\n" +
                "    \"variables\": {}\n" +
                "}";

        // Create HttpEntity with headers and body
        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

        // Make the POST request
        String url = "https://leetcode.com/graphql/";
        ResponseEntity<LeetCodeDailyProblem> responseEntity = restTemplate.exchange(
                url, HttpMethod.POST, requestEntity, LeetCodeDailyProblem.class);

        // Process the response
        LeetCodeDailyProblem dailyProblemResponse = responseEntity.getBody();
        if (dailyProblemResponse != null && dailyProblemResponse.getData() != null) {
            LeetCodeDailyProblem.ActiveDailyCodingChallengeQuestion problem =
                    dailyProblemResponse.getData().getActiveDailyCodingChallengeQuestion();

            if (problem != null) {
                String date = problem.getDate();
                String link = problem.getLink();
                String questionTitle = problem.getQuestion().getTitle();
                String difficulty = problem.getQuestion().getDifficulty();

                leetCodeService.addDailyProblem(date, link, questionTitle, difficulty);

                log.info("Daily Problem added successfully");
            }
        } else {
            log.error("No data found for today's problem.");
        }
    }
}
