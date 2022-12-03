package com.tracker.goals.model.enums.goal;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GoalStatus {
    CREATED("Создана"),
    STARTED("Начата"),
    PAUSED("Приостановлена"),
    SUCCESSFUL("Успех"),
    FAILURE("Провал");

    @JsonValue
    private final String value;
}
