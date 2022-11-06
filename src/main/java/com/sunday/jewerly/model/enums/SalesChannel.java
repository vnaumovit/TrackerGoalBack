package com.sunday.jewerly.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SalesChannel {
    TARGET_INSTAGRAM("Таргет инстаграма"),
    INSTAGRAM("Инстаграм"),
    SHOP("Магазин"),
    OTHER("Другое");

    private final String value;
}
