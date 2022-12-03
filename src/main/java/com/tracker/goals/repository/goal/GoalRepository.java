package com.tracker.goals.repository.goal;

import com.tracker.goals.model.entity.goal.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GoalRepository extends JpaRepository<Goal, UUID> {

}