package com.wangyousong.app.growthbackend.web.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class BatchSavePostsDtoV1 {
    private List<PostDtoV1> posts;
}
