package com.wangyousong.app.growthbackend.service;

import com.wangyousong.app.growthbackend.web.controller.dto.CreateProjectDtoV1;
import com.wangyousong.app.growthbackend.web.controller.dto.ProjectDtoV1;

import java.util.List;

public interface ProjectService {
    boolean create(CreateProjectDtoV1 dto);

    ProjectDtoV1 findById(String id);

    List<ProjectDtoV1> listAll();
}
