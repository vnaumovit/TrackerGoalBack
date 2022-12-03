package com.tracker.goals.mapper.goal;

import com.tracker.goals.model.entity.goal.GoalGroup;
import com.tracker.goals.model.dto.goal.GoalGroupDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GoalGroupMapper {

    GoalGroupDto toDto(GoalGroup goalGroup);

    GoalGroup toEntity(GoalGroupDto goalGroupDto);

    List<GoalGroupDto> toDtos(List<GoalGroup> goalGroups);
}
