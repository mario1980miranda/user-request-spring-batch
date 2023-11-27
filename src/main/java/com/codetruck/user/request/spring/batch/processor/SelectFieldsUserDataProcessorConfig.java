package com.codetruck.user.request.spring.batch.processor;

import com.codetruck.user.request.spring.batch.dto.UserDTO;
import com.codetruck.user.request.spring.batch.entities.UserRequest;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SelectFieldsUserDataProcessorConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(SelectFieldsUserDataProcessorConfig.class);

    private int counter = 1;

    @Bean
    public ItemProcessor<UserDTO, UserRequest> selectFieldsUserDataProcessor() {
        return item -> {

            UserRequest user = new UserRequest();
            user.setName(item.getName());
            user.setLogin(item.getLogin());
            user.setAvatarUrl(item.getAvatarUrl());

            LOGGER.info("[PROCESSOR STEP] select user field " + counter + " - " + item);
            counter++;

            return user;
        };
    }
}
