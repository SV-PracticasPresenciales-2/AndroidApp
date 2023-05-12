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
    //todo revisar String
    private String postDate;
    private Integer likes;
    //todo revisar String
    private User userPost;
    private int userId;

    public Post(long id, String message, String postDate, Integer likes, User userPost) {
        this.id = id;
        this.message = message;
        this.postDate = postDate;
        this.likes = likes;
        this.userPost = userPost;
    }

    public Post(String message, String postDate, Integer likes, int userId) {
        this.message = message;
        this.postDate = postDate;
        this.likes = likes;
        this.userId = userId;
    }
}
