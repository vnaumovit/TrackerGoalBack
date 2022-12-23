package com.tracker.goals.model.entity.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "tasks")
@Getter
@AllArgsConstructor
@Builder()
@Setter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue()
    private UUID id;
    private String name;
    private Boolean isDone;
    private Timestamp createAt;
}
