package com.wangyousong.app.growthbackend.service.impl;

import com.wangyousong.app.growthbackend.domain.Project;
import com.wangyousong.app.growthbackend.repository.mongo.ProjectRepository;
import com.wangyousong.app.growthbackend.service.ProjectService;
import com.wangyousong.app.growthbackend.web.controller.dto.CreateProjectDtoV1;
import com.wangyousong.app.growthbackend.web.controller.dto.ProjectDtoV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository repository;

    @Override
    public boolean create(CreateProjectDtoV1 dto) {
        Project project = dto.toEntity();
        if (project.hasEmptyStroke()) {
            log.warn("project {} has empty stroke", project.getName());
            return false;
        }
        repository.save(project);
        return true;
    }

    @Override
    public ProjectDtoV1 findById(String id) {
        Project project = repository.findById(id).orElseThrow();
        return new ProjectDtoV1(project);
    }

    @Override
    public List<ProjectDtoV1> listAll() {
        return repository.findAll()
                .stream()
                .map(ProjectDtoV1::new)
                .toList();
    }
}
