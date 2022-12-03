package com.tracker.goals.service.abst.habit;

import com.tracker.goals.model.dto.PageDto;
import com.tracker.goals.model.dto.habit.HabitDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface HabitService {

    List<HabitDto> findAllWithPageable(PageDto pageDto);

    Optional<HabitDto> findById(UUID id);

    HabitDto save(HabitDto goalDto);

    void deleteById(UUID id);

    void deleteByIds(Set<UUID> ids);

}
