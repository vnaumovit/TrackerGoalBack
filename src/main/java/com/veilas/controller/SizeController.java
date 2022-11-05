package com.veilas.controller;

import com.veilas.mapper.SizeMapper;
import com.veilas.model.Size;
import com.veilas.model.dto.SizeDto;
import com.veilas.repository.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sizes")
public class SizeController {

    private final SizeMapper sizeMapper;
    private final SizeRepository sizeRepository;

    @GetMapping("/getByItemId/{itemId}")
    public ResponseEntity<List<SizeDto>> getSizesByItemId(@PathVariable UUID itemId) {
        List<Size> sizes = sizeRepository.findByItemId(itemId);
        List<SizeDto> sizeDtos = sizeMapper.toDtos(sizes);
        return new ResponseEntity<>(sizeDtos, HttpStatus.OK);
    }
}
