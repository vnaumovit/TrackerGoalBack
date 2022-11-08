package com.sunday.jewelry.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemType {
    Кольцо("Кольцо"),
    Колье("Колье"),
    Браслет("Браслет"),
    Серьга("Серьга"),
    Шарм("Шарм");

    @JsonValue
    final String value;
}
