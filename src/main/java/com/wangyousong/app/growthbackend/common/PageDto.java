package com.wangyousong.app.growthbackend.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageDto<T> {
    @JsonProperty("page")
    private int pageNum;
    private int size;
    private List<T> data;

    public PageDto<T> toDto(Page<T> page) {
        return new PageDto<>(page.getNumber(), page.getSize(), page.getContent());
    }
}
