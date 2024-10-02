package com.wangyousong.app.growthbackend.web.controller;

import com.wangyousong.app.growthbackend.common.IdService;
import com.wangyousong.app.growthbackend.common.R;
import com.wangyousong.app.growthbackend.web.controller.dto.MovieDtoV1;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/v1/movies")
public class MovieApiV1 {

    @Resource
    private IdService idService;

    @Cacheable(cacheNames = "movieList")
    @CrossOrigin
    @GetMapping
    public R<List<MovieDtoV1>> list() {
        List<MovieDtoV1> movies = List.of(
                new MovieDtoV1(idService.generateId(), "https://growth-public.oss-cn-shanghai.aliyuncs.com/movies/imdb/compressed/avatar.jpeg"),
                new MovieDtoV1(idService.generateId(), "https://growth-public.oss-cn-shanghai.aliyuncs.com/movies/imdb/compressed/black-pather.jpg"),
                new MovieDtoV1(idService.generateId(), "https://growth-public.oss-cn-shanghai.aliyuncs.com/movies/imdb/compressed/Bombshell-2019.jpeg"),
                new MovieDtoV1(idService.generateId(), "https://growth-public.oss-cn-shanghai.aliyuncs.com/movies/imdb/compressed/CODA-2021.jpg"),
                new MovieDtoV1(idService.generateId(), "https://growth-public.oss-cn-shanghai.aliyuncs.com/movies/imdb/compressed/django-unchained.jpeg"),
                new MovieDtoV1(idService.generateId(), "https://growth-public.oss-cn-shanghai.aliyuncs.com/movies/imdb/compressed/forrest-gump.jpeg"),
                new MovieDtoV1(idService.generateId(), "https://growth-public.oss-cn-shanghai.aliyuncs.com/movies/imdb/compressed/inception.jpeg"),
                new MovieDtoV1(idService.generateId(), "https://growth-public.oss-cn-shanghai.aliyuncs.com/movies/imdb/compressed/Joker-2019.jpeg"),
                new MovieDtoV1(idService.generateId(), "https://growth-public.oss-cn-shanghai.aliyuncs.com/movies/imdb/compressed/mad-max.jpeg"),
                new MovieDtoV1(idService.generateId(), "https://growth-public.oss-cn-shanghai.aliyuncs.com/movies/imdb/compressed/moulin-rouge.jpeg"),
                new MovieDtoV1(idService.generateId(), "https://growth-public.oss-cn-shanghai.aliyuncs.com/movies/imdb/compressed/Parasite-2019.jpeg"),
                new MovieDtoV1(idService.generateId(), "https://growth-public.oss-cn-shanghai.aliyuncs.com/movies/imdb/compressed/ring-1.jpeg"),
                new MovieDtoV1(idService.generateId(), "https://growth-public.oss-cn-shanghai.aliyuncs.com/movies/imdb/compressed/ring-2.jpeg"),
                new MovieDtoV1(idService.generateId(), "https://growth-public.oss-cn-shanghai.aliyuncs.com/movies/imdb/compressed/ring-3.jpeg"),
                new MovieDtoV1(idService.generateId(), "https://growth-public.oss-cn-shanghai.aliyuncs.com/movies/imdb/compressed/shawshank.jpeg"),
                new MovieDtoV1(idService.generateId(), "https://growth-public.oss-cn-shanghai.aliyuncs.com/movies/imdb/compressed/socail-network.jpeg"),
                new MovieDtoV1(idService.generateId(), "https://growth-public.oss-cn-shanghai.aliyuncs.com/movies/imdb/compressed/the-wolf-of-wall.jpeg"),
                new MovieDtoV1(idService.generateId(), "https://growth-public.oss-cn-shanghai.aliyuncs.com/movies/imdb/compressed/three-board.jpg"),
                new MovieDtoV1(idService.generateId(), "https://growth-public.oss-cn-shanghai.aliyuncs.com/movies/imdb/compressed/whiplash.jpeg")
        );
        return R.success(movies);
    }
}
