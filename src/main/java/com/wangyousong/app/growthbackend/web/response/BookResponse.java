package com.wangyousong.app.growthbackend.web.response;

import com.wangyousong.app.growthbackend.domain.Author;
import com.wangyousong.app.growthbackend.domain.Book;
import com.wangyousong.app.growthbackend.domain.Tag;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
public class BookResponse {
    private String id;
    private String title;
    private String subTitle;
    private String publishedAt;
    private String cover;
    private String description;
    private String category;
    private List<String> authors;
    private List<String> tags;


    public BookResponse(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.subTitle = book.getSubTitle();
        this.publishedAt = book.getPublishedAt();
        this.cover = book.getCover();
        this.description = book.getDescription();
        this.category = book.getCategory().getName();
        this.authors = book.getAuthors().stream().map(Author::getName).toList();
        Collection<Tag> tagList = ObjectUtils.defaultIfNull(book.getTags(), Collections.emptyList());
        this.tags = tagList.stream().map(Tag::getName).toList();
    }
}
