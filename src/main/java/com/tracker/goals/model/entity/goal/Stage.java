package com.tracker.goals.model.entity.goal;

import com.tracker.goals.model.enums.goal.StageStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "stages")
@Getter
@AllArgsConstructor
@Builder()
@Setter
@NoArgsConstructor
public class Stage {

    @Id
    @GeneratedValue()
    private UUID id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private StageStatus status;
}
