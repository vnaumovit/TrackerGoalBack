package com.veilas.mapper;

import com.veilas.model.Item;
import com.veilas.model.Size;
import com.veilas.model.dto.SizeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SizeMapper {

    private final ItemMapper itemMapper;

    public List<Size> toEntities(List<SizeDto> sizeDtos) {
        return sizeDtos
                .stream()
                .map(s -> new Size(s.getId(), s.getSize(), s.getQuantity(),
                        Item.builder().id(s.getItemId()).build()
                ))
                .collect(Collectors.toList());
    }

    public List<SizeDto> toDtos(List<Size> sizes) {
        return sizes
                .stream()
                .map(s -> new SizeDto(s.getId(), s.getSize(), s.getQuantity(), s.getItem().getId()))
                .collect(Collectors.toList());
    }
}
