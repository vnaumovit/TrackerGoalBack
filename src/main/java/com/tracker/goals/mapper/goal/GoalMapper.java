package com.tracker.goals.mapper.goal;

import com.tracker.goals.model.entity.goal.Goal;
import com.tracker.goals.model.dto.goal.GoalDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GoalGroupMapper.class, StageMapper.class})
public interface GoalMapper {

    GoalDto toDto(Goal goal);

    Goal toEntity(GoalDto goalDto);

    List<GoalDto> toDtos(List<Goal> goals);

}
