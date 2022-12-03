package com.tracker.goals.repository.goal;

import com.tracker.goals.model.entity.goal.Stage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StageRepo extends JpaRepository<Stage, UUID> {
}
