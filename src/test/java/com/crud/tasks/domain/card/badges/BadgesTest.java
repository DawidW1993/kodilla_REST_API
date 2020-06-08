package com.crud.tasks.domain.card.badges;

import org.junit.Test;

import static org.junit.Assert.*;

public class BadgesTest {
    Trello trello = new Trello(1,1);
    Badges badges = new Badges(new AttachmentsByType(trello),1);

    @Test
    public void getBadgesAttachmentsByType() {
        //When
        AttachmentsByType byType = badges.getBadgesAttachmentsByType();
        //Then
        assertEquals(1,byType.getAttachmentsByTypeTrello().getCard());
        assertEquals(1,byType.getAttachmentsByTypeTrello().getBoard());

    }

    @Test
    public void getVotes() {
        //Then
        assertEquals(1,badges.getVotes());
    }
}