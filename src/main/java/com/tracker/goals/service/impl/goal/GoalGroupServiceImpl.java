package com.tracker.goals.service.impl.goal;

import com.tracker.goals.mapper.goal.GoalGroupMapper;
import com.tracker.goals.model.dto.goal.GoalGroupDto;
import com.tracker.goals.repository.goal.GoalGroupRepository;
import com.tracker.goals.service.abst.goal.GoalGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GoalGroupServiceImpl implements GoalGroupService {

    private final GoalGroupRepository goalGroupRepository;
    private final GoalGroupMapper goalGroupMapper;

    @Override
    public List<GoalGroupDto> getGoalGroups() {
        return goalGroupMapper.toDtos(goalGroupRepository.findAll());
    }
}
