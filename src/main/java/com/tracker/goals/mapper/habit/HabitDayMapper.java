package com.tracker.goals.mapper.habit;

import com.tracker.goals.model.dto.habit.HabitDayDto;
import com.tracker.goals.model.entity.habit.HabitDay;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HabitDayMapper {

    @Mapping(source = "habit.id", target = "habitId")
    HabitDayDto toDto(HabitDay habitDay);

    @Mapping(source = "habitId", target = "habit.id")
    HabitDay toEntity(HabitDayDto habitDayDto);

    List<HabitDay> toEntities(List<HabitDayDto> habitDayDtos);
}
