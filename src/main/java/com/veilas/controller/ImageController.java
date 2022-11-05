package com.veilas.controller;


import com.veilas.exception.ImageNotFoundException;
import com.veilas.mapper.ImageMapper;
import com.veilas.model.Image;
import com.veilas.model.dto.ImageDto;
import com.veilas.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/images")
public class ImageController {

    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    @GetMapping("/byItemId/{id}")
    public ResponseEntity<ImageDto> getImageByItemId(@PathVariable UUID id) {
        Image image = imageRepository
                .findByItemId(id)
                .orElseThrow(() -> new ImageNotFoundException("Фото с itemId " + id + " не найдено"));
        ImageDto imageDto = imageMapper.toDto(image);
        return ResponseEntity.ok(imageDto);
    }
}
