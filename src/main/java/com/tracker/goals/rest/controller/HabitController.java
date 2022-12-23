package com.tracker.goals.rest.controller;

import com.tracker.goals.model.dto.PageDto;
import com.tracker.goals.model.dto.habit.HabitDayDto;
import com.tracker.goals.model.dto.habit.HabitDto;
import com.tracker.goals.model.entity.habit.HabitDay;
import com.tracker.goals.service.abst.habit.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("api/habit")
@CrossOrigin
public class HabitController {

    private final HabitService habitService;

    @GetMapping("/getAllThisWeek")
    public ResponseEntity<List<HabitDto>> getAllThisWeek(@RequestParam("pageNumber") Integer pageNumber,
                                                         @RequestParam("pageSize") Integer pageSize) {
        List<HabitDto> habits = habitService.calculateHabitsWithThisWeek(new PageDto(pageNumber, pageSize));
        return ResponseEntity.ok(habits);
    }

    @PostMapping("/saveHabit")
    public ResponseEntity<HabitDto> saveHabit(@RequestBody HabitDto habit) {
        return ResponseEntity.ok(habitService.save(habit));
    }

    @PostMapping("/updateHabitDays")
    public ResponseEntity<List<HabitDayDto>> updateHabitDays(@RequestBody List<HabitDayDto> habitDays) {
        return ResponseEntity.ok(habitService.updateHabitDays(habitDays));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam UUID id) {
        habitService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAll(@RequestParam Set<UUID> ids) {
        habitService.deleteByIds(ids);
        return ResponseEntity.ok().build();
    }
}
