package com.wangyousong.app.growthbackend.web.controller;

import com.wangyousong.app.growthbackend.common.R;
import com.wangyousong.app.growthbackend.service.ProjectService;
import com.wangyousong.app.growthbackend.web.controller.dto.CreateProjectDtoV1;
import com.wangyousong.app.growthbackend.web.controller.dto.ProjectDtoV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/paint")
@RequiredArgsConstructor
@CrossOrigin
public class PaintApiV1 {
    private final ProjectService projectService;

    @PostMapping("/projects/new")
    public R<Boolean> createProject(@RequestBody CreateProjectDtoV1 dto) {
        return R.success(projectService.create(dto));
    }

    @GetMapping("/projects/{id}")
    public R<ProjectDtoV1> findById(@PathVariable String id) {
        return R.success(projectService.findById(id));
    }

    @GetMapping("/projects")
    public R<List<ProjectDtoV1>> listAll() {
        return R.success(projectService.listAll());
    }
}
