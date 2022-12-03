package com.tracker.goals.rest.controller;

import com.tracker.goals.model.dto.goal.GoalDto;
import com.tracker.goals.model.dto.goal.GoalGroupDto;
import com.tracker.goals.model.dto.PageDto;
import com.tracker.goals.service.abst.goal.GoalGroupService;
import com.tracker.goals.service.abst.goal.GoalService;
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
@RequestMapping("api/goal")
@CrossOrigin
public class GoalController {

    private final GoalService goalService;
    private final GoalGroupService goalGroupService;

    @PostMapping("/getGoals")
    public ResponseEntity<List<GoalDto>> getGoals(@RequestBody PageDto pageDto) {
        List<GoalDto> goals = goalService.findAllWithPageable(pageDto);
        return ResponseEntity.ok(goals);
    }

    @GetMapping("/getGoal")
    public ResponseEntity<GoalDto> getGoal(@RequestParam UUID id) {
        GoalDto goal = goalService.findById(id).orElseThrow(() ->
                new RuntimeException("Goal not found"));
        return ResponseEntity.ok(goal);
    }

    @PostMapping("/saveGoal")
    public ResponseEntity<GoalDto> saveGoal(@RequestBody GoalDto goalDto) {
        GoalDto goal = goalService.save(goalDto);
        return ResponseEntity.ok(goal);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam UUID id) {
        goalService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAll(@RequestParam Set<UUID> id) {
        goalService.deleteByIds(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getGoalGroups")
    public ResponseEntity<List<GoalGroupDto>> getGoalGroups() {
        return ResponseEntity.ok(goalGroupService.getGoalGroups());
    }
}
