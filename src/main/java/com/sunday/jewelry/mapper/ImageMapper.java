package com.sunday.jewelry.mapper;

import com.sunday.jewelry.model.Image;
import com.sunday.jewelry.model.Item;
import com.sunday.jewelry.model.dto.ImageDto;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {

    public Image toEntity(ImageDto imageDto) {
        return new Image(
                imageDto.getId(),
                imageDto.getUrl(),
                imageDto.getName(),
                imageDto.getPicture(),
                imageDto.getIsMain(),
                new Item().builder().id(imageDto.getItemId()).build()
        );
    }

    public ImageDto toDto(Image image) {
        return new ImageDto(
                image.getId(),
                image.getUrl(),
                image.getName(),
                image.getPicture(),
                image.getIsMain(),
                image.getItem().getId()
        );
    }
}
