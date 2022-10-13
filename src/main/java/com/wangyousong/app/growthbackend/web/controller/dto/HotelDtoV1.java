package com.wangyousong.app.growthbackend.web.controller.dto;

import com.wangyousong.app.growthbackend.domain.Hotel;

public record HotelDtoV1(
        String id,
        String name,
        String picUrl,
        String cityName,
        String zone,
        String price,
        String favoriteCount,
        double commentScore,
        String originImage
) {

    public static HotelDtoV1 toHotelDtoV1(Hotel it) {
        return new HotelDtoV1(it.getId(),
                it.getName(),
                it.getPictureUrl(),
                it.getCityName(),
                it.getZone(),
                it.getPrice(),
                it.getFavoriteCount(),
                it.getCommentScore(),
                it.getOriginImage());
    }
}
