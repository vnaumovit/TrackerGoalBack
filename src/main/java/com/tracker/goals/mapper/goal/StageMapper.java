package com.tracker.goals.mapper.goal;

import com.tracker.goals.model.entity.goal.Stage;
import com.tracker.goals.model.dto.goal.StageDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StageMapper {

    Stage toEntity(StageDto stageDto);

    StageDto toDto(Stage stage);
}
