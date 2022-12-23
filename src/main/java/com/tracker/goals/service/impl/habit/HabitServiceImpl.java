package com.tracker.goals.service.impl.habit;

import com.tracker.goals.mapper.habit.HabitDayMapper;
import com.tracker.goals.mapper.habit.HabitMapper;
import com.tracker.goals.model.dto.PageDto;
import com.tracker.goals.model.dto.habit.HabitDayDto;
import com.tracker.goals.model.dto.habit.HabitDto;
import com.tracker.goals.model.entity.habit.Habit;
import com.tracker.goals.model.entity.habit.HabitDay;
import com.tracker.goals.repository.habit.HabitDayRepo;
import com.tracker.goals.repository.habit.HabitRepo;
import com.tracker.goals.service.abst.habit.HabitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static java.time.LocalDate.now;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class HabitServiceImpl implements HabitService {

    public static final TemporalAdjuster MONDAY_THIS_WEEK = TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY);
    private final HabitRepo habitRepo;
    private final HabitDayRepo habitDayRepo;
    private final HabitMapper mapper;
    private final HabitDayMapper habitDayMapper;

    @Override
    @Transactional
    public List<HabitDto> calculateHabitsWithThisWeek(PageDto pageDto) {
        PageRequest pageRequest = PageRequest.of(pageDto.getPageNumber(), pageDto.getPageSize());
        List<Habit> habits = habitRepo.findAll(pageRequest).getContent();
        List<HabitDay> habitDays = new ArrayList<>();
        LocalDate date = now().with(MONDAY_THIS_WEEK);
        habits.forEach(h -> {
            List<HabitDay> currentDays = h.getHabitDays();
            if (isNull(currentDays) ||
                currentDays.stream().noneMatch(d -> d.getDate().with(MONDAY_THIS_WEEK).equals(date))) {
                List<HabitDay> week = calculateThisWeek(h);
                h.setHabitDays(week);
                habitDays.addAll(week);
            }
        });
        habitDayRepo.saveAll(habitDays);
        habitRepo.saveAll(habits);
        habits.forEach(h -> h.setHabitDays(getHabitDaysThisWeek(date, h.getHabitDays())));
        return mapper.toDtos(habits);
    }

    @Override
    public List<HabitDayDto> updateHabitDays(List<HabitDayDto> habitDays) {
        habitDayRepo.saveAll(habitDayMapper.toEntities(habitDays));
        return habitDays;
    }

    @Override
    @Transactional
    public HabitDto save(HabitDto habitDto) {
        Habit habit = mapper.toEntity(habitDto);
        habit.setCreateAt(Timestamp.valueOf(LocalDateTime.now()));
        List<HabitDay> habitDays = calculateThisWeek(habit);
        habitDayRepo.saveAll(habitDays);
        habit.setHabitDays(habitDays);
        habitRepo.save(habit);
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

    private List<HabitDay> calculateThisWeek(Habit habit) {
        LocalDate date = now().with(MONDAY_THIS_WEEK);
        List<HabitDay> habitDays = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            habitDays.add(HabitDay.newHabitDay(habit, date));
            date = date.plusDays(1);
        }
        return habitDays;
    }

    private List<HabitDay> getHabitDaysThisWeek(LocalDate date, List<HabitDay> habitDays) {
        return habitDays.stream().filter(hd -> hd.getDate().with(MONDAY_THIS_WEEK).equals(date))
                        .sorted(Comparator.comparing(HabitDay::getDate)).toList();
    }
}
