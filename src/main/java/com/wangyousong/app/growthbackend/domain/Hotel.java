package com.wangyousong.app.growthbackend.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "hotels")
public class Hotel extends BaseDomain {
    private String name;
    private String pictureUrl;
    private String cityName;
    private String zone;
    private String price;
    private String lat;
    private String lng;
    private String originImage;
    private String favoriteCount;
    private double commentScore;
}


