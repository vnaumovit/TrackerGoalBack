package com.tracker.goals.model.dto.habit;

import com.tracker.goals.model.enums.habit.CheckmarkStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder()
@Setter
public class HabitCheckmarkDto {
    private UUID id;
    private LocalDate date;
    private CheckmarkStatus status;
}
