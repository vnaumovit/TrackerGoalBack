package com.sunday.jewelry.service.abst;

import com.sunday.jewelry.model.Item;
import com.sunday.jewelry.model.dto.Filter;
import com.sunday.jewelry.model.dto.ItemDto;
import com.sunday.jewelry.model.dto.PageDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ItemService {

    List<ItemDto> getItems(PageDto pageDto);

    ItemDto getItemById(UUID id);

    List<ItemDto> getItemsLikeName(String name, PageDto pageDto);

    List<ItemDto> getItemsByFilter(Filter filter);

    ItemDto save(ItemDto item);

    void deleteById(UUID id);
}
