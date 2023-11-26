package com.codetruck.user.request.spring.batch.entities;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserRequest {

    private String login;
    private String name;
    private String avatarUrl;

}
