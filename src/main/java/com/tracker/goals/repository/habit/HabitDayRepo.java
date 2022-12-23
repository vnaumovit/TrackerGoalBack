package com.tracker.goals.repository.habit;

import com.tracker.goals.model.entity.habit.HabitDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface HabitDayRepo extends JpaRepository<HabitDay, UUID> {

    @Query(value = """
    SELECT h.* FROM habit_days h
    WHERE DATE_TRUNC('week', h.date) = DATE_TRUNC('week', current_date)
    AND habit_id IN :habitIds
    """, nativeQuery = true)
    List<HabitDay> findAllByHabitIdsThisWeek(@Param("habitIds") List<UUID> habitIds);
}
