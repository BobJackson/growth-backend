package com.wangyousong.app.growthbackend.data;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.wangyousong.app.growthbackend.domain.Hotel;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HotelDataCollector {

    public List<Hotel> parse() throws IOException {
        List<Hotel> hotels = new ArrayList<>();
        String path = "/Users/bob/Documents/github/growth-backend/src/test/resources/favorite.json";
        String jsonFile = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(jsonFile, Map.class);
        List<Map> list = (List<Map>) map.get("FavoriteList");

        for (Map m : list) {
            Object productTag = m.get("ProductTag");
            if (!"酒店".equals(productTag.toString())) {
                continue;
            }
            Object productName = m.get("ProductName");
            Object picUrl = m.get("PicUrl");

            Hotel hotel = new Hotel();
            hotel.setName(productName.toString());
            hotel.setPictureUrl(picUrl.toString());
            hotels.add(hotel);
        }
        return hotels;
    }

    public static void main(String[] args) throws IOException {
        HotelDataCollector collector = new HotelDataCollector();
        List<Hotel> hotels = collector.parse();
        System.out.println(hotels.size());
        hotels.forEach(System.out::println);
    }
}
