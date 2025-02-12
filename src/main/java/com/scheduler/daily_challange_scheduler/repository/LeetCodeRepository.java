package com.scheduler.daily_challange_scheduler.repository;

import com.scheduler.daily_challange_scheduler.entity.DailyProblem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface LeetCodeRepository extends JpaRepository<DailyProblem, LocalDate> {
}
