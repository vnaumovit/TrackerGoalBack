package com.tracker.goals.model.dto.habit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder()
@Setter
public class HabitDayDto {
    private UUID id;
    private LocalDate date;
    private Boolean status;
    private UUID habitId;
}
