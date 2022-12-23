package com.tracker.goals.model.entity.habit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.REMOVE;

@Entity
@Table(name = "habits")
@Getter
@AllArgsConstructor
@Builder()
@Setter
@NoArgsConstructor
public class Habit {
    @Id
    @GeneratedValue()
    private UUID id;
    private String name;
    private Timestamp createAt;

    @OneToMany(fetch = FetchType.LAZY,  cascade = REMOVE, mappedBy = "habit")
    private List<HabitDay> habitDays;
}
