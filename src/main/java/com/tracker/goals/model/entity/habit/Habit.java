package com.tracker.goals.model.entity.habit;

import com.tracker.goals.model.enums.goal.GoalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "habits")
@Getter
@AllArgsConstructor
@Builder()
@Setter
@NoArgsConstructor
public class Habit {
    @Id
    @GeneratedValue()
    private UUID id;
    private String name;
    private String description;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "habit_id")
    private List<HabitCheckmark> habitCheckmarks;
}
