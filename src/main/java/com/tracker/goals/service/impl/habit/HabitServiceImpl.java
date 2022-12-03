package com.tracker.goals.service.impl.habit;

import com.tracker.goals.mapper.habit.HabitMapper;
import com.tracker.goals.model.dto.PageDto;
import com.tracker.goals.model.dto.habit.HabitDto;
import com.tracker.goals.model.entity.habit.Habit;
import com.tracker.goals.repository.habit.HabitCheckmarkRepo;
import com.tracker.goals.repository.habit.HabitRepo;
import com.tracker.goals.service.abst.habit.HabitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class HabitServiceImpl implements HabitService {

    private final HabitRepo habitRepo;
    private final HabitCheckmarkRepo habitCheckmarkRepo;
    private final HabitMapper mapper;

    @Override
    public List<HabitDto> findAllWithPageable(PageDto pageDto) {
        PageRequest pageRequest = PageRequest.of(pageDto.getPageNumber(), pageDto.getPageSize());
        Page<Habit> habits = habitRepo.findAll(pageRequest);
        return mapper.toDtos(habits.getContent());
    }

    @Override
    public Optional<HabitDto> findById(UUID id) {
        Habit habit = habitRepo.findById(id).orElse(null);
        return isNull(habit) ? Optional.empty() : Optional.of(mapper.toDto(habit));
    }

    @Override
    public HabitDto save(HabitDto habitDto) {
        Habit habit = mapper.toEntity(habitDto);
        habitCheckmarkRepo.saveAll(habit.getHabitCheckmarks());
        return mapper.toDto(habit);
    }

    @Override
    public void deleteById(UUID id) {
        habitRepo.deleteById(id);
    }

    @Override
    public void deleteByIds(Set<UUID> ids) {
        habitRepo.deleteAllById(ids);
    }
}
