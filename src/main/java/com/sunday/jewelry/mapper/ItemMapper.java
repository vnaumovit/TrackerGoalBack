package com.sunday.jewelry.mapper;

import com.sunday.jewelry.model.Item;
import com.sunday.jewelry.model.dto.ItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ItemMapper {
    private SizeMapper sizeMapper;
    private final ImageMapper imageMapper;

    @Lazy
    @Autowired
    public void setSizeMapper(SizeMapper sizeMapper) {
        this.sizeMapper = sizeMapper;
    }

    public Item toEntity(ItemDto itemDto) {
        return new Item(
                itemDto.getId(),
                itemDto.getName(),
                itemDto.getCode(),
                itemDto.getItemType(),
                itemDto.getRetailPrice(),
                imageMapper.toEntity(itemDto.getImage()),
                itemDto.getSizes() != null ? sizeMapper.toEntities(itemDto.getSizes()) : null,
                itemDto.getIsInStock()
        );
    }

    public ItemDto toDto(Item item) {
        return new ItemDto(
                item.getId(),
                item.getName(),
                item.getCode(),
                item.getItemType(),
                item.getRetailPrice(),
                imageMapper.toDto(item.getImage()),
                item.getSizes() != null ? sizeMapper.toDtos(item.getSizes()) : null,
                item.getIsInStock()
        );
    }

    public List<ItemDto> toDtos(List<Item> items) {
        return items
                .stream()
                .map(i -> new ItemDto(
                        i.getId(),
                        i.getName(),
                        i.getCode(),
                        i.getItemType(),
                        i.getRetailPrice(),
                        imageMapper.toDto(i.getImage()),
                        i.getSizes() != null ? sizeMapper.toDtos(i.getSizes()) : null,
                        i.getIsInStock()
                )).collect(Collectors.toList());
    }
}
