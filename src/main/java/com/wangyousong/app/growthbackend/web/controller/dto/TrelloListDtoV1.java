package com.wangyousong.app.growthbackend.web.controller.dto;

import com.wangyousong.app.growthbackend.domain.IdText;
import com.wangyousong.app.growthbackend.domain.TrelloList;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class TrelloListDtoV1 {
    private String id;
    private String text;
    private List<TrelloTaskDtoV1> tasks = new ArrayList<>();

    public TrelloListDtoV1(TrelloList trelloList) {
        this.id = trelloList.getId();
        this.text = trelloList.getText();
        this.tasks = Optional.ofNullable(trelloList.getTasks())
                .orElse(new ArrayList<>())
                .stream()
                .map(it -> new TrelloTaskDtoV1(it.getId(), it.getText()))
                .toList();
    }

    public TrelloList toEntity() {
        TrelloList trelloList = new TrelloList();
        trelloList.setId(id);
        trelloList.setText(text);
        List<IdText> idTexts = Optional.ofNullable(tasks)
                .orElse(new ArrayList<>())
                .stream()
                .map(TrelloTaskDtoV1::toEntity)
                .toList();
        trelloList.setTasks(idTexts);
        return trelloList;
    }
}
