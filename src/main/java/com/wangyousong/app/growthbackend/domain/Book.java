package com.wangyousong.app.growthbackend.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "books")
public class Book extends BaseDomain {
    private String title;
    private String subTitle;
    private String publishedAt;
    private String cover;
    private String description;
    private Collection<Author> authors;
    private Category category;
    private Collection<Tag> tags;
    private String publisher;
    private Boolean hidden;

    public String extractFileExtension() {
        return cover.substring(cover.lastIndexOf("."));
    }
}
