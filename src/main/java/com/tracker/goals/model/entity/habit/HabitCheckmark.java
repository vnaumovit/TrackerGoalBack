package com.tracker.goals.model.entity.habit;

import com.tracker.goals.model.enums.habit.CheckmarkStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "habit_checkmarks")
@Getter
@AllArgsConstructor
@Builder()
@Setter
@NoArgsConstructor
public class HabitCheckmark {
    @Id
    @GeneratedValue()
    private UUID id;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private CheckmarkStatus status;
}
