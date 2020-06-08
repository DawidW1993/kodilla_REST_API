package com.crud.tasks.domain.card.badges;

import org.junit.Test;

import static org.junit.Assert.*;

public class TrelloTest {
    Trello trello = new Trello(1,1);

    @Test
    public void getBoard() {
        assertEquals(1,trello.getBoard());
    }

    @Test
    public void getCard() {
        assertEquals(1,trello.getCard());
    }
}