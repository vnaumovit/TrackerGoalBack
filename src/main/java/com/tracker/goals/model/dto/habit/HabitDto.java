package com.tracker.goals.model.dto.habit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder()
@Setter
public class HabitDto {
    private UUID id;
    private String name;
    private String description;
    @OneToMany
    private List<HabitCheckmarkDto> habitCheckmarks;
}
