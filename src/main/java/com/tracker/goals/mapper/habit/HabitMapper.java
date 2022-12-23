package com.tracker.goals.mapper.habit;

import com.tracker.goals.model.dto.habit.HabitDto;
import com.tracker.goals.model.entity.habit.Habit;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = HabitDayMapper.class)
public interface HabitMapper {

    HabitDto toDto(Habit habit);
    Habit toEntity(HabitDto habitDto);

    List<HabitDto> toDtos(List<Habit> habits);

    List<Habit> toEntities(List<HabitDto> habitDtos);
}
