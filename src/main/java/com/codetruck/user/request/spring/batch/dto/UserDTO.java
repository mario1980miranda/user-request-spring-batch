package com.codetruck.user.request.spring.batch.dto;

import lombok.*;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    private Long id;
    private String login;
    private String name;
    private String location;
    private String avatarUrl;
    private Integer followers;
    private Integer following;
    private Instant created;
    private String reposUrl;
    private String company;
}
