package com.exadel.sandbox.repository.statistic;

import com.exadel.sandbox.model.EventStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventStatisticRepository extends JpaRepository<EventStatistic, Long> {
}
