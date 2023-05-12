package com.svalero.globalfeed.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Post {

    private long id;
    private String message;
    private LocalDateTime postDate;
    private Integer likes;
    private User userPost;
}
