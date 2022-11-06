package com.sunday.jewelry.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemType {
    Ring("Кольцо"),
    Necklace("Колье"),
    Bracelet("Браслет"),
    Earring("Серьга"),
    Charm("Шарм");

    @JsonValue
    final String value;
}
