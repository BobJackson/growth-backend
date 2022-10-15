package com.wangyousong.app.growthbackend.web.openapi;

import com.wangyousong.app.growthbackend.common.IdsRequest;
import com.wangyousong.app.growthbackend.common.R;
import com.wangyousong.app.growthbackend.service.TagService;
import com.wangyousong.app.growthbackend.web.request.TagRequest;
import com.wangyousong.app.growthbackend.web.response.TagResponse;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "tags api")
@RestController
@RequestMapping("/openapi/tags")
public class TagApi {

    @Resource
    private TagService tagService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public R<String> create(@RequestBody TagRequest request) {
        return R.success(tagService.create(request));
    }

    @GetMapping("/{id}")
    public R<TagResponse> find(@PathVariable String id) {
        return R.success(tagService.findById(id));
    }

    @PostMapping("/actions/query-by-ids")
    public R<List<TagResponse>> list(@RequestBody IdsRequest request) {
        return R.success(tagService.list(request.ids()));
    }

}
