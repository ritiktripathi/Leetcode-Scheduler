package com.scheduler.daily_challange_scheduler.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private String Date;
    private String link;
    private String difficulty;
    private String question;

    public void setLink (String link) {
        if (link != null)
            this.link = "https://leetcode.com/" + link;
    }
}
