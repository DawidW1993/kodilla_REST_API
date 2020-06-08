package com.crud.tasks.trello.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrelloConfigTest {
    @Autowired
    TrelloConfig trelloConfig;

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloToken;

    @Value("${trello.api.username}")
    private String trelloUsername;

    @Test
    public void getTrelloApiEndpoint() {
        assertEquals(trelloApiEndpoint,trelloConfig.getTrelloApiEndpoint());
    }

    @Test
    public void getTrelloAppKey() {
        assertEquals(trelloAppKey, trelloConfig.getTrelloAppKey());
    }

    @Test
    public void getTrelloToken() {
        assertEquals(trelloToken,trelloConfig.getTrelloToken());
    }

    @Test
    public void getTrelloUsername() {
        assertEquals(trelloUsername,trelloConfig.getTrelloUsername());
    }
}