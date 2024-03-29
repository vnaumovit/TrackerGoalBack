package com.tracker.goals.model.dto.habit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder()
@Setter
public class HabitDto {
    private UUID id;
    private String name;
    private Timestamp createAt;
    @OneToMany
    private List<HabitDayDto> habitDays;
}
