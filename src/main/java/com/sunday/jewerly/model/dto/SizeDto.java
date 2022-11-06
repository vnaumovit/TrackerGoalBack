package com.sunday.jewerly.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class SizeDto {
    private UUID id;
    @Min(0)
    @NotNull
    private Float size;
    @Min(0)
    @NotNull
    private Integer quantity;
    private UUID itemId;
}