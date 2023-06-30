package com.wangyousong.app.growthbackend.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@Data
public class SimpleBook {
    @Id
    private String id;
    @Indexed
    private String coverUrl;

    public SimpleBook(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}
