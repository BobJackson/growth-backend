package com.wangyousong.app.growthbackend.web.request;

import com.wangyousong.app.growthbackend.domain.Book;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class BookRequest {
    @NotBlank(message = "title can't be empty!")
    private String title;

    private String subTitle;

    private String publishedAt;

    @NotBlank(message = "cover can't be empty!")
    private String cover;

    @NotBlank(message = "description can't be empty!")
    private String description;

    @NotEmpty(message = "authors can't be empty!")
    private Set<String> authors;

    @NotBlank(message = "category can't be empty!")
    private String category;

    private Set<String> tags = new LinkedHashSet<>();

    @NotBlank(message = "publisher can't be empty!")
    private String publisher;

    private boolean hidden;

    public Book toEntity(String id) {
        Book book = new Book();
        book.setId(id);
        book.setTitle(StringUtils.trim(title));
        book.setSubTitle(StringUtils.trim(subTitle));
        book.setCover(cover);
        book.setPublishedAt(publishedAt);
        book.setDescription(StringUtils.trim(description));
        book.setPublisher(StringUtils.trim(publisher));
        book.setHidden(hidden);
        return book;
    }
}
