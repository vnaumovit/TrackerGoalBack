package com.tracker.goals.repository.habit;

import com.tracker.goals.model.entity.habit.HabitCheckmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HabitCheckmarkRepo extends JpaRepository<HabitCheckmark, UUID> {
}
