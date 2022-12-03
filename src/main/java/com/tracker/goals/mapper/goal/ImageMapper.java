package com.tracker.goals.mapper.goal;

import com.tracker.goals.model.entity.goal.Image;
import com.tracker.goals.model.dto.goal.ImageDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    Image toEntity(ImageDto imageDto);

    ImageDto toDto(Image image);
}
