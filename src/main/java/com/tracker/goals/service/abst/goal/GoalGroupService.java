package com.tracker.goals.service.abst.goal;

import com.tracker.goals.model.dto.goal.GoalGroupDto;

import java.util.List;

public interface GoalGroupService {

    List<GoalGroupDto> getGoalGroups();
}
