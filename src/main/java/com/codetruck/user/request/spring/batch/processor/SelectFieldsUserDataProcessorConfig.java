package com.codetruck.user.request.spring.batch.processor;

import com.codetruck.user.request.spring.batch.dto.UserDTO;
import com.codetruck.user.request.spring.batch.entities.UserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SelectFieldsUserDataProcessorConfig {

    private static Logger logger = LoggerFactory.getLogger(SelectFieldsUserDataProcessorConfig.class);

    @Bean
    public ItemProcessor<UserDTO, UserRequest> selectFieldsUserDataProcessor() {
        return new ItemProcessor<UserDTO, UserRequest>() {
            @Override
            public UserRequest process(UserDTO item) throws Exception {
                return null;
            }
        };
    }
}
