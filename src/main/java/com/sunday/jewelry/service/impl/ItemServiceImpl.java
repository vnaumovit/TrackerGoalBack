package com.sunday.jewelry.service.impl;

import com.sunday.jewelry.exception.ItemException;
import com.sunday.jewelry.mapper.ItemMapper;
import com.sunday.jewelry.model.Image;
import com.sunday.jewelry.model.Item;
import com.sunday.jewelry.model.Size;
import com.sunday.jewelry.model.dto.Filter;
import com.sunday.jewelry.model.dto.ItemDto;
import com.sunday.jewelry.model.dto.PageDto;
import com.sunday.jewelry.repository.ImageRepository;
import com.sunday.jewelry.repository.ItemRepository;
import com.sunday.jewelry.repository.SizeRepository;
import com.sunday.jewelry.service.abst.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    public static final String ITEM_NOT_FOUND = "Item с %s id не найден";
    private final ItemRepository itemRepository;
    private final SizeRepository sizeRepository;
    private final ImageRepository imageRepository;
    private final ItemMapper itemMapper;

    @Override
    public List<ItemDto> getItems(PageDto pageDto) {
        PageRequest paging = PageRequest.of(pageDto.getPageNumber(), pageDto.getPageSize());
        List<Item> items = itemRepository.findAllWithPageable(paging);
        return itemMapper.toDtos(items);
    }

    @Override
    public ItemDto getItemById(UUID id) {
        Item item = itemRepository.findById(id).orElseThrow(
                () -> new ItemException(String.format(ITEM_NOT_FOUND, id))
        );
        return itemMapper.toDto(item);
    }

    @Override
    public List<ItemDto> getItemsLikeName(String name, PageDto pageDto) {
        PageRequest paging = PageRequest.of(pageDto.getPageNumber(), pageDto.getPageSize());
        List<Item> items = itemRepository.findItemsLikeName(name.toLowerCase(), paging);
        return itemMapper.toDtos(items);
    }

    @Override
    public List<ItemDto> getItemsByFilter(Filter filter) {
        List<Item> items;
        PageRequest paging = PageRequest.of(filter.getPageDto().getPageNumber(), filter.getPageDto().getPageSize());
        List<Float> sizes = filter.getSizes().stream().map(Float::parseFloat).toList();
        boolean isInStock = sizes.stream().anyMatch(s -> s > 0);
        if (isInStock) {
            items = itemRepository.findAllFilterWithSizes(filter.getItemType(), sizes, paging);
        } else {
            items = itemRepository.findAllByFilter(filter.getItemType(), filter.getIsInStock(), paging);
        }
        return itemMapper.toDtos(items);
    }

    @Transactional
    public ItemDto save(ItemDto itemDto) {
        Item item = itemMapper.toEntity(itemDto);
        item.setCode(generateCode());
        saveOrUpdate(item);
        return itemMapper.toDto(itemRepository.save(item));
    }

    @Transactional
    public ItemDto update(ItemDto itemDto) {
        Item item = itemMapper.toEntity(itemDto);
        saveOrUpdate(item);
        return itemMapper.toDto(itemRepository.save(item));
    }

    private void saveOrUpdate(Item item) {
        List<Size> oldSizes = sizeRepository.findByItemId(item.getId());
        List<Size> sizes = item.getSizes();
        item.setIsInStock(!sizes.isEmpty());
        oldSizes.removeAll(sizes);
        Image image = item.getImage();
        image.setItem(item);
        sizes.forEach(s -> s.setItem(item));
        sizeRepository.deleteAll(oldSizes);
        sizeRepository.saveAll(sizes);
        imageRepository.save(image);
        item.setSizes(sizes);
        item.setImage(image);
    }

    public String generateCode() {
        String code = itemRepository.getLastCode().orElse(null);
        if (nonNull(code)) {
            int num = Integer.parseInt(code.substring(3)) + 1;
            if (num < 10) {
                code = "000" + num + "00";
            } else if (num < 100) {
                code = "000" + num + "0";
            } else {
                code = "000" + num;
            }
            return code;
        } else {
            return "000100";
        }
    }

    public void deleteById(UUID id) {
        itemRepository.deleteById(id);
    }
}
