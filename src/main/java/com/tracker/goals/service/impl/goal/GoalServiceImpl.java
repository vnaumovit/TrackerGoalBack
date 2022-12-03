package com.tracker.goals.service.impl.goal;

import com.tracker.goals.mapper.goal.GoalMapper;
import com.tracker.goals.model.dto.PageDto;
import com.tracker.goals.model.dto.goal.GoalDto;
import com.tracker.goals.model.entity.goal.Goal;
import com.tracker.goals.model.entity.goal.Stage;
import com.tracker.goals.repository.goal.GoalGroupRepository;
import com.tracker.goals.repository.goal.GoalRepository;
import com.tracker.goals.repository.goal.StageRepo;
import com.tracker.goals.service.abst.goal.GoalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static java.time.LocalDate.now;
import static org.thymeleaf.util.ListUtils.isEmpty;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;
    private final GoalGroupRepository goalGroupRepository;
    private final StageRepo stageRepo;
    private final GoalMapper goalMapper;

    @Override
    public List<GoalDto> findAllWithPageable(PageDto pageDto) {
        PageRequest pageRequest = PageRequest.of(pageDto.getPageNumber(), pageDto.getPageSize());
        Page<Goal> goals = goalRepository.findAll(pageRequest);
        log.info("Get all goals with Pageable {}", goals);
        return goalMapper.toDtos(goals.getContent());
    }

    @Override
    public Optional<GoalDto> findById(UUID id) {
        log.info("Get goal with id {}", id);
        Goal goal = goalRepository.findById(id).orElse(null);
        return Optional.of(goalMapper.toDto(goal));
    }

    @Override
    public GoalDto save(GoalDto goalDto) {
        log.info("Save goal {}", goalDto);
        Goal goal = goalMapper.toEntity(goalDto);
        List<Stage> stages = goal.getStages();
        if (!isEmpty(stages)) {
            stages.forEach(s -> s.setStartDate(now()));
            goal.setStages(stageRepo.saveAll(stages));
        }
        goal.setStartDate(now());
        goalRepository.save(goal);
        return goalMapper.toDto(goal);
    }

    @Override
    public void deleteById(UUID id) {
        log.info("Delete goal with id {}", id);
        goalRepository.deleteById(id);
    }

    @Override
    public void deleteByIds(Set<UUID> ids) {
        log.info("Delete goals with ids {}", ids);
        goalRepository.deleteAllById(ids);
    }

}
