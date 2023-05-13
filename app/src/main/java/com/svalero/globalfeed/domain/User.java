package com.svalero.globalfeed.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private long id;
    private String username;
    private String password;
    private String name;
    private String biography;
    //todo revisar string
    private String registerDate;
    private Integer phoneNumber;
    private boolean active = true;
    private List<Post> postList;
    private Set<Role> roles;
}