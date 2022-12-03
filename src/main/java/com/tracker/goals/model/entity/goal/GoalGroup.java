package com.tracker.goals.model.entity.goal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "goal_groups")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoalGroup {
    @Id
    @GeneratedValue()
    private UUID id;
    private String name;
    private String description;
}
