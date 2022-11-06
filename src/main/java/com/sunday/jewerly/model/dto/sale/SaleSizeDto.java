package com.sunday.jewerly.model.dto.sale;

import com.sunday.jewerly.model.sale.SaleItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class SaleSizeDto {
    private UUID id;

    private Float size;

    private Integer quantity;

    private SaleItem saleItem;
}
