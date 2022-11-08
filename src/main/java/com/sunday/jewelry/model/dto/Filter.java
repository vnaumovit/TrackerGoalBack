package com.sunday.jewelry.model.dto;

import com.sunday.jewelry.model.enums.ItemType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Filter {
    @NotNull
    PageDto pageDto ;
    ItemType itemType;
    Boolean isInStock;
    List<String> sizes;
}
