package com.veilas.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.jackson.JsonComponent;

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
