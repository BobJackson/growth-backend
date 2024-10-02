package com.wangyousong.app.growthbackend.web.controller.dto;

import com.wangyousong.app.growthbackend.domain.IdText;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrelloTaskDtoV1 {
    private String id;
    private String text;

    public IdText toEntity() {
        return new IdText(id, text);
    }
}
