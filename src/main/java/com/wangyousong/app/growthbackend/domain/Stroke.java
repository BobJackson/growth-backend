package com.wangyousong.app.growthbackend.domain;

import lombok.Data;

import java.util.List;

@Data
public class Stroke {
    private String color;
    private List<Point> points;
}
