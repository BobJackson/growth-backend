package com.wangyousong.app.growthbackend.web.controller.dto;

import com.wangyousong.app.growthbackend.domain.Project;
import com.wangyousong.app.growthbackend.domain.Stroke;
import lombok.Data;

import java.util.List;

@Data
public class CreateProjectDtoV1 {
    private String name;
    private String image;
    private List<Stroke> strokes;

    public Project toEntity() {
        Project project = new Project();
        project.setName(name);
        project.setImage(image);
        project.setStrokes(strokes);
        return project;
    }
}
