package com.tracker.goals.rest.controller;


import com.tracker.goals.repository.goal.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/images")
public class ImageController {

    private final ImageRepository imageRepository;

}
