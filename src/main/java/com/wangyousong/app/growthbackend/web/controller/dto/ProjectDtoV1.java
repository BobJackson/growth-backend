package com.wangyousong.app.growthbackend.web.controller.dto;

import com.wangyousong.app.growthbackend.domain.Project;
import com.wangyousong.app.growthbackend.domain.Stroke;
import lombok.Data;

import java.util.List;

@Data
public class ProjectDtoV1 {
    private String id;
    private String name;
    private String image;
    private List<Stroke> strokes;

    public ProjectDtoV1(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.image = project.getImage();
        this.strokes = project.getStrokes();
    }
}
