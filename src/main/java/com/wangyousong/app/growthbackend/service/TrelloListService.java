package com.wangyousong.app.growthbackend.service;

import com.wangyousong.app.growthbackend.domain.TrelloList;

import java.util.List;

public interface TrelloListService {
    boolean saveAll(List<TrelloList> trelloLists);

    List<TrelloList> listAll();
}
