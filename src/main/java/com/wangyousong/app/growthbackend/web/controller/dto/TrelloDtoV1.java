package com.wangyousong.app.growthbackend.web.controller.dto;

import cn.hutool.core.collection.CollUtil;
import com.wangyousong.app.growthbackend.domain.TrelloList;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class TrelloDtoV1 {
    private List<TrelloListDtoV1> lists = new ArrayList<>();
    private Object draggedItem;

    public TrelloDtoV1(List<TrelloList> trelloLists) {
        if (CollUtil.isEmpty(trelloLists)) {
            return;
        }
        lists = trelloLists.stream()
                .map(TrelloListDtoV1::new)
                .toList();
    }

    public List<TrelloList> toTrelloList() {
        if (CollUtil.isEmpty(lists)) {
            return new ArrayList<>();
        }
        return lists.stream()
                .map(TrelloListDtoV1::toEntity)
                .toList();
    }
}
