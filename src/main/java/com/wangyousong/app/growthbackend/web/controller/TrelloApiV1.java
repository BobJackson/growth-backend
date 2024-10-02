package com.wangyousong.app.growthbackend.web.controller;

import com.wangyousong.app.growthbackend.common.R;
import com.wangyousong.app.growthbackend.service.TrelloListService;
import com.wangyousong.app.growthbackend.web.controller.dto.TrelloDtoV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
public class TrelloApiV1 {

    private final TrelloListService service;

    @PostMapping("/save")
    public R<Boolean> save(@RequestBody TrelloDtoV1 data) {
        return R.success(service.saveAll(data.toTrelloList()));
    }

    @GetMapping("/load")
    public R<TrelloDtoV1> load() {
        TrelloDtoV1 data = new TrelloDtoV1(service.listAll());
        return R.success(data);
    }
}
