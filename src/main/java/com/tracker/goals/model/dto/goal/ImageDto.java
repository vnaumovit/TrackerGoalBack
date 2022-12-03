package com.tracker.goals.model.dto.goal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageDto {
    private Integer id;
    private String name;
    private byte[] picture;
    private Boolean isMain = false;
}
