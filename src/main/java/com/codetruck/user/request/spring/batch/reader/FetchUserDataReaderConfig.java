package com.codetruck.user.request.spring.batch.reader;

import com.codetruck.user.request.spring.batch.domain.ResponseUser;
import com.codetruck.user.request.spring.batch.dto.UserDTO;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Configuration
public class FetchUserDataReaderConfig implements ItemReader<UserDTO> {

    public static Logger LOGGER = LoggerFactory.getLogger(FetchUserDataReaderConfig.class);

    private static final String BASE_URL = "http://localhost:8888";

    private final RestTemplate restTemplate = new RestTemplate();

    @Getter
    private int page = 0;

    private List<UserDTO> users = new ArrayList<>();
    private int userIndex = 0;

    @Value("${chunkSize}")
    private int chunkSize;
    @Value("${pageSize}")
    private int pageSize;

    @Override
    public UserDTO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        UserDTO user;
        if (userIndex < users.size())
            user = users.get(userIndex);
        else
            user = null;

        userIndex++;
        return user;
    }

    private List<UserDTO> fetchUserDataFromAPI() {
        String url = BASE_URL + "/clients?page=%d&size=%d";

        LOGGER.info("[READER STEP] Fetching data...");
        LOGGER.info("[READER STEP] Request uri " + String.format(url,getPage(),pageSize));

        ResponseEntity<ResponseUser> response = restTemplate.exchange(
                String.format(
                        url,
                        getPage(),
                        pageSize
                ),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseUser>() {
                });

        return Objects.requireNonNull(response.getBody()).getContent();
    }

    public void incrementPage() {
        this.page++;
    }

    @BeforeChunk
    public void beforeChunk(ChunkContext context) {
        for (int i = 0; i < chunkSize; i += pageSize) {
            users.addAll(fetchUserDataFromAPI());
        }
    }

    @AfterChunk
    public void afterChunk(ChunkContext context) {
        LOGGER.info("[READER STEP] Final chunk...");
        incrementPage();
        userIndex = 0;
        users = new ArrayList<>();
    }

}
