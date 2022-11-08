package com.sunday.jewelry.model.dto;

import com.fasterxml.jackson.annotation.JsonKey;
import com.sunday.jewelry.model.enums.ItemType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ItemDto {

    private UUID id;

    @NotBlank(message = "Введите имя товара")
    private String name;

    private String code;

    @NotNull(message = "Выберите тип объекта")
    @JsonKey
    private ItemType itemType;

    @Min(value = 0, message = "Цена не может быть меньше 0 ")
    private Long retailPrice;

    @NotNull(message = "Выберите изображение")
    private ImageDto image;

    @NotNull
    private List<SizeDto> sizes;
    private Boolean isInStock;
}
