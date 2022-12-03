package com.tracker.goals.model.enums.goal;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StageStatus {
    CREATED("Создан"),
    FINISHED("Завершен");

    @JsonValue
    private final String value;
}
