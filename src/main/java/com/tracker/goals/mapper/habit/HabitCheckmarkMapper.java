package com.tracker.goals.mapper.habit;

import com.tracker.goals.model.dto.habit.HabitCheckmarkDto;
import com.tracker.goals.model.entity.habit.HabitCheckmark;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HabitCheckmarkMapper {

    HabitCheckmarkDto toDto(HabitCheckmark habitCheckmark);

    HabitCheckmark toEntity(HabitCheckmarkDto habitCheckmarkDto);
}
