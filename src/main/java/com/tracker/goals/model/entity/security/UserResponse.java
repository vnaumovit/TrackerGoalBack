package com.tracker.goals.model.entity.security;

import com.tracker.goals.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {
    private final UserDto user;
    private final String accessToken;
}
