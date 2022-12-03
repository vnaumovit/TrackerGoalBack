package com.tracker.goals.repository.habit;

import com.tracker.goals.model.entity.habit.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HabitRepo extends JpaRepository<Habit, UUID> {
}
