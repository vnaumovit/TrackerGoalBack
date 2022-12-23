package goals.mapper;

import com.tracker.goals.mapper.habit.HabitDayMapper;
import com.tracker.goals.mapper.habit.HabitDayMapperImpl;
import com.tracker.goals.model.dto.habit.HabitDayDto;
import com.tracker.goals.model.entity.habit.Habit;
import com.tracker.goals.model.entity.habit.HabitDay;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static java.time.LocalDate.now;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HabitDayMapperTest {

    public static final UUID HABIT_ID = UUID.randomUUID();
    private HabitDayMapper habitDayMapper = new HabitDayMapperImpl();
    private static final UUID ID = HABIT_ID;
    private static final Boolean STATUS = false;
    private static final LocalDate DATE = now();
    private static final Habit HABIT = Habit.builder().id(HABIT_ID).build();

    @Test
    public void shouldConvertEntityToDto() {
        HabitDayDto actual = habitDayMapper.toDto(getHabitDay());
        assertEquals(ID, actual.getId());
        assertEquals(STATUS, actual.getStatus());
        assertEquals(DATE, actual.getDate());
        assertEquals(HABIT_ID, actual.getHabitId());
    }

    @Test
    public void shouldConvertDtoToEntity() {
        HabitDay actual = habitDayMapper.toEntity(getHabitDayDto());
        assertEquals(ID, actual.getId());
        assertEquals(STATUS, actual.getStatus());
        assertEquals(DATE, actual.getDate());
        assertEquals(HABIT_ID, actual.getHabit().getId());
    }

    private HabitDay getHabitDay() {
        return HabitDay.builder()
                       .id(ID)
                       .date(DATE)
                       .habit(HABIT)
                       .status(STATUS)
                       .build();
    }

    private HabitDayDto getHabitDayDto() {
        return HabitDayDto.builder()
                          .id(ID)
                          .date(DATE)
                          .habitId(HABIT_ID)
                          .status(STATUS)
                          .build();
    }
}
