package com.codetruck.user.request.spring.batch.writer;

import com.codetruck.user.request.spring.batch.dto.UserDTO;
import com.codetruck.user.request.spring.batch.entities.UserRequest;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsertUserDataDBWriterConfig {

    @Bean
    public ItemWriter<UserRequest> insertUserDataDBWriter() {
        return users -> users.forEach(System.out::println);
    }
}
