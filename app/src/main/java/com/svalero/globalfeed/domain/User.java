package com.svalero.globalfeed.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private long id;
    private String username;
    private String password;
    private String name;
    private String biography;
    private LocalDate registerDate;
    private Integer phoneNumber;
    private boolean active = true;
    private List<Post> postList;
    private Set<Role> roles;
}