package com.veilas.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusSale {
    NEW("Новый"),
    PURCHASE("Оплачен"),
    SENT("Отправлен"),
    Received("Получен");
    private final String value;
}
