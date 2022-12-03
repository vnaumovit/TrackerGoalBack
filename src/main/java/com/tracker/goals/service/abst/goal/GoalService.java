package com.tracker.goals.service.abst.goal;

import com.tracker.goals.model.dto.goal.GoalDto;
import com.tracker.goals.model.dto.PageDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface GoalService {

    List<GoalDto> findAllWithPageable(PageDto pageDto);

    Optional<GoalDto> findById(UUID id);

    GoalDto save(GoalDto goalDto);

    void deleteById(UUID id);

    void deleteByIds(Set<UUID> ids);

}
