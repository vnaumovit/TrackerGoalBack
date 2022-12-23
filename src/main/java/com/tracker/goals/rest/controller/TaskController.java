package com.tracker.goals.rest.controller;

import com.tracker.goals.model.dto.PageDto;
import com.tracker.goals.model.entity.task.Task;
import com.tracker.goals.repository.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import static java.time.LocalDateTime.now;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("api/task")
@CrossOrigin
public class TaskController {

    private final TaskRepo taskRepo;

    @PostMapping("/getAll")
    public ResponseEntity<List<Task>> getTasks(@RequestBody PageDto pageDto) {
        PageRequest pageRequest = PageRequest.of(pageDto.getPageNumber(), pageDto.getPageSize());
        return ResponseEntity.ok(taskRepo.getAllThisDay(pageRequest));
    }

    @PostMapping("/saveTask")
    public ResponseEntity<Task> saveTask(@RequestBody Task task) {
        task.setCreateAt(Timestamp.valueOf(now()));
        return ResponseEntity.ok(taskRepo.save(task));
    }

    @PostMapping("/updateStatusTask")
    public ResponseEntity<Void> updateStatusTask(@RequestBody Task task) {
        taskRepo.updateStatusTask(task.getId(), task.getIsDone());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam UUID id) {
        taskRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
