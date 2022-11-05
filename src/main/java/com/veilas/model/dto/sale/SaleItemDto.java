package com.veilas.model.dto.sale;

import com.veilas.model.sale.SaleSize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class SaleItemDto {
    private UUID id;

    private String name;

    private List<SaleSizeDto> saleSize;
}
