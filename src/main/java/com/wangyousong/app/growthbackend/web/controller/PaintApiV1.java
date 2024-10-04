package com.wangyousong.app.growthbackend.web.controller;

import com.wangyousong.app.growthbackend.service.ProjectService;
import com.wangyousong.app.growthbackend.web.controller.dto.CreateProjectDtoV1;
import com.wangyousong.app.growthbackend.web.controller.dto.ProjectDtoV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/paint")
@RequiredArgsConstructor
public class PaintApiV1 {
    private final ProjectService projectService;

    @PostMapping("/projects/new")
    public boolean createProject(@RequestBody CreateProjectDtoV1 dto) {
        return projectService.create(dto);
    }

    @GetMapping("/projects/{id}")
    public ProjectDtoV1 findById(@PathVariable String id) {
        return projectService.findById(id);
    }

    @GetMapping("/projects")
    public List<ProjectDtoV1> listAll() {
        return projectService.listAll();
    }
}
