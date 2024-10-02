package com.wangyousong.app.growthbackend.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "trello_list")
public class TrelloList extends BaseDomain {
    private String text;
    private List<IdText> tasks = new ArrayList<>();
}
