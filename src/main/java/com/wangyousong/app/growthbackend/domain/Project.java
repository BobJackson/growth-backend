package com.wangyousong.app.growthbackend.domain;

import cn.hutool.core.collection.CollUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Document
public class Project extends BaseDomain {
    private String name;
    private String image;
    private List<Stroke> strokes = new ArrayList<>();

    public boolean hasEmptyStroke() {
        return CollUtil.isEmpty(strokes);
    }
}
