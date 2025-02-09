package com.wangyousong.app.growthbackend.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@Document(collection = "users")
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseDomain implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
}
