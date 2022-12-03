package com.tracker.goals.mapper.habit;

import com.tracker.goals.model.dto.habit.HabitDto;
import com.tracker.goals.model.entity.habit.Habit;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = HabitCheckmarkMapper.class)
public interface HabitMapper {

    HabitDto toDto(Habit habit);

    List<HabitDto> toDtos(List<Habit> habits);

    Habit toEntity(HabitDto habitDto);
}
