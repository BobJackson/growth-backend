package com.wangyousong.app.growthbackend.web.openapi;

import com.wangyousong.app.growthbackend.common.IdsRequest;
import com.wangyousong.app.growthbackend.common.R;
import com.wangyousong.app.growthbackend.service.TagService;
import com.wangyousong.app.growthbackend.web.request.TagRequest;
import com.wangyousong.app.growthbackend.web.response.TagResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController("/openapi/tags")
public class TagApi {

    @Resource
    private TagService tagService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public R<String> create(@RequestBody TagRequest request) {
        return R.succeed(tagService.create(request));
    }

    @GetMapping("/{id}")
    public R<TagResponse> find(@PathVariable String id) {
        return R.succeed(tagService.findById(id));
    }

    @PostMapping("/actions/query-by-ids")
    public R<List<TagResponse>> list(@RequestBody IdsRequest request) {
        return R.succeed(tagService.list(request.ids()));
    }

}
