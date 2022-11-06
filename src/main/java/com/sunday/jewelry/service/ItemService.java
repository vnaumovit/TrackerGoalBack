package com.sunday.jewelry.service;

import com.sunday.jewelry.exception.ItemException;
import com.sunday.jewelry.model.Item;
import com.sunday.jewelry.repository.ImageRepository;
import com.sunday.jewelry.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemService {

    public static final String ITEM_NOT_FOUND = "Item с %s id не найден";
    private final ItemRepository itemRepository;
    private final ImageRepository imageRepository;

    public List<Item> getItems(Pageable pageable) {
        return itemRepository.findAllWithPageable(pageable);
    }

    public Item getItemById(UUID id) {
        return itemRepository.findById(id).orElseThrow(
                () -> new ItemException(String.format(ITEM_NOT_FOUND, id))
        );
    }


    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public String generateCode() {
        String code = itemRepository.getLastCode().orElse(null);
        if (code != null) {
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
