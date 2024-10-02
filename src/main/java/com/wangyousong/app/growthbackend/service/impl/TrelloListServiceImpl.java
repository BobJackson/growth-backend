package com.wangyousong.app.growthbackend.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.wangyousong.app.growthbackend.domain.TrelloList;
import com.wangyousong.app.growthbackend.repository.mongo.TrelloListRepository;
import com.wangyousong.app.growthbackend.service.TrelloListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrelloListServiceImpl implements TrelloListService {

    private final TrelloListRepository repository;

    @Override
    public boolean saveAll(List<TrelloList> trelloLists) {
        if (CollUtil.isEmpty(trelloLists)) {
            log.warn("trello list is empty!");
            return false;
        }
        repository.saveAll(trelloLists);
        return true;
    }

    @Override
    public List<TrelloList> listAll() {
        return repository.findAll();
    }
}
