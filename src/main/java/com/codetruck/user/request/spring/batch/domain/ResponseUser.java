package com.codetruck.user.request.spring.batch.domain;

import com.codetruck.user.request.spring.batch.dto.UserDTO;

import java.util.List;

public class ResponseUser {

    private List<UserDTO> content;

    public List<UserDTO> getContent() {
        return content;
    }

}
