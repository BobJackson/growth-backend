package com.wangyousong.app.growthbackend.web.response;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Data;

@Data
public class BookStatisticResponse {
    private Long total;
    private Long hidden;
    private Long visible;

    @JsonGetter
    public Long getVisible() {
        return total - hidden;
    }
}
