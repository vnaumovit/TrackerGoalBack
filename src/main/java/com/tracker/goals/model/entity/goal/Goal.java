package com.tracker.goals.model.entity.goal;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "goals")
@Getter
@AllArgsConstructor
@Builder()
@Setter
@NoArgsConstructor
public class Goal {
    @Id
    @GeneratedValue()
    private UUID id;
    private String title;
    private String finishTitle;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private GoalStatus status;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_id")
    private List<Stage> stages = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private GoalGroup goalGroup;
}
