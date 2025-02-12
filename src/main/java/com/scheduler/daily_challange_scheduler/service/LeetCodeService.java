package com.scheduler.daily_challange_scheduler.service;


import com.scheduler.daily_challange_scheduler.entity.DailyProblem;
import com.scheduler.daily_challange_scheduler.model.Response;
import com.scheduler.daily_challange_scheduler.repository.LeetCodeRepository;
import com.scheduler.daily_challange_scheduler.scheduler.DailyScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LeetCodeService {

    @Autowired
    private LeetCodeRepository leetCodeRepository;

    @Autowired
    DailyScheduler dailyScheduler;

    public void addDailyProblem() {

        dailyScheduler.fetchDailyProblem();
    }

    public void addDailyProblem(String date, String link, String question, String diff) {

        leetCodeRepository.save(toEntity(date, link, question, diff));
    }

    public Response getTodayProblem() {

        LocalDate today = LocalDate.now();

        DailyProblem dailyProblem = leetCodeRepository.findById(today).orElse(null);
        return toResponse(dailyProblem);
    }

    private Response toResponse(DailyProblem dailyProblem) {

        if(dailyProblem == null) return null;
        Response response = new Response();
        response.setDate(dailyProblem.getDate().toString());
        response.setDifficulty(dailyProblem.getDifficulty());
        response.setQuestion(dailyProblem.getQuestion());
        response.setLink(dailyProblem.getLink());

        return response;
    }

    private DailyProblem toEntity(String date, String link, String question, String diff) {

        DailyProblem dailyProblem = new DailyProblem();
        dailyProblem.setDate(LocalDate.parse(date));
        dailyProblem.setLink(link);
        dailyProblem.setQuestion(question);
        dailyProblem.setDifficulty(diff);

        return dailyProblem;
    }
}
