package com.scheduler.daily_challange_scheduler.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "DAILY_PROBLEM")
@Getter
@Setter
public class DailyProblem {

    @Id
    @Column(name = "date", unique = true)  // Make date the primary key
    private LocalDate date;  // Use LocalDate for the date

    private String question;
    private String difficulty;
    private String link;
}
