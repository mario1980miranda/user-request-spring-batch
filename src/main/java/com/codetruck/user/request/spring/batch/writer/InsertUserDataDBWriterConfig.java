package com.codetruck.user.request.spring.batch.writer;

import com.codetruck.user.request.spring.batch.entities.UserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class InsertUserDataDBWriterConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(InsertUserDataDBWriterConfig.class);

    @Bean
    public ItemWriter<UserRequest> insertUserDataDBWriter(@Qualifier("appDB")DataSource dataSource) {

        LOGGER.info("[WRITER STEP] Inserting user data...");

        return new JdbcBatchItemWriterBuilder()
                .dataSource(dataSource)
                .sql("INSERT INTO tb_user (login, name, avatar_url) VALUES (:login, :name, :avatarUrl)")
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .build();
    }
}
