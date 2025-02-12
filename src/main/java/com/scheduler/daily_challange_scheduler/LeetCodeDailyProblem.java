package com.scheduler.daily_challange_scheduler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeetCodeDailyProblem {

    private Data data;

    @Getter
    @Setter
    public static class Data {
        private ActiveDailyCodingChallengeQuestion activeDailyCodingChallengeQuestion;
    }

    @Getter
    @Setter
    public static class ActiveDailyCodingChallengeQuestion {
        private String date;
        private String link;
        private Question question;
    }

    @Getter
    @Setter
    public static class Question {
        private String frontendQuestionId;
        private String title;
        private String titleSlug;
        private String difficulty;
    }
}
