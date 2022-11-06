package com.sunday.jewerly.controller;


import com.sunday.jewerly.mapper.ItemMapper;
import com.sunday.jewerly.model.Image;
import com.sunday.jewerly.model.Item;
import com.sunday.jewerly.model.Size;
import com.sunday.jewerly.model.dto.ItemDto;
import com.sunday.jewerly.model.dto.PageDto;
import com.sunday.jewerly.repository.ImageRepository;
import com.sunday.jewerly.repository.SizeRepository;
import com.sunday.jewerly.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/items")
@RequiredArgsConstructor
@Validated
public class ItemController {

    private final ItemService itemService;
    private final SizeRepository sizeRepository;
    private final ImageRepository imageRepository;
    private final ItemMapper itemMapper;

    @PostMapping()
    public ResponseEntity<List<ItemDto>> getAllItemsWithPageable(@RequestBody() PageDto pageDto) {
        PageRequest paging = PageRequest.of(pageDto.getPageNumber(), pageDto.getPageSize());
        List<Item> items = itemService.getItems(paging);
        List<ItemDto> itemDtos = itemMapper.toDtos(items);
        return new ResponseEntity<>(itemDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getItemById(@PathVariable("id") UUID id) {
        Item item = itemService.getItemById(id);
        ItemDto itemDto = itemMapper.toDto(item);
        return new ResponseEntity<>(itemDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> pageDelete(@PathVariable("id") UUID id) {
        itemService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addItem")
    @Transactional
    public ResponseEntity<Item> createItem(@Valid @RequestBody ItemDto itemDto) {
        Item item = itemMapper.toEntity(itemDto);
        item.setCode(itemService.generateCode());
        saveOrUpdate(item);
        itemService.save(item);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/updateItem")
    public ResponseEntity<ItemDto> itemEdit(@Valid @RequestBody ItemDto itemDto) {
        Item item = itemMapper.toEntity(itemDto);
        saveOrUpdate(item);
        itemService.save(item);
        return new ResponseEntity<>(itemDto, HttpStatus.OK);
    }

    private void saveOrUpdate(Item item) {
        List<Size> oldSizes = sizeRepository.findByItemId(item.getId());
        List<Size> sizes = item.getSizes();
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
}
