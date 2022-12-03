package com.tracker.goals.repository.goal;

import com.tracker.goals.model.entity.goal.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image,UUID> {

}