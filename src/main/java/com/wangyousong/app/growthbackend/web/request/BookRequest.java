package com.wangyousong.app.growthbackend.web.request;

import com.wangyousong.app.growthbackend.domain.Book;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class BookRequest {
    @NotBlank
    private String title;
    private String subTitle;
    private String publishedAt;
    @NotBlank
    private String cover;
    @NotBlank
    private String description;
    @NotBlank
    private Set<String> authors;
    @NotBlank
    private String category;
    private Set<String> tags = new LinkedHashSet<>();
    @NotBlank
    private String press;
    private boolean hidden;

    public Book toEntity(String id) {
        Book book = new Book();
        book.setId(id);
        book.setTitle(StringUtils.trim(title));
        book.setSubTitle(StringUtils.trim(subTitle));
        book.setCover(cover);
        book.setPublishedAt(publishedAt);
        book.setDescription(StringUtils.trim(description));
        book.setPress(StringUtils.trim(press));
        book.setHidden(hidden);
        return book;
    }
}
