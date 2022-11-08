package com.sunday.jewelry.controller;


import com.sunday.jewelry.model.dto.Filter;
import com.sunday.jewelry.model.dto.ItemDto;
import com.sunday.jewelry.model.dto.PageDto;
import com.sunday.jewelry.service.impl.ItemServiceImpl;
import lombok.RequiredArgsConstructor;
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
import org.springframework.web.bind.annotation.RequestParam;
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

    private final ItemServiceImpl itemService;

    @PostMapping()
    public ResponseEntity<List<ItemDto>> getAllItemsWithPageable(@RequestBody() PageDto pageDto) {
        List<ItemDto> items = itemService.getItems(pageDto);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getItemById(@PathVariable("id") UUID id) {
        ItemDto item = itemService.getItemById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<ItemDto>> getItemsLikeName(@RequestParam("name") String name, @RequestBody PageDto pageDto) {
        List<ItemDto> items = itemService.getItemsLikeName(name, pageDto);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PostMapping("/getByFilter")
    public ResponseEntity<List<ItemDto>> getAllItemsByFilter(@RequestBody Filter filter) {
        List<ItemDto> items = itemService.getItemsByFilter(filter);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> pageDelete(@PathVariable("id") UUID id) {
        itemService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addItem")
    @Transactional
    public ResponseEntity<ItemDto> createItem(@Valid @RequestBody ItemDto itemDto) {
        ItemDto item = itemService.save(itemDto);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PutMapping("/updateItem")
    public ResponseEntity<ItemDto> itemEdit(@Valid @RequestBody ItemDto itemDto) {
        ItemDto item = itemService.update(itemDto);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
