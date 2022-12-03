package goals.mapper;

import com.tracker.goals.mapper.goal.GoalMapper;
import com.tracker.goals.mapper.GoalMapperImpl;
import com.tracker.goals.model.entity.goal.Goal;
import com.tracker.goals.model.entity.goal.GoalGroup;
import com.tracker.goals.model.dto.goal.GoalDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoalMapperTest {

    private GoalMapper goalMapper = new GoalMapperImpl();
    private static final UUID ID = UUID.randomUUID();
    private static final String NAME = "Name";
    private static final String DESCRIPTION = "Description";
    private static final LocalDateTime START_DATE = LocalDateTime.now();
    private static final LocalDateTime END_DATE = LocalDateTime.of(2022, 12, 1, 5, 6);
    private static final GoalGroup GOAL_TYPE = new GoalGroup();

    @Test
    public void shouldConvertEntityToDto() {
        GoalDto actual = goalMapper.toDto(getGoal());
        assertEquals(ID, actual.getId());
        assertEquals(NAME, actual.getName());
        assertEquals(DESCRIPTION, actual.getDescription());
        assertEquals(START_DATE, actual.getStartDate());
        assertEquals(END_DATE, actual.getEndDate());
        assertEquals(GOAL_TYPE, actual.getGoalGroup());
    }

    @Test
    public void shouldConvertDtoToEntity() {
        Goal actual = goalMapper.toEntity(getGoalDto());
        assertEquals(ID, actual.getId());
        assertEquals(NAME, actual.getName());
        assertEquals(DESCRIPTION, actual.getDescription());
        assertEquals(START_DATE, actual.getStartDate());
        assertEquals(END_DATE, actual.getEndDate());
        assertEquals(GOAL_TYPE, actual.getGoalGroup());
    }

    private Goal getGoal() {
        return Goal.builder()
                   .id(ID)
                   .name(NAME)
                   .description(DESCRIPTION)
                   .startDate(START_DATE)
                   .endDate(END_DATE)
                   .goalGroup(GOAL_TYPE).build();
    }

    private GoalDto getGoalDto() {
        return GoalDto.builder()
                      .id(ID)
                      .name(NAME)
                      .description(DESCRIPTION)
                      .startDate(START_DATE)
                      .endDate(END_DATE)
                      .goalGroup(GOAL_TYPE).build();
    }
}
