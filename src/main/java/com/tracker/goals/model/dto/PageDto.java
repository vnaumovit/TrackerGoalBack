package com.tracker.goals.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class PageDto {
    int pageNumber;
    int pageSize;
}
