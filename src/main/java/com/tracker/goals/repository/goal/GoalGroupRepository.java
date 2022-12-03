package com.tracker.goals.repository.goal;

import com.tracker.goals.model.entity.goal.GoalGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GoalGroupRepository extends JpaRepository<GoalGroup, UUID> {
}
