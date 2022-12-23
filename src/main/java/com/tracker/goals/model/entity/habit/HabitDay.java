package com.tracker.goals.model.entity.habit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "habit_days")
@Getter
@AllArgsConstructor
@Builder()
@Setter
@NoArgsConstructor
public class HabitDay {
    @Id
    @GeneratedValue
    private UUID id;
    private LocalDate date;
    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habit_id")
    private Habit habit;

    public static HabitDay newHabitDay(Habit habit, LocalDate date) {
        return HabitDay.builder()
                .habit(habit)
                .date(date)
                .status(false)
                .build();
    }
}
