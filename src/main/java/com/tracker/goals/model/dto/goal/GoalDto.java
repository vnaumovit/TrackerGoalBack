package com.tracker.goals.model.dto.goal;

import com.tracker.goals.model.enums.goal.GoalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoalDto {
    private UUID id;
    private String title;
    private String finishTitle;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private GoalStatus status;
    private List<StageDto> stages;
    private GoalGroupDto goalGroup;
}
